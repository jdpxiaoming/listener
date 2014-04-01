package com.lewen.listener.activity;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.bean.Constants;
import com.lewen.listener.util.ToastUtil;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;

public class ActivityLogin extends BaseActivity implements OnClickListener{

	private Button btnLogin;
	public static QQAuth mQQAuth;
	private Tencent mTencent;
	public static String mAppid="101050174";
	//sina
	private Button btnSina;
	private Weibo mWeibo;
	public static Oauth2AccessToken accessToken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btnLogin	=	(Button) findViewById(R.id.loginqqbt);
		btnLogin.setOnClickListener(this);
		
		mQQAuth = QQAuth.createInstance(mAppid, ActivityLogin.this.getApplicationContext());
		mTencent = Tencent.createInstance(mAppid, ActivityLogin.this.getApplicationContext());
	
		btnSina		=	(Button) findViewById(R.id.loginsinabt);
		btnSina.setOnClickListener(this);
		
		mWeibo = Weibo.getInstance(Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.loginqqbt:
			
			doQQLogin();
			break;
		case R.id.loginsinabt:
			
			doSinaLogin();
			break;
		default:
			break;
		}
		
	}

	
	private void doSinaLogin() {
		// TODO Auto-generated method stub
		mWeibo.anthorize(ActivityLogin.this, new AuthDialogListener());
	}

	private void doQQLogin() {
		IUiListener listener = new BaseUiListener() {
			@Override
			protected void doComplete(JSONObject values) {
//				updateUserInfo();
			}
		};
		//mQQAuth.login(this, "all", listener);
		mTencent.loginWithOEM(this, "all", listener,"10000144","10000144","xxxx");
	}
	
	/*private void updateUserInfo() {
		if (mQQAuth != null && mQQAuth.isSessionValid()) {
			IUiListener listener = new IUiListener() {
				
				@Override
				public void onError(UiError e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onComplete(final Object response) {
					Message msg = new Message();
					msg.obj = response;
					msg.what = 0;
					mHandler.sendMessage(msg);
					new Thread(){

						@Override
						public void run() {
							JSONObject json = (JSONObject)response;
							if(json.has("figureurl")){
								Bitmap bitmap = null;
								try {
									bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
								} catch (JSONException e) {
									
								}
								Message msg = new Message();
								msg.obj = bitmap;
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						}
						
					}.start();
				}
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					
				}
			};
//			  MainActivity.mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null,
//	                    Constants.HTTP_GET, requestListener, null);
//			mInfo = new UserInfo(this, mQQAuth.getQQToken());
//			mInfo.getUserInfo(listener);
			
		} else {
//			mUserInfo.setText("");
//			mUserInfo.setVisibility(android.view.View.GONE);
//			mUserLogo.setVisibility(android.view.View.GONE);
		}
	}*/
	
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			ToastUtil.showToastShort("登录成功",ActivityLogin.this);
			Intent localIntent = new Intent(ActivityLogin.this.getApplicationContext(), ActivitySlidingMenue.class);
			localIntent.putExtra("tag", "splash");
			startActivity(localIntent);
			overridePendingTransition(R.anim.bg_slide_down_in, R.anim.bg_slide_down_out);
		    finish();
		    overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
			doComplete((JSONObject)response);
		}

		protected void doComplete(JSONObject values) {
			
			//jump to the 
			
		}

		@Override
		public void onError(UiError e) {
			ToastUtil.showToastShort("onError: " + e.errorDetail,ActivityLogin.this);
		}

		@Override
		public void onCancel() {
			ToastUtil.showToastShort("onCancel:",ActivityLogin.this);
		}
	}
	
	
	class AuthDialogListener implements WeiboAuthListener {

		@Override
		public void onComplete(Bundle values) {

			String code = values.getString("code");
			String expires_in = values.getString("expires_in");

			Log.i("poe", "code:" + code);
			Log.i("poe", "expires_in:" + expires_in);

			String token = values.getString("access_token");
			Log.i("poe", "token:" + token);
			
			Intent localIntent = new Intent(ActivityLogin.this.getApplicationContext(), ActivitySlidingMenue.class);
			localIntent.putExtra("tag", "splash");
			startActivity(localIntent);
			overridePendingTransition(R.anim.bg_slide_down_in, R.anim.bg_slide_down_out);
		    finish();
		    overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
		}

		@Override
		public void onError(WeiboDialogError e) {
			Toast.makeText(getApplicationContext(), "Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Auth cancel", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(), "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
