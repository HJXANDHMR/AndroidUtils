package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date operation utility class.
 *
 */

public class DateUtil {

    /**
     * English abbreviation e.g.: 2016
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * English abbreviation e.g.: 12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * English abbreviation e.g.: 1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * English abbreviation (default) e.g.: 2016-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * English full format e.g.: 2016-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * English full format e.g.: 2016-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * Full time with millisecond precision e.g.: yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * Full time with millisecond precision e.g.: yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * Chinese abbreviation e.g.: 2016年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * Chinese abbreviation e.g.: 2016年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * Chinese abbreviation e.g.: 2016年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * Chinese full format e.g.: 2016年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * Full Chinese time with millisecond precision
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }


    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }


    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }


    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }


    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }


    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }


    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }


    /**
     * Get current date string
     * @param format    format type
     * @return  formatted date string
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);

    }


    /**
     *
     * @param time current time
     * @return  formatted to seconds
     */

    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }


    /**
     *
     * @param time  current time
     * @return  current day
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }


    /**
     *
     * @param time time
     * @return time in milliseconds
     */
    // Format to milliseconds
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }


    /**
     * Add months to a date
     * @param date date
     * @param n number of months to add
     * @return   date after adding months
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();

    }


    /**
     * Add days to a date
     * @param date date
     * @param n number of days to add
     * @return   date after adding days
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();

    }


    /**
     * Get the time at a certain hour from now
     *
     * @param format time format
     * @param h hours from now e.g.: h=-1 for previous hour, h=1 for next hour
     * @return  the time at a certain hour from now
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }


    /**
     * Get timestamp
     * @return timestamp string
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }



    /**
     * Description: return month
     *
     * @param date Date
     * @return month
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * Description: return day
     *
     * @param date Date
     * @return day
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * Description: return hour
     *
     * @param date date
     * @return hour
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * Description: return minute
     *
     * @param date date
     * @return minute
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }


    /**
     * Get the default date pattern
     * @return  default format
     */
    public static String getDatePattern() {

        return FORMAT_YMDHMS;
    }


    /**
     * Return seconds
     *
     * @param date Date
     * @return seconds
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    /**
     * Extract date string using preset format
     *
     * @param strDate date string
     * @return extracted date
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());

    }


    /**
     * Description: return milliseconds
     *
     * @param date date
     * @return milliseconds
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    /**
     * Days between given date string and today using default format
     *
     * @param date date string
     * @return days between given date and today
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }


    /**
     * Extract date string using user-defined format
     *
     * @param strDate date string
     * @param pattern date format
     * @return  extracted date
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * Days between given date string and today using user-defined format
     *
     * @param date date string
     * @param format date format
     * @return  days between given date and today
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }
    
}
