package com.lewen.listener.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import android.os.Build;
import android.util.Log;

public class TBException implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
		StringBuilder localStringBuilder = new StringBuilder();
	    localStringBuilder.append("Version code is ");
	    localStringBuilder.append(Build.VERSION.SDK_INT + "\t");
	    localStringBuilder.append("Model is ");
	    localStringBuilder.append(Build.MODEL + "\t");
	    localStringBuilder.append(paramThrowable.toString() + "\t");
	    StringWriter localStringWriter = new StringWriter();
	    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
	    localStringBuilder.append(localStringWriter.toString());
	    Log.w("exection", localStringBuilder.toString());
//	    x.a.append(" ***  exection info ***" + localStringBuilder.toString());
//	    Log.w("exection", x.a.toString());
	    android.os.Process.killProcess(android.os.Process.myPid());
	}

}
