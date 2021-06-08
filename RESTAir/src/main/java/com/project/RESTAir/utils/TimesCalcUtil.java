package com.project.RESTAir.utils;

public class TimesCalcUtil {
	
	/*
	 * These are some basic methods to try and calculate Integer representation
	 * of time from String representation and vice versa.
	 * String representation - HH:mm
	 * Integer representation - HH*3600 + mm*60
	 */
	
	public static Integer calcTimeFromString(String timeString) {
		String[] timeComponents = timeString.split(":");
		//timeComponents[0] = hours; timeComponents[1] = minutes;
		return (Integer.parseInt(timeComponents[0]) * 3600) + (Integer.parseInt(timeComponents[1]) * 60);
	}
	
	public static String formatTimeToString(Integer timeValue) {
		Integer hours = timeValue / 3600;
		Integer minutes = (timeValue - (hours * 3600)) / 60;
		//Add leading zero to single digit numbers
		return String.format("%02d:%02d", hours, minutes);
	}

}
