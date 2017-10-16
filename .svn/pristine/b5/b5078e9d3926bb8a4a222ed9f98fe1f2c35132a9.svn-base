package com.fh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private final static SimpleDateFormat daySingle = new SimpleDateFormat("dd");

    /**
     * 获取YYYY格式
     * 
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     * 
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     * 
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }
    
    //根据日期返回天
    public static String getDayByDate(String date) {
        return daySingle.format(fomatDate(date));
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * 
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }
    /**
     * 转化成时间格式
     * @param date
     * @return
     * @throws ParseException 
     */
    public static Date getTime(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if (StringUtils.isEmpty(date)) {
			return null;
		}
    	return sdf.parse(date);
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式 YYMMDD c传入的需要转换的日期 type 1表示YYMMDD +" 23:59:59";
     * 2表示YYMMDD +" 00:00:00";
     * 
     * @return
     * @throws ParseException
     */
    public static Date getyyyymmddhhmmss(String YYMMDD, int type) throws ParseException {
    	if (StringUtils.isEmpty(YYMMDD)) {
			return null;
		}
        String yyyymmddhhmmss = "";
        if (type == 1) {
            yyyymmddhhmmss = YYMMDD + " 00:00:00";
        } else if (type == 2) {
            yyyymmddhhmmss = YYMMDD + " 23:59:59";
        } else {
            return null;
        }

        return sdfTime.parse(yyyymmddhhmmss);
    }

    /**
     * 取得现在的日期，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @return 返回格式化的日期字符串
     */
    public static String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date Now = new Date();
        return formatter.format(Now);
    }

    /**
     * @Title: compareDate
     * @Description: (日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * <p>
     * Description: 得到几天前的时间
     * </p>
     * 
     * @param d 日期
     * @param day 天数
     * @return Date 计算后的日期
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 格式化
     * */
    public static String formateDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 格式化 在当前日期是加一天
     * */
    @SuppressWarnings("static-access")
    public static String formateDateAddOne(Date date, String pattern) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);
        Date  date1 = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date1);
        return dateString;
    }
    
    /**
     * <p>
     * Description: 得到几天前的时间
     * </p>
     * 
     * @param d 日期
     * @param day 天数
     * @return Date 计算后的日期
     */
    public static String formateDateSub(Date d, int day) {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return fm.format(now.getTime());
    }

    /**
     * 格式化日期
     * 
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     * 
     * @return
     */
    public static Date fomatDate(String date, String formatter) {
        DateFormat fmt = new SimpleDateFormat(formatter);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Date fomatDateByInterface(String date, String formatter) throws ParseException {
        DateFormat fmt = new SimpleDateFormat(formatter);
        return fmt.parse(date);
    }
    
    /**
     * 格式化日期
     * 
     * @return
     */
    public static String fomatDateStr(String date, String formatter) {
        DateFormat fmt = new SimpleDateFormat(formatter);
        try {
            return fmt.format(fmt.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     * 
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     * 
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     * 
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * @param date 日期
     * @return 获取当前日期所在月的第一天
     */
    public static String getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdfDay.format(calendar.getTime());
    }

    /**
     * 得到n天之后是周几
     * 
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * <p>
     * Description: 得到传入日期那天的开始时间 （YYYY-MM-DD 00:00:00）
     * </p>
     * 
     * @param date 日期
     * @throws ParseException
     */
    public static Date formateDateFirst(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date) + " 00:00:00";
        return sdf.parse(dateString);
    }

    /**
     * <p>Description: 根据传入的字符日期将当前的时分秒加上</p>
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date formatDateHMS(String dateStr) throws ParseException {
        if(Tools.isEmpty(dateStr));
        String nowStr = sdfTime.format(new Date());
        Date date = sdfTime.parse(dateStr + nowStr.substring(10, 19));
        return date;
    }
    
    /** 
     * 获取某月的最后一天 
     * @Title:getLastDayOfMonth 
     * @Description: 
     * @param:@param year 
     * @param:@param month 
     * @param:@return 
     * @return:String 
     * @throws 
     */  
    public static String getLastDayOfMonth(int year,int month)  
    {  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置月份  
        cal.set(Calendar.MONTH, month-1);  
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String lastDayOfMonth = sdf.format(cal.getTime());  
          
        return lastDayOfMonth;  
    }  
    
    public static void main(String[] args) {
        String fmdate = "2017-04-30";
        System.out.println(formateDateAddOne(fomatDate(fmdate), "yyyy-MM-dd"));
        
        System.out.println();
    }
    
}
