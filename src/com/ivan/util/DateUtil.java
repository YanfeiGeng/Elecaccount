package com.ivan.util;

import java.util.Calendar;

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
	 * Get week name, like: ����һ
	 * @return
	 */
	public static String getWeekNameInLocale(){
		Calendar calendar = Calendar.getInstance();
		return "����" + changeWeek(calendar.get(Calendar.DAY_OF_WEEK));
	}
	
	
	private static String changeWeek(int dayOfWeek){
		switch(dayOfWeek){
			case 1: 
				return "��";
			case 2: 
				return "��";
			case 3: 
				return "��";
			case 4: 
				return "��";
			case 5: 
				return "��";
			case 6: 
				return "��";
			case 7: 
				return "һ";
			default:
				return "��";
		}
	}
	
	public static void main(String[] args){
		DateUtil util = new DateUtil();
//		util.getTodayAsString();
		System.out.println(util.getWeekNameInLocale());
	}

}