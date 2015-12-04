package com.ckx.lang.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 得到 properties
 *
 * @author chenguiyong
 * @version 1.0, 2014-6-19 下午1:32:38
 * @Description
 */
public class ConfigProperties {

    private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);

    private String[] fileNames = {"/constants.properties"};

    private volatile static ConfigProperties propertiesHelps = null;

    public static Map<Object, Object> map = null;

    private ConfigProperties() {
        InputStream in = null;

        try {
            map = new HashMap<Object, Object>();
            for (String name : fileNames) {
                in = ConfigProperties.class.getResourceAsStream(name);
                Properties props = new Properties();
                props.load(in);
                Set<Object> keys = props.keySet();
                for (Object o : keys) {
                    Object t = props.get(o);
                    map.put(o, t);
                }
                in.close();
            }
        } catch (IOException e) {
            logger.error(Arrays.toString(fileNames) + "读取失败！", e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                logger.error(Arrays.toString(fileNames) + "关闭失败！", e);
            }
        }
    }

    /**
     * 根据key 获取值
     *
     * @return String
     * @param    String key
     */
    public static String getProperty(String key) {

        if (null == propertiesHelps) {
            synchronized (ConfigProperties.class) {
                if (null == propertiesHelps) {
                    propertiesHelps = new ConfigProperties();
                }
            }
        }
        return (String) map.get(key);
    }

    //	public static String getFileRoot() {
//		
//		return getProperty("fileroot");
//	}
    public static void main(String[] args) {

    }
}
