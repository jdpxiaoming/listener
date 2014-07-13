package com.lewen.listener.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.adapter.Adapter4Friend;
import com.lewen.listener.adapter.Myadapter;
import com.lewen.listener.adapter.PaperAdapter;
import com.lewen.listener.bean.Constants;
import com.lewen.listener.bean.Friend;
import com.lewen.listener.bean.Queue;
import com.lewen.listener.bean.UserInfo;
import com.lewen.listener.http.HttpUtil;
import com.lewen.listener.service.JsonService;
import com.lewen.listener.util.ImageCacheUtil;
import com.lewen.listener.util.LoggerUtil;
import com.lewen.listener.util.ToastUtil;
import com.lewen.listener.view.MyListView;
import com.lewen.listener.view.MyPullToRefreshListView;
import com.lewen.listener.view.MyViewPager;
import com.lewen.listener.view.SlideHolder;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
/**
 * 贵州，本市
 * @author poe
 */
@SuppressLint("ValidFragment")
public class FragementYanChu extends Fragment implements OnClickListener ,OnTouchListener{

	protected static final String Tag = FragementYanChu.class.toString();
	private SlideHolder mSlideHolder;//control menue
	private boolean MSLIDINGTAG=false;
	private MyViewPager mViewPager;
	private LayoutInflater lin;
	private PagerAdapter adapter;
	private int toId = 0;
	private Context mContext;
	//progressbar
	private LinearLayout progressbar1,progressbar2;
	private RadioGroup mRadioGroup;
	private int[] idsTabbar=new int[]{R.id.button_left,R.id.button_middle,R.id.button_right};

	//FRIENDS LIST
	private MyListView myListView;
	private LinearLayout linAD;
	private UserInfo user;
	
	//CITY LIST
	private MyPullToRefreshListView listview,listviewCountry;
	private String[] mStrings = {"poe 's test 1",
			"爱上一个魔鬼的天使", "伤心太平洋", "流浪的猫", "度娘一夜情",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu"
			};
	//friend
	private TextView textLv,textTb,textHbs,textRmb;
	private List<Friend> flist;
		
		
	private BaseAdapter adapter4LIST;

	public FragementYanChu(Context mContext,SlideHolder mSlideHolder) {
		super();
		this.mContext = mContext;
		this.mSlideHolder=mSlideHolder;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	}

	

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		ImageCacheUtil.ClearCache(TBApplication.imageLoader);
		super.onDestroy();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		lin = inflater;
		if (container == null) {
			return null;
		}
		container = (ViewGroup) inflater.inflate(R.layout.layout_theatre, null);
		
		//tabhost action init 
		mRadioGroup = (RadioGroup) container.findViewById(R.id.rb_group);
		
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.button_left:
					toId = 0;
					mViewPager.setCurrentItem(toId);
					break;
				case R.id.button_middle:
					toId = 1;
					mViewPager.setCurrentItem(toId);
					break;
				case R.id.button_right:
					toId = 2;
					mViewPager.setCurrentItem(toId);
					break;
				}
			}
		});
		
//		progressbar = (LinearLayout) container
//				.findViewById(R.id.progressOfTheatre);
		// viewflipper
		mViewPager = (MyViewPager) container.findViewById(R.id.viewpagerOfTheatre);

		initVP();

		return container;
	}

	private void initVP() {
		List<View> views = new ArrayList<View>();
		views.add(lin.inflate(R.layout.layout_friends, null));
		views.add(lin.inflate(R.layout.layout_city, null));
		views.add(lin.inflate(R.layout.layout_city, null));

		adapter = new PaperAdapter(views);

		mViewPager.setAdapter(adapter);

		// 给viewpager增加监听器
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// 1.设置背景颜色
				if(toId!=arg0){
					toId = arg0;
					mRadioGroup.check(idsTabbar[toId]);
				}
				
				MSLIDINGTAG = false;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				System.out.println("["+arg0+"]"+"["+arg1+"]"+"["+arg2+"]");
				
				if(arg0+arg1+arg2==0.0){
					if(MSLIDINGTAG){
						System.out.println("划不动了，请展示SlidingMenue");
						mSlideHolder.toggle();
						MSLIDINGTAG = false;
					}
					
					MSLIDINGTAG = true;
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});

		//朋友们
		textLv		=	(TextView) views.get(0).findViewById(R.id.txtLevelOfFriend);
		textTb		=	(TextView) views.get(0).findViewById(R.id.txtTBOfFriend);
		textHbs		=	(TextView) views.get(0).findViewById(R.id.txtRedOfFriend);
		textRmb		=	(TextView) views.get(0).findViewById(R.id.txtRMBOfFriend);
		
		linAD		=	(LinearLayout) views.get(0).findViewById(R.id.linAD);
		myListView = (MyListView) views.get(0).findViewById(R.id.listViewOfFriends);
		addADHead(linAD);
		
		/*
//		progressbar1 = (LinearLayout) views.get(0).findViewById(R.id.progressbarOfShowInfo);
		ArrayAdapter<String> mAdapter =  
				new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, Arrays.asList(mStrings));
		myListView.setAdapter(mAdapter);	
		
		
		myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				LoggerUtil.i(Tag, "item clicked!");
				ToastUtil.showToastShort( "clicked!+ on  " +arg2,mContext);
				// TODO Auto-generated method stub
				}
		});*/
		
		//本市,suzhou
		listview = (MyPullToRefreshListView)views.get(1).findViewById(R.id.listview);
		listview.getRefreshableView().setSelector(R.drawable.item_selector);
		listview.setOnRefreshListener(new OnRefreshListener<ListView>() {
			
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(mContext.getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});
		
		
		List<Queue> listDatas = new ArrayList<Queue>();
		
		for(int i=0;i<mStrings.length;i++){
			
			Queue que  = new Queue("", mStrings[i], (float) 4.0);
			listDatas.add(que);
		}
		
		adapter4LIST = new Myadapter(listDatas,mContext);
		listview.setAdapter(adapter4LIST);
		listview.getRefreshableView().setDivider(null);
		
		
		//全国
		listviewCountry = (MyPullToRefreshListView)views.get(2).findViewById(R.id.listview);
		listviewCountry.getRefreshableView().setSelector(R.drawable.item_selector);
		listviewCountry.setOnRefreshListener(new OnRefreshListener<ListView>() {
			
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(mContext.getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});
		listviewCountry.setAdapter(adapter4LIST);
		listviewCountry.getRefreshableView().setDivider(null);
		
		getUserInfo();
	}
	
	//test get user info by cookies
	private void getUserInfo() {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					NameValuePair pair1 = new BasicNameValuePair("page", 0+"");
					NameValuePair pair2 = new BasicNameValuePair("pagesize", 10+"");
			        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			        pairList.add(pair1);
			        pairList.add(pair2);
			         
			        String result	=	HttpUtil.sendPost(pairList,Constants.url_get_friends);// "http://ting.joysw.cn/index.php/api/members/info");
			        if(!TextUtils.isEmpty(result)){
			        	
			        	System.out.println(result);
			        	flist = JsonService.getMyFriends(result);
			        	if(flist.size()>0){
			        		System.out.println("success!");
			        	}
			        }
			        
			        //获取用户的各类财富值、红宝石、听币、rmb
			        List<NameValuePair> pairList2 = new ArrayList<NameValuePair>();
			        //test money
			        String result2	=	HttpUtil.sendPost(pairList2,"http://ting.joysw.cn/index.php/api/members/money");
			        System.out.println(result2);
			        user = JsonService.getUserInfoMoney(result2);
			        if(user!=null){
			        	mHandler.sendEmptyMessage(100);
			        }
				}
			}).start();
		}

	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		
			switch(msg.what){
			
			case 100://friend toper refresh
				if(user!=null){
					textLv.setText(user.getUser_level());
					textTb.setText(user.getUser_tb());
					textHbs.setText(user.getUser_red());
					textRmb.setText(user.getUser_rmb());
				}
				
				if(flist!=null&&flist.size()>0){
					Adapter4Friend adapter = new Adapter4Friend(flist, mContext);
	        		myListView.setAdapter(adapter);
				}else{
					//默认的、or从数据库去拉去信息
				}
				
				break;
			}
			
		}
		
	};
	
	private class GetDataTask extends AsyncTask<String, Integer, String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			listview.onRefreshComplete();
			listviewCountry.onRefreshComplete();
		}
		
	}

	private void addADHead(LinearLayout lin) {
		final AdView adview = new AdView((Activity) mContext, AdSize.BANNER,  Constants.GDT_APPID, Constants.GDT_BANNERPOSID);
	    AbsListView.LayoutParams layout =
	        new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    adview.setLayoutParams(layout);
	    lin.addView(adview);
//	    myListView2.addHeaderView(adview);
	    adview.fetchAd(new AdRequest());
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.linearFriendsOfTheatre:
			toId = 0;
			break;
		case R.id.linearLocalOfTheatre: 
			toId = 1;
			break;
		case R.id.linearCountryOfTheatre:
			toId  =2;
			break;
//		case R.id.imgbtnLeftOfFooter:
//			mSlideHolder.toggle();
//			break;
		default:
			break;
		}
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		 switch (event.getAction()) {
		    case MotionEvent.ACTION_MOVE:
		        mViewPager.requestDisallowInterceptTouchEvent(true);
		        break;
		    case MotionEvent.ACTION_UP:
		    case MotionEvent.ACTION_CANCEL:
		    	mViewPager.requestDisallowInterceptTouchEvent(false);
		        break;
		    }
		return false;
	}

}
