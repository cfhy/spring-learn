package com.yyb.common.utils;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 把字符串转换成指定格式的日期
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr, String pattern) throws ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, pattern);
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t =  System.currentTimeMillis()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t =  System.currentTimeMillis()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	 /**
	  * 将时间戳转换为时间
	  * @param s 时间戳
	  * */
	public static Date stampToDate(Long s){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(s);
		return date;
	}

	/**
	 * 对比时间如果传递过来
	 * 的时间大于现在的时间则返回true
	 * @param date 对比时间
	 * @return boolean
	 * */
	public static boolean compareDate(Date date){
		Date nowDate=new Date();
		boolean flag=nowDate.before(date);
		return  flag;
	}

	public static Date addDateSecond(Date date,int second){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND,second);
		date=calendar.getTime();
		return  date;
	}

	/**
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * @param months
	 *            月数
	 * @param days
	 *            天数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定年数，月数，天数之后的日期
	 */
	public static Date afterDate(Date date, int years, int months, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		cal.add(Calendar.MONTH, months);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * @param months
	 *            月数
	 * @param days
	 *            天数
	 * @param hours
	 *            小时数
	 * @param minutes
	 *            分钟数
	 * @param seconds
	 *            秒数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定年数，月数，天数，小时数，分钟数，秒数之后的日期
	 */
	public static Date afterDate(Date date, int years, int months, int days, int hours, int minutes, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		cal.add(Calendar.MONTH, months);
		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		cal.add(Calendar.MINUTE, minutes);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param days
	 *            天数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定天数之后的日期
	 */
	public static Date afterDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param months
	 *            月数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定月数之后的日期
	 */
	public static Date afterMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定年数之后的日期
	 */
	public static Date afterYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * @param months
	 *            月数
	 * @param days
	 *            天数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定年数，月数，天数之前的日期
	 */
	public static Date beforeDate(Date date, int years, int months, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -years);
		cal.add(Calendar.MONTH, -months);
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * @param months
	 *            月数
	 * @param days
	 *            天数
	 * @param hours
	 *            小时数
	 * @param minutes
	 *            分钟数
	 * @param seconds
	 *            秒数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定年数，月数，天数，小时数，分钟数，秒数之前的日期
	 */
	public static Date beforeDate(Date date, int years, int months, int days, int hours, int minutes, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -years);
		cal.add(Calendar.MONTH, -months);
		cal.add(Calendar.DAY_OF_MONTH, -days);
		cal.add(Calendar.HOUR_OF_DAY, -hours);
		cal.add(Calendar.MINUTE, -minutes);
		cal.add(Calendar.SECOND, -seconds);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param days
	 *            天数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定天数之前的日期
	 */
	public static Date beforeDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param months
	 *            月数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定月数之前的日期
	 */
	public static Date beforeMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -months);
		return cal.getTime();
	}

	/**
	 * @param date
	 *            日期
	 * @param years
	 *            年数
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回指定年数之前的日期
	 */
	public static Date beforeYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -years);
		return cal.getTime();
	}

	/**
	 * 取得指定日期的23点59分59秒
	 *
	 * @param dateStr
	 *            日期 年月
	 * @return : 格式化为当天的最后一秒
	 * @author : chenlong
	 * @version : 1.00
	 * @throws ParseException
	 * @create time : 2013-3-18
	 * @description : 取得指定日期的最后一秒
	 */
	public static Date getDayLastSecond(String dateStr) throws ParseException {
		// 当日期字符串不为空或者""时，转换为Date类型
		if (dateStr != null || !"".equals(dateStr)) {
			Date date = org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, parsePatterns);
			// 实例化Calendar类型
			Calendar cal = Calendar.getInstance();
			// 设置年月
			cal.setTime(date);
			// 设置时间为23时
			cal.set(Calendar.HOUR_OF_DAY, 23);
			// 设置时间为59分
			cal.set(Calendar.MINUTE, 59);
			// 设置时间为59秒
			cal.set(Calendar.SECOND, 59);
			// 设置时间为999毫秒
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		} else {
			return null;
		}
	}

	/**
	 * 获取传入日期一天的结束时间 yyyy-MM-dd 23:59:59
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayLastSecond(Date date) {
		// 实例化Calendar类型
		Calendar cal = Calendar.getInstance();
		// 设置年月
		cal.setTime(date);
		// 设置时间为23时
		cal.set(Calendar.HOUR_OF_DAY, 23);
		// 设置时间为59分
		cal.set(Calendar.MINUTE, 59);
		// 设置时间为59秒
		cal.set(Calendar.SECOND, 59);
		// 设置时间为999毫秒
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 取得指定年月的第一天
	 *
	 * @param yearMonthStr
	 *            年月
	 * @return : firstDay 第一天
	 * @author : youyd
	 * @version : 1.00
	 * @throws ParseException
	 * @create time : 2013-2-25 下午12:43:16
	 * @description : 取得指定年月的第一天
	 */
	public static Date getFirstDay(String yearMonthStr) throws ParseException {
		// 当日期字符串不为空或者""时，转换为Date类型
		if (yearMonthStr != null || !"".equals(yearMonthStr)) {
			Date yearMonth = parseDate(yearMonthStr, "yyyy-MM");
			// 实例化Calendar类型
			Calendar cal = Calendar.getInstance();
			// 设置年月
			cal.setTime(yearMonth);
			// 设置日期为该月第一天
			cal.set(Calendar.DATE, 1);
			// 返回指定年月的第一天
			return cal.getTime();
		} else {
			return null;
		}

	}

	/**
	 * 取得指定年月的最后一天
	 *
	 * @param yearMonthStr
	 *            年月
	 * @return : lastDay 最后一天
	 * @author : youyd
	 * @version : 1.00
	 * @throws ParseException
	 * @create time : 2013-2-25 下午12:43:16
	 * @description : 取得指定年月的最后一天
	 */
	public static Date getLastDay(String yearMonthStr) throws ParseException {
		// 当日期字符串不为空或者""时，转换为Date类型
		if (yearMonthStr != null || !"".equals(yearMonthStr)) {
			Date yearMonth = parseDate(yearMonthStr, "yyyy-MM");
			// 实例化Calendar类型
			Calendar cal = Calendar.getInstance();
			// 设置年月
			cal.setTime(yearMonth);
			// 设置月份为下一月份
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			// 设置日期为下一月份第一天
			cal.set(Calendar.DATE, 1);
			// 设置时间为23时
			cal.set(Calendar.HOUR_OF_DAY, 23);
			// 设置时间为59分
			cal.set(Calendar.MINUTE, 59);
			// 设置时间为59秒
			cal.set(Calendar.SECOND, 59);
			// 设置时间为999毫秒
			cal.set(Calendar.MILLISECOND, 999);
			// 回滚一天 即上月份的最后一天
			cal.roll(Calendar.DATE, -1);
			// 返回指定年月的最后一天
			return cal.getTime();
		} else {
			return null;
		}

	}

	/**
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return
	 * @about version ：1.00
	 * @auther : lifajun
	 * @Description ：返回两个日期相差天数的绝对值
	 */
	public static Integer intervalDay(Date date1, Date date2) {
		return Math.abs((int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24)));
	}

	/**
	 * @description 计算量日期中的月数差
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Integer intervalMonth(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		int year1 = calendar1.get(Calendar.YEAR);
		int year2 = calendar2.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH);
		int month2 = calendar2.get(Calendar.MONTH);
		int month12 = Math.abs(year1 - year2) * 12;
		if (year1 - year2 > 0) {
			month1 += month12;
		} else if (year2 - year1 > 0) {
			month2 += month12;
		}
		return Math.abs(month2 - month1);
	}

}
