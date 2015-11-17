package com.db.cs4400.fancyhotel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Util {
	
	public static String LOGIN_PANEL = "LOGIN_PANEL";
	public static String USER_REGISTRATION_PANEL = "USER_REGISTRATION_PANEL";
	public static String PROVIDE_REVIEW_PANEL = "PROVIDE_REVIEW_PANEL";
	public static String FUNTIONALITY_PANEL = "FUNTIONALITY_PANEL";
	public static String SEARCH_ROOM_PANEL = "SEARCH_ROOM_PANEL";
	public static String RESERVATION_PANEL = "RESERVATION_PANEL";
	public static String CONFIRMATION_PANEL = "CONFIRMATION_PANEL";
	public static String UPDATE_RESERVATION_PANEL = "UPDATE_RESERVATION_PANEL";
	
	public static long getDifferenceDays(final Date startDate, final Date endDate) {
	    long diff = endDate.getTime() - startDate.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static String parseDate(final Date date) {
		if (date == null) {
			return null;
		}
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(date);
	}

}
