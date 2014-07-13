package com.lewen.listener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.util.ToastUtil;

public class ActivityPKResult extends BaseActivity {

	private ImageButton imgback;
	private RelativeLayout rel_topter_bg;
	private ImageView img_topter_icon,img_toper_tip;
	private TextView tvScore1,tvScore2,tvName1,tvName2;
	private TextView tvBenju,tvPKTotal;
	private ImageButton imgRecover,imgShow;
	private String xml;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_pk_result);
		
		init();
	}

	private void init() {
		
		xml	=	getIntent().getStringExtra("xml");
		
		imgback	=	(ImageButton) findViewById(R.id.imgbtnBack);
		imgback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		tvName1		=	(TextView) findViewById(R.id.textName1OfPKResult);
		tvName2		= 	(TextView) findViewById(R.id.textName2OfPKResult);
		tvScore1	=	(TextView) findViewById(R.id.textScore1OfPKResult);
		tvScore2	=	(TextView) findViewById(R.id.textScore2OfPKResult);
		
		rel_topter_bg	=	(RelativeLayout) findViewById(R.id.relativeBgOfPKResult);
		img_toper_tip	=	(ImageView) findViewById(R.id.imgTipOfPKResult);
		img_topter_icon	=	(ImageView) findViewById(R.id.imgIconOfPKResult);
		
		
		tvBenju		=	(TextView) findViewById(R.id.textScoreOfPKResult);
		tvPKTotal	=	(TextView) findViewById(R.id.textPKScoreOfPKResult);
		
		imgRecover	=	(ImageButton) findViewById(R.id.imgRecover);
		imgRecover.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActivityPKResult.this,ActivityListenWord.class);
				intent.putExtra("xml", xml);
				startActivity(intent);
				overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
				finish();
			}
		});
		
		findViewById(R.id.imgShow).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtil.throwTipShort("暂未实现此功能！");
			}
		});
		//set the datas
		if(TBApplication.getPreferenceData("warname")!=null){
			tvName2.setText(TBApplication.getPreferenceData("warname"));
		}
		
		if(TBApplication.person!=null){
			tvName1.setText(TBApplication.person.getUserName());
		}
		
	}
	
	
}
