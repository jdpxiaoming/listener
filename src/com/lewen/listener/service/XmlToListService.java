package com.lewen.listener.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

import com.lewen.listener.bean.Question;

import android.util.Xml;

public class XmlToListService {
	
	/*public static rsp_login GetLogin(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		rsp_login rsp = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				rsp = new rsp_login();
				break;
			case XmlPullParser.START_TAG:
				String tag = parser.getName();
				if(rsp!=null){
					if ("type".equals(tag)) {
						rsp.setType(parser.nextText());
					} else if ("cmd".equals(tag)) {
						rsp.setCmd(parser.nextText());
					} else if ("userId".equals(tag)) {
						rsp.setUserId(parser.nextText());
					} else if ("roleId".equals(tag)) {
						rsp.setRoleId(parser.nextText());
					} else if ("userLoginRet".equals(tag)) {
						rsp.setUserLoginRet(parser.nextText());
					} else if ("ierrorCode".equals(tag)) {
						rsp.setIerrorCode(parser.nextText());
					} else if ("menuShow".equals(tag)) {
						rsp.setMenuShow(parser.nextText());
					} else if ("err".equals(tag)) {
						rsp.setErr(parser.nextText());
					} 
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return rsp;
	}*/
	
	public static List<Question> GetQuestionList(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		List<Question> news = null;
		Question newInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				news = new ArrayList<Question>();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if("main".equals(name)){
					newInfo = new Question();
				}
				if(newInfo!=null){
					
					if("id".equals(name))
						newInfo.setId(parser.nextText());
					if("title".equals(name)){
						newInfo.setAnswer(parser.nextText());
					}
//					if("parent".equals(name)){
//						newInfo.setParent_id(parser.nextText());
//					}
//					if("type".equals(name)){
//						newInfo.setType(parser.nextText());
//					}
					
				}
				break;
			case XmlPullParser.END_TAG:
				if("node".equals(parser.getName())){
					news.add(newInfo);
					newInfo = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return news;
	}
	
	/**
	 * 获取指定点的播放地址
	 * @param str
	 * @return
	 * @throws Exception
	 *//*
	public static List<channelOnLine> GetVideoAddress(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		List<channelOnLine> lists=new ArrayList<channelOnLine>();
		channelOnLine talkpic = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if("channel".equals(name)){
					talkpic = new channelOnLine();
				}
				if(talkpic!=null){
					if("device_name".equals(name))
						talkpic.setDevice_name(parser.nextText());
					if("device_type".equals(name)){
						talkpic.setDevice_type(parser.nextText());
					}
					if ("userName".equals(name)) {
						talkpic.setUserName(parser.nextText());
					}
					if("userPsw".equals(name)){
						talkpic.setUserPsw(parser.nextText());
					}
					if("device_ipAddr".equals(name)){
						talkpic.setDevice_ipAddr(parser.nextText());
					}
					if("player_Addr".equals(name)){
						talkpic.setPlayer_Addr(parser.nextText());
					}
					if("device_portNo".equals(name)){
						talkpic.setDevice_portNo(parser.nextText());
					}
					if("channelNo".equals(name)){
						talkpic.setChannelNo(parser.nextText());
					}
					if("channelName".equals(name)){
						talkpic.setChannelName(parser.nextText());
					}
					if("playerAddrType".equals(name)){
						talkpic.setPlayerAddrType(parser.nextText());
					}
					if("device_id".equals(name)){
						talkpic.setDevice_id(parser.nextText());
					}
					if("channelId".equals(name)){
						talkpic.setChannelId(parser.nextText());
					}
					if("parise".equals(name)){
						talkpic.setPraise(parser.nextText());
					}
					if("watchNum".equals(name)){
						talkpic.setWatch(parser.nextText());
					}
					if("historyWatch".equals(name)){
						talkpic.setHistory_watch(parser.nextText());
					}
				}
				break;
			case XmlPullParser.END_TAG:
				if("channel".equals(parser.getName())){
//					talkpic = new channelOnLine();
					lists.add(talkpic);
				}
				break;
			}
			eventType = parser.next();
		}
		
		return lists;
	}
	
	
	 * 解析失败返回NUll
	 
	public static history_video  GetPlayVideoHistory(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		history_video newInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				newInfo = new history_video();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if(newInfo!=null){
					
					if("deviceId".equals(name))
						newInfo.setDeviceId(parser.nextText());
					if("channelId".equals(name)){
						newInfo.setChannelId(parser.nextText());
					}
					if("playAddr".equals(name)){
						newInfo.setPlayaddr(parser.nextText());
					}
					if("ierrorCode".equals(name)){
						newInfo.setErr(parser.nextText());
					}
					if("err".equals(name)){
						newInfo.setErrdesc(parser.nextText());
					}
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		
		return newInfo;
	}
	
	 * 解析失败返回NUll
	 * 
	 * <JoyMon>
		<type>rsp</type>
		<cmd>57354</cmd>
		<deviceId>82</deviceId>
		<channelId>244</channelId>
		<listCount>1</listCount>
		<playAddr_0>rtmp://202.136.60.234/record/82-1-20140514132816</playAddr_0>
		<ierrorCode>0</ierrorCode>
		<err>ok</err>
		</JoyMon>
	 * 
	 
	public static List<history_video>  GetPlayVideoHistoryCollection(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		
		List<history_video> hList = new ArrayList<history_video>();
		history_video newInfo = null;
		int count = Integer.parseInt(str.substring(str.indexOf("<listCount>")+11,str.indexOf("</listCount>")));
		
		if(count>0){
			
			for(int i=0;i<count;i++){
				String address = str.substring(str.indexOf("<"+i+">")+(i+"").length()+2,str.indexOf("</"+i+">"));
				newInfo = new history_video();
				newInfo.setPlayaddr(address);
				hList.add(newInfo);
			}
		}
		
		return hList;
	}
	
	
	 * 赞次通道的count总数
	 
	public static rsp_parise  GetCountOfZan(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		rsp_parise newInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				newInfo = new rsp_parise();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				
				if(newInfo!=null){
					
					if("parise".equals(name))
						newInfo.setParise_count(parser.nextText());
					if("err	".equals(name)){
						newInfo.setErr(parser.nextText());
					}
					if("errdesc".equals(name)){
						newInfo.setErrdesc(parser.nextText());
					}
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		
		return newInfo;
	}
	
	
	*//**
	 * 获取在线用列表
	 * @param str
	 * @return
	 * @throws Exception
	 *//*
	public static List<String> GetUserNameList(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		List<String> news = null;
		String newInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				news = new ArrayList<String>();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
					if("username".equals(name)){
						newInfo=parser.nextText();
						System.out.println(newInfo);
						news.add(newInfo);
					}else if("userNum".equals(name)){
						System.out.println("userNum:"+parser.nextText());
					}
				break;
			case XmlPullParser.END_TAG:
				if("username".equals(parser.getName())){
//					news.add(newInfo);
				}
				break;
			}
			eventType = parser.next();
		}
		
		return news;
	}
	
	
	*//**
	 * 获取充值记录列表
	 * @param str
	 * @return
	 * @throws Exception
	 *//*
	public static List<rsp_recharge_record> GetRechargeList(String str)throws Exception{
		if(str==null||"".equals(str))
			return null;
		List<rsp_recharge_record> news = null;
		rsp_recharge_record newInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		InputStream  inputStream   =   new   ByteArrayInputStream(str.getBytes());
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				news = new ArrayList<rsp_recharge_record>();
				break;
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if("buy".equals(name)){
					newInfo = new rsp_recharge_record();
				}
				if(newInfo!=null){
					
					 if("buyid".equals(name)){
						newInfo.setBuyId(parser.nextText());
					}else  if("begintime".equals(name)){
						newInfo.setBeginTime(parser.nextText());
					}else  if("amount".equals(name)){
						newInfo.setAmount(parser.nextText());
					}else  if("balance".equals(name)){
						newInfo.setBalance(parser.nextText());
					}
					
				}
				break;
			case XmlPullParser.END_TAG:
				if("buy".equals(parser.getName())){
					news.add(newInfo);
					newInfo = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return news;
	}
	
	*//**
	 * 获取在线用户发来的语音数据
	 * @param str
	 * @return
	 *//*
	public static byte[] getRspOfSpeak(String str){
		byte[] result =null;
		
		if(str!=null&&str.contains(">")){
			
			String temp = str.substring(str.lastIndexOf(">")+1,str.length());
			
			result = temp.getBytes();
		}
		
		return result;
	}*/
}
