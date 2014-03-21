package com.lewen.listener.util;

import android.content.Context;
import android.widget.Toast;

/**
 * toast util
 * @author Administrator
 *
 */
public class ToastUtil {
	
	public static void showToastLong(String content ,Context mContext){
		Toast.makeText(mContext, content, 800);
	}
	public static void showToastMiddle(String content ,Context mContext){
		Toast.makeText(mContext, content, 600);
	}
	public static void showToastShort(String content ,Context mContext){
		Toast.makeText(mContext, content, 300);
	}
}
