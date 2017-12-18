package com.ivymei.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类，提供有关日期操作方面的方法。
 */

public class DateUtil {
	public static final int PROMOTION_STATUS_PAST = 0;
	public static final int PROMOTION_STATUS_RUNNING = 1;
	public static final int PROMOTION_STATUS_NO_RUNING = 2;

	/**
	 * 时间格式
	 */
	public final static String TIME_FORMAT = "HH:mm:ss:SS";

	/**
	 * 时间格式
	 */
	public final static String TIME_FORMAT_2 = "HH:mm";

	/**
	 * 缺省短日期格式
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 月份日期格式
	 */
	public final static String DEFAULT_SHORT_DATE_MONTH_FORMAT = "yyyy-MM";

	/**
	 * yyyy-MM-dd HH:mm:ss格式数据。
	 */
	public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm格式数据。
	 */
	public final static String DEFAULT_DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm";

	/**
	 * yyyy-MM-dd格式数据。
	 */
	public final static String DATE_ONLY_FORMAT = "yyyy-MM-dd";
	/**
	 * yyyyMMdd格式数据。
	 */
	public final static String DATE_ONLY_FORMAT_2 = "yyyyMMdd";
	/**
	 * 缺省短日期格式
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT_ZH = "yyyy年M月d日";

	/**
	 * 缺省长日期格式
	 */
	public final static String DEFAULT_LONG_DATE_FORMAT = DEFAULT_SHORT_DATE_FORMAT + " " + TIME_FORMAT;

	/**
	 * Java能支持的最小日期字符串（yyyy-MM-dd）。
	 */
	public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

	/**
	 * Java能支持的最小日期字符串（yyyy-MM-dd HH:mm:ss:SS）。
	 */
	public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";

	/**
	 * Java能支持的最小的Timestamp。
	 */
	public final static Timestamp JAVA_MIN_TIMESTAMP = convertStrToTimestamp(JAVA_MIN_LONG_DATE_STR);

	/**
	 * 比较当前时间是否在beginDate与endDate两个日期之间。
	 * @param beginDate 开始时间
	 * @param endDate 结束时间。
	 * @return 当前时间是否在此之间。
	 */
	public static boolean checkCurDateBetween(String beginDate, String endDate) {
		long beginDateTime = convertDateStrToMilliSeconds(beginDate, DEFAULT_DATE_TIME_FORMAT);
		long endDateTime = convertDateStrToMilliSeconds(endDate, DEFAULT_DATE_TIME_FORMAT);
		long curDateTime = System.currentTimeMillis();

		if (curDateTime >= beginDateTime && curDateTime <= endDateTime) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前日期的上一周星期一的日期。注意只返回yyyy-MM-dd格式的数据。
	 * @return 获取当前日期的上一周星期一的日期
	 */
	public static String getMondayDateForLastWeek() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获取当前日期的上一周星期日的日期。注意只返回yyyy-MM-dd格式的数据。
	 * 
	 * @return 返回上一周星期日的日期。
	 */
	public static String getSundayDateForLastWeek() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}


	public static String formatDate(String dateStr, String formatStyle) {
		Date date = convertStrToDate(dateStr, formatStyle);
		dateStr = convertDateToStr(date, formatStyle);
		return dateStr;
	}

	/**
	 * 取得指定日期所在周的第一天
	 * @param date 指定日期
	 * @return 第一天日期。
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在月的第一天
	 * @param date 指定日期
	 * @return 第一天日期。
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		cDay.set(Calendar.DAY_OF_MONTH, 1);
		return cDay.getTime();
	}

	/**
	 * 取得指定日期所在年的第一天
	 * @param date 指定日期
	 * @return 第一天日期。
	 */
	public static Date getFirstDayOfYear(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.clear();
		Integer year = Integer.parseInt(convertDateToStr(date, "yyyy"));
		cDay.set(Calendar.YEAR, year);
//		cDay.setTime(date);
//		cDay.set(Calendar.DAY_OF_YEAR, 1);
		return cDay.getTime();
	}

	/**
	 * 取得指定日期所在月的最后一天
	 * @param date 指定日期
	 * @return 最后一天日期。
	 */
	public static Date getLastDayOfYear(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.clear();
		Integer year = Integer.parseInt(convertDateToStr(date, "yyyy"));
		cDay.set(Calendar.YEAR, year);
		cDay.roll(Calendar.DAY_OF_YEAR, -1);
		return cDay.getTime();
	}

	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 取得指定日期所在月的最后一天
	 * @param date 指定日期
	 * @return 最后一天日期。
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cDay.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 * @param date 指定日期
	 * @return 返回最后一天的日期。
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 获取指定日期在当年中的所在周数。
	 * 
	 * @param dateStr  年月日 时分秒。
	 * @return 返回当年的所在周数
	 */
	public static int getWeekOfYearByDate(String dateStr) {
		Calendar calendar = Calendar.getInstance();// new GregorianCalendar();
		Date date = DateUtil.convertStrToDate(dateStr, DateUtil.DEFAULT_DATE_TIME_FORMAT);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 把字符串转换为Timestamp类型，对于短日期格式，自动把时间设为系统当前时间。
	 * @param dateStr 指定日期
	 * @return Timestamp 转换的Timestamp
	 * @see #convertStrToTimestamp(String,boolean)
	 */
	public static Timestamp convertStrToTimestamp(String dateStr) {
		return convertStrToTimestamp(dateStr, false);
	}


	/**
	 * 把字符串转换为Timestamp类型。
	 * 
	 * @param dateStr
	 *            - 日期字符串，只支持"yyyy-MM-dd"和"yyyy-MM-dd HH:mm:ss:SS"两种格式。
	 *            如果为"yyyy-MM-dd"，系统会自动取得当前时间补上。
	 * @param addZeroTime
	 *            - 当日期字符串为"yyyy-MM-dd"这样的格式时，addZeroTime为true表示
	 *            用0来设置HH:mm:ss:SS，否则用当前Time来设置。
	 * @return Timestamp
	 */
	private static Timestamp convertStrToTimestamp(String dateStr, boolean addZeroTime) {
		if (dateStr == null) {
			return null;
		}

		String dStr = dateStr.trim();
		if (dStr.indexOf(" ") == -1) {
			if (addZeroTime) {
				dStr = dStr + " 00:00:00:00";
			} else {
				dStr = dStr + " " + getCurrDateStr(DateUtil.TIME_FORMAT);
			}
		}

		Date utilDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_LONG_DATE_FORMAT);

		try {
			utilDate = simpleDateFormat.parse(dStr);
		} catch (Exception ex) {
			throw new RuntimeException("DateUtil.convertStrToTimestamp(): " + ex.getMessage());
		}

		return new Timestamp(utilDate.getTime());
	}

	/**
	 * 得到系统当前时间的Timestamp对象
	 * 
	 * @return 系统当前时间的Timestamp对象
	 */
	public static Timestamp getCurrTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * <p>
	 * 取得当前日期，并将其转换成格式为"dateFormat"的字符串 例子：假如当前日期是 2003-09-24 9:19:10，则：
	 * 
	 * <pre>
	 * getCurrDateStr("yyyyMMdd")="20030924"
	 * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
	 * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param dateFormat
	 *            String 日期格式字符串
	 * @return String
	 */
	public static String getCurrDateStr(String dateFormat) {
		return convertDateToStr(new Date(), dateFormat);
	}

	/**
	 * 将日期类型转换成指定格式的日期字符串
	 * 
	 * @param date
	 *            待转换的日期
	 * @param dateFormat
	 *            日期格式字符串
	 * @return String
	 */
	public static String convertDateToStr(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 将指定格式的字符串转换成日期类型
	 * 
	 * @param date
	 *            待转换的日期字符串
	 * @param dateFormat
	 *            日期格式字符串
	 * @return Date
	 */
	public static Date convertStrToDate(String dateStr, String dateFormat) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			throw new RuntimeException("DateUtil.convertStrToDate():" + e.getMessage());
		}
	}

	/**
	 * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
	 * 
	 * @param datepart
	 *            两位的格式字符串，yy表示年，MM表示月，dd表示日
	 * @param startdate
	 *            开始日期
	 * @param enddate
	 *            结束日期
	 * @return double 如果enddate>startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
	 */
	public static double dateDiff(String datepart, Date startdate, Date enddate) {
		if (datepart == null || datepart.equals("")) {
			throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
		}

		double days = (double) (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);

		if (datepart.equals("yy")) {
			days = days / 365;
		} else if (datepart.equals("MM")) {
			days = days / 30;
		} else if (datepart.equals("dd")) {
			return days;
		} else {
			throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
		}
		return days;
	}

	/**
	 * 把日期对象加减年、月、日后得到新的日期对象
	 * 
	 * @param depart
	 *            年、月、日、时、分(yy\MM\dd\hh\mm)
	 * @param number
	 *            加减因子
	 * @param date
	 *            需要加减年、月、日的日期对象
	 * @return Date 新的日期对象
	 */
	public static Date addDate(String datepart, int number, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (datepart.equals("yy")) {
			cal.add(Calendar.YEAR, number);
		} else if (datepart.equals("MM")) {
			cal.add(Calendar.MONTH, number);
		} else if (datepart.equals("dd")) {
			cal.add(Calendar.DATE, number);
		} else if (datepart.equals("hh")) { // 新加设置时间的方法
			cal.add(Calendar.HOUR, number);
		} else if (datepart.equals("mm")) { // 新加设置分钟的方法
			cal.add(Calendar.MINUTE, number);
		} else {
			throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值：" + datepart);
		}

		return cal.getTime();
	}

	/**
	 * 将普通时间 格式的字符串转化成unix时间戳
	 * 
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static long convertDateStrToUnixTimeStamp(String dateStr, String dateFormat) {
		if (StringUtil.isNullOrBlank(dateStr)) {
			return 0;
		}
		long timeStamp = DateUtil.convertStrToDate(dateStr, dateFormat).getTime();
		return timeStamp / 1000;
	}

	/**
	 * 将普通时间 格式的字符串转化成unix时间戳 单位为毫秒
	 * 
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static long convertDateStrToMilliSeconds(String dateStr, String dateFormat) {
		if (StringUtil.isNullOrBlank(dateStr)) {
			return 0;
		}
		long timeStamp = DateUtil.convertStrToDate(dateStr, dateFormat).getTime();
		return timeStamp;
	}

	public static Date convertUnixTimeStampToDate(long timeStamp) {
//		if (timeStamp == 0) {
//			return null;
//		}
		Long timestamp = Long.parseLong(timeStamp + "") * 1000;
		Date date = new Date();
		date.setTime(timestamp);
		return date;
	}

	/**
	 * 将unix时间戳转化成普通时间 格式的字符串
	 * 
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static String convertUnixTimeStampToDateStr(long timeStamp, String dateFormat) {
		if (timeStamp == 0) {
			return "";
		}
		Long timestamp = Long.parseLong(timeStamp + "") * 1000;
		String dateStr = DateUtil.convertDateToStr(new Date(timestamp), dateFormat);
		return dateStr;
	}

	/**
	 * 获取当前unix时间的秒数。
	 * 
	 * @return
	 */
	public static long getCurrentUnixTimeSecond() {
		return getCurrTimestamp().getTime() / 1000;
	}

	/**
	 * 0为已经过期了，1为进行中。2为尚未进行。 如果两个日期都为null，则认为正在进行中。。
	 * 
	 * @return
	 */
	public static int validCurrDateInRange(String startDate, String endDate) {
		if (StringUtil.isNullOrBlank(startDate) && StringUtil.isNullOrBlank(endDate)) {
			throw new IllegalArgumentException("抱歉，开始日期与结束日期不能同时为空！");
		}

		long currDateSecond = getCurrentUnixTimeSecond();

		long startDateSecond = -1;
		long endDateSecond = -1;

		if (!StringUtil.isNullOrBlank(startDate)) {
			startDateSecond = convertDateStrToUnixTimeStamp(startDate, DEFAULT_DATE_TIME_FORMAT);
		}
		if (!StringUtil.isNullOrBlank(endDate)) {
			endDateSecond = convertDateStrToUnixTimeStamp(endDate, DEFAULT_DATE_TIME_FORMAT);
		}

		if (startDateSecond < 1 && endDateSecond < 1) {
			throw new IllegalArgumentException("抱歉，参数不正确！");
		}

		// 当前日期与两个时间进行相减对比。
		long startDateCha = currDateSecond - startDateSecond;
		long endDateCha = endDateSecond - currDateSecond;

		// 判断参数是否传null过来，如果不是传null进来，则判断传值的合法性。
		if (startDateSecond > 0 && endDateSecond > 0) {
			long temp = endDateSecond - startDateSecond;
			if (temp < 0) {
				throw new IllegalArgumentException("抱歉，数据不合理，开始时间超过了结束时间.");
			} else if (startDateCha >= 0 && endDateCha >= 0) {
				return PROMOTION_STATUS_RUNNING;// 正在进行中。
			} else if (startDateCha < 0 && endDateCha >= 0) {
				return PROMOTION_STATUS_NO_RUNING;// 尚未进行
			}
		} else if (startDateSecond > 0) {

			if (startDateCha >= 0) {
				return PROMOTION_STATUS_RUNNING;
			} else {
				return PROMOTION_STATUS_NO_RUNING;
			}
		} else if (endDateSecond > 0) {

			if (endDateCha >= 0) {
				return PROMOTION_STATUS_RUNNING;
			}
		}

		return PROMOTION_STATUS_PAST;

	}

	/**
	 * 当天的开始时间
	 * 
	 * @return
	 */
	public static String getCurDateStart() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return formatter.format(new Date());
	}

	/**
	 * 当天的结束时间
	 * 
	 * @return
	 */
	public static String getCurDateEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return formatter.format(new Date());
	}

	/**
	 * 计算2个日期之间的间隔的周期数
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @param msPeriod
	 *            单位周期的毫秒数
	 * @return 周期数
	 */
	public static long getPeriodNum(Date start, Date end, long msPeriod) {
		return getIntervalMs(start, end) / msPeriod;
	}

	/**
	 * 计算2个日期之间的毫秒数
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 毫秒数
	 */
	public static long getIntervalMs(Date start, Date end) {
		return end.getTime() - start.getTime();
	}

	/**
	 * 将小时数转换成天数
	 * 
	 * @param hour
	 * @return
	 */
	public static String convertHourToDayStr(int hour) {
		if (hour < 24) {
			return hour + "小时";
		}

		int day = hour / 24;
		int h = hour % 24;
		if (h == 0) {
			return day + "天";
		}
		return day + "天" + h + "小时";
	}
	
	/**
	 * 将日期转换成默认的yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param hour
	 * @return
	 */
	public static String convertDateToString(Date date) {
		return DateUtil.convertDateToStr(date, DateUtil.DEFAULT_DATE_TIME_FORMAT);
	}
	
	
	/**
	 * 输入时间或字符串，查看该时间是星期几
	 * 1 表示 星期一 
	 * 2 表示 星期二
	 * .. 
	 * 7 表示 星期天 
	 * 0 表示输入时间错误
	 * */
	public static int weeknumOfDate(Date date){
		if(date == null){
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weeknum = c.get(Calendar.DAY_OF_WEEK);
		if(weeknum == 1){
			return 7;
		}else{
			return weeknum-1;
		}
	}
	public static int weeknumOfDate(String dateStr){
		if(dateStr == null || dateStr.trim().length() == 0){
			return 0;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date date = null;
		try {
			sdf.applyPattern(DEFAULT_SHORT_DATE_FORMAT);  // 尝试以 yyyy-MM-dd 解析
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			try {
				sdf.applyPattern(DEFAULT_DATE_TIME_FORMAT); // 尝试以 yyyy-MM-dd HH:mm:ss 解析
				date = sdf.parse(dateStr);
			} catch (ParseException e1) {
				return 0;
			}
		}
		return weeknumOfDate(date);
	}
	
	
	
	/**
	 * 当前时间离结束时间还差多少时间
	 * 
	 * @param endDate
	 * 			结束时间
	 * @return
	 */
    public static String timeBeforeEnd(Date endDate) {
        if (null == endDate) return null;
        long now = Calendar.getInstance().getTimeInMillis();
        long duration = endDate.getTime() - now;
        long day = duration / 86400000;
        long temp = duration % 86400000;
        long hour = temp / 3600000;
        long minute = temp % 3600000 / 60000;
        String result = "剩余";
        if (day > 0) {
            result += day + "天";
        } else if (hour > 0) {
            result += hour + "小时";
        } else if (minute > 0) {
            result += minute + "分钟";
        } else {
            result += "1分钟";
        }
        return result;
    }
    
    /**
     * 通过时间阈值，返回当前阈值前后的时间
     * 		如：-50  秒	
     * 		返回：相对于指定时间 50  秒前的时间
     * 
     * @param dateTime
     * 				指定时间
     * @param thresholdValue
     * 				阈值数值
     * @param thresholdUnit
     * 				阈值单位
     * @return
     */
    public static Date getTimeByThreshold(Date dateTime, Integer thresholdValue, String thresholdUnit) {
    	Calendar currentCal = Calendar.getInstance();
    	currentCal.setTime(dateTime);
    	if("秒".equals(thresholdUnit)) {
    		currentCal.add(Calendar.SECOND, thresholdValue);
    	} else if("分钟".equals(thresholdUnit)) {
    		currentCal.add(Calendar.MINUTE, thresholdValue);
    	} else if("小时".equals(thresholdUnit)) {
    		currentCal.add(Calendar.HOUR, thresholdValue);
    	} else if("天".equals(thresholdUnit)) {
    		currentCal.add(Calendar.DATE, thresholdValue);
    	}
    	return currentCal.getTime();
    }
    
    /**
	 * 获取两个时间的毫秒差
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static Long getMillisDiff(Date beginTime, Date endTime) {
		if(beginTime==null || endTime==null)
			return 0l;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(beginTime);
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(endTime);
		
		long beginMils = c1.getTimeInMillis();
		long endMils = c2.getTimeInMillis();
		Long millisDiff = endMils - beginMils;
		return millisDiff;
	}
	
	public static String getDateStr(Date targetTime) {
		if(targetTime==null)
			return "";
		return convertDateToString(targetTime);
	}
	
	/**
	 * 获取当前仅仅有日期 ，即到天 yyyyMMdd 
	 * 		不含时分秒的日期
	 * 
	 * @return
	 */
	public static Date getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		String dateStr = convertDateToStr(cal.getTime(), DEFAULT_SHORT_DATE_FORMAT);
		return convertStrToDate(dateStr, DEFAULT_SHORT_DATE_FORMAT);
	}
	
	
	/**
	 * 获取两个时间的小时差
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static Long getHourDiff(Date beginTime, Date endTime) {
		Long hourDiff = getMillisDiff(beginTime, endTime)/(1000*3600);
		return hourDiff;
	}
	
	/**
	 * 获取两个时间的分钟差
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static Long getMinuteDiff(Date beginTime, Date endTime) {
		Long minuteDiff = getMillisDiff(beginTime, endTime)/(1000*60);
		return minuteDiff;
	}
	
	/**
	 * 获取两个时间的秒差
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static Long getSecondDiff(Date beginTime, Date endTime) {
		Long secondDiff = getMillisDiff(beginTime, endTime)/1000;
		return secondDiff;
	}
    
	/**
	 * 获取指定时间 前后几小时后的时间
	 * 
	 * @param dateTime
	 * 				指定的 时间
	 * @param hours
	 * 				指定前后小时数
	 * @return
	 */
	public static Date getTimeByHour(Date dateTime, Integer hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTime);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();
	}
	
	
	/**
	 * 格式化两个时间
	 * 		比如：2015-09-10 10:08    2015-09-10 11:52
	 * 		格式化后： 10:08--11:52
	 */
	public static String formatHourMinPatter(Date startTime, Date endTime) {
		return convertDateToStr(startTime, "HH:mm") + "-" + convertDateToStr(endTime, "HH:mm");
	}
	
	/**
	 * 获取当前日期是星期几<br>
	 * <br>
	 * 
	 * @param dateTime
	 *            需要判断的时间<br>
	 * @return dayForWeek 返回日期对应的星期数 <br>
	 *  比如：周一、周二、周日。。。。。<br>
	 * @Exception 发生异常<br>
	 */
	public static String getWeekName(Date dateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		
		int dayForWeek = 0;
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		}
		String weekname = "周一";
		switch (dayForWeek) {
		case 7:
			weekname = "周日";
			break;
		case 6:
			weekname = "周六";
			break;
		case 5:
			weekname = "周五";
			break;
		case 4:
			weekname = "周四";
			break;
		case 3:
			weekname = "周三";
			break;
		case 2:
			weekname = "周二";
			break;
		case 1:
			weekname = "周一";
		}
		return weekname;
	}
	
	 /**
     * 指定日期格式，判断两个日期是否相等
     * 
     * @param oneDate
     * @param twoDate
     * @param pattern
     * @return
     */
    public static boolean isEqualTwoDate(Date oneDate, Date twoDate, String pattern) {
    	if(oneDate!=null && twoDate!=null) {
    		String formatOneDate = convertDateToStr(oneDate, pattern);
        	String formatTwoDate = convertDateToStr(twoDate, pattern);
        	if(formatOneDate.equals(formatTwoDate))
        		return true;
    	}
    	return false;
    }

	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List lDate = new ArrayList();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}




	/**
	 * 获取某天的零点时间
	 * @param time long
	 * @return long
	 */
	public static Long getStartTime(Long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		Calendar startDate = new GregorianCalendar();//开始时间
		startDate.setTimeInMillis(time * 1000);
		Calendar endDate = new GregorianCalendar();//结束时间
		// 当天开始时间
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		return startDate.getTime().getTime() / 1000;
	}

	/**
	 * 获取某天的结束时间
	 * @param  time long
	 * @return long
	 */
	public static Long getEndTime(Long time){
		Calendar endDate = new GregorianCalendar();//结束时间
		endDate.setTimeInMillis(time  * 1000 );
		// 当天结束时间
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		return endDate.getTime().getTime() / 1000;
	}

    /**
     * 获取指定时间所属月份的第一天
     *
     * @param timestamp
     * @return
     */
    public static Long getFirstDayOfMonth(Long timestamp) {
		Date date = DateUtil.convertUnixTimeStampToDate(timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Long t = calendar.getTimeInMillis() / 1000;
		return t;
	}
    /**
     * 获取指定时间所属月份的第一天
     *
     * @param timestamp
     * @return
     */
    public static Long getLastDayOfMonth(Long timestamp) {
        Date date = DateUtil.convertUnixTimeStampToDate(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Long t = calendar.getTimeInMillis() / 1000;
        return t;
    }


	/**
	 * 获取指定时间的前几个月或后几个月的时间戳
	 *  1 代表后一个月 ， -1 代表前一个月
	 * @param timestamp
	 * @return
	 */
	public static Long getBeforOrAfterMonth(Long timestamp,Integer month) {
		Date date = DateUtil.convertUnixTimeStampToDate(timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,month);

		Long t = calendar.getTimeInMillis() / 1000;
		return t;
	}

	public static void main(String[] args) {
//		System.out.println(getFirstDayOfYear(new Date()).getTime());
//		System.out.println(getLastDayOfYear(new Date()).getTime());
//
//		// 获取前月的第一天
//		Date firstDayOfYear = DateUtil.getFirstDayOfYear(DateUtil.convertUnixTimeStampToDate(1483200000));
//		Integer startDate = Integer.parseInt(firstDayOfYear.getTime() / 1000 + "");
//
//		// 获取前月的最后一天
//		Date lastDayOfYear = DateUtil.getLastDayOfYear(DateUtil.convertUnixTimeStampToDate(1483200000));
//		Integer endDate = Integer.parseInt(lastDayOfYear.getTime() / 1000 + "");
//
//		System.out.println(startDate);
//		System.out.println(endDate);


			Map<String, Object> map = new HashMap<>();
			map.put("A", 1);
			map.put("B", "22");

			System.out.println(JSON.toJSONString(map, SerializerFeature.WriteNonStringKeyAsString));
	}
}