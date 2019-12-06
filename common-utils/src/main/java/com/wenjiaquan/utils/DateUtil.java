package com.wenjiaquan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**   
* @Title: DateUtil.java 
* @Package com.bawei.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月6日 下午2:21:50 
* @version V1.0   
*/
public class DateUtil {
	private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static int getAge(Date birthDate) {
		Calendar calendar=Calendar.getInstance();
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH);
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(birthDate);
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
		int age=nowYear-birthYear;
		if(birthMonth>nowMonth) {
			age--;
		}
		if(birthMonth==nowMonth && birthDay>nowDay) {
			age--;
		}
		return age;
	}
	public static int getAge(String birthday) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate=null;
		try {
			birthDate=simpleDateFormat.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAge(birthDate);
	}
	//获取开始日期和结束日期之间有多少天
	
	public static int getDayNum(Date date1, Date date2) {
		Long dayTime = 1000 * 60 * 60 * 24L;
		long startTime = date1.getTime();
		long endTime = date2.getTime();
		Double dayNum = Math.abs((endTime - startTime) / dayTime * 1.0);
		return dayNum.intValue()+1;
	}
	public static int getDayNum(Date date) {
		Date date2 = new Date();
		return getDayNum(date, date2);
	}
	
	/**
	 * 验证指定日期是否为今天
	 */
	public static boolean isToday(Date theDate) {
		Date date = new Date();
		String format1 = format.format(date);
		String format2=format.format(theDate);
		return format1.equals(format2);
	}
	/**
	 * 判断指定日期是否在本周
	 */
	public static boolean isInweek(Date theDate) {
		Calendar c = Calendar.getInstance();
		int nowYear = c.get(Calendar.YEAR);
		//一年中的第几个星期
		int nowWeek = c.get(Calendar.WEEK_OF_YEAR);
		c.setTime(theDate);
		int theYear = c.get(Calendar.YEAR);
		int theWeek = c.get(Calendar.WEEK_OF_YEAR);
		return nowYear==theYear && nowWeek==theWeek;
	}
	/**
	 * 获取指定日期月份的第一天
	 */
	public static Date getFirstDateInMonth(Date theDate) {
		Calendar c=Calendar.getInstance();
		c.setTime(theDate);
		c.set(Calendar.DAY_OF_MONTH,1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * 获取指定日期的最后一天
	 * @param args
	 * @throws ParseException
	 */
	public static Date getLastDate(Date theDate) {
		Calendar c=Calendar.getInstance();
		c.setTime(theDate);
		c.add(Calendar.MONTH, 1);
		Date firstDate=getFirstDateInMonth(c.getTime());
		c.setTime(firstDate);
		c.add(Calendar.SECOND, -1);
		/*
		 * Calendar c=Calendar.getInstance(); c.setTime(theDate); c.add(Calendar.MONTH,
		 * 1); c.set(Calendar.DAY_OF_MONTH,1); c.set(Calendar.HOUR_OF_DAY, 0);
		 * c.set(Calendar.MINUTE,0); c.set(Calendar.SECOND, 0); c.add(Calendar.SECOND,
		 * -1);
		 */
		return c.getTime();
	}
	/**
	 * 判断指定的两个日期是否相等
	 * @param args
	 * @throws ParseException
	 */
	public static int compareTime(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}if(time1>time2){
			return 1;
		}
		return -1;
	}
	public static void main(String[] args) throws ParseException {
		System.out.println(compareTime(format.parse("2019-02-06"),format.parse("2019-02-15")));
	}
}
