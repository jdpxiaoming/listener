package com.lewen.listener.activity;

import com.lewen.listener.R;
import com.lewen.listener.exception.TBException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ActivitySplash extends Activity {

	Handler handler =new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Intent localIntent = new Intent(ActivitySplash.this.getApplicationContext(), HomePageFragmentActivity.class);
			startActivity(localIntent);
			overridePendingTransition(R.anim.bg_slide_down_in, R.anim.bg_slide_down_out);
		    finish();
		    overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Thread.setDefaultUncaughtExceptionHandler(new TBException());
		
		setContentView(R.layout.layout_splash);
		
		handler.sendMessageDelayed(handler.obtainMessage(), 700L);
	}

}
