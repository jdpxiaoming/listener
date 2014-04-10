package com.lewen.listener.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.util.Util;

public class ActivityPersonalInfo extends BaseActivity {

	private ImageView icon;
	private TextView txtName;
	private Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_person_info);
		
		init();
	}

	private Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(null!=bitmap)
				icon.setImageBitmap(bitmap);
		}
	};
	private void init() {
		
		icon	=	(ImageView) findViewById(R.id.imgHeadOfPersionalInfo);
		txtName	=	(TextView) findViewById(R.id.textNameOfPersionalInfo);
		
		if(null!=TBApplication.person){
			txtName.setText(TBApplication.person.getUserName());
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					bitmap = Util.getbitmap(TBApplication.person.getIcon_url());
					handler.sendEmptyMessage(0);
				}
			}).start();;
			
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Bitmap b =icon.getDrawingCache();
		if(null!=b) {
			b.recycle();
			b =null;
		}
		super.onDestroy();
		
	}
}
