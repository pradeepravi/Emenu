package com.pradeep.menu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
	/**
	 * Pass Date in yyyy/mm/dd format and get the Java Util Date object
	 * @param date
	 * @return
	 */
	public static  Date getDate(String date){
		    java.util.Date utilDate = null;

		    try {
		      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		      utilDate = formatter.parse(date);
		      System.out.println("utilDate:" + utilDate);
		    } catch (ParseException e) {
		      System.out.println(e.toString());
		      e.printStackTrace();
		}

		return utilDate;
	}
}
