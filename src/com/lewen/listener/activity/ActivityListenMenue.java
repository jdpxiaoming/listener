package com.lewen.listener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.util.ToastUtil;
import com.lewen.listener.vlc.VideoPlayerActivity;

public class ActivityListenMenue extends BaseActivity {
	
//	private TextView mTextViewWord,mTextViewPicture,mTextViewWorld;
	
	private ImageButton imgBtn ;
	private ImageView	imgWorld,imgPicture;
	private LinearLayout linearDivider,linearWorld;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_listen_menue);
		
		init();
	}

	private void init() {
		
		imgBtn		=	(ImageButton) findViewById(R.id.imgbtnBack);
		imgWorld	=	(ImageView) findViewById(R.id.imgGuessWorldOfListenMenue);
		imgPicture	=	(ImageView) findViewById(R.id.imgGuessPictureOfListenMenue);
		linearDivider=	(LinearLayout) findViewById(R.id.linearDivider);
		linearWorld	=	(LinearLayout) findViewById(R.id.linearListenerWorld);
		
		imgBtn.setOnClickListener(clickListener);
		imgWorld.setOnClickListener(clickListener);
		imgPicture.setOnClickListener(clickListener);
		linearWorld.setOnClickListener(clickListener);
		
		//师
		
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent intent =new Intent();
			switch (v.getId()) {
			case R.id.imgbtnBack://猜单词
				finish();
				break;
			case R.id.imgGuessWorldOfListenMenue://听单词
				intent.setClass(ActivityListenMenue.this, ActivityListenWord.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				break;
			case R.id.imgGuessPictureOfListenMenue://猜图片
				intent.setClass(ActivityListenMenue.this, ActivityListenPicture.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				ToastUtil.throwTipShort("暂未实现此功能！");
				break;
			case R.id.linearListenerWorld:
//				intent.setClass(ActivityListenMenue.this, ActivityListenWorld.class);
//				startActivity(intent);
//				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				VideoPlayerActivity.start(ActivityListenMenue.this, "http://tftp.joysw.cn/Courseware/140710010639983255/20140710010357/111.flv", false);
				break;
			default:
				break;
			}
		}
	};
}
