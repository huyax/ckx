/*
 * DateUtil.java 1.0.1
 * 
 * Aug 20, 2008
 * 
 * Copyrihgt 2008 Sysway, Inc. All rights reserved. Sysway PROPRIETARY/CONFIDENTIAL. Use is subject to licese terms. http://www.sysway.cn
 */
package com.ckx.lang.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期操作工具类,提供通用的日期操作方法<br>
 * 本类中涉及到的所有日期时间格式表达串均遵循jdk中时间表达标准,参见下表: <br>
 * 
 * <table border=0 cellspacing=3 cellpadding=2 style='font-size:10pt;border:1px solid #000' summary="Chart shows pattern letters, date/time component, presentation, and examples.">
 * <tr bgcolor="#ccccff">
 * <th align=left>字符表达式
 * <th align=left>日期或时间表达含义
 * <th align=left>数据类型
 * <th align=left>例子
 * <tr>
 * <td><code>G</code>
 * <td>纪元描述
 * <td>文本
 * <td><code>AD 公元</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>y</code>
 * <td>年份描述
 * <td>年份
 * <td><code>1996</code>; <code>96</code>
 * <tr>
 * <td><code>M</code>
 * <td>一年中的第几月
 * <td>月份
 * <td><code>July</code>; <code>Jul</code>; <code>07</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>w</code>
 * <td>一年中的第几周
 * <td>数值
 * <td><code>27</code>
 * <tr>
 * <td><code>W</code>
 * <td>一个月中的第几周
 * <td>数值
 * <td><code>2</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>D</code>
 * <td>一年中第几天
 * <td>数值
 * <td><code>189</code>
 * <tr>
 * <td><code>d</code>
 * <td>一月中的第几天
 * <td>数值
 * <td><code>10</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>F</code>
 * <td>周几
 * <td>数值
 * <td><code>2</code>
 * <tr>
 * <td><code>E</code>
 * <td>周几
 * <td>文本
 * <td><code>Tuesday</code>; <code>Tue</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>a</code>
 * <td>Am/pm 标识
 * <td>文本
 * <td><code>PM 下午</code>
 * <tr>
 * <td><code>H</code>
 * <td>一天中的小时 (0-23)
 * <td>数值
 * <td><code>0</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>k</code>
 * <td>一天中的小时(1-24)
 * <td>数值
 * <td><code>24</code>
 * <tr>
 * <td><code>K</code>
 * <td>一天中的小时 am/pm (0-11)
 * <td>数值
 * <td><code>0</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>h</code>
 * <td>一天中的小时 am/pm (1-12)
 * <td>数值
 * <td><code>12</code>
 * <tr>
 * <td><code>m</code>
 * <td>分钟
 * <td>数值
 * <td><code>30</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>s</code>
 * <td>秒数
 * <td>数值
 * <td><code>55</code>
 * <tr>
 * <td><code>S</code>
 * <td>毫秒
 * <td>数值
 * <td><code>978</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>z</code>
 * <td>Time zone
 * <td>通用时区
 * <td><code>Pacific Standard Time</code>; <code>PST</code>;
 * <code>GMT-08:00</code>
 * <tr>
 * <td><code>Z</code>
 * <td>Time zone
 * <td>RFC 822标准时区
 * <td><code>-0800</code>
 * </table>
 * 
 * @author lh
 * 
 */
public final class DateUtil {
	
	/**
	 * dateDiff()方法的unit参数,以年为单位
	 */
	public final static byte	DIFF_YEAR			= 0;
	
	/**
	 * dateDiff()方法的unit参数,以月为单位
	 */
	public final static byte	DIFF_MONTH			= 1;
	
	/**
	 * dateDiff()方法的unit参数,以日为单位
	 */
	public final static byte	DIFF_DAY			= 2;
	
	/**
	 * dateDiff()方法的unit参数,以小时为单位
	 */
	public final static byte	DIFF_HOUR			= 3;
	
	/**
	 * dateDiff()方法的unit参数,以分钟为单位
	 */
	public final static byte	DIFF_MINUTE			= 4;
	
	/**
	 * dateDiff()方法的unit参数,以秒为单位
	 */
	public final static byte	DIFF_SECONDE		= 5;
	
	/**
	 * dateDiff()方法的unit参数,以毫秒为单位
	 */
	public final static byte	DIFF_MILLISECOND	= 6;
	
	/**
	 * 取得当前日期对象
	 * 
	 * @return 返回java.util.Date日期对象
	 */
	public static Date getCurDate() {
		return getCurCalendar().getTime();
	}
	
	/**
	 * 取得当前小时
	 * 
	 * @return 返回java.util.Date日期对象
	 */
	public static int getCurHour() {
		return getCurCalendar().get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取当前时间日历对象
	 * 
	 * @return 返回java.util.Calendar日期对象
	 */
	public static Calendar getCurCalendar() {
		return Calendar.getInstance();
	}
	
	/**
	 * 取得当前时间,格式为HH:MM:SS
	 * 
	 * @return 返回当前时间
	 */
	public static String getCurTime() {
		return getDate(getCurDate(), "HH:mm:ss");
	}
	
	/**
	 * 取得当前日期的字符串表示,格式为 yyyy-MM-dd
	 * 
	 * @return 返回日期的字符串表示
	 */
	public static String getDate() {
		return getDate(getCurDate(), "yyyy-MM-dd");
	}
	
	public static String getDateHour() {
		return getDate(getCurDate(), "yyyy-MM-dd") + "_" + getCurHour();
	}
	
	public static String getPreHourDate() {
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR_OF_DAY, -1);
		
		String date = getDate(now.getTime(), "yyyy-MM-dd");
		int hour = now.get(Calendar.HOUR_OF_DAY);
		
		return date + "_" + hour;
	}
	
	public static Date getPreDate() {
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -1);
		
		return now.getTime();
	}
	
	public static Date getTodayDate() {
	  Calendar cal = Calendar.getInstance();
	  
	  Calendar now = Calendar.getInstance();
	  now.clear();
	  now.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	  return now.getTime();
	}
	
	/**
	 * 获取前一个小时的日期（不包含时间数据）
	 * 
	 * @return
	 */
	public static Date getPreHoursDate() {
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.HOUR_OF_DAY, -1);
	  
	  Calendar now = Calendar.getInstance();
	  now.clear();
	  now.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	  return now.getTime();
	}
	
	public static int getPreHour() {
	  
	  Calendar now = Calendar.getInstance();
	  now.add(Calendar.HOUR_OF_DAY, -1);
	  
	  return now.get(Calendar.HOUR_OF_DAY);
	}
	
	public static String getCurrHourDate() {
		
		Calendar now = Calendar.getInstance();
		
		String date = getDate(now.getTime(), "yyyy-MM-dd");
		int hour = now.get(Calendar.HOUR_OF_DAY);
		
		return date + "_" + hour;
	}
	
	/**
	 * 取得7天前的日期
	 */
	public static String get7DayBeforDate() {
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 7);
		
		return getDate(now.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 取得1天前的日期
	 */
	public static String get1DayBeforDate() {
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 1);
		
		return getDate(now.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 取得30天前的日期
	 */
	public static String get30DayBeforDate() {
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 30);
		
		return getDate(now.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获取一小时以前的时刻
	 */
	public static Date getHourBeforeTime(int hour) {
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) - hour);
		return now.getTime();
	}
	
	/**
	 * 获取当前日期时间字符串,格式为 yyyy-MM-dd hh:mm:ss
	 * 
	 * @return 返回当前字符串
	 */
	public static String getDatetime() {
		return getDate(getCurDate(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 将指定Date类型转换成指定格式的字符串,格式串参见类注释
	 * 
	 * @param date
	 *            日期方式
	 * @param format
	 *            指定的格式,当format为NULL或空串时,<BR>
	 *            默认为 yyyy-MM-dd 格式
	 * @return 当date为NULL时,返回空串
	 */
	public static String getDate(Date date, String format) {
		
		String dtstr = "";
		if (date == null) {
			return dtstr;
		}
		
		if (format == null || "".equals(format.trim())) {
			format = "yyyy-MM-dd";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		dtstr = sdf.format(date);
		return (dtstr == null ? "" : dtstr);
		
	}
	
	public static String getDate(Date date) {
		return getDate(date, "yyyy-MM-dd");
	}
	
	/**
	 * 取得标准格式的日期: HH:mm:ss
	 * 
	 * @param c
	 *            日期对象
	 * @return 返回指定时间的小时:分钟:秒数
	 */
	public static String getTime(Calendar c) {
		return getDate(c.getTime(), "HH:mm:ss");
	}
	
	/**
	 * 取得当前年份
	 * 
	 * @return 四位年份 yyyy
	 */
	public static String getYear() {
		Calendar c = getCurCalendar();
		return "" + c.get(Calendar.YEAR);
	}
	
	/**
	 * 取得当前月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		Calendar c = getCurCalendar();
		return "" + (c.get(Calendar.MONTH) + 1);
	}
	
	public static String getLastMothEnd() {
		
		Calendar c = getCurCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		return formartRandomDate(c.getTime(), "yyyy-MM-dd");
	}
	
	public static String getLastMothStart() {
		
		Calendar c = getCurCalendar();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		return formartRandomDate(c.getTime(), "yyyy-MM-dd");
	}
	
	public static String getThisMothStart() {
		
		Calendar c = getCurCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		return formartRandomDate(c.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 取得当前日
	 * 
	 * @return
	 */
	public static String getDay() {
		Calendar c = getCurCalendar();
		return "" + (c.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 取得当前星期数 1,2,3,4,5,6,7 代表 星期一.....
	 * 
	 * @return
	 */
	public static String getWeek() {
		Calendar c = getCurCalendar();
		int week = c.get(Calendar.DAY_OF_WEEK);
		if (week > 1) {
			week--;
		} else {
			week = 7;
		}
		return "" + week;
	}
	
	/**
	 * 将指定字串转换按指定格式转换成java.util.Date类型
	 * 
	 * @param dateStr
	 *            日期字串
	 * @param format
	 *            指定的格式,当format为NULL或空串时,<BR>
	 *            默认为 yyyy-MM-dd 格式
	 * @return 当dateStr 为null或者转换出错时,返回NULL值
	 * @throws RuntimeException
	 *             日期格式与格式串不一致时，抛出RuntimeException
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		if (dateStr == null || "".equals(dateStr.trim())) {
			return getCurDate();
		}
		if (format == null || "".equals(format.trim())) {
			format = "yyyy-MM-dd";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException ex) {
			throw new RuntimeException("日期格式与格式串不一致", ex);
		}
		
		return date;
	}
	
	/**
	 * 将指定字串转换按指定格式转换成java.util.Date类型
	 * 
	 * @param dateStr
	 *            日期字串 yyyy-MM-dd 格式
	 * @return 当dateStr 为null或者转换出错时,返回NULL值
	 * @throws RuntimeException
	 *             日期格式与格式串不一致时，抛出RuntimeException
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}
	
	/**
	 * 将指定字串转换按指定格式转换成java.util.Date类型
	 * 
	 * @param dateStr
	 *            日期字串 yyyy-MM-dd HH:mm:ss 格式
	 * @return 当dateStr 为null或者转换出错时,返回NULL值
	 * @throws RuntimeException
	 *             日期格式与格式串不一致时，抛出RuntimeException
	 */
	public static Date paseDatetime(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 将java.util.Date转换成 java.sql.Date类型
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return java.sql.Date对象,如果date为NULL,则返回NULL值
	 */
	public static java.sql.Date parseSQLDate(Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * 将java.util.Date转换成 java.sql.Timestamp类型
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return ava.sql.Timestamp对象,如果date为NULL,则返回NULL值
	 */
	public static Timestamp parseTimestamp(Date date) {
		
		if (date == null) {
			return null;
		}
		return new Timestamp(date.getTime());
		
	}
	
	/**
	 * 得到由c指定的日期所在月的开始日期
	 * 
	 * @param c
	 * @return
	 */
	public static Calendar getMonthBegin(Calendar c) {
		Calendar r = getCurCalendar();
		r.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		return (r);
	}
	
	/**
	 * 得到由c指定的日期所在月的结束日期
	 * 
	 * @param c
	 * @return
	 */
	public static Calendar getMonthEnd(Calendar c) {
		Calendar r = getCurCalendar();
		r.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1, 23, 59, 59);
		r.add(Calendar.DATE, -1);
		return (r);
	}
	
	/**
	 * 将指定时间串转换成日期时间对象
	 * 
	 * @param dateStr
	 *            时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
	 * @return 返回指定时间的Calendar对象
	 * @throws NullPointerException
	 *             当日期时间格式不正确时
	 */
	public static Calendar parseCalendar(String dateStr) {
		Date dt = null;
		dt = parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		if (dt == null) {
			dt = parseDate(dateStr);
		}
		Calendar c = getCurCalendar();
		c.setTime(dt);
		return (c);
	}
	
	/**
	 * @see #dateDiff(Calendar, Calendar, byte)
	 * 
	 * @return 相差时差
	 * 
	 */
	public static long dateDiff(String dateFrom, String dateTo, byte unit) {
		Calendar from = parseCalendar(dateFrom);
		Calendar to = parseCalendar(dateTo);
		return dateDiff(from, to, unit);
	}
	
	/**
	 * @see #dateDiff(Calendar, Calendar, byte)
	 * 
	 * @param dateFrom
	 *            开始时间
	 * @param dateTo
	 *            结束时间
	 * @param unit
	 *            单位
	 * @return
	 */
	public static long dateDiff(Date dateFrom, Date dateTo, byte unit) {
		Calendar from = Calendar.getInstance();
		from.setTime(dateFrom);
		Calendar to = Calendar.getInstance();
		to.setTime(dateTo);
		return dateDiff(from, to, unit);
	}
	
	/**
	 * 返回2个日期之间的差距 unit是时间计算的单位,为本类常量DIFF_&lgt;XXXX&rgt;中的任一类型
	 * 
	 * @param dateFrom
	 *            开始时间的时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
	 * @param dateTo
	 *            结束时间的时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
	 * @param unit
	 *            是时间计算的单位,为以下值中的任一值:<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_YEAR &nbsp;以年为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MONTH &nbsp;以月为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_DAY &nbsp;以日为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_HOUR &nbsp;以小时为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MINUTE &nbsp;以分钟为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_SECONDE &nbsp;以秒为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MILLISECOND &nbsp;以毫秒为单位&nbsp;
	 * 
	 * @return 相差时差
	 */
	@SuppressWarnings("static-access")
	public static long dateDiff(Calendar dateFrom, Calendar dateTo, byte unit) {
		long diff = dateTo.getTimeInMillis() - dateFrom.getTimeInMillis();
		long interval = 0;
		switch (unit) {
			case 0: {
				Calendar from = dateFrom;
				Calendar to = dateTo;
				interval = to.get(to.YEAR) - from.get(from.YEAR);
				from.set(from.YEAR, to.get(to.YEAR));
				if (from.after(to)) {
					if (interval < 0) {
						interval++;
					} else {
						interval--;
					}
				}
				break;
			}
			case 1: {
				int year = dateTo.get(dateTo.YEAR) - dateFrom.get(dateFrom.YEAR);
				int month = dateTo.get(dateTo.MONTH) - dateFrom.get(dateFrom.MONTH);
				Calendar from = dateFrom;
				Calendar to = dateTo;
				from.set(from.YEAR, dateTo.get(dateTo.YEAR));
				from.set(from.MONTH, dateTo.get(dateTo.MONTH));
				interval = year * 12 + month;
				if (from.after(to)) {
					if (interval < 0) {
						interval++;
					} else {
						interval--;
					}
				}
				break;
			}
			case 2:
				interval = (int) (diff / (1000 * 60 * 60 * 24));
				break;
			
			case 3:
				interval = (int) (diff / (1000 * 60 * 60));
				break;
			
			case 4:
				interval = (int) (diff / (1000 * 60));
				break;
			
			case 5:
				interval = (int) (diff / 1000);
				break;
			
			default:
				interval = diff;
		}
		return interval;
	}
	
	/**
	 * 自由串格式化日期串,对于下列表中的字符支持\转义<br>
	 * 例如:<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\yyyy 结果为 y08y (原因为第一个被转义,剩下的字串只能构造成两位年份)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\y 结果为 y <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\yyyyy 结果为 y2008 <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\yyyyyy 结果为 y2008y <br>
	 * 其它字符如此类同
	 * 
	 * @param date
	 *            日期对象
	 * @param strFormat
	 *            格式串,含义如下,注意大小写区分:<br>
	 * 
	 *            <table border=0 cellspacing=3 cellpadding=2 style='font-size:10pt;border:1px solid #000' summary="Chart shows pattern letters, date/time component, presentation, and examples.">
	 *            <tr bgcolor="#ccccff">
	 *            <th align=left>字符表达式
	 *            <th align=left>日期或时间表达含义
	 *            <th align=left>例子
	 *            <tr>
	 *            <td><code>yyyy</code>
	 *            <td>四位年
	 *            <td><code>2000, 2009</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>yy</code>
	 *            <td>两位年
	 *            <td><code>00, 09</code>
	 *            <tr>
	 *            <td><code>MM</code>
	 *            <td>两位月
	 *            <td><code>07 , 23</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>M</code>
	 *            <td>普通月
	 *            <td><code>7 , 23</code>
	 *            <tr>
	 *            <td><code>dd</code>
	 *            <td>两位天
	 *            <td><code>03, 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>d</code>
	 *            <td>普通天
	 *            <td><code>3, 12</code>
	 *            <tr>
	 *            <td><code>hh</code>
	 *            <td>两位小时
	 *            <td><code>03, 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>h</code>
	 *            <td>普通小时
	 *            <td><code> 3, 12</code>
	 *            <tr>
	 *            <td><code>mm</code>
	 *            <td>分
	 *            <td><code>03 , 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>m</code>
	 *            <td>分
	 *            <td><code>3 , 12</code>
	 *            <tr>
	 *            <td><code>ss</code>
	 *            <td>秒
	 *            <td><code>03 , 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>s</code>
	 *            <td>秒
	 *            <td><code>3 , 12</code>
	 *            <tr>
	 *            <td><code>SSS</code>
	 *            <td>三位微秒
	 *            <td><code>003, 012 , 199</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>S</code>
	 *            <td>微秒
	 *            <td><code>3, 12 , 199</code>
	 *            <tr>
	 *            <td><code>F</code>
	 *            <td>周几 ,数值
	 *            <td><code>3</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>E</code>
	 *            <td>周几 ,文本
	 *            <td><code>星期三, Tuesday</code>
	 *            <tr>
	 *            <td><code>a</code>
	 *            <td>Am/pm 标识
	 *            <td><code> PM, 下午</code>
	 *            </table>
	 * @since 1.0.1
	 * @return 如果date 或者 strFormat 为null,则返回空串，否则返回指定格式串
	 */
	public static String formartRandomDate(Date date, String strFormat) {
		if (date == null || strFormat == null) {
			return "";
		}
		
		String key = strFormat;
		key = key.replaceAll("(?<!\\\\)yyyy", getDate(date, "yyyy"));
		key = key.replaceAll("(?<!\\\\)yy", getDate(date, "yy"));
		key = key.replaceAll("\\\\y", "y");
		
		key = key.replaceAll("(?<!\\\\)MM", getDate(date, "MM"));
		key = key.replaceAll("(?<!\\\\)M", getDate(date, "M"));
		key = key.replaceAll("(?<!\\\\)mm", getDate(date, "mm"));
		key = key.replaceAll("(?<!\\\\)m", getDate(date, "m"));
		key = key.replaceAll("\\\\(M|m)", "$1");
		
		key = key.replaceAll("(?<!\\\\)dd", getDate(date, "dd"));
		key = key.replaceAll("(?<!\\\\)d", getDate(date, "d"));
		key = key.replaceAll("\\\\d", "d");
		
		key = key.replaceAll("(?<!\\\\)hh", getDate(date, "hh"));
		key = key.replaceAll("(?<!\\\\)h", getDate(date, "h"));
		key = key.replaceAll("(?<!\\\\)HH", getDate(date, "HH"));
		key = key.replaceAll("(?<!\\\\)H", getDate(date, "H"));
		key = key.replaceAll("\\\\(H|h)", "$1");
		
		key = key.replaceAll("(?<!\\\\)ss", getDate(date, "ss"));
		key = key.replaceAll("(?<!\\\\)s", getDate(date, "s"));
		key = key.replaceAll("(?<!\\\\)SSS", getDate(date, "SSS"));
		key = key.replaceAll("(?<!\\\\)SS", getDate(date, "SS"));
		key = key.replaceAll("(?<!\\\\)s", getDate(date, "S"));
		key = key.replaceAll("\\\\(S|s)", "$1");
		
		key = key.replaceAll("(?<!\\\\)F", getDate(date, "F"));
		key = key.replaceAll("\\\\F", "F");
		key = key.replaceAll("(?<!\\\\)E", getDate(date, "E"));
		key = key.replaceAll("\\\\E", "E");
		key = key.replaceAll("(?<!\\\\)a", getDate(date, "a"));
		key = key.replaceAll("\\\\a", "a");
		
		return key;
	}
	
	/**
	 * 日期是否在区间内
	 * 
	 * @param date 日期
	 * @param dateFrom 区间开始
	 * @param dateTo 区间结束
	 * @return
	 */
	public static boolean betweenDate(Date date, Date dateFrom, Date dateTo) {
		return date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) < 0;
	}
	
	/**
	 * 获取周中第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		
		return c.getTime();
	}
	
	/**
	 * 获取周中最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		
		c.add(Calendar.DAY_OF_MONTH, 6);
		
		return c.getTime();
	}
	
	/**
	 * 获取月份第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		return c.getTime();
	}
	
	/**
	 * 获取月份最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return c.getTime();
	}
	
	public static Date getSpecifiedDayBefore(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return c.getTime();
	}
	
	public static Date addDay(Date date, int day) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.add(Calendar.DAY_OF_MONTH, day);
		
		return c.getTime();
	}
	
	public static Date addWeek(Date date, int week) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.add(Calendar.WEEK_OF_YEAR, week);
		
		return c.getTime();
	}
	
	public static class A {
		
		private String	status;
		
		/**
		 * @return Returns the status
		 */
		public String getStatus() {
			return status;
		}
		
		/**
		 * @param status
		 *            The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}
	}
	
	public static Date parseDatetime(String date) {
		SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date s = t.parse(date);
			return s;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 获取当个月的第一天的时间戳
	 * 
	 * @return
	 */
	public static long getFirstDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		long re = c.getTimeInMillis();
		
		return re;
	}
	
	/**
	 * 获取当月的最后一天末的时间戳
	 * 
	 * @return
	 */
	public static long getLastDayTimeOfMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		c.add(Calendar.MONTH, 1);
		long re = c.getTimeInMillis();
		re -= 1;
		
		return re;
	}
	
	/**
	 * 获取上个月的第一天的时间戳
	 * 
	 * @return
	 */
	public static long getFirstDayTimeOfLastMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		c.add(Calendar.MONTH, -1);
		long re = c.getTimeInMillis();
		
		return re;
	}
	
	/**
	 * 获取昨天最后时间的时间戳 结束时间yyyy-MM-dd 23:59:59
	 * 
	 * @return
	 */
	public static long getToDayLastTime() {
		Calendar cal = Calendar.getInstance();
		// 设置时间
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime().getTime();
	}
	
	/**
	 * 获取昨天最后时间前30天的开始时间时间戳 开始时间yyyy-MM-dd 00:00:00
	 * 
	 * @return
	 */
	public static long getToDayLastTime30DayBefore() {
		Calendar cal = Calendar.getInstance();
		// 设置时间
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, -29);
		return cal.getTime().getTime();
	}
	
	/**
	 * 获取上月的最后一天末的时间戳
	 * 
	 * @return
	 */
	public static long getLastDayTimeOfLastMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long re = c.getTimeInMillis();
		re -= 1;
		
		return re;
	}
	
	/**
	 * 得到yyyyMM格式的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYearMonth(Date date) {
		DateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(date);
	}
	
	/**
	 * 得到yyyy-MM格式的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear_Month(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(date);
	}
	
	/**
	 * 得到yyyy-MM-dd格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear_Month_Day(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 得到yyyy-MM-dd格式的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 根据时间得到天 格式yyyyMMdd
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static int getDay(long time) {
		String date = new SimpleDateFormat("yyyyMMdd").format(time);
		return Integer.parseInt(date);
	}
	
	/**
	 * 根据时间得到年 格式yyyy
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static int getYear(long time) {
		// String date = new SimpleDateFormat("yyyy").format(time);
		// return Integer.parseInt(date);
		return getYearAndWeek(time)[0];
	}
	
	/**
	 * 根据指定时间返回 年和周
	 * 
	 * @param time
	 *            返回数组year=int[0] week=year[1]
	 * @return
	 */
	public static int[] getYearAndWeek(long time) {
		if (time <= 0) {
			return new int[] { 0, 0 };
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(time));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(time));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一星期的第一天
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		if (month == 12 && week == 1) {
			year++;
		}
		int[] yw = { year, week };
		return yw;
	}
	
	/**
	 * 根据时间得到周 一年的第多少周
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static int getWeek(long time) {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTimeInMillis(time);
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一星期的第一天
		// int week = calendar.get(Calendar.WEEK_OF_YEAR);
		// return week;
		return getYearAndWeek(time)[1];
	}
	
	/**
	 * 根据时间得到月 格式yyyyMM
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static int getMonth(long time) {
		String date = new SimpleDateFormat("yyyyMM").format(time);
		return Integer.parseInt(date);
	}
	
	// 获取当前时间所在年的最大周数
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}
	
	// 获取当前时间所在年的周数
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 获取今天开始时间
	 * 
	 * @return
	 */
	public static long getToDayBeginTime() {
		Calendar cal = Calendar.getInstance();
		// 设置时间
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 0);
		cal.add(Calendar.SECOND, 0);
		return cal.getTime().getTime();
	}
	
	/**
	 * 得到上周期的月和周期 [0] 月 </br> [1] 周期</br>
	 * 
	 * @return
	 */
	public static int[] getLastMonthAndCycle() {
		int[] o = new int[2];
		String day = (getThisMonthAndCycle()[0] + "").substring(4, 6);
		int year = Integer.parseInt((getThisMonthAndCycle()[0] + "").substring(0, 4));
		int month = getThisMonthAndCycle()[0];
		int cycle = 0;
		if (getThisMonthAndCycle()[1] == 1) {
			if ("01".equals(day)) {
				year--;
				month = Integer.parseInt(year + "12");
			} else {
				month--;
			}
			cycle = 3;
		} else {
			cycle = getThisMonthAndCycle()[1] - 1;
		}
		o[0] = month;
		o[1] = cycle;
		return o;
	}
	
	/**
	 * 得到本周期的月和周期 [0] 月 </br> [1] 周期</br>
	 * 
	 * @return
	 */
	public static int[] getThisMonthAndCycle() {
		int cycle = 1;
		String str = new SimpleDateFormat("yyyyMMdd").format(new Date());
		int month = Integer.valueOf(str.substring(0, 6));
		int day = Integer.valueOf(str.substring(6, 8));
		if (day <= 10) {
			cycle = 1;
		}
		if (day > 10 && day <= 20) {
			cycle = 2;
		}
		if (day > 20) {
			cycle = 3;
		}
		return new int[] { month, cycle };
	}
	
	public static long getYesterdayMs() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.DAY_OF_YEAR, -1);
		long timeInMillis = c.getTimeInMillis();
		return timeInMillis;
	}
	
	public static Date getDate(long time) {
		
		Calendar c = getCurCalendar();
		c.setTimeInMillis(time);
		
		return c.getTime();
	}
	
	/**
	 * 取最近一周的开始时间
	 * 
	 * @return
	 */
	public static long getLastWeekMs() {
		return new Date().getTime() - 7 * 24 * 60 * 60 * 1000;
	}
	
	public static void main(String[] args) throws InterruptedException {
		/*for (int i = 0; i < 10; i++)
			System.out.println(new Random().nextInt(1));*/
		
		
	}
	/**
     * 获取指定小时前的时间
     * @param changeDay
     * @return
     */
    public static String getNextTime(int hours) {
        
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY) + hours;
        cal.set(Calendar.HOUR_OF_DAY, hour);
        
        return new SimpleDateFormat("yyyyMMddHH").format(cal.getTime());
    }
	
}
