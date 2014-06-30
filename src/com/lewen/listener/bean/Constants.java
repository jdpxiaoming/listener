package com.lewen.listener.bean;

public class Constants {

	 //应用的key 请到官方申请正式的appkey替换APP_KEY
	public static final String QQ_APP_ID="101050174"; 
	public static final String APP_KEY="3302598352"; 
	//app secret
	public static final String APP_SECRET="5a8406e2c57a0ce60f65216dc076a04f";
	
	// 替换为开发者REDIRECT_URL
	public static final String REDIRECT_URL = "https://github.com/";//"http://www.sina.com";
	//新支持scope 支持传入多个scope权限，用逗号分隔
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," +
					"friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
						"follow_app_official_microblog";
	
	public static final String RENREN_APP_ID = "266425";
	public static final String RENREN_API_KEY = "2c7696ed99b34d8091d79a97737a3028";
	public static final String RENREN_SECRET_KEY = "48784ab4ed0e4068aae4f0ce9d34ceea";
	
//	公司dfault签名 ：9c94e11af56fe0f112ca6f6ed1ec26be
	/**
	 * 广点通
	 */
	public static final String GDT_APPID = "1101152570";
	public static final String GDT_BANNERPOSID = "9079537218417626401";
//	public static final String GDT_APPWALLPOSID = "9007479624379698465";
	public static final String GDT_INTERTERISTALPOSID = "8935422030341770529";
	public static final String GDT_SPLASHPOSID = "8863364436303842593";
	
	
	public static final String url_login_replay="http://ting.joysw.cn/index.php/api/login/reply";			//登录 接口
	public static final String url_splash_pic="http://ting.joysw.cn/index.php/api/index/start";				//启动图片
	public static final String url_get_friends="http://ting.joysw.cn/index.php/api/default/index";			//听友们(通过qq获取列表)
	public static final String url_get_local_province="http://ting.joysw.cn/index.php/api/default/province";//省排行榜
	public static final String url_get_country_best="http://ting.joysw.cn/index.php/api/default/best";		//全国牛人榜
	public static final String url_get_country_hot="http://ting.joysw.cn/index.php/api/default/hotcourse";	//全国热播榜
	public static final String url_get_country_star="http://ting.joysw.cn/index.php/api/default/star";		//全国新星榜
	public static final String url_get_customer_info="http://ting.joysw.cn/index.php/api/member/info";		//个人信息
	public static final String url_mod_customer_info="http://ting.joysw.cn/index.php/api/member/modinfo";	//修改个人信息
	public static final String url_get_follow_info="http://ting.joysw.cn/index.php/api/member/following";	//获取我关注的人
	public static final String url_get_follower_info="http://ting.joysw.cn/index.php/api/member/follower";	//关注我的人
	public static final String url_friend_add="http://ting.joysw.cn/index.php/api/member/friendadd";		//关注别人
	public static final String url_friend_delete="http://ting.joysw.cn/index.php/api/member/frienddel";		//取消关注
	public static final String url_get_save_lession="http://ting.joysw.cn/index.php/api/member/favorite";	//我收藏的课件
	public static final String url_get_my_course="http://ting.joysw.cn/index.php/api/course/my";			//我的课件
	public static final String url_get_sub_course="http://ting.joysw.cn/index.php/api/course/showsub";		//课件小节
	public static final String url_get_shop="http://ting.joysw.cn/index.php/api/shop/index";				//商城首页课件
	public static final String url_get_shop_prop="http://ting.joysw.cn/index.php/api/shop/prop";			//商城道具
	public static final String url_get_shop_price="http://ting.joysw.cn/index.php/api/shop/price";			//商城购买红宝石
	
}

