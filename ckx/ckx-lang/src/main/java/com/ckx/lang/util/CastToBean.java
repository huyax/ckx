package com.ckx.lang.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CastToBean
{

	/**
	 * Map 转换成为Bean
	 * @param map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToBean(Map<?, ?> map, Class<T> bean) throws Exception
	{
		T instance = null;
		instance = bean.newInstance();
		for (Object key : map.keySet())
		{
			Object value = map.get(key);
			String fieldName = (String) key;
			String[] fieldSplit = fieldName.split("_");
			for (int i = 0; i < fieldSplit.length; i++)
			{
				if (i == 0)
				{
					fieldName = fieldSplit[i];
				}
				else
				{
					fieldName += fieldSplit[i].substring(0, 1).toUpperCase() + fieldSplit[i].substring(1, fieldSplit[i].length());
				}
			}
			String methodName = "set";
			if (Character.isUpperCase(fieldName.charAt(1)))
			{
				methodName += fieldName;
			}
			else
			{
				methodName += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			}

			Field field = bean.getDeclaredField(fieldName);
			Method method = bean.getDeclaredMethod(methodName, field.getType());
			method.invoke(instance, typeCast(field.getType(), value.toString()));

		}
		return instance;
	}

	/**
	 * 页面提交表单转换成为Bean
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T formToBean(HttpServletRequest request, Class<T> cls) throws Exception
	{
		T bean = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = field.getName();
			String methodName = "set";
			if (Character.isUpperCase(fieldName.charAt(1)))
			{
				methodName += fieldName;
			}
			else
			{
				methodName += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			}
			Method method = cls.getDeclaredMethod(methodName, field.getType());
			String req_param = request.getParameter(fieldName);

			method.invoke(bean, typeCast(field.getType(), req_param));

		}
		return bean;
	}

	/**
	 * 类型强转
	 * @param fieldType
	 * @param param
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T typeCast(Class<T> fieldType, String param)
	{
		T value = null;
		try
		{
			if (param == null)
			{
				return null;
			}
			if (fieldType == String.class)
			{
				value = (T) param;
			}
			else if (fieldType == Integer.class)
			{
				value = (T) Integer.valueOf(param);
			}
			else if (fieldType == Double.class)
			{
				value = (T) Double.valueOf(param);
			}
			else if (fieldType == Long.class)
			{
				value = (T) Long.valueOf(param);
			}
			else if (fieldType == Character.class)
			{
				value = (T) Character.valueOf(param.toCharArray()[0]);
			}
			else if (fieldType == Boolean.class)
			{
				value = (T) Boolean.valueOf(param);
			}
			else if (fieldType == Float.class)
			{
				value = (T) Float.valueOf(param);
			}
			else if (fieldType == Short.class)
			{
				value = (T) Short.valueOf(param);
			}
			else if (fieldType == Timestamp.class)
			{
				if (param.trim().length() > 10)
				{
					value = (T) Timestamp.valueOf(param);
				}
				else
				{
					value = (T) Timestamp.valueOf(param + " 00:00:00");
				}
			}
			else if (fieldType == Date.class)
			{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (param.trim().length() > 10)
				{
					value = (T) format.parse(param);
				}
				else
				{
					value = (T) format.parse(param + " 00:00:00");
				}
			}
		}
		catch (Exception e)
		{
			return null;
		}
		return value;
	}
}
