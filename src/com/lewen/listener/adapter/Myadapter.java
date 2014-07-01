package com.lewen.listener.adapter;

import java.util.ArrayList;
import java.util.List;
import com.lewen.listener.R;
import com.lewen.listener.bean.Queue;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Myadapter extends BaseAdapter {
	
//	private List<Queue> datas;

	private List<List<Queue>> cache= new ArrayList<List<Queue>>();
	private Context mContext;
	
	 public Myadapter(List<Queue> datas, Context mContext){
//		this.datas = datas;
		this.mContext = mContext;
		this.cache = doSplite(datas);
	}
	
	 /**
	  * 数据分3类
	  * 1
	  * 11
	  * 111
	  * @param datas2
	  * @return
	  */
	private List<List<Queue>> doSplite(List<Queue> datas2) {
		List<List<Queue>> mList =new ArrayList<List<Queue>>();
		
		if(datas2==null||datas2.size()==0){
		}else{
			List<Queue> list = new ArrayList<Queue>();
			list.add(datas2.get(0));
			mList.add(list);
			
			if(datas2.size()==1){
				//default
			}else if(datas2.size()<10){
				
				addNote(mList,1,2,datas2);
				
			}else{
				addNote(mList,1,2,datas2.subList(0, 9));
				addNote(mList,9,3,datas2);
			}
		}
		
		return mList;
	}
	
	/**
	 * @param mList 引用的结果集合
	 * @param startPos  开始整理的位置
	 * @param max		每项数据的最大值
	 * @param datas2    数据源List<String>
	 */
	private void addNote(List<List<Queue>> mList, int startPos,int max, List<Queue> datas2) {
		// TODO Auto-generated method stub
		List<Queue> list2=new ArrayList<Queue>();
		
		for(int i=startPos;i<datas2.size();i++){
			
			if(list2.size()<max){
				
				list2.add(datas2.get(i));
				
				if(i==datas2.size()-1||list2.size()==max){
					/*
					if(i==datas2.size()-1&&list2.size()<max){
						int j = list2.size();
						while(j<max){
							list2.add("");
							j++;
						}
					}*/
					mList.add(list2);
					list2 =new ArrayList<Queue>();
				}
			}
		}
	}

	@Override
	public int getCount() {
		return cache.size();
	}

	@Override
	public Object getItem(int position) {
		return cache.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		System.out.println(position+"&&&&&&&&&&");
		List<Queue> mValue = cache.get(position);
		int tag =1;
		
//		if(position == cache.size()-1){
		if(position==0){
			
		}else if(position>0&&position<5){
				tag = 2;
		}else {
				tag = 3;
		}
//		}
		
		switch (tag) {
		case 1:
//			 if(convertView==null||convertView.findViewById(R.id.text1OfItemOne)==null){
				 convertView	=	LayoutInflater.from(mContext).inflate(R.layout.item_list_one, null);
			     convertView.setTag(R.id.text1OfItemOne, convertView.findViewById(R.id.text1OfItemOne));
//			 }
			
			 ((TextView)convertView.getTag(R.id.text1OfItemOne)).setText(mValue.get(0).getName());
			 
			break;
		case 2:
//			if(convertView==null||convertView.findViewById(R.id.text2OfItemTwo)==null){
				convertView	=	LayoutInflater.from(mContext).inflate(R.layout.item_list_two, null);
				
				convertView.setTag(R.id.text1OfItemTwo, convertView.findViewById(R.id.text1OfItemTwo));
				convertView.setTag(R.id.text2OfItemTwo, convertView.findViewById(R.id.text2OfItemTwo));
//			}
			
			((TextView)convertView.getTag(R.id.text1OfItemTwo)).setText(mValue.get(0).getName());
			
			if(mValue.size()>1){
				((TextView)convertView.getTag(R.id.text2OfItemTwo)).setText(mValue.get(1).getName());
			}
			
			break;
		case 3:
//			if(convertView==null||convertView.findViewById(R.id.text3OfItemThree)==null){
				convertView	=	LayoutInflater.from(mContext).inflate(R.layout.item_list_three, null);
				
				convertView.setTag(R.id.text1OfItemThree, convertView.findViewById(R.id.text1OfItemThree));
				convertView.setTag(R.id.text2OfItemThree, convertView.findViewById(R.id.text2OfItemThree));
				convertView.setTag(R.id.text3OfItemThree, convertView.findViewById(R.id.text3OfItemThree));
//			}
			((TextView)convertView.getTag(R.id.text1OfItemThree)).setText(mValue.get(0).getName());
			
			if(mValue.size()==2){
				
				((TextView)convertView.getTag(R.id.text2OfItemThree)).setText(mValue.get(1).getName());
			}
			if(mValue.size()==3){
				((TextView)convertView.getTag(R.id.text2OfItemThree)).setText(mValue.get(1).getName());
				((TextView)convertView.getTag(R.id.text3OfItemThree)).setText(mValue.get(2).getName());
			}
			break;

		default:
			break;
		}
		
		return convertView;
	}
}
