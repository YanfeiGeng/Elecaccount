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
	 * Get week name, like: ����һ
	 * @return
	 */
	public static String getWeekNameInLocale(String date){
		if(date != null){
			String[] detail = date.split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(detail[0]), Integer.parseInt(detail[1]) + 1, Integer.parseInt(detail[2]));
			return "����" + changeWeek(calendar.get(Calendar.DAY_OF_WEEK));
		} else {
			return "��������";
		}
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
		System.out.println(util.getWeekNameInLocale("2011-5-13"));
	}

}