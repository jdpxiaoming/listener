package com.lewen.listener.util;

import android.util.Log;

public class LoggerUtil {

	public static void d(String Tag ,String paramObject){
		Log.d(Tag, paramObject);
	}
	public static void e(String Tag ,String conent){
		Log.e(Tag, conent);
	}
	public static void i(String Tag ,String conent){
		Log.i(Tag, conent);
	}
	
	 private static int a = 1;

	  public static void a(int paramInt)
	  {
	    a = paramInt;
	  }

	  public static void a(String paramString)
	  {
	    if (a < 3)
	      Log.d("memory", paramString);
	  }

	  public static void a(String paramString1, String paramString2)
	  {
	    if (a < 2)
	      Log.v(paramString1, paramString2);
	  }

	  public static void b(String paramString)
	  {
	    if (a < 3)
	      Log.i("memory", paramString);
	  }

	  public static void b(String paramString1, String paramString2)
	  {
	    if (a < 3)
	      Log.d(paramString1, paramString2);
	  }

	  public static void c(String paramString1, String paramString2)
	  {
	    if (a < 4)
	      Log.i(paramString1, paramString2);
	  }

//	  public static void d(String paramString1, String paramString2)
//	  {
//	    if (a < 5)
//	      Log.w(paramString1, paramString2);
//	  }
//
//	  public static void e(String paramString1, String paramString2)
//	  {
//	    if (a < 6)
//	      Log.e(paramString1, paramString2);
//	  }
}
