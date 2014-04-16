package com.lewen.listener.activity;

import java.util.HashMap;
import com.lewen.listener.R;
import com.lewen.listener.bean.Task;
import com.lewen.listener.bean.TaskType;
import com.lewen.listener.fragment.FragementYanChu;
import com.lewen.listener.view.SlideHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivitySlidingMenue extends FragmentActivity implements
		OnClickListener {

	private SlideHolder mSlideHolder;
	// private LinearLayout lin_home, lin_yetai, lin_vip, lin_search, lin_more;
	private LinearLayout lin_area_layer, lin_dy, lin_hz, lin_news, lin_msg, lin_yt;
	private ImageButton imgButtonSetting;
	private ImageView mImageViewMyself;
	private String from = "yanchu";
	private Context c;
	//footer nav
	private ImageView mImageViewNav,mImageViewSing,mImageViewNews;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_sliding_menu);
		c = this;

		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		from = getIntent().getStringExtra("tag");
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		mSlideHolder.setAllowInterceptTouch(false);
		mSlideHolder.setEnabled(false);
		
		//footer
		mImageViewNav	=	(ImageView) findViewById(R.id.imgbtnLeftOfFooter);
		mImageViewNav.setOnClickListener(this);
		
		mImageViewSing	=	(ImageView) findViewById(R.id.imgbtnCenterOfFooter);
		mImageViewSing.setOnClickListener(this);
		
		mImageViewNews	=	(ImageView) findViewById(R.id.imgbtnRightOfFooter);
		mImageViewNews.setOnClickListener(this);
		
		// nav icon on left
		lin_area_layer	= (LinearLayout) findViewById(R.id.area_layer);
		lin_area_layer.setOnClickListener(this);
		
		//system set
		imgButtonSetting	=	(ImageButton) findViewById(R.id.nav_bt_setting);
		imgButtonSetting.setOnClickListener(this);
		
		//我
		mImageViewMyself	=	(ImageView) findViewById(R.id.nav_bt_myself);
		mImageViewMyself.setOnClickListener(this);
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
				// 1.演出
				if ("splash".equals(from)) {
					ts = new Task(TaskType.GET_YANCHU, hm);
				} else
				// 2.电影
				if ("dianying".equals(from)) {
					ts = new Task(TaskType.GET_DIANYING, hm);
				} else
				// 3.会展
				if ("huizhan".equals(from)) {
					ts = new Task(TaskType.GET_HUIZHAN, hm);
				} else
				// 4.每月易迅
				if ("news".equals(from)) {
					ts = new Task(TaskType.GET_NEWS, hm);
				} else
				// 5.金鸡湖美术馆
				if ("meishuguan".equals(from)) {
					ts = new Task(TaskType.GET_MEISHUGUAN, hm);
				} else
				// 6.金鸡湖艺坛
				if ("yitan".equals(from)) {
					ts = new Task(TaskType.GET_YITAN, hm);
				}

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
		case TaskType.GET_YANCHU:
			frag = new FragementYanChu(c, mSlideHolder);
			transaction.replace(R.id.linearcontent, frag);
			transaction.commit();
			break;
		// case TaskType.GET_DIANYING:
		// frag = new FragementFilm(c,mSlideHolder);
		// transaction.replace(R.id.linearcontent, frag);
		// transaction.commit();
		// break;
		// case TaskType.GET_HUIZHAN:
		// frag = new FragementHuiZhan(c,mSlideHolder);
		// transaction.replace(R.id.linearcontent, frag);
		// transaction.commit();
		// break;
		// case TaskType.GET_NEWS:// news art info
		// frag = new FragementNews(c,mSlideHolder);
		// transaction.replace(R.id.linearcontent, frag);
		// transaction.commit();
		// break;
		// case TaskType.GET_MEISHUGUAN:
		// frag = new FragementMeiShuGuan(c,mSlideHolder);
		// transaction.replace(R.id.linearcontent, frag);
		// transaction.commit();
		// break;
		// case TaskType.GET_YITAN:
		// frag = new FragementYiTan(c,mSlideHolder);
		// transaction.replace(R.id.linearcontent, frag);
		// transaction.commit();
		// break;
		default:
			break;
		}

//		 mSlideHolder.toggle();

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
		 case R.id.area_layer:
		 intent.setClass(this, CityList.class);
		 startActivity(intent);
		 break;
		 case R.id.nav_bt_setting:
			 intent.setClass(this, ActivitySet.class);
			 startActivity(intent);
			 break;
		 case R.id.nav_bt_myself:
		 intent.setClass(this, ActivityPersonalInfo.class);
		 startActivity(intent);
		 break;
		// case R.id.linearVip:
		// intent.setClass(this, Activity_VIP.class);
		// startActivity(intent);
		// finish();
		// break;
		// case R.id.linearMore:
		// intent.setClass(this, Activity_More.class);
		// startActivity(intent);
		// finish();
		// break;
		// // 子选项的跳转
		// case R.id.linearYanchuOfSlidingMenue:
		// from="yanchu";
		// loadFragment(from);
		// break;
		// case R.id.linearDianYingOfSlidingMenue:
		// from="dianying";
		// loadFragment(from);
		// break;
		// case R.id.linearHuiZhanOfSlidingMenue:
		// from= "huizhan";
		// loadFragment(from);
		// break;
		// case R.id.linearMonthInfoOfSlidingMenue:
		// from="news";
		// loadFragment(from);
		// break;
		// case R.id.linearMeiShuOfSlidingMenue:
		// from="meishu";
		// loadFragment(from);
		// break;
		// case R.id.linearYiTanOfSlidingMenue:
		// from="yitan";
		// loadFragment(from);
		// break;
		default:
			break;
		}

		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}
}
