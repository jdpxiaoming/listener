package com.lewen.listener.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;

public class ActivitySet extends BaseActivity {

	private ImageView imgBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.settings_activity);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		imgBack		=	(ImageView) findViewById(R.id.imgbtnBack);
		
		
		imgBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
