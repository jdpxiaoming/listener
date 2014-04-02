package com.lewen.listener.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.adapter.Myadapter;
import com.lewen.listener.adapter.PaperAdapter;
import com.lewen.listener.bean.Constants;
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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import android.widget.RadioGroup.OnCheckedChangeListener;
/**
 * 演出
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
	
	//CITY LIST
	private MyPullToRefreshListView listview;
	private String[] mStrings = {"poe 's test 1",
			"爱上一个魔鬼的天使", "伤心太平洋", "流浪的猫", "度娘一夜情",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler" , "!! test" 
			};
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
		views.add(lin.inflate(R.layout.layout_country, null));

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

		myListView = (MyListView) views.get(0).findViewById(R.id.listViewOfFriends);
		addADHead(myListView);
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
//				ShowInfo sinfo=showList.get(arg2);
//				Intent intent =new Intent(mContext,TheatreYanchuDetail.class);
//				intent.putExtra("filmID",sinfo.getDramaID() );
//				intent.putExtra("img", sinfo.getTitleImage());
//				intent.putExtra("title", sinfo.getDramaName());
//				intent.putExtra("type", sinfo.getDramaType());
//				intent.putExtra("time", sinfo.getShowTime());
//				intent.putExtra("price", sinfo.getPrice());
//				
//				startActivity(intent);
				}
		});
		
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
//				new GetDataTask().execute();
			}
		});
		
		
		adapter4LIST = new Myadapter(Arrays.asList(mStrings),mContext);
		listview.setAdapter(adapter4LIST);
		listview.getRefreshableView().setDivider(null);
	}

	private void addADHead(MyListView myListView2) {
		final AdView adview = new AdView((Activity) mContext, AdSize.BANNER,  Constants.GDT_APPID, Constants.GDT_BANNERPOSID);
	    AbsListView.LayoutParams layout =
	        new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    adview.setLayoutParams(layout);
	    myListView2.addHeaderView(adview);
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

//	private class task4YanchuZX extends AsyncTask<String, String, String> {
//
//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//			super.onPreExecute();
//			ExproApplication.throwTips("加载演出资讯...");
//			progressbar1.setVisibility(View.VISIBLE);
//		}
//
//		@Override
//		protected String doInBackground(String... params) {
//			try {
//				adList = XmlToListService.GetAD(HttpUtil.sendGetRequest(
//						Constants.YANCHU_AD));
//			} catch (Exception e) {
//				e.printStackTrace();
//				Log.e("poe", "sax解析出错！"+e.getMessage());
//			}
//			
//			try {
//				showList = XmlToListService.GetShowInfo(HttpUtil
//						.sendGetRequest( Constants.YANCHU_ZIXUN + PageSize
//								+ "/" + PageIndex));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(String result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
////			mSlideHolder.toggle();
//			// 处理结果
//			if(adList!=null&&adList.size()>0){
//				fillGallery();
//			}else{
//				Log.i("poe", "获取广告数据失败！");
//			}
//			
//			if(showList!=null&&showList.size()>0){
//					listAdapter=new Adapter4ShowinfoList(mContext, showList,new lastIndexLoad() {
//						
//						@Override
//						public void loadData() {
//							// TODO Auto-generated method stub
//							PageIndex++;
//							task4YanchuZX2 ts = new task4YanchuZX2();
//							ts.execute();
//						}
//					});
//					listview.setAdapter(listAdapter);
//			}
//			
//			if(mSlideHolder.isOpened()){
//				mSlideHolder.toggle();
//			}
//			
//			progressbar1.setVisibility(View.GONE);
//		}
//	}
	
}
