package com.lewen.listener.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.Toast;

import com.androidquery.auth.GoogleHandle;
import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.bean.AuthReply;
import com.lewen.listener.bean.Constants;
import com.lewen.listener.bean.Person;
import com.lewen.listener.http.HttpUtil;
import com.lewen.listener.service.XmlToListService;
import com.lewen.listener.util.ToastUtil;
import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennClient.LoginListener;
import com.tencent.connect.UserInfo;
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

	private Button	btnBack,btnLogin,btnQQ;
	public static QQAuth mQQAuth;
	private Tencent mTencent;
	public static String mAppid="101050174";
	private UserInfo mInfo;
	//sina
	private Button btnSina;
	private Weibo mWeibo;
	public static Oauth2AccessToken accessToken;
	
	//renren
	private Button btnRenren;
	private RennClient rennClient;
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				goNext();
				break;
			case 1://已经登录了！~
				ToastUtil.throwTipShort("已经登录了~");
				break;
			default:
				break;
			}
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		
		initView();
	}

	private void initView() {
		TBApplication.pushPreferenceData("session", null);
		btnBack		=	(Button) findViewById(R.id.gobackbt);
		btnBack.setOnClickListener(this);
		
		btnLogin	=	(Button) findViewById(R.id.loginbt);
		btnLogin.setOnClickListener(this);
		
		btnQQ	=	(Button) findViewById(R.id.loginqqbt);
		btnQQ.setOnClickListener(this);
		
		mQQAuth = QQAuth.createInstance(mAppid, ActivityLogin.this.getApplicationContext());
		mTencent = Tencent.createInstance(mAppid, ActivityLogin.this.getApplicationContext());
	
		btnSina		=	(Button) findViewById(R.id.loginsinabt);
		btnSina.setOnClickListener(this);
		mWeibo = Weibo.getInstance(Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
	
		btnRenren	=	(Button) findViewById(R.id.loginrenrenbt);
		btnRenren.setOnClickListener(this);
		
		rennClient = RennClient.getInstance(this);
		rennClient.init(Constants.RENREN_APP_ID, Constants.RENREN_API_KEY, Constants.RENREN_SECRET_KEY);
		rennClient
				.setScope("read_user_blog read_user_photo read_user_status read_user_album "
						+ "read_user_comment read_user_share publish_blog publish_share "
						+ "send_notification photo_upload status_update create_album "
						+ "publish_comment publish_feed");
		// rennClient.setScope("read_user_blog read_user_status");
		rennClient.setTokenType("bearer");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.gobackbt:
			
			finish();
			break;
		case R.id.loginqqbt:
			
			doQQLogin();
			break;
		case R.id.loginsinabt:
			
			doSinaLogin();
			break;
			
		case R.id.loginrenrenbt:
			doRenrenLogin();
			break;
			
		case R.id.loginbt:
			Intent localIntent = new Intent(ActivityLogin.this.getApplicationContext(), ActivitySlidingMenue.class);
			localIntent.putExtra("tag", "splash");
			startActivity(localIntent);
			overridePendingTransition(R.anim.bg_slide_down_in, R.anim.bg_slide_down_out);
		    finish();
			break;
		default:
			break;
		}
		
	}

	
	private void doRenrenLogin() {
		// TODO Auto-generated method stub
		
		rennClient.setLoginListener(new LoginListener() {
			@Override
			public void onLoginSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(ActivityLogin.this, "登录成功",
						Toast.LENGTH_SHORT).show();
				Intent localIntent = new Intent(ActivityLogin.this.getApplicationContext(), ActivitySlidingMenue.class);
				localIntent.putExtra("tag", "splash");
				startActivity(localIntent);
				overridePendingTransition(R.anim.bg_slide_down_in, R.anim.bg_slide_down_out);
			    finish();
			    overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
			}

			@Override
			public void onLoginCanceled() {
			}
		});
		rennClient.login(this);
	}

	private void doSinaLogin() {
		// TODO Auto-generated method stub
		mWeibo.anthorize(ActivityLogin.this, new AuthDialogListener());
	}
	

	/**
	 * qq开放登录
	 */
	private void doQQLogin() {
		IUiListener listener = new BaseUiListener() {
			@Override
			protected void doComplete(JSONObject values) {
				System.out.println(values.toString());
				
				//解析 openid 和 expirek_time
				try {
					final AuthReply ar  = XmlToListService.GetAuth(values.toString());
					if(null!=ar){
						TBApplication.pushPreferenceData("openid", ar.getOpenID());
					new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								if(TBApplication.getPreferenceData("session")!=null){
									System.out.println("session有效："+TBApplication.getPreferenceData("session"));
									mHandler.sendEmptyMessage(1);
								}else{
									
								NameValuePair pair1 = new BasicNameValuePair("openid", ar.getOpenID());
						        NameValuePair pair2 = new BasicNameValuePair("source", "qq");
						        NameValuePair pair3 = new BasicNameValuePair("expired_in", ar.getExpire_time());
						        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
						        pairList.add(pair1);
						        pairList.add(pair2);
						        pairList.add(pair3);
						         
						        String result	=	HttpUtil.sendPost(pairList, "http://ting.joysw.cn/index.php/api/login/reply");
						        
						        if(!TextUtils.isEmpty(result)){
						        	
						        	System.out.println(result);
				                    JSONObject object;
				                     
									try {
										
										object = new JSONObject(result);
										JSONObject data = object.getJSONObject("data");
										TBApplication.pushPreferenceData("uid", data.getString("uid"));
										TBApplication.pushPreferenceData("salt", data.getString("salt"));
										//save the cookie
//										CookieSyncManager.getInstance().sync();
										//save the cookies
//										System.out.println(Cookie);
//										mHandler.sendEmptyMessage(0);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									updateUserInfo();
						        	}
								}
									//执行跳转
									
						        }
						}).start();
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
//				updateUserInfo();
			}
		};
		//mQQAuth.login(this, "all", listener);
		mTencent.loginWithOEM(this, "all", listener,"10000144","10000144","xxxx");
		
	}

	
	//test get user info by cookies
		private void testCookie() {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					NameValuePair pair1 = new BasicNameValuePair("uid", TBApplication.getPreferenceData("uid"));
			        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			        pairList.add(pair1);
			         
			        String result	=	HttpUtil.sendPost(pairList, "http://ting.joysw.cn/index.php/api/members/info");
			        
			        if(!TextUtils.isEmpty(result)){
			        	
			        	System.out.println(result);
						//执行跳转
			        }
				}
			}).start();
		}
		
	private void updateUserInfo() {
		if (mQQAuth != null && mQQAuth.isSessionValid()) {
			IUiListener listener = new IUiListener() {
				
				@Override
				public void onError(UiError e) {
					
				}
				
				@Override
				public void onComplete(final Object response) {
					System.out.println("用户基本信息："+response.toString());
					new Thread(){

						@Override
						public void run() {
							JSONObject json = (JSONObject)response;
							if(json!=null){
								Person mPerson = new Person();
								try {
									mPerson.setIcon_url(json.getString("figureurl_qq_2"));
									mPerson.setUserName(json.getString("nickname"));
									mPerson.setGender(json.getString("gender"));
									TBApplication.person = mPerson;
									mHandler.sendEmptyMessage(0);
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
			mInfo = new UserInfo(this, mQQAuth.getQQToken());
			mInfo.getUserInfo(listener);
			
			
		}
	}	
	//respone :{"ret":0,"pay_token":"9DEBA12D7A2CB4D91B1C479199F57C9A","pf":"desktop_m_qq-10000144-android-10000144-xxxx","openid":"5D81D416455D15BC36B0AA6C3252BC1A","expires_in":7776000,"pfkey":"b7acd38a96c89a26c2c118e454342e93","msg":"sucess","access_token":"FD037441B82A929790D680C9B84D016B"}
	/**
	 * {
    "ret": 0, 
    "pay_token": "9DEBA12D7A2CB4D91B1C479199F57C9A", 
    "pf": "desktop_m_qq-10000144-android-10000144-xxxx", 
    "openid": "5D81D416455D15BC36B0AA6C3252BC1A", 
    "expires_in": 7776000, 
    "pfkey": "b7acd38a96c89a26c2c118e454342e93", 
    "msg": "sucess", 
    "access_token": "FD037441B82A929790D680C9B84D016B"
	}
	 * @author poe
	 *
	 */
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			doComplete((JSONObject)response);
		}

		protected void doComplete(JSONObject values) {
			
			//jump to the 
			
		}

		@Override
		public void onError(UiError e) {
			ToastUtil.throwTipShort("onError: " + e.errorDetail);
		}

		@Override
		public void onCancel() {
			ToastUtil.throwTipShort("onCancel:");
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
			
			goNext();
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
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	       mTencent.onActivityResult(requestCode, resultCode, data);
	 }
	
	private void goNext() {
		Intent localIntent = new Intent(ActivityLogin.this.getApplicationContext(), ActivitySlidingMenue.class);
		localIntent.putExtra("tag", "splash");
		startActivity(localIntent);
		overridePendingTransition(R.anim.bg_slide_down_in, R.anim.bg_slide_down_out);
	    finish();
	    overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
	}
}
