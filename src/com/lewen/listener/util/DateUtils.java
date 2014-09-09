package com.lewen.listener.util;

import java.util.Calendar;
import java.util.Locale;

import android.text.format.DateFormat;

public class DateUtils {

	 /**
	  * test UTC 获取世界协调时间 
	  * 
	  * 默认授权过时为3个月后
	  * 
	  * @return
	  */
	public static String getExpireTime(){
		 
        Calendar cal = Calendar.getInstance(Locale.CHINA);  
	    int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
	    int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
	    cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset)); 
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+3);
	    String time = DateFormat.format("yyyy'-'MM'-'dd'T'kk':'mm':'ss'Z'", cal).toString();  
	    System.out.println(time);
	    
	    return time;
	}
}
