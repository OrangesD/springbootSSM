package com.zhao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;

/**
 * 日期处理
 * 
 * @author OrangesD
 *
 */
@SuppressWarnings("unchecked")
public class DateUtil {
	private static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	private static SimpleDateFormat sdfTimesss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

	protected static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 获取yyyyMMddHHmmss格式的当前时间
	 * 
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	/**
	 * 获取yy-MM-dd 上午(下午)HH:mm格式的时间
	 * 例如：18-12-26 上午10:09
	 * @param sdf
	 * @return
	 */
	public static String today(SimpleDateFormat sdf) {
		return sdf.format(new Date());
	}
	/**
	 * 获取YYYY格式的当前时间年份
	 * 例如：2018
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	/**
	 * 获取yyyy-MM-dd HH:mm:ss:SSS格式的当前时间
	 * 例如：2018-12-26 10:12:17:713
	 * @return
	 */
	public static String getTimeSSS() {
		return sdfTimesss.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式的当前日期
	 * 例如：2018-12-26
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	

	/**
	 * 获取YYYYMMDD格式的当前日期
	 * 例如：20181226
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}
	

	/**
	 * 获取YYYYMMDD格式
	 * 例如：20181226
	 * @param date 日期
	 * @return
	 */
	public static String getDays(Date date) {
		return sdfDays.format(date);
	}
	
	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式的当前时间
	 * 例如：2018-12-26 10:16:47
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	

	/**
	 * 日期比较 (如果s>=e 返回true，否则返回false)
	 * 
	 * @param s
	 * @param e
	 * @return
	 */
	public static boolean compareDate(String s, String e) {
		if ((fomatDate(s) == null) || (fomatDate(e) == null)) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}
	
	/**
	 * 日期比较(如果s>=e 返回true，否则返回 false)
	 * 
	 * @param s
	 * @param e
	 * @return
	 */
	public static boolean compareDate(Date s, Date e) {
		if ((s == null) || (e == null)) {
			return false;
		}
		return s.getTime() >= e.getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 截取时间只显示年月日，格式(YYYY-MM-dd)
	 * 例如：2018-12-26
	 * @param date 时间
	 * @return
	 */
	public static String fomatDateString2(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			return sdfDay.format(fmt.parse(date));
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return null;
	}
	/**
	 * 截取时间只显示年月,格式(YYYY年MM月)
	 * 例如：2018年12月
	 * @param date 时间
	 * @return
	 */
	public static String fomatDateString(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM");
		try {
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy年MM月");
			return sdfDay.format(fmt.parse(date));
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return null;
	}
	/**
	 * 格式化日期
	 * 例如：Wed Dec 26 10:24:34 CST 2018
	 * @param date 时间(2018-12-26 10:24:34)
	 * @return
	 */
	public static Date fomatDate4Time(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	/**
	 * 检验日期是否合法
	 * 说明:格式为yyyy-MM-dd的时间是否合法
	 * @param s
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * 检验日期是否合法
	 * 说明:格式为yyyy-MM-dd HH:mm:ss的时间是否合法
	 * @param s
	 * @return
	 */
	public static boolean isValidTime(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	/**
	 * 两个时间相差多少年
	 * 注意：时间格式为yyyy-MM-dd
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) ((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / 86400000L / 365L);
			return years;
		} catch (Exception e) {
		}
		return 0;
	}

	
	/**
	 * 两个时间相差多少秒
	 * 注意：时间格式为yyyy-MM-dd HH:mm:ss
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static int getDiffMin2(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			int minute = (int) (fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / 1000;
			return minute;
		} catch (Exception e) {
		}
		return 0;
	}
	
	/**
	 * 两个时间相差多少分
	 * 注意：时间格式为yyyy-MM-dd HH:mm:ss
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static int getDiffMin(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			int minute = (int) (fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / 60000;
			return minute;
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 时间相减 得到天数
	 * 时间格式yyyy-MM-dd
	 * @param beginDateStr 开始时间
	 * @param endDateStr 结束时间
	 * @return
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0L;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / 86400000L;

		return day;
	}
	
	/**
	 * 得到n天后的日期,格式(YYYY-MM-dd)
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance();
		canlendar.add(5, daysInt);
		Date date = canlendar.getTime();

		String dateStr = sdfDay.format(date);

		return dateStr;
	}
	
	/**
	 * 得到n天后的日期
	 * 
	 * @param date 开始时间 格式(YYYY-MM-dd)
	 * @param days 天数
	 * @return
	 */
	public static String getAfterDayDate(String date, String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(fomatDate(date));
		canlendar.add(5, daysInt);
		Date nextDate = canlendar.getTime();

		String dateStr = sdfDay.format(nextDate);
		return dateStr;
	}
	

	/**
	 * 得到n秒后的日期
	 * 
	 * @param date 时间 格式(YYYY-MM-dd HH:mm:ss)
	 * @param second 多少秒
	 * @return
	 */
	public static String getAfterMinuteDate(String date, long second) {
		Date nextDate = new Date(fomatDate4Time(date).getTime() + second * 1000L);

		String dateStr = sdfTime.format(nextDate);
		return dateStr;
	}
	
	/**
	 * 得到n天后是星期几
	 * 
	 * @param days n天
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);
		Calendar canlendar = Calendar.getInstance();
		canlendar.add(5, daysInt);
		Date date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 自定义格式化日期
	 * 
	 * @param date 时间
	 * @param format 格式
	 * @return
	 */
	public static Date fomatDateDefine(String date, String format) {
		DateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 月初
	 * 
	 * @param date 时间
	 * @param dateFomat 时间格式
	 * @return
	 */
	public static String getMonthStart(Date date, String dateFomat) {
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(date);
		canlendar.set(canlendar.get(Calendar.YEAR), canlendar.get(Calendar.MONTH), 1, 0, 0, 0);
		date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFomat);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 月末
	 * 
	 * @param date 时间
	 * @param dateFomat 时间格式
	 * @return
	 */
	public static String getMonthEnd(Date date, String dateFomat) {
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(date);
		canlendar.add(Calendar.MONTH, 1);
		canlendar.set(canlendar.get(Calendar.YEAR), canlendar.get(Calendar.MONTH), 1, 0, 0, 0);
		date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFomat);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 获取月份最后一天
	 * 例如：2018-12-31
	 * @param year 年 (2018)
	 * @param month 月 (12)
	 * @return
	 */
	public static String geMonthEnd2(String year, String month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, lastDay);
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(c.getTime());
	}
	

	/**
	 * 当前时间的后n个月时间
	 * 例如：2019-05-26 11:09:55
	 * @param dateFomat n个月
	 * @return
	 */
	public static String getFrontMonth(int dateFomat) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, dateFomat);
		Date dfront = new Date();
		dfront = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String frontDate = sdf.format(dfront);
		return frontDate;
	}
	
	/**
	 * 获取一个日期是当前年份的第几周
	 * 
	 * @param date 日期 格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static int getWeekNumber(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int weekNum = 0;
		try {
			Date dates = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();// 得到日历
			calendar.setTime(dates);
			weekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return weekNum;
	}
	
	/**
	 * 获取一个日期是当前年份的第几周
	 * 
	 * @param date 日期 格式(yyyy-MM-dd)
	 * @return
	 */
	public static int getWeekNumberNew(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		int weekNum = 0;
		try {
			Date dates = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dates);
			weekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return weekNum;
	}

	/**
	 * 获取两个时间之间的周数
	 * 例如：[50, 51]
	 * @param date1 时间一 格式yyyy-MM-dd HH:mm:ss
	 * @param date2 时间二 格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static List<String> getWeekInterval(String date1, String date2) {
		List weeks = new ArrayList();
		int start = 0;
		int end = 0;
		start = getWeekNumber(date1);
		end = getWeekNumber(date2);
		int length = end - start;
		String w = "";
		for (int i = start; i <= end; i++) {
			w = "" + i;
			weeks.add(w);
		}
		return weeks;
	}
	

	/**
	 * 获取两个时间之间的周数
	 * 例如：[50, 51]
	 * @param date1 时间一 格式yyyy-MM-dd
	 * @param date2 时间二 格式yyyy-MM-dd
	 * @return
	 */
	public static List<String> getWeekIntervalNew(String date1, String date2) {
		List weeks = new ArrayList();
		int start = 0;
		int end = 0;
		start = getWeekNumberNew(date1);
		end = getWeekNumberNew(date2);
		int length = end - start;
		String w = "";
		for (int i = start; i <= end; i++) {
			w = "" + i;
			weeks.add(w);
		}
		return weeks;
	}
	

	/**
	 * 获取一个时间的年份
	 * 
	 * @param date 时间 格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static int getYear(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year = 0;
		try {
			Date dt = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt);
			year = calendar.get(1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return year;
	}
	
	/**
	 * 获取一个时间的年份(YYYY) 不带时分秒
	 * 
	 * @param date 时间 格式yyyy-MM-dd
	 * @return
	 */
	public static int getYearNew(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int year = 0;
		try {
			Date dt = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt);
			year = calendar.get(Calendar.YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return year;
	}

	/**
	 * 获取一个时间的月份(mm)
	 * 
	 * @param date 时间 格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static int getMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int month = 0;
		try {
			Date dt = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt);
			month = calendar.get(calendar.MONTH) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * 获取一个时间的月份(mm) 不带时分秒
	 * 
	 * @param date 时间 格式(yyyy-MM-dd)
	 * @return
	 */
	public static int getMonthNew(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int month = 0;
		try {
			Date dt = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt);
			month = calendar.get(calendar.MONTH) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * 获取两个时间之间的月数
	 * 例如：[5, 6, 7, 8, 9, 10, 11]
	 * 时间一<时间二
	 * @param date1 时间一 格式(yyyy-MM-dd HH:mm:ss)
	 * @param date2 时间二 格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static List<String> getMonthInterval(String date1, String date2) {
		List months = new ArrayList();
		int start = 0;
		int end = 0;
		start = getMonth(date1);

		end = getMonth(date2);
		String w = "";
		for (int i = start; i <= end; i++) {
			w = "" + i;
			months.add(w);
		}
		return months;
	}


	/**
	 * 获取两个时间之间的月数 能够跨年运算
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static List<String> getMonthIntervalNew(String date1, String date2) {
		List months = new ArrayList();
		int startMonth = 0;// 开始时间月份
		int endMonth = 0;// 结束时间月份
		int startYear = 0;// 开始时间年份
		int endYear = 0;// 结束时间年份
		startMonth = getMonthNew(date1);
		startYear = getYearNew(date1);
		endMonth = getMonthNew(date2);
		endYear = getYearNew(date2);
		int monthsLent = (endYear - startYear) * 12 + (endMonth - startMonth);
		String w = "";
		for (int i = 1; i <= monthsLent; i++) {
			w = "" + i;
			months.add(w);
		}
		return months;
	}


	/**
	 * 
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws Exception
	 */
	/*public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
		List months = new ArrayList();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		Calendar curr = min;
		while (curr.before(max)) {
			months.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		return months;
	}*/

	
	/**
	 * 获取两个时间之间的年份
	 * 例如：[2015, 2016, 2017, 2018]
	 * @param date1 时间一 格式(yyyy-MM-dd HH:mm:ss)
	 * @param date2 时间二 格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static List<String> getYearInterval(String date1, String date2) {
		List months = new ArrayList();
		int start = 0;
		int end = 0;
		start = getYear(date1);
		end = getYear(date2);
		String w = "";
		for (int i = start; i <= end; i++) {
			w = "" + i;
			months.add(w);
		}
		return months;
	}
	

	/**
	 * 获取本周开始时间(精确到时分秒)
	 * 例如：2018-12-24 00:00:00
	 * @return
	 */
	public static String getWeekStartTime() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			dayOfWeek += 7;
		}

		cal.add(Calendar.DATE, 2 - dayOfWeek);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
	}
	
	/**
	 * 获取本月开始时间(精确到时分秒)
	 * 例如：2018-12-01 00:00:00
	 * @return
	 */
	public static String getMonthStartTime() {
		Calendar cal = Calendar.getInstance();

		cal.set(cal.get(1), cal.get(Calendar.MONTH), 1, 0, 0, 0);

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
	}
	
	/**
	 * 获取本年开始时间(精确到时分秒)
	 * 例如：2018-01-01 00:00:00
	 * @return
	 */
	public static String getYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
	}
	

	/**
	 * 上周星期一日期(yyyy-MM-dd)
	 * 例如：2018-12-17
	 * @return
	 */
	public static String getLastWeekFristDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	/**
	 * 上周星期天日期（yyyy-MM-dd）
	 * 例如：2018-12-23
	 * @return
	 */
	public static String getLastWeekLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0 * 7);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	/**
	 * 获取本季度第一天日期 格式(yyyy-MM-dd)
	 * 
	 * @return
	 */
	public static String getCurrentQuarterStartTime() {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONDAY) + 1;
		if ((currentMonth >= 1) && (currentMonth <= 3))
			cal.set(Calendar.MONDAY, 0);
		else if ((currentMonth >= 4) && (currentMonth <= 6))
			cal.set(Calendar.MONDAY, 3);
		else if ((currentMonth >= 7) && (currentMonth <= 9))
			cal.set(Calendar.MONDAY, 4);
		else if ((currentMonth >= 10) && (currentMonth <= 12))
			cal.set(Calendar.MONDAY, 9);
		cal.set(Calendar.DATE, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	/**
	 * 获取今年第一天日期(yyyy-MM-dd)
	 * 
	 * @return
	 */
	public static String getCurrentYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONDAY, 0);
		cal.set(Calendar.DATE, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	/**
	 * 获取前n天日期(yyyy-MM-dd)
	 * @param datys n天
	 * @return
	 */
	public static String getYseterdayTime(int datys) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -datys);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	/**
	 * 更改时间格式 转换成上下午
	 * @param date
	 * @param sdf
	 * @return
	 */
	public static String date2String(Date date, SimpleDateFormat sdf) {
		try {
			return sdf.format(date);
		} catch (Exception e) {
		}
		return sdf.format(new Date());
	}
	/**
	 * 两个时间差 格式为(0时0分4秒)
	 * @param time1 时间一 格式(yyyy-MM-dd HH:mm:ss)
	 * @param time2 时间二 格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String calculate2Time(String time1, String time2) {
		if (StringUtils.isEmpty(time1)) {
			return null;
		}
		if (StringUtils.isEmpty(time2)) {
			return null;
		}
		Date d1 = fomatDate4Time(time1);
		Date d2 = fomatDate4Time(time2);
		long seconds = d2.getTime() - d1.getTime();
		return secondToString(seconds);
	}
	
	/**
	 * 毫秒转换成时分秒
	 * 
	 * @param milliseconds
	 * @return
	 */
	public static String secondToString(long milliseconds) {
		if(milliseconds<0){
			milliseconds= Math.abs(milliseconds);
			long secondFormat = milliseconds / 1000L;
			int hour = (int) (secondFormat / 3600L);
			int minute = (int) (secondFormat % 3600L / 60L);
			int second = (int) (secondFormat % 60L);
			return "-"+hour + "时" + minute + "分" + second + "秒";
			
		}
		long secondFormat = milliseconds / 1000L;
		int hour = (int) (secondFormat / 3600L);
		int minute = (int) (secondFormat % 3600L / 60L);
		int second = (int) (secondFormat % 60L);
		return hour + "时" + minute + "分" + second + "秒";
	}

	/**
	 * 验证字符串是否是合法日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static boolean validIsDate(String date) {
		try {
			sdfDay.parse(date);
			return true;
		} catch (ParseException e) {
		}
		return false;
	}

	/**
	 * 获取当月之内的所有日期
	 * 
	 * @param thisMonth
	 * @param endDate
	 * @return
	 */
/*	public static List<String> getBetweenDates(Date thisMonth, String endDate) {
		PageData weekDays = new PageData();
		weekDays.put(Calendar.SUNDAY, "日");
		weekDays.put(Calendar.MONDAY, "一");
		weekDays.put(Calendar.TUESDAY, "二");
		weekDays.put(Calendar.WEDNESDAY, "三");
		weekDays.put(Calendar.THURSDAY, "四");
		weekDays.put(Calendar.FRIDAY, "五");
		weekDays.put(Calendar.SATURDAY, "六");
		List dates = new ArrayList();

		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(thisMonth);
		Integer weekDay = Integer.valueOf(tempStart.get(7));

		dates.add(new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(thisMonth.getTime())) + "("
				+ weekDays.get(weekDay) + ")");
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(fomatDate(endDate));
		while (tempStart.before(tempEnd)) {
			dates.add(new SimpleDateFormat("yyyy-MM-dd").format(tempStart.getTime()) + "("
					+ weekDays.get(Integer.valueOf(tempStart.get(Calendar.DAY_OF_WEEK))) + ")");
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return dates;
	}*/
}