package com.lewen.listener.adapter;

import java.util.List;

import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.activity.AcitivityQuestionOption;
import com.lewen.listener.activity.ActivityListenWord;
import com.lewen.listener.activity.ActivityPKResult;
import com.lewen.listener.bean.Friend;
import com.lewen.listener.util.ImageCacheUtil;
import com.lewen.listener.util.ToastUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter4Friend extends BaseAdapter{

	private List<Friend> listDatas;
	private Context mContext;

	
	public Adapter4Friend(List<Friend> listDatas, Context mContext) {
		super();
		this.listDatas = listDatas;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listDatas.indexOf(listDatas.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView==null||convertView.findViewById(R.id.imgIconOfFriend)==null){
			convertView	=	LayoutInflater.from(mContext).inflate(R.layout.item_list_friend, null);
		}
		
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();

		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.icon 		= 	(ImageView) convertView.findViewById(R.id.imgIconOfFriend);
			viewHolder.tvName		=	(TextView) convertView.findViewById(R.id.textNameOfFriendItem);
			viewHolder.imgSex		=	(ImageView) convertView.findViewById(R.id.imgSexOfFriendItem);
			viewHolder.tvLevel		=	(TextView) convertView.findViewById(R.id.textLevelOfFriendItem);
			viewHolder.tvScore		=	(TextView) convertView.findViewById(R.id.textScoreOfFriendItem);
			viewHolder.tvAction		=	(TextView) convertView.findViewById(R.id.textActionOfFriendItem);
			
			convertView.setTag(viewHolder);
		}
		
		
		final Friend friend = listDatas.get(position);
		if(friend!=null){
			ImageCacheUtil ic = new ImageCacheUtil();
			
			viewHolder.tvName.setText(friend.getName());
			viewHolder.tvLevel.setText(friend.getLevel());
//			viewHolder.tvScore.setText(friend.get);
			
			//加载图片
			ic.loadImageList(TBApplication.imageLoader, viewHolder.icon, friend.getIcon());
			
			if("0".equals(friend.getSex())){//保密、未知
				viewHolder.imgSex.setImageResource(R.drawable.icon_home_male);
			}else if("1".equals(friend.getSex())){//男
				viewHolder.imgSex.setImageResource(R.drawable.icon_home_male);
			}else if("2".equals(friend.getSex())){//女
				viewHolder.imgSex.setImageResource(R.drawable.icon_home_femal);
			}
			
			//act动作：1(应战 2挑战 3查看结果)
			final String act = friend.getAct();
			if("1".equals(act)){
				viewHolder.tvAction.setText("应战");
				viewHolder.tvAction.setBackgroundResource(R.drawable.red2_to_blue);
			}else if("2".equals(act)){
				viewHolder.tvAction.setText("挑战");
				viewHolder.tvAction.setBackgroundResource(R.drawable.red_to_blue);
			}else {
				viewHolder.tvAction.setText("查看结果");
				viewHolder.tvAction.setBackgroundResource(R.drawable.green_to_blue);
			}
			
			viewHolder.tvAction.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					TBApplication.friend	= friend;
					TBApplication.pushPreferenceData("warname", friend.getName());
					if("1".equals(act)){//应战
						//跳转到答题界面
						TBApplication.goNext(mContext, ActivityListenWord.class);
					}else if("2".equals(act)){//发起挑战-》选题-》开始
						TBApplication.goNext(mContext, AcitivityQuestionOption.class);
					}else {//查看结果，查看pk结果页面
						TBApplication.goNext(mContext, ActivityPKResult.class);
					}
					
				}
			});
		}
		
		return convertView;
	}

	 class ViewHolder{
		ImageView icon;//图片
		ImageView imgSex;//性别
		TextView tvName;//朋友名字
		TextView tvLevel;//等级
		TextView tvScore;//朋友名字
		TextView tvAction;//应战、挑战、
	}
}
