package com.lewen.listener.util;

import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * toast util
 * @author Administrator
 *
 */
public class ToastUtil {
	
	private static android.widget.Toast toast;
	private static Handler handler = new Handler();
	private static Runnable run = new Runnable() {
		public void run() {
			toast.cancel();
		}
	};
	
	private static TextView tv;

	public static void throwTipShort(String conent) {
		
		if(toast==null){
			init(conent);
		}
		
		tv.setText(conent);
		toast.show();
		handler.postDelayed(run, Toast.LENGTH_SHORT+1500);
	}

	@SuppressLint("ShowToast")
	private static void init(String conent) {
		toast = Toast.makeText(TBApplication.App, conent, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		
		LayoutInflater lin = LayoutInflater.from(TBApplication.App);
		View layout = lin.inflate(R.layout.loading,null);
		tv	=	(TextView) layout.findViewById(R.id.tvOfToast);
		
		toast.setView(layout);
	}
	
	public static void throwTipLong(String conent) {
		
		if(toast==null){
			init(conent);
		}
		tv.setText(conent);
		
		
		handler.postDelayed(run, Toast.LENGTH_LONG+2500);
		toast.show();
	}
}
