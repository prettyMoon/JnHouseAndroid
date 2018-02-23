package jnhouse.topwellsoft.com.jnhouse_android.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimeUtils {
    /**
     * <p>
     * 日期时间处理
     * </p>
     * <p/>
     * <p>
     * 该类主要将日期和时间转换成 以1970年1月1日0点0分为基准准的毫秒数的长整数来表示 然后对长整数进行处理 并提供与一般日期格式的相互转换的方法
     * </p>
     */

    private static GregorianCalendar calendar = new GregorianCalendar();

    public final static String KEY_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public final static String KEY_FORMAT2 = "yyyy-MM-dd";
    public final static String KEY_FORMAT3 = "yyyy/MM/dd";

    public final static long DAY = 86400000; // 一天的毫秒数

    public final static long HOUR = 3600000; // 一小时的毫秒数

    public final static long MINUTE = 60000; // 一分钟的毫秒数

    public final static long SECOND = 1000; // 一秒钟的毫秒数

    private static String[] weekString = new String[]{"空", "星期一", "星期二",
            "星期三", "星期四", "星期五", "星期六", "星期日"};

    public static long getNowMillis() {
        GregorianCalendar now = new GregorianCalendar();
        return now.getTimeInMillis();
    }

    // 获得当前时间“yyyy-mm-dd hh:mm:ss.000”
    public static String getNow() {
        GregorianCalendar now = new GregorianCalendar();
        return getDateTime(now.getTimeInMillis());
    }

    // 获得当前时间的毫秒表示(长整型)
    public static long getCurDateMillis() {
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = df.format(now.getTime());
        return getDateMillis(sDate);
    }

    // 获得当前日期“yyyy-MM-dd”
    public static String getCurDate() {
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = df.format(now.getTime());
        return sDate;
    }

    // 提供“yyyy-mm-dd”形式的字符串到毫秒的转换
    public static long getDateMillis(String dateString) {
        String[] date = dateString.split("-");
        long dateMillis = 0;
        try {
            dateMillis = getDateMillis(Integer.parseInt(date[0]),
                    Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("输入的日期格式不正确！");
        }
        return dateMillis;
    }

    // 提供“hh:mm:ss.000”形式的字符串 转换成毫秒
    // 或“hh:mm:ss”形式的字符串 转换成毫秒
    public static long getTimeMillis(String timeString) {
        long lTimeMillis = 0l;
        try {
            int temp = 0;
            String tempStr = timeString.replace('.', ':');
            String[] time = tempStr.split(":");
            temp = Integer.parseInt(time[0]);
            lTimeMillis += temp * HOUR;
            temp = Integer.parseInt(time[1]);
            lTimeMillis += temp * MINUTE;
            temp = Integer.parseInt(time[2]);
            lTimeMillis += temp * SECOND;
            if (time.length > 3) { // 如果有毫秒
                temp = Integer.parseInt(time[3]);
                lTimeMillis += temp;
            }
        } catch (Exception ex) {
        }
        return lTimeMillis;
    }

    // 提供“yyyy-mm-dd hh:mm:ss.000”形式的字符串 转换成毫秒
    // 或“yyyy-mm-dd hh:mm:ss”形式的字符串 转换成毫秒
    public static long getDateTimeMillis(String dateTimeString) {
        String[] date = dateTimeString.split(" ");
        if (date.length < 2) { // 中间没空格
            return 0;
        }
        if (date.length > 2) { // 中间有多个空格
            date[1] = date[date.length - 1]; //
        }
        // System.out.println(date[0]);
        // System.out.println(date[1]);
        long dateL = getDateMillis(date[0]);
        long timeL = getTimeMillis(date[1]);
        return dateL + timeL;
    }

    // 提供yyyy-mm-dd hh:mm:ss形式字符串，显示距离目标时间多久
    public static String showDateRank(String timeString, String targetTime) {
        long stringLong = getDateTimeMillis(timeString);
        long targetLong = getDateTimeMillis(targetTime);
        long timeLong;
        int min;
        if (stringLong > targetLong) {
            timeLong = stringLong - targetLong;
        } else {
            timeLong = targetLong - stringLong;
        }
        min = (int) (timeLong / MINUTE);
        if (min < 1) {
            return "刚刚";
        }
        if (min < 60) {
            return min + "分钟";
        }
        min = (int) (timeLong / HOUR);
        if (min < 24) {
            return min + "小时";
        }
        min = (int) (timeLong / DAY);
        if (min < 5) {
            return min + "天";
        }
        return targetTime.substring(0, 10);
    }

    // 根据输入的整型“int yyyy”，“int mm”，“int dd”，
    // 转换成毫秒表示的时间(长整型)OK
    public static long getDateMillis(int year, int month, int day) {
        // 说明：在系统中用0，1，2,...,11表示一年中的12个月份
        // 所以转化时要将实际月份 减去 1
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        return calendar.getTimeInMillis();
    }

    // 根据输入的毫秒数，获得日期“yyyy-mm-dd”字符串
    public static String getDate(long millis) {
        String sDate = getYear(millis) + "-";
        String month = "" + getMonth(millis);
        String day = "" + getDay(millis);
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        sDate += month + "-";
        sDate += day;
        return sDate;
    }

    // 根据输入的毫秒数，获得日期“mm-dd”字符串
    public static String getMonthAndDay(long millis) {
        String sDate = "" + getMonth(millis);
        String day = "" + getDay(millis);
        if (sDate.length() == 1) {
            sDate = "0" + sDate;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        return sDate + "-" + day;
    }

    // 根据输入的毫秒数，获得日期时间“hh:mm:ss.000”字符串
    public static String getTime(long millis) {
        calendar.setTimeInMillis(millis);
        String sTime = "";
        sTime += getHour(millis) + ":";
        sTime += getMinute(millis) + ":";
        sTime += getSecond(millis) + ".";
        String strMillSec = "" + getMillSecond(millis);
        if (strMillSec.length() == 1) {
            strMillSec = "00" + strMillSec;
        } else if (strMillSec.length() == 2) {
            strMillSec = "0" + strMillSec;
        }
        sTime += strMillSec;
        return sTime;
    }

    // 根据输入的毫秒数，获得日期时间“yyyy-mm-dd hh:mm:ss.000”字符串
    public static String getDateTime(long millis) {
        calendar.setTimeInMillis(millis);
        String sDate = getDate(millis);
        String sTime = getTime(millis);
        return sDate + " " + sTime;
    }

    // 根据输入的毫秒数，获得 年
    public static int getYear(long millis) {
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR);
    }

    // 根据输入的毫秒数，获得 月
    public static int getMonth(long millis) {
        // 说明：在系统中用0，1，2,...,11表示一年中的12个月份
        // 所以要转化到实际月份时， 需要将系统的月份加 1
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH) + 1;// 系统月份加1
    }

    // 根据输入的毫秒数，获得 日
    public static int getDay(long millis) {
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DATE);
    }

    // 根据输入的毫秒数，获得 小时
    public static int getHour(long millis) {
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    // 根据输入的毫秒数，获得 分钟
    public static int getMinute(long millis) {
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MINUTE);
    }

    // 根据输入的毫秒数，获得 秒
    public static int getSecond(long millis) {
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.SECOND);
    }

    // 根据输入的毫秒数，获得 毫秒
    public static int getMillSecond(long millis) {
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MILLISECOND);
    }

    // 根据“yyyy-mm-dd”得到 "星期几",如“星期二”OK
    public static String getWeekDay(String dateString) {
        String weekDay = "";
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date givedDate = dateFormatter.parse(dateString);
            SimpleDateFormat weekFormatter = new SimpleDateFormat("E",
                    Locale.CHINESE);
            weekDay = weekFormatter.format(givedDate);
        } catch (ParseException ee) {
            ee.printStackTrace();
            return weekDay;
        }
        return weekDay;
    }

    // 根据输入的毫秒数，得到 "星期几",如“星期二”OK
    public static String getWeekDay(long date) {
        String weekDay = "";
        String dateString = getDate(date);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date givedDate = dateFormatter.parse(dateString);
            SimpleDateFormat weekFormatter = new SimpleDateFormat("E",
                    Locale.CHINESE);
            weekDay = weekFormatter.format(givedDate);
        } catch (ParseException ee) {
            ee.printStackTrace();
            return weekDay;
        }
        return weekDay;
    }

    //
    // 根据“yyyy-mm-dd”得到 整型表示的周几，如 1：(星期一)，2：(星期二)
    public static int getIntWeekDay(String dateString) {
        String sWeekDay = getWeekDay(dateString);
        int iWeekDay = 0;
        for (int i = 1; i <= 7; i++) {
            if (weekString[i].equals(sWeekDay)) {
                iWeekDay = i;
                break;
            }
        }
        return iWeekDay;
    }

    // 根据输入的毫秒数，得到 整型表示的周几，如 1：(星期一)，2：(星期二)
    public static int getIntWeekDay(long date) {
        String sWeekDay = getWeekDay(date);
        int iWeekDay = 0;
        for (int i = 1; i <= 7; i++) {
            if (weekString[i].equals(sWeekDay)) {
                iWeekDay = i;
                break;
            }
        }
        return iWeekDay;
    }

    /**
     * 获取 从某日期起第一个星期几的日期“yyyy-mm-dd” 如：得到2005-10-26以后的第一个星期一是什么日期 startDate 起始日
     * 格式“yyyy-mm-dd” weekday 要查找的星期数 如“星期四” 返回 “yyyy-mm-dd”
     */
    public static String getDateStringByDateAndWeekday(String startDate,
                                                       String weekday) {
        long datemillis = getDateMillis(startDate);
        String sWeekday = getWeekDay(datemillis);
        while (!sWeekday.equals(weekday)) {
            datemillis += DAY;
            sWeekday = getWeekDay(datemillis);
        }
        return getDate(datemillis);
    }

    /**
     * 获取 从某日期起第一个星期几的日期“yyyy-mm-dd” 如：得到2005-10-26以后的第一个星期一是什么日期 startDate 起始日
     * 格式“yyyy-mm-dd” weekday 要查找的星期数 如“星期四” 返回 long
     */
    public static long getDateMillisByDateAndWeekday(String startDate,
                                                     String weekday) {
        long datemillis = getDateMillis(startDate);
        String sWeekday = getWeekDay(datemillis);
        while (!sWeekday.equals(weekday)) {
            datemillis += DAY;
            sWeekday = getWeekDay(datemillis);
        }
        return datemillis;
    }

    public static void main(String[] args) {
        System.out.println(convertStr10(1459881828));
    }

    // 获得当前时间--zjc
    public static Date getNowTime(String format) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        GregorianCalendar gc = new GregorianCalendar();
        try {
            return sdFormat.parse(sdFormat.format(gc.getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // 获得当前时间--zjc
    public static String getNowTimeStr(String format) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        GregorianCalendar gc = new GregorianCalendar();

        return sdFormat.format(gc.getTime());
    }

    //
    public static Date getNowTime() {
        return getNowTime("yyyy-MM-dd HH:mm:ss");
    }

    //
    public static Date formatDateTime(String timeSrc, String f) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(f);
        try {
            if (timeSrc == null || timeSrc.trim().equals(""))
                return null;

            Date tmpDate = sdFormat.parse(timeSrc);
            return tmpDate;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return null;
        }
    }

    //
    public static String parseDateTime(String endDate, String f) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(f);
        if (endDate == null)
            return null;
        return sdFormat.format(endDate);
    }

    /**
     * 格式化时间到字符串
     *
     * @param srcDate 源时间
     * @param format
     * @return
     */
    public static String getTimeStr(Date srcDate, String format) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        if (srcDate == null)
            return "";

        return sdFormat.format(srcDate);
    }

    // 得到上一个月的当前日期
    public static Date getLastMonthDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date lastDate = c.getTime();
        return lastDate;
    }

    // 得到当前日期的第几天
    public static Date getLastDayDate(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, count); // 得到前几天，count为负时是前几天，为正时是后几天
        Date lastDate = calendar.getTime();
        return lastDate;
    }

    public static String parseDateTime(Date timeSrc, String f) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(f);
        if (timeSrc == null)
            return null;
        try {
            return sdFormat.format(timeSrc);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    /**
     * @author TP-wwh 获去当前日期 所对应的的 年初
     */
    public static String getBeginOfYear(String dateStr) {

        if (dateStr != null && dateStr.length() > 0) {
            return dateStr.substring(0, 3) + "-01-01";
        } else {
            return null;
        }

    }

    /**
     * @author TP-wwh 获去当前日期 所对应的的 年末
     */
    public static String getLastOfYear(String dateStr) {

        if (dateStr != null && dateStr.length() > 0) {
            int year = Integer.valueOf(dateStr.substring(0, 4)) - 1;
            return String.valueOf(year) + "-12-31";
        } else {
            return null;
        }

    }

    public static Long getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime()
                .getTime()) / (1000 * 60 * 60 * 24);
    }

    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    // 根据月份获取时间
    public static Date getTime_month(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    // 根据年份获取时间
    public static Date getTime_year(Date date, Integer years) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }

    /**
     * 获取当前日期前（后）多少分钟的日期。正数表示后几分钟，负数表示前几分钟
     *
     * @param date
     * @param shift
     * @return
     */
    public static String getDateMinShift(String date, int shift) {
        long datemillis = getDateTimeMillis(date);
        datemillis += shift * MINUTE;
        return getDateTime(datemillis);
    }

    /**
     * 获取当前日期前（后）几天的日期。正数表示后几天，负数表示前几天
     *
     * @param date
     * @param shift
     * @return
     */
    public static String getDateStringShift(String date, int shift) {
        long datemillis = getDateMillis(date);
        datemillis += shift * DAY;
        return getDate(datemillis);
    }


    /**
     * 获取当前日期前（后）几天的日期。正数表示后几天，负数表示前几天
     *
     * @param date
     * @param shift
     * @return
     */
    public static Date getDateShift(String date, int shift) {
        long datemillis = getDateMillis(date);
        datemillis += shift * DAY;
        return formatDateTime(getDate(datemillis), "yyyy-MM-dd");
    }

    /**
     * 获取当前日期前（后）几小时的日期。正数表示后几小时，负数表示前几小时
     *
     * @param date
     * @param shift
     * @return
     */
    public static Date getDateHourShift(String date, int shift) {
        long datemillis = getDateTimeMillis(date);
        datemillis += shift * HOUR;
        return formatDateTime(getDateTime(datemillis), KEY_FORMAT1);
    }

    /**
     * 获取当前日期前（后）多少分钟的日期。正数表示后几分钟，负数表示前几分钟
     *
     * @param date
     * @param shift
     * @return
     */
    public static Date getDateMinShift1(String date, int shift) {
        long datemillis = getDateTimeMillis(date);
        datemillis += shift * MINUTE;
        return formatDateTime(getDateTime(datemillis), KEY_FORMAT1);
    }

    /**
     * 获取当前日期前（后）几天的秒数。正数表示后几天，负数表示前几天
     *
     * @param date
     * @param shift
     * @return
     */
    public static long getDateMillisShift(String date, int shift) {
        long datemillis = getDateMillis(date);
        datemillis += shift * DAY;
        return datemillis;
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param fromDate 开始日
     * @param toDate   结束日
     * @param blnAbs   是否取绝对值
     * @return
     */
    public static int calcDays(Date fromDate, Date toDate, boolean blnAbs) {

        long miliSeconds1 = fromDate.getTime();
        long miliSeconds2 = toDate.getTime();

        if (blnAbs == false) {
            // 不足2天算1天
            return (int) ((miliSeconds2 - miliSeconds1) / 86400000);
        }

        // 超过1天算2天
        return (int) (Math.abs(miliSeconds2 - miliSeconds1 - 1) / 86400000) + 1;
    }

    /**
     * 计算两个日期之间的秒数
     *
     * @param fromDate 开始日
     * @param toDate   结束日
     * @return
     */
    public static int calcSeconds(Date fromDate, Date toDate) {

        long miliSeconds1 = fromDate.getTime();
        long miliSeconds2 = toDate.getTime();
        if (miliSeconds2 < miliSeconds1) {
            return -1;
        }
        return (int) ((miliSeconds2 - miliSeconds1) / 1000);
    }

    /**
     * 判断是否是闰年
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            return true;
        return false;
    }


    public static String getFirstDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(
                " 00:00:00");
        return str.toString();

    }

    /**
     * 当月最后一天
     *
     * @return
     */
    public static String getLastDay(Date theDate) {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //
        // String s = df.format(theDate);
        // StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        // return str.toString();
        if (theDate == null)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        int MaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay);
        return getTimeStr(c.getTime(), "yyyy-MM-dd");
    }

    /**
     * 比较两个日期之间的大小
     *
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }

    /**
     * 　　* get months interval between two Date 　　* @param date1 　　* @param
     * date2 　　* @return
     */
    public static int getMonths(Date date1, Date date2) {
        int iMonth = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);
            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);
            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
                    .get(Calendar.YEAR)) {
                iMonth = (objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
                        .get(Calendar.YEAR))
                        * 12
                        + objCalendarDate2.get(Calendar.MONTH)
                        - objCalendarDate1.get(Calendar.MONTH);
            } else {
                iMonth = objCalendarDate2.get(Calendar.MONTH)
                        - objCalendarDate1.get(Calendar.MONTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * 　　* get years interval between two Date 　　* @param date1 　　* @param date2
     * 　　* @return
     */
    public static int getYears(Date date1, Date date2) {
        int iYear = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);
            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);
            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            iYear = (objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
                    .get(Calendar.YEAR));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iYear;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        return day + "天" + hour + "小时" + min + "分";
    }

    // 根据身份证获取年龄
    public static int getAgeByccId(String ccId) {
        if (ccId != null) {
            ccId = ccId.replaceAll("\\s*", "");
            if (ccId.length() == 18) {
                String year = ccId.substring(6, 10);
                String month = ccId.substring(10, 12);
                String day = ccId.substring(12, 14);
                return getYears(
                        formatDateTime(year + "-" + month + "-" + day,
                                KEY_FORMAT2), getNowTime(KEY_FORMAT2));
            } else {
                return 0;
            }
        } else {
            return 0;
        }

    }

    // 根据10位数时间戳返回：今天、昨天、日期(MM-dd)
    public static String convertStr10(long date_time) {
        int today = (int) (getDateMillis(getCurDate()) / 1000);
        int ytday = today - 86400;
        if (date_time >= today) {
            return "今天" + today;
        }
        if (date_time >= ytday) {
            return "昨天" + ytday;
        }
        return getMonthAndDay(date_time * 1000);
    }
}
