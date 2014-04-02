package com.lewen.listener.activity;

import com.lewen.listener.R;
import com.lewen.listener.bean.Constants;
import com.lewen.listener.exception.TBException;
import com.qq.e.splash.SplashAd;
import com.qq.e.splash.SplashAdListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ActivitySplash extends Activity {

	Handler handler =new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Intent localIntent = new Intent(ActivitySplash.this.getApplicationContext(), ActivityLogin.class);
			localIntent.putExtra("tag", "splash");
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
		showAd();
	}

	private void showAd() {
		FrameLayout container = (FrameLayout) this
				.findViewById(R.id.splashcontainer);

		new SplashAd(this, container, Constants.GDT_APPID, Constants.GDT_SPLASHPOSID,
				new SplashAdListener() {

					@Override
					public void onAdPresent() {
						Log.i("test", "present");
						Toast.makeText(ActivitySplash.this,
								"SplashPresent", Toast.LENGTH_LONG).show();
					}

					@Override
					public void onAdFailed(int arg0) {
						Log.i("test", "fail" + arg0);
						Toast.makeText(ActivitySplash.this,
								"SplashFail" + arg0, Toast.LENGTH_LONG).show();
						ActivitySplash.this.finish();
					}

					@Override
					public void onAdDismissed() {
						Log.i("test", "dismiss");
						Toast.makeText(ActivitySplash.this,
								"SplashDismissed", Toast.LENGTH_LONG).show();
						ActivitySplash.this.finish();
					}
				});
	}

}
