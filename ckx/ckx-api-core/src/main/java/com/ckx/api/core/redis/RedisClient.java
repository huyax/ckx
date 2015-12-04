package com.ckx.api.core.redis;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.ckx.lang.util.BeanUtils;
import com.ckx.lang.util.SerializeUtil;

@Repository
public class RedisClient {

    protected
    @Autowired
    StringRedisTemplate template;
    protected
    @Autowired
    RedisTemplate<Serializable, Serializable> redisTemplate;

    public boolean exists(final String key) {
        return template.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                return connection.exists(bKey);
            }
        });
    }

    /**
     * 写入对象到Hash中
     *
     * @param key
     * @param bean
     */
    public <T> void hSet(final String key, final T bean) {
        BoundHashOperations<String, String, String> ops = template.boundHashOps(key);
        ops.putAll(beanToMap(bean));
    }

    /**
     * 取得符合规则的所有Key值
     *
     * @param pattern 正则表达式
     * @return
     */
    public Set<String> keys(final String pattern) {
        return template.execute(new RedisCallback<Set<String>>() {
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = redisTemplate.getStringSerializer().serialize(pattern);
                Set<byte[]> keys = connection.keys(bKey);
                Set<String> sKeys = new HashSet<String>();
                for (byte[] bs : keys) {
                    sKeys.add(redisTemplate.getStringSerializer().deserialize(bs));
                }
                return sKeys;
            }
        });
    }

    /**
     * 设置生存时间
     *
     * @param key    key值
     * @param expire 生存时间
     */
    public boolean expire(final String key, final int expire) {
        return template.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                return connection.expire(bKey, expire);
            }
        });
    }

    /**
     * 设置生存时间
     *
     * @param key      key值
     * @param expireat 生存时间
     */
    public boolean expireAt(final String key, final long expireat) {
        return template.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                return connection.expireAt(bKey, expireat);
            }
        });
    }

    /**
     * 根据Key查询
     *
     * @param key    key值
     * @param fields 序号获取的字段
     * @return
     */
    public List<String> hMGet(final String key, final String... fields) {
        return template.execute(new RedisCallback<List<String>>() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                byte[][] params = new byte[fields.length][];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = template.getStringSerializer().serialize(fields[i]);
                }
                List<byte[]> value = connection.hMGet(bKey, params);
                List<String> result = new ArrayList<String>();
                for (byte[] bs : value) {
                    String rValue = template.getStringSerializer().deserialize(bs);
                    if (!StringUtils.isBlank(rValue)) {
                        result.add(rValue);
                    }
                }
                return result;
            }
        });
    }

    /**
     * 像Hash中存入
     *
     * @param key    key值
     * @param params 存入的字段及值
     */
    public void hMSet(final String key, final Map<String, String> params) {
        template.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                Map<byte[], byte[]> saveParams = new HashMap<byte[], byte[]>();
                for (String field : params.keySet()) {
                    saveParams.put(template.getStringSerializer().serialize(field), template.getStringSerializer().serialize(params.get(field)));
                }
                connection.hMSet(bKey, saveParams);
                return null;
            }

        });
    }

    /**
     * 根据Key查询
     *
     * @param key    key值
     * @param bean   需要生成的对象
     * @param fields 需要查询的字段
     * @return
     */
    public <T> T hGet(final String key, final Class<T> bean, String... columns) {
        List<String> allColumn = null;
        if (columns == null || columns.length <= 0) {
            Field[] fls = bean.getDeclaredFields();
            allColumn = new ArrayList<String>();
            for (Field field : fls) {
                try {
                    String fieldName = field.getName(), methodName = null, char_2 = fieldName.substring(1, 2);
                    if (char_2.endsWith(char_2.toUpperCase())) {
                        methodName = "set" + fieldName;
                    } else {
                        methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    }
                    Method method = bean.getMethod(methodName, field.getType());
                    if (method != null) {
                        allColumn.add(fieldName);
                    }
                } catch (Exception e) {
                }
            }
        }
        final String[] fields = columns == null || columns.length <= 0 ? allColumn.toArray(new String[allColumn.size()]) : columns;
        return template.execute(new RedisCallback<T>() {
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                byte[][] params = new byte[fields.length][];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = template.getStringSerializer().serialize(fields[i]);
                }
                List<byte[]> value = connection.hMGet(bKey, params);
                return byteListToBean(template, bean, fields, value);
            }
        });
    }

    /**
     * 获取所有key下的所有内容
     *
     * @param key key值
     * @return
     */
    public <T> Map<String, T> hGetAll(final String key, final Class<T> type) {
        return template.execute(new RedisCallback<Map<String, T>>() {
            public Map<String, T> doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                Map<byte[], byte[]> all = connection.hGetAll(bKey);
                Map<String, T> result = new HashMap<String, T>();
                for (byte[] bs : all.keySet()) {
                    String rKey = template.getStringSerializer().deserialize(bs);
                    String rValue = template.getStringSerializer().deserialize(all.get(bs));
                    result.put(rKey, BeanUtils.typeCast(type, rValue));
                }
                return result;
            }
        });
    }


    /**
     * 根据key删除Hash值
     *
     * @param key
     * @return
     */
    public Long del(final String key) {
        return template.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(template.getStringSerializer().serialize(key));
            }
        });
    }

    /**
     * 删除指定Key下的field
     *
     * @param key    key值
     * @param fields field值
     * @return
     */
    public Long hDel(final String key, final String... fields) {
        return template.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                byte[][] params = new byte[fields.length][];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = template.getStringSerializer().serialize(fields[i]);
                }
                return connection.hDel(bKey, params);
            }
        });
    }

    /**
     * 返回List转换成为对象
     *
     * @param redisTemplate
     * @param bean
     * @param value
     * @return
     */
    private <T> T byteListToBean(RedisTemplate<?, ?> redisTemplate, Class<T> bean, String[] fields, List<byte[]> value) {
        T instance = null;
        boolean isNull = true;
        try {
            instance = bean.newInstance();
            for (int i = 0; i < fields.length; i++) {
                try {
                    String fieldName = fields[i], methodName = null, char_2 = fieldName.substring(1, 2);
                    if (char_2.endsWith(char_2.toUpperCase())) {
                        methodName = "set" + fieldName;
                    } else {
                        methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    }
                    Field field = bean.getDeclaredField(fieldName);
                    Method method = bean.getMethod(methodName, field.getType());
                    String object = redisTemplate.getStringSerializer().deserialize(value.get(i));
                    method.invoke(instance, BeanUtils.typeCast(field.getType(), object));
                    isNull = isNull ? object == null : false;
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {

        }
        return isNull ? null : instance;
    }

    /**
     * 对象转换成成为Map类型
     *
     * @param bean
     * @return
     */
    private <T> Map<String, String> beanToMap(T bean) {
        Class<? extends Object> cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String, String> data = new HashMap<String, String>();
        for (Field field : fields) {
            try {
                String fieldName = field.getName(), methodName = null, char_2 = fieldName.substring(1, 2);
                if (char_2.endsWith(char_2.toUpperCase())) {
                    methodName = "get" + fieldName;
                } else {
                    methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }
                Method method = cls.getMethod(methodName);
                Object value = method.invoke(bean);
                if (value != null) {
                    data.put(fieldName, value.toString());
                }
            } catch (Exception e) {

            }
        }
        return data;
    }

    /**
     * 检查imei是否第一次，如果为第一次保存，返回数字 1，否则大于1
     *
     * @param imeiNo
     * @param imeiKey
     * @return
     */
    public Long hIncrBy(final String key, final String column) {
        return template.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bColumn = redisTemplate.getStringSerializer().serialize(column);
                byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                return connection.hIncrBy(bKey, bColumn, 1);
            }
        });
    }


    /**
     * 写入对象到Hash中
     *
     * @param beans  出入对象集合
     * @param expire 失效时间  小于0为不设置
     */
    public void hSet(final Map<String, Object> beans, final int expire) {
        template.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : beans.keySet()) {
                    byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                    connection.hMSet(bKey, beanToByteMap(beans.get(key)));
                    if (expire >= 0) {
                        connection.expire(bKey, expire);
                    }
                }
                return null;
            }
        });
    }

    /**
     * 对象转换成成为Map类型
     *
     * @param bean
     * @return
     */
    private <T> Map<byte[], byte[]> beanToByteMap(T bean) {
        Class<? extends Object> cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<byte[], byte[]> data = new HashMap<byte[], byte[]>();
        for (Field field : fields) {
            try {
                String fieldName = field.getName(), methodName = null, char_2 = fieldName.substring(1, 2);
                if (char_2.endsWith(char_2.toUpperCase())) {
                    methodName = "get" + fieldName;
                } else {
                    methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }
                Method method = cls.getMethod(methodName);
                Object value = method.invoke(bean);
                if (value != null) {
                    data.put(redisTemplate.getStringSerializer().serialize(fieldName), redisTemplate.getStringSerializer().serialize(value.toString()));
                }
            } catch (Exception e) {

            }
        }
        return data;
    }

    /**
     * @param hIncrs
     * @return
     */
    public Map<String, Long> hIncrBy(final Map<String, String> hIncrs) {
        return template.execute(new RedisCallback<Map<String, Long>>() {
            public Map<String, Long> doInRedis(RedisConnection connection) throws DataAccessException {
                Map<String, Long> nums = new HashMap<String, Long>();
                for (String key : hIncrs.keySet()) {
                    byte[] bColumn = redisTemplate.getStringSerializer().serialize(hIncrs.get(key));
                    byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                    nums.put(key + "_" + hIncrs.get(key), connection.hIncrBy(bKey, bColumn, 1));
                }
                return nums;
            }
        });
    }

    /**
     * @param hIncrs
     * @return
     */
    public Map<String, Long> hIncrByNew(final Map<String, String> hIncrs, final long count) {
        return template.execute(new RedisCallback<Map<String, Long>>() {
            public Map<String, Long> doInRedis(RedisConnection connection) throws DataAccessException {
                Map<String, Long> nums = new HashMap<String, Long>();
                for (String key : hIncrs.keySet()) {
                    byte[] bColumn = redisTemplate.getStringSerializer().serialize(hIncrs.get(key));
                    byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                    nums.put(key + "_" + hIncrs.get(key), connection.hIncrBy(bKey, bColumn, count));
                }
                return nums;
            }
        });
    }

    /**
     * 根据Key查询
     *
     * @param key    key值
     * @param bean   需要生成的对象
     * @param fields 需要查询的字段
     * @return
     */
    public <T> List<T> keysToBean(final String pattern, final Class<T> bean) {
        List<String> allColumn = null;
        Field[] fls = bean.getDeclaredFields();
        allColumn = new ArrayList<String>();
        for (Field field : fls) {
            try {
                String fieldName = field.getName(), methodName = null, char_2 = fieldName.substring(1, 2);
                if (char_2.endsWith(char_2.toUpperCase())) {
                    methodName = "set" + fieldName;
                } else {
                    methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }
                Method method = bean.getMethod(methodName, field.getType());
                if (method != null) {
                    allColumn.add(fieldName);
                }
            } catch (Exception e) {
            }
        }
        final String[] fields = allColumn.toArray(new String[allColumn.size()]);

        return template.execute(new RedisCallback<List<T>>() {
            public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
                byte[][] params = new byte[fields.length][];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = template.getStringSerializer().serialize(fields[i]);
                }

                byte[] bKey = redisTemplate.getStringSerializer().serialize(pattern);
                Set<byte[]> keys = connection.keys(bKey);
                List<T> beans = new ArrayList<T>();
                for (byte[] bs : keys) {
                    List<byte[]> value = connection.hMGet(bs, params);
                    beans.add(byteListToBean(template, bean, fields, value));
                }
                return beans;
            }
        });
    }

    /**
     * 批量设置生存时间
     *
     * @param expires
     */
    public void expire(final Map<String, Integer> expires) {
        template.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                for (String key : expires.keySet()) {
                    byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                    connection.expire(bKey, expires.get(key));
                }
                return null;
            }
        });
    }

    /**
     * 像Hash中存入
     *
     * @param key    key值
     * @param field  存入的字段
     * @param value  存入的值
     * @param expire 失效时间
     */
    public void hSet(final String key, final String field, final Object value, final Integer expire) {
        template.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                Map<byte[], byte[]> saveParams = new HashMap<byte[], byte[]>();
                saveParams.put(template.getStringSerializer().serialize(field), SerializeUtil.serialize(value));
                connection.hMSet(bKey, saveParams);
                if (expire != null && expire > 0) {
                    connection.expire(bKey, expire);
                }
                return null;
            }

        });
    }

    /**
     * 取Hash数据
     *
     * @param key
     * @param field
     * @return
     */
    public Object hGet(final String key, final String field) {
        return template.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                byte[] bField = template.getStringSerializer().serialize(field);
                return SerializeUtil.unserialize(connection.hGet(bKey, bField));
            }
        });
    }

    /**
     * 获取所有key下的所有内容的值
     *
     * @param key key值
     * @return
     */
    public List<String> hGetAllValue(final String key) {
        return template.execute(new RedisCallback<List<String>>() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                List<byte[]> all = connection.hVals(bKey);
                List<String> result = new ArrayList<String>();
                for (byte[] bs : all) {
                    String rValue = template.getStringSerializer().deserialize(bs);
                    result.add(rValue);
                }
                return result;
            }
        });
    }


    /**
     * 入栈
     *
     * @param key
     * @param value
     * @return
     */
    public Long lPush(final String key, final String value) {
        return template.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = redisTemplate.getStringSerializer().serialize(key);
                byte[] bValue = redisTemplate.getStringSerializer().serialize(value);
                return connection.lPush(bKey, bValue);
            }
        });
    }

    /**
     * 出栈（阻塞）
     *
     * @param key
     * @param time（等待阻塞时间，0为永久）
     * @return
     */
    public Object bRPop(final String key, final Integer time) {
        return template.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bKey = template.getStringSerializer().serialize(key);
                List<byte[]> list = connection.bRPop(time, bKey);
                if (list != null && list.size() > 1) {
                    return template.getValueSerializer().deserialize(list.get(1));
                }
                return null;
            }
        });
    }

}
