package com.lewen.listener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;

public class ActivityListenMenue extends BaseActivity {
	
	private TextView mTextViewWord,mTextViewPicture,mTextViewWorld;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_listen_menue);
		
		init();
	}

	private void init() {
		
		mTextViewWord	=	(TextView) findViewById(R.id.textWord);
		mTextViewPicture=	(TextView) findViewById(R.id.textPicture);
		mTextViewWorld	=	(TextView) findViewById(R.id.textWorld);
		
		mTextViewWord.setOnClickListener(clickListener);
		mTextViewPicture.setOnClickListener(clickListener);
		mTextViewWorld.setOnClickListener(clickListener);
		
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent intent =new Intent();
			switch (v.getId()) {
			case R.id.textWord://猜单词
				intent.setClass(ActivityListenMenue.this, ActivityListenWord.class);
				break;
			case R.id.textWorld://听世界
				intent.setClass(ActivityListenMenue.this, ActivityListenWorld.class);
				break;
			case R.id.textPicture://猜图片
				intent.setClass(ActivityListenMenue.this, ActivityListenPicture.class);
				break;
			default:
				break;
			}
			
			startActivity(intent);
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		}
	};
}
