package com.lewen.listener.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;

public class ActivityListenWorld extends BaseActivity {
	
	private TextView mTextViewWord,mTextViewPicture,mTextViewWorld;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_listen_world);
		
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

			switch (v.getId()) {
			case R.id.textWord://猜单词
				
				break;
			case R.id.textWorld://听世界
				
				break;
			case R.id.textPicture://猜图片
				
				break;
			default:
				break;
			}
		}
	};
}
