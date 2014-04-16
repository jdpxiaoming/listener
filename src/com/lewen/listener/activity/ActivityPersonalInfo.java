package com.lewen.listener.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LongSparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.bean.Constants;
import com.lewen.listener.listener.BaseUIListener;
import com.lewen.listener.util.Util;
import com.lewen.listener.view.GetInviteParamsDialog;
import com.lewen.listener.view.GetInviteParamsDialog.OnGetInviteParamsCompleteListener;
import com.lewen.listener.view.GetPkBragParamsDialog;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

public class ActivityPersonalInfo extends BaseActivity {

//	private String mAppID ="222222";
	private ImageView icon;
	private TextView txtName;
	private Bitmap bitmap;
	
	// set to 1 for test params
	private int mNeedInputParams = 1;
	private Tencent mTencent;
	private LongSparseArray<IUiListener> mLiteners;
	//邀请、挑战、炫耀
	private TextView textInvite,textPK,textShow;
	
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
		
		textInvite	=	(TextView) findViewById(R.id.textInvite);
		textPK		=	(TextView) findViewById(R.id.textPK);
		textShow	=	(TextView) findViewById(R.id.textShow);
		
		textInvite.setOnClickListener(clickListener);
		textPK.setOnClickListener(clickListener);
		textShow.setOnClickListener(clickListener);
		
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
		
		mTencent = Tencent.createInstance(Constants.QQ_APP_ID, this);
		mLiteners = new LongSparseArray<IUiListener>(10);
	}
	
	OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.textInvite:
				onClickInvite() ;
				break;
			case R.id.textPK:
				onClickPkBrag();
				break;
			case R.id.textShow:
				onClickPkBrag();
				break;
			default:
				break;
			}
		}
	};
	
	private void onClickInvite() {
		if (mTencent.isReady()) {
		    if (mNeedInputParams == 1) {
                new GetInviteParamsDialog(this, new OnGetInviteParamsCompleteListener() {

                    @Override
                    public void onGetParamsComplete(Bundle params) {
                        //mSocialApi.invite(MainActivity.this, params, new BaseUiListener());
                        mTencent.invite(ActivityPersonalInfo.this, params, new BaseUIListener(ActivityPersonalInfo.this));
                    }
                }).show();
            } else {
                Bundle params = new Bundle();
                // TODO keywords.
                params.putString(SocialConstants.PARAM_APP_ICON,
                        "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
                params.putString(SocialConstants.PARAM_APP_DESC ,
                        "AndroidSdk_2_0: voice description!");
                params.putString(SocialConstants.PARAM_ACT, "进入应用");
                
                //mSocialApi.voice(MainActivity.this, params, new BaseUiListener());
                mTencent.invite(ActivityPersonalInfo.this, params, new BaseUIListener(ActivityPersonalInfo.this));
            }
		}
	}
	
	private void onClickPkBrag() {
		if (mTencent.isReady()) {
		    if (mNeedInputParams == 1) {
                new GetPkBragParamsDialog(
                        this,
                        new GetPkBragParamsDialog.OnGetPkBragParamsCompleteListener() {

                            @Override
                            public void onGetParamsComplete(Bundle params) {
                                if ("pk".equals((String) params.get(SocialConstants.PARAM_TYPE))) {
                                    mTencent.challenge(ActivityPersonalInfo.this, params, new BaseUIListener(ActivityPersonalInfo.this));
                                } else if ("brag".equals((String) params
                                        .get(SocialConstants.PARAM_TYPE))) {
                                    mTencent.brag(ActivityPersonalInfo.this, params, new BaseUIListener(ActivityPersonalInfo.this));
                                }
                            }
                        }).show();
            } else {
                Bundle params = new Bundle();
                params.putString(SocialConstants.PARAM_RECEIVER, "3B599FF138EE42DD7FE2234D3B89C44B");
                params.putString(SocialConstants.PARAM_SEND_MSG, "向某某某发起挑战");
                params.putString(SocialConstants.PARAM_IMG_URL, "http://i.gtimg.cn/qzonestyle/act/qzone_app_img/app888_888_75.png");
                mTencent.challenge(ActivityPersonalInfo.this, params, new BaseUIListener(ActivityPersonalInfo.this));
            }
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
