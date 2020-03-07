package com.mi.haircut.utils;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.ParseException;

/**
 * @version 1.0
 * @author
 * @date 2016年7月2日 下午4:25:22
 */
public class TimeUtil {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formats2 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat formathms = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	public static SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	public static SimpleDateFormat formatOrder = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat formats3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 获取今日的结束时间
	 * 
	 * @return
	 */
	public static Date getnowEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	/**
	 * 获取今日的开始时间
	 * 
	 * @return
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	/**
	 * 获取最近的时间
	 * 
	 * @param day
	 * @return
	 */
	public static Date nextDate(int num) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -num);
		Date time = cal.getTime();
		System.out.println(time);
		return time;
	}

	/**
	 * 计算明年的时间
	 */
	public static Date nextYear(Date todayDate) {
		if (todayDate == null)
			return null;
		long afterTime = (todayDate.getTime() / 1000) + 60 * 60 * 24 * 365;
		todayDate.setTime(afterTime * 1000);
		return todayDate;
	}

	/**
	 * 获取指定年的时间
	 */
	public static Date getDateByYear(Date todayDate, int year) {
		if (todayDate == null)
			return null;
		if (year <= 0)
			year = 1;
		long afterTime = (todayDate.getTime() / 1000) + 60 * 60 * 24 * 365 * year;
		todayDate.setTime(afterTime * 1000);
		return todayDate;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		if (smdate == null || bdate == null)
			return 0;
		try {
			smdate = formats.parse(formats.format(smdate));
			bdate = formats.parse(formats.format(bdate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	
	
	
	/**
	 * 计算两个日期之间相差的时分秒
	 */
	public static String hmsBetween(Date smdate, Date bdate) {
		if (smdate == null || bdate == null)
			return "";
		try {
			smdate = formats.parse(formats.format(smdate));
			bdate = formats.parse(formats.format(bdate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 1000;
		String hms = secondParseHMS(between_days);

		return hms;
	}

	/**
	 * 计算两个日期之间的大小
	 */
	public static boolean betweenDate(Date smdate, Date bdate) {
		if (smdate == null || bdate == null)
			return false;
		try {
			smdate = formats.parse(formats.format(smdate));
			bdate = formats.parse(formats.format(bdate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		if ((time1 - time2) > 10000) {
			return true;
		}
		return false;
	}

	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				// timeStr = unitFormat(minute) + ":" + unitFormat(second);
				timeStr = unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
			} else {
				hour = minute / 60;
				// if (hour > 99)
				// return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				// timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" +
				// unitFormat(second);
				timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
			}
		}
		return timeStr;
	}
	

	/**
	 * 计算两个时间差
	 * 
	 * @param newDate
	 * @param oldDate
	 * @return
	 */
	public static String getDistanceTime(Date newDate, Date oldDate) {
		if (newDate == null)
			return null;
		if (oldDate == null)
			return null;
		String result = null;
		newDate = getNowDate(newDate);
		oldDate = getNowDate(oldDate);
		Long ltime = oldDate.getTime() - newDate.getTime();
		long day = ltime / (24 * 60 * 60 * 1000);
		long hour = (ltime / (60 * 60 * 1000) - day * 24);
		long min = ((ltime / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (ltime / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		if (hour >= 24) {
			result = day + "天前";
		} else if (0 < hour && hour < 24) {
			result = hour + "小时前";
		} else {
			if (min > 0) {
				result = min + "分钟前";
			} else {
				if (s > 0) {
					result = s + "秒前";
				} else {
					result = "首次登录";
				}
			}
		}
		return result;
	}

	private static Date getNowDate(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	public static String secToTime(Long time) {
		String timeStr = null;
		Long hour = 0l;
		Long minute = 0l;
		Long second = 0l;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				// timeStr = unitFormat(minute) + ":" + unitFormat(second);
				timeStr = unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
			} else {
				hour = minute / 60;
				// if (hour > 99)
				// return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				// timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" +
				// unitFormat(second);
				timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	public static String unitFormat(Long i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Long.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	public static String getCurrentDate() {
		return format.format(System.currentTimeMillis());
	}

	public static String getTomorrowDate() {
		return format.format(System.currentTimeMillis() + 86400000);
	}

	public static String getDateByDayNum(int num) {
		return format.format(System.currentTimeMillis() + 86400000 * num);
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar a = Calendar.getInstance();
		return a.get(Calendar.YEAR) + "";
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		return monthFormat.format(System.currentTimeMillis());
	}

	public static String getNextDay(String currentDay) throws java.text.ParseException {
		try {
			return format.format(format.parse(currentDay).getTime() + 86400000);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String formatDate(String day) throws java.text.ParseException {
		try {
			return format.format(format.parse(day).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String formatHMS(Date date) {
		try {
			return formathms.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String formatYMD(Date date) {
		try {
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取当前日
	 * 
	 * @return
	 */
	public static String getDay() {
		return dayFormat.format(System.currentTimeMillis());
	}

	/**
	 * 获取当前是星期几
	 * 
	 * @return
	 */
	public static int getWeek() {
		Calendar a = Calendar.getInstance();
		return a.get(Calendar.DAY_OF_WEEK) - 1;
		// 返回的是中文的星期
		// Date date = new Date();
		// SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		// return dateFm.format(date);
	}

	/**
	 * 获取当前年月日
	 * 
	 * @return
	 */
	public static String getYearMonthDay() {
		String year = getYear();
		String month = getMonth();
		String day = getDay();

		return year + month + day;
	}

	/**
	 * string转时间
	 * 
	 * @return
	 * @throws java.text.ParseException 
	 * @throws java.text.ParseException
	 */
	public static Date strParseDate(String str){
		try {
			return formats.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * string转时间
	 * 
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date strParseDate2(String str) throws java.text.ParseException {
		try {
			return formats2.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * string转时间
	 * 
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date strParseDates(String str) {
		try {
			return format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * string转时间
	 * 
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date strParseDates2(String str) {
		try {
			return format2.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间转string
	 * 
	 * @return
	 */
	public static String dataformat(Date date) {

		try {
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dataformat2(Date date) {

		try {
			return formats2.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间转string
	 * 
	 * @return
	 */
	public static String dateformatsStr(Date date) {

		try {
			return formats.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dataformat3(Date date) {

		try {
			return formats3.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间转string
	 * 
	 * @return
	 */
	public static String dateformatStr(Date date) {

		try {
			if (date == null)
				return null;
			return ymdhms.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间转string
	 * 
	 * @return
	 */
	public static String dateFormtStr2(Date date) {

		try {
			if (date == null)
				date = new Date();
			return formatOrder.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 利用正则表达式判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 把秒转接成时分秒
	 */
	public static String secondParseHMS(long a) {
		if (a <= 0)
			return "00:00:00";
		long s = a * 1000;
		long xs = (s / 1000 / 60 / 60);
		long fz = (s / 1000 / 60) - xs * 60;
		long m = (s / 1000) - fz * 60 - xs * 60 * 60;
		String str = xs + "小时" + fz + "分钟" + m + "秒";
		return str;
	}

	/**
	 * 把毫秒转接成时分秒
	 */
	public static String millisecondParseHMS(long s) {
		if (s <= 0)
			return "0秒";
		long xs = (s / 1000 / 60 / 60);
		long fz = (s / 1000 / 60) - xs * 60;
		long m = (s / 1000) - fz * 60 - xs * 60 * 60;
		StringBuilder str = new StringBuilder();
		if (xs != 0)
			str.append(xs + "小时");
		if (fz != 0)
			str.append(fz + "分钟");
		str.append(m + "秒");
		return str.toString();
	}

	/**
	 * 把秒转接成时分秒
	 */
	public static String maoParseHMS(long s) {
		if (s <= 0)
			return "00:00:00";
		long xs = (s / 60 / 60);
		long fz = (s / 60) - xs * 60;
		long m = (s) - fz * 60 - xs * 60 * 60;
		String str = xs + "小时" + fz + "分钟" + m + "秒";
		return str;
	}

	/**
	 * 根据自定义的标记查询时间
	 * 
	 * @return
	 */
	public static String selectDateByflag(int flag) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, flag);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(date);

		return dateString;
	}

	public static Date getStartTimeByFlag(int flag) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, flag);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
 
	public static Date getEndTimeByFlag(int flag) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, flag);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	public static Date getStartTimeByStr(String str) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strParseDates(str));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getEndTimeByStr(String str) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strParseDates(str));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 根据自定义的标记查询时间
	 * 
	 * @return
	 */
	public static Date queryDateByflag(int flag) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, flag);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();// 这个时间就是日期往后推一天的结果

		return date;
	}
	/**
	 * 根据自定义的标记查询时间
	 * 
	 * @return
	 */
	public static Date queryDateByflag2(Date date,int flag) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, flag);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();// 这个时间就是日期往后推一天的结果
		
		return date;
	}

	/**
	 * 根据传入的时间转换成年月日
	 * 
	 * @return
	 */
	public static String selectYMDByDate(Date date) {
		if (date == null)
			return "";

		return format.format(date);
	}

	/**
	 * 把某个日期时间转换为当天的最后一秒
	 * 
	 * @param time
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Date getDayAtLastSecond(Date time) {
		final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
		String deadTimeStr = YYYY_MM_DD.format(time) + " 23:59:59";
		final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date rTime = null;
		try {
			rTime = YYYY_MM_DD_HH_MM_SS.parse(deadTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return rTime;
		}
	}

	@SuppressWarnings("finally")
	public static Date getDayAtFirstSecond(Date time) {
		final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
		String deadTimeStr = YYYY_MM_DD.format(time) + " 00:00:00";
		final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date rTime = null;
		try {
			rTime = YYYY_MM_DD_HH_MM_SS.parse(deadTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return rTime;
		}
	}
}
