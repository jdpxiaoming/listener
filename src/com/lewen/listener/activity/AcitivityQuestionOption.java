package com.lewen.listener.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;
import com.lewen.listener.adapter.Adapter4Lession;
import com.lewen.listener.adapter.Adapter4Lession.ISelected;
import com.lewen.listener.bean.Constants;
import com.lewen.listener.bean.Question;
import com.lewen.listener.bean.QuestionProp;
import com.lewen.listener.http.HttpUtil;
import com.lewen.listener.service.JsonService;
import com.lewen.listener.service.XmlToListService;
import com.lewen.listener.util.ToastUtil;
/**
 * 选择题库页面
 * @author poe
 *
 */
public class AcitivityQuestionOption extends BaseActivity {

	private List<QuestionProp> datas ;
	private ListView  listview;
	private Adapter4Lession adapter ;
	private Context mContext;
	private ProgressBar progressbar;
	
	private Handler mHandler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			progressbar.setVisibility(View.GONE);
			switch (msg.what) {
			case 0://..
				if(null!=datas){
					adapter 	=	new Adapter4Lession(datas, mContext	,new ISelected() {
								
								@Override
								public void selected(String id) {
									getLessionsById(id);
								}
							});
		        	listview.setAdapter(adapter);
				}else{
					ToastUtil.throwTipShort("获取数据失败！");
				}
				break;
			case 1://选定题目，跳转
				if(msg.obj!=null){
					String url = (String) msg.obj;
					Intent intent = new Intent(mContext,ActivityListenWord.class);
					intent.putExtra("xml", url);
					System.out.println(url);
					mContext.startActivity(intent);
					overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
					finish();
				}else{
					ToastUtil.throwTipShort("获取数据失败！");
				}
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
		
		setContentView(R.layout.layout_question_menue);
		mContext  = this;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		progressbar		=	(ProgressBar) findViewById(R.id.progress);
		listview		=	(ListView) findViewById(R.id.listviewOfLession);
		findViewById(R.id.imgbtnBack).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//获取课件
		getLessions();
	}
	//test get user info by cookies
		private void getLessions() {
				// TODO Auto-generated method stub
			progressbar.setVisibility(View.VISIBLE);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
				        String result	=	HttpUtil.getConnection(Constants.url_get_my_course);
				        if(!TextUtils.isEmpty(result)){
				        	System.out.println(result);
				        	//解析 lession列表 点击谁再去拉去对应的数据
				        	datas = JsonService.getMyLessions(result);
				        }
				        mHandler.sendEmptyMessage(0);
					}
				}).start();
			}

		/**
		 * 获取对应的题目xml详细信息
		 * @param id
		 */
		private void getLessionsById(final String id) {
			// TODO Auto-generated method stub
			progressbar.setVisibility(View.VISIBLE);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					NameValuePair pair1 = new BasicNameValuePair("id", id);
			        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			        pairList.add(pair1);
			        
			    	String url_xml =null;
			        String course = HttpUtil.sendPostWithoutSession(pairList, Constants.url_get_sub_course);
			        if(!TextUtils.isEmpty(course)){
			        	System.out.println(course);
			        	//获取 课件的xml地址
			        	try {
							JSONObject data = new JSONObject(course).getJSONArray("data").getJSONObject(0);
							url_xml = data.getString("path");
						
						}	 catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	
			        	mHandler.sendMessage(mHandler.obtainMessage(1, url_xml));
			        }
				}
			}).start();
		}
		
}
