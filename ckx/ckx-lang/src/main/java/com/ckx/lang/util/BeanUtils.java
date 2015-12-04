package com.ckx.lang.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BeanUtils {

    @SuppressWarnings("unchecked")
    public static <T> T typeCast(Class<T> fieldType, String param) {
        T value = null;
        try {
            if (param == null) {
                return null;
            }
            if (fieldType == String.class) {
                value = (T) param;
            } else if (fieldType == Integer.class) {
                value = (T) Integer.valueOf(param);
            } else if (fieldType == Double.class) {
                value = (T) Double.valueOf(param);
            } else if (fieldType == Long.class) {
                value = (T) Long.valueOf(param);
            } else if (fieldType == Character.class) {
                value = (T) Character.valueOf(param.toCharArray()[0]);
            } else if (fieldType == Boolean.class) {
                value = (T) Boolean.valueOf(param);
            } else if (fieldType == Float.class) {
                value = (T) Float.valueOf(param);
            } else if (fieldType == Short.class) {
                value = (T) Short.valueOf(param);
            } else if (fieldType == Date.class) {
                value = (T) parse(param, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            }
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    public static <T> T formToBean(HttpServletRequest request, Class<T> cls) throws Exception {
        T bean = cls.newInstance();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String methodName = "set";
            if (Character.isUpperCase(fieldName.charAt(1))) {
                methodName += fieldName;
            } else {
                methodName += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            }
            Method method = cls.getDeclaredMethod(methodName, field.getType());
            String req_param = request.getParameter(fieldName);
            try {
                method.invoke(bean, typeCast(field.getType(), req_param));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    public static <T> Map<String, String> beanToMap(T bean) {
        Class<? extends Object> cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String, String> data = new HashMap<String, String>();
        for (Field field : fields) {
            try {
                String fieldName = field.getName();
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
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

    public static Date parse(String str, String pattern, Locale locale) {
        if (str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date date, String pattern, Locale locale) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern, locale).format(date);
    }

}
