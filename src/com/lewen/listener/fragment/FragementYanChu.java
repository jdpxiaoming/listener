package com.lewen.listener.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.adapter.Myadapter;
import com.lewen.listener.adapter.PaperAdapter;
import com.lewen.listener.util.ImageCacheUtil;
import com.lewen.listener.view.MyPullToRefreshListView;
import com.lewen.listener.view.MyViewPager;
import com.lewen.listener.view.SlideHolder;
//import com.ssac.expro.kewen.adapter.Adapter4ShowinfoList;
//import com.ssac.expro.kewen.adapter.Adapter4ShowinfoList.lastIndexLoad;
//import com.ssac.expro.kewen.adapter.Adapter4TheatreActivitesList;
//import com.ssac.expro.kewen.adapter.Adapter4TheatreActivitesList.lastIndexLoad4Activities;
//import com.ssac.expro.kewen.adapter.ImageAdapter4GalleryTheatre;
//import com.ssac.expro.kewen.adapter.PaperAdapter;
//import com.ssac.expro.kewen.bean.AD;
//import com.ssac.expro.kewen.bean.Constants;
//import com.ssac.expro.kewen.bean.ShowInfo;
//import com.ssac.expro.kewen.bean.Theatre;
//import com.ssac.expro.kewen.service.MainService;
//import com.ssac.expro.kewen.service.XmlToListService;
//import com.ssac.expro.kewen.sinaweibo.SinaAcitivity;
//import com.ssac.expro.kewen.util.HttpUtil;
//import com.ssac.expro.kewen.util.ImageCacheUtil;
//import com.ssac.expro.kewen.view.MyViewPager;
//import com.ssac.expro.kewen.view.NumberDotImageView;
//import com.ssac.expro.kewen.view.SlideHolder;
//import com.ssac.expro.kewen.view.SlowFlipGallery;
import android.annotation.SuppressLint;
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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

	private SlideHolder mSlideHolder;//control menue
	private MyViewPager mViewPager;
	private LayoutInflater lin;
//	private LinearLayout progressbar;
	private PagerAdapter adapter;
	private int toId = 0;
	private Context mContext;
	//progressbar
	private LinearLayout progressbar1,progressbar2;
	private RadioGroup mRadioGroup;
	private int[] idsTabbar=new int[]{R.id.button_left,R.id.button_middle,R.id.button_right};

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
//				updateTextColorBefore(toId);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});

//		listview = (ListView) views.get(0).findViewById(R.id.listviewOfShowInfo);
//		progressbar1 = (LinearLayout) views.get(0).findViewById(R.id.progressbarOfShowInfo);
		
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
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
//				}
//		});
		
		listview = (MyPullToRefreshListView)views.get(1).findViewById(R.id.listview);
		
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
//		listview.sett
//		//listview2
//		listView2= (ListView) views.get(1).findViewById(R.id.listviewOfTheatreActivies);
//		progressbar2 = (LinearLayout) views.get(1).findViewById(R.id.progressbarOfTheatreActivies);
//		listView2.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				Theatre  sinfo=theatreList.get(arg2);
//				Intent intent =new Intent(mContext,TheatreActivitiesDetail.class);
//				intent.putExtra("contentID",sinfo.getContentID() );
//				intent.putExtra("img", sinfo.getTitleImage());
//				intent.putExtra("title", sinfo.getContentTitle());
//				intent.putExtra("time", sinfo.getBeginTime());
//				
//				startActivity(intent);
//				}
//		});
//		task4YanchuZX ts1 = new task4YanchuZX();
//		ts1.execute();
	}
//	 //给 gallery 放数据。
//	  public void fillGallery() {
//		  
//	    this.mImageAdapter = new ImageAdapter4GalleryTheatre(mContext,adList);
//	    this.mGallery.setAdapter(this.mImageAdapter);
//	    final int i = adList.size();
//	    
//	    timer = new Timer();
//	    mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//	    {
//	      public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
//	      {
//	    	  FragementYanChu.this.mDotImageView.refresh(i, paramInt);
//	      }
//
//		@Override
//		public void onNothingSelected(AdapterView<?> arg0) {
//			// TODO Auto-generated method stub
//		}
//	    });
//	    this.mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
//	    {
//	      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
//	      {
//	        if (paramInt == -1 + MainService.adList.size()){
////	        	jumpActivity();
//	        	ExproApplication.throwTips("go over!");
//	        }
//	      }
//	    });
//	    
//	    this.mGallery.setOnTouchListener(new View.OnTouchListener()
//	    {
//	      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
//	      {
//	        return FragementYanChu.this.gestureDetector.onTouchEvent(paramMotionEvent);
//	      }
//	    });
//	    
//	    timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				postion++;
//				handler.sendEmptyMessage(0);
//			}
//		},  10*1000, 2500);
//	 }

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