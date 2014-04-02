package com.lewen.listener.bean;

public class Constants {

	 //应用的key 请到官方申请正式的appkey替换APP_KEY
	public static final String APP_KEY="3302598352"; 
	//app secret
	public static final String APP_SECRET="5a8406e2c57a0ce60f65216dc076a04f";
	
	// 替换为开发者REDIRECT_URL
	public static final String REDIRECT_URL = "https://github.com/";//"http://www.sina.com";
	//新支持scope 支持传入多个scope权限，用逗号分隔
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," +
					"friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
						"follow_app_official_microblog";
//	公司dfault签名 ：9c94e11af56fe0f112ca6f6ed1ec26be
	
	/**
	 * 广点通
	 */
	public static final String GDT_APPID = "1101152570";
	public static final String GDT_BANNERPOSID = "9079537218417626401";
//	public static final String GDT_APPWALLPOSID = "9007479624379698465";
	public static final String GDT_INTERTERISTALPOSID = "8935422030341770529";
	public static final String GDT_SPLASHPOSID = "8863364436303842593";
}
