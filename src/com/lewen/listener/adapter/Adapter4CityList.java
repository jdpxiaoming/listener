package com.lewen.listener.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.lewen.listener.R;
import com.lewen.listener.bean.CityModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter4CityList extends BaseAdapter {
	private LayoutInflater inflater;
	private List<CityModel> list;
	private List<CityModel> searchList ;//满足搜索条件的集合
	private HashMap<String, Integer> alphaIndexer;
	private String[] sections;

	public Adapter4CityList(Context context, List<CityModel> list) {

		this.inflater = LayoutInflater.from(context);
		this.list = this.searchList = list;
		alphaIndexer = new HashMap<String, Integer>();
		sections = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			// 当前汉语拼音首字母
			// getAlpha(list.get(i));
			String currentStr = list.get(i).getNameSort();
			// 上一个汉语拼音首字母，如果不存在为“ ”
			String previewStr = (i - 1) >= 0 ? list.get(i - 1)
					.getNameSort() : " ";
			if (!previewStr.equals(currentStr)) {
				String name = list.get(i).getNameSort();
				alphaIndexer.put(name, i);
				sections[i] = name;
			}
		}

	}

	//String content = et.getText().toString().trim();
//	mCityNames.clear();
//	mCityNames = getSelectCityNames(content);
//	setAdapter(mCityNames);
	
	//过滤文字匹配的条件
	public void Filter(CharSequence cs){
		
		String s =cs.toString().trim();
		if(null!=s&&s.length()>0){
			
			searchList=new ArrayList<CityModel>();
			
			//筛选
			for(int i=0;i<list.size();i++){
				
				if(list.get(i).getCityName().contains(cs)){
					
					searchList.add(list.get(i));
					
				}
			}
			
		}
		
		//刷新adapter
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return searchList.size();
	}

	@Override
	public Object getItem(int position) {
		return searchList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(searchList.get(position).getCityName());
		String currentStr = searchList.get(position).getNameSort();
		String previewStr = (position - 1) >= 0 ? searchList.get(position - 1)
				.getNameSort() : " ";
		if (!previewStr.equals(currentStr)) {
			holder.alpha.setVisibility(View.VISIBLE);
			holder.alpha.setText(currentStr);
		} else {
			holder.alpha.setVisibility(View.GONE);
		}
		return convertView;
	}

	private class ViewHolder {
		TextView alpha;
		TextView name;
	}

}
