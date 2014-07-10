package com.lewen.listener.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lewen.listener.bean.Friend;
import com.lewen.listener.bean.UserInfo;

/**
 * 
 * @author poe
 *
 */
public class JsonService {

	public static UserInfo getUserInfoMoney(String input){
		UserInfo user =null;
		
		
		 JSONObject object;
	        //"data":{"rmb":"0","jf":"5","tb":"10000",
	        //"hbs":"10000","ts":"0","cb":"0","fd":"0","sd":"0","gz":"0","fs":"0"}
			try {
				object = new JSONObject(input);
				JSONObject data = object.getJSONObject("data");
				user	=	 new UserInfo();
				user.setUser_rmb(data.getString("rmb"));
				user.setUser_level(data.getString("lv"));
				user.setUser_tb(data.getString("tb"));
				user.setUser_red(data.getString("hbs"));
				user.setReplay_time(data.getString("cb"));
				user.setReverse_time(data.getString("fd"));
				user.setUser_speed(data.getString("sd"));
				user.setTips(data.getString("ts"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return user;
	}
	
	public static List<Friend> getMyFriends(String input){
		List<Friend> result =new ArrayList<Friend>();
		
		try {
			JSONObject object = new JSONObject(input);
			JSONArray jsonArray = object.getJSONArray("data");
			if(jsonArray!=null&&jsonArray.length()>0){
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jfriend = jsonArray.getJSONObject(i);
					Friend friend = new Friend();
					
					friend.setUid(jfriend.getString("uid"));
					friend.setName(jfriend.getString("truename"));
					friend.setIcon(jfriend.getString("img"));
					friend.setSex(jfriend.getString("sex"));
					friend.setType(jfriend.getString("type"));
					friend.setAct(jfriend.getString("act"));
					friend.setWarid(jfriend.getString("warid"));
					friend.setLevel(jfriend.getString("level"));
					
					result.add(friend);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
