package com.lewen.listener.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.adapter.Myadapter;
import com.lewen.listener.bean.Task;
import com.lewen.listener.bean.TaskType;
import com.lewen.listener.fragment.FragementYanChu;
import com.lewen.listener.http.HttpUtil;
import com.lewen.listener.view.SlideHolder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivitySlidingMenue extends FragmentActivity implements
		OnClickListener {

	private SlideHolder mSlideHolder;
	private LinearLayout lin_area_layer;
	private ImageView mImageViewMyself,	mImageViewShopping,mImageViewSetting,
	mImageViewScore,mImageViewHome,	mImageViewActive,mImageViewNearBy,mImageViewWebsite;
	private String from = "yanchu";
	private Context c;
	// footer nav
	private ImageView mImageViewNav, mImageViewSing, mImageViewNews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_sliding_menu);
		c = this;

		init();
		
		
		testCookie();
	}

	//test get user info by cookies
	private void testCookie() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				NameValuePair pair1 = new BasicNameValuePair("uid", TBApplication.getPreferenceData("uid"));
//		        NameValuePair pair2 = new BasicNameValuePair("source", "qq");
//		        NameValuePair pair3 = new BasicNameValuePair("expired_in", ar.getExpire_time());
		        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		        pairList.add(pair1);
//		        pairList.add(pair2);
//		        pairList.add(pair3);
		         
		        String result	=	HttpUtil.sendPost(pairList,"http://ting.joysw.cn/index.php/api/members/info");// "http://ting.joysw.cn/index.php/api/members/info");
		        if(!TextUtils.isEmpty(result)){
		        	
		        	System.out.println(result);
//                    JSONObject object;
//					try {
//						object = new JSONObject(result);
//						JSONObject data = object.getJSONObject("data");
//						TBApplication.pushPreferenceData("uid", data.getString("uid"));
//						TBApplication.pushPreferenceData("salt", data.getString("salt"));
//						
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					//执行跳转
		        }
		        
		        List<NameValuePair> pairList2 = new ArrayList<NameValuePair>();
		        //test money
		        String result2	=	HttpUtil.sendPost(pairList2,"http://ting.joysw.cn/index.php/api/members/money");
		        System.out.println(result2);
		        
			}
		}).start();
	}

	public void init() {
		// TODO Auto-generated method stub
		from = getIntent().getStringExtra("tag");
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		mSlideHolder.setAllowInterceptTouch(false);
		mSlideHolder.setEnabled(false);

		// footer
		mImageViewNav = (ImageView) findViewById(R.id.imgbtnLeftOfFooter);
		mImageViewNav.setOnClickListener(this);

		mImageViewSing = (ImageView) findViewById(R.id.imgbtnCenterOfFooter);
		mImageViewSing.setOnClickListener(this);

		mImageViewNews = (ImageView) findViewById(R.id.imgbtnRightOfFooter);
		mImageViewNews.setOnClickListener(this);

		// nav icon on left
		lin_area_layer = (LinearLayout) findViewById(R.id.area_layer);
		lin_area_layer.setOnClickListener(this);

		// system set
		mImageViewWebsite	=	(ImageView) findViewById(R.id.nav_bt_website);
		mImageViewMyself 	= 	(ImageView) findViewById(R.id.nav_bt_user_info);
		mImageViewActive	=	(ImageView) findViewById(R.id.nav_bt_activie);
		mImageViewNearBy	=	(ImageView) findViewById(R.id.nav_bt_nearby);
		mImageViewScore		=	(ImageView) findViewById(R.id.nav_bt_score);
		mImageViewShopping	=	(ImageView) findViewById(R.id.nav_bt_shopping);
		mImageViewSetting	=	(ImageView) findViewById(R.id.nav_bt_setting);
		mImageViewHome		=	(ImageView) findViewById(R.id.nav_bt_home);
		
		
		
		mImageViewMyself.setOnClickListener(this);
		mImageViewWebsite.setOnClickListener(this);
		mImageViewActive.setOnClickListener(this);
		mImageViewNearBy.setOnClickListener(this);
		mImageViewScore.setOnClickListener(this);
		mImageViewShopping.setOnClickListener(this);
		mImageViewSetting.setOnClickListener(this);
		mImageViewHome.setOnClickListener(this);
		// 通知服务获取ad数据
		loadFragment(from);
	}

	/**
	 * 根据 from加载不同的随便布局
	 * 
	 * @param from
	 */
	private void loadFragment(final String from) {
		// MainService.newTask(ts);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HashMap<String, Object> hm = new HashMap<String, Object>();
				Task ts = null;
				// 1.首页
				if ("splash".equals(from)) {
					ts = new Task(TaskType.GET_HOME, hm);
				}
				/*else
				// 2.电影
				if ("dianying".equals(from)) {
					ts = new Task(TaskType.GET_DIANYING, hm);
				} */

				if (ts != null)
					refresh(ts.getTaskID());
			}
		}, 100);

	}

	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		int type = (Integer) param[0];
		FragmentTransaction transaction = this.getSupportFragmentManager()
				.beginTransaction();
		loadFram(type, transaction);
	}

	private void loadFram(int key, FragmentTransaction transaction) {
		Fragment frag;
		switch (key) {
		case TaskType.GET_HOME:
			frag = new FragementYanChu(c, mSlideHolder);
			transaction.replace(R.id.linearcontent, frag);
			transaction.commit();
			break;
		default:
			break;
		}

		// mSlideHolder.toggle();
	}

	Intent intent = new Intent();

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		switch (v.getId()) {
		case R.id.imgbtnLeftOfFooter:
			mSlideHolder.toggle();
			break;
		case R.id.imgbtnCenterOfFooter:
			intent.setClass(this, ActivityListenMenue.class);
			startActivity(intent);
			break;
		case R.id.area_layer:
			intent.setClass(this, CityList.class);
			startActivity(intent);
			break;
		case R.id.nav_bt_user_info:
			intent.setClass(this, ActivityPersonalInfo.class);
			startActivity(intent);
			break;
		case R.id.nav_bt_website:
			Uri uri = Uri.parse("http://www.baidu.com");  
            intent = new Intent(Intent.ACTION_VIEW, uri);  
            startActivity(intent);  
			break;
		case R.id.nav_bt_home:
			mSlideHolder.toggle();
			break;
		case R.id.nav_bt_setting:
			intent.setClass(this, ActivitySet.class);
			startActivity(intent);
			break;
		default:
			break;
		}

		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}
}
