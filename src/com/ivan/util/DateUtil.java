package com.ivan.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	/**
	 * Get today date as String format
	 * @return
	 */
	public static String getTodayAsString(){
		Calendar now = Calendar.getInstance();
		String today = now.get(Calendar.YEAR) + "-" + now.get(Calendar.MONTH) + "-" + now.get(Calendar.DATE);
		return today;
	}
	
	/**
	 * Get week name, like: 星期一
	 * @return
	 */
	public static String getWeekNameInLocale(String date){
		if(date != null){
			String[] detail = date.split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(detail[0]), Integer.parseInt(detail[1]) + 1, Integer.parseInt(detail[2]));
			return "星期" + changeWeek(calendar.get(Calendar.DAY_OF_WEEK));
		} else {
			return "星期其他";
		}
	}
	
	
	private static String changeWeek(int dayOfWeek){
		switch(dayOfWeek){
			case 1: 
				return "二";
			case 2: 
				return "三";
			case 3: 
				return "四";
			case 4: 
				return "五";
			case 5: 
				return "六";
			case 6: 
				return "日";
			case 7: 
				return "一";
			default:
				return "空";
		}
	}
	
	public static void main(String[] args){
		DateUtil util = new DateUtil();
//		util.getTodayAsString();
		System.out.println(util.getWeekNameInLocale("2011-5-13"));
	}

}