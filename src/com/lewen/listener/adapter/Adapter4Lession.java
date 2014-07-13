package com.lewen.listener.adapter;

import java.util.List;
import com.lewen.listener.R;
import com.lewen.listener.TBApplication;
import com.lewen.listener.bean.QuestionProp;
import com.lewen.listener.util.ImageCacheUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter4Lession extends BaseAdapter{

	private List<QuestionProp> listDatas;
	private Context mContext;

	private ISelected iSelected;
	
	public Adapter4Lession(List<QuestionProp> listDatas, Context mContext,
			ISelected iSelected2) {
		super();
		this.listDatas = listDatas;
		this.mContext = mContext;
		this.iSelected	=	iSelected2;
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
		
		if(convertView==null||convertView.findViewById(R.id.imgIconOfLessionItem)==null){
			convertView	=	LayoutInflater.from(mContext).inflate(R.layout.item_list_lession, null);
		}
		
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();

		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.icon 		= 	(ImageView) convertView.findViewById(R.id.imgIconOfLessionItem);
			viewHolder.tvName		=	(TextView) convertView.findViewById(R.id.textNameOfLessionItem);
			viewHolder.tvSummary	=	(TextView) convertView.findViewById(R.id.textSummaryOfLessionItem);
			viewHolder.tvAction		=	(TextView) convertView.findViewById(R.id.textActionOfLessionItem);
			
			convertView.setTag(viewHolder);
		}
		
		
		final QuestionProp friend = listDatas.get(position);
		
		if(friend!=null){
			
			viewHolder.tvName.setText(friend.getTitle());
			viewHolder.tvSummary.setText(friend.getSummary());
			
			//加载图片
			ImageCacheUtil ic = new ImageCacheUtil();
			ic.loadImageList(TBApplication.imageLoader, viewHolder.icon, friend.getIcon());
			
			viewHolder.tvAction.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				iSelected.selected(friend.getId());
				}
			});
		}
		
		return convertView;
	}

	 class ViewHolder{
		ImageView icon;//图片
		TextView tvName;//朋友名字
		TextView tvSummary;//朋友名字
		TextView tvAction;//应战、挑战、
	}
	
	 
	 public interface ISelected{
		 /**
		  * 选择的题型id
		  * @param id
		  */
		 public  void selected(String id);
	}
}


