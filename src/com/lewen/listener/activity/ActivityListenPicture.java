package com.lewen.listener.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;

public class ActivityListenPicture extends BaseActivity {
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_guess_picture);
		
		init();
	}

	private void init() {
		btnBack	=	(Button) findViewById(R.id.gobackbt);
		
		btnBack.setOnClickListener(clickListener);
		
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.gobackbt://
				finish();
				break;
			default:
				break;
			}
		}
	};
}
