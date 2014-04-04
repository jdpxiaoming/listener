package com.lewen.listener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

/**
 * 一些全局的布置
 * @author Administrator
 */
public class TBApplication extends Application {

	public static SharedPreferences sp = null;
	public static TBApplication  App;
	public static String version = "";
	public static DisplayMetrics metrics =null;
	public static ImageLoader imageLoader=ImageLoader.getInstance();
	// 百度MapAPI的管理类
	public LocationClient mLocationClient;
	private MyLocationListener mMyLocationListener;
	public TextView mLocationResult;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		App = this;
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		
		//检测 当前版本 ，并生成当前版本的V+版本号
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			 version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
		
		mLocationClient = new LocationClient(this);
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
	}

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			//Receive Location 
			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation){
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//				sb.append("\ndirection : ");
//				sb.append(location.getDirection());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				//运营商信息
//				sb.append("\noperationers : ");
//				sb.append(location.getOperators());
//			}
			sb.append(location.getCity());
			logMsg(sb.toString());
			Log.i("BaiduLocationApiDem", sb.toString());
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			
		}
	}
	
	/**
	 * 显示请求字符串
	 * @param str
	 */
	public void logMsg(String str) {
		try {
			if (mLocationResult != null)
				mLocationResult.setText(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 存放token的方法
	 * 
	 * @param tag
	 * @param data
	 */
	public static void pushPreferenceData(String tag, String data) {
		Editor editor = sp.edit();
		editor.putString(tag, data);
		editor.commit();
	}

	/**
	 * 取出参数token
	 * 
	 * @param tag
	 *            标记
	 * @return
	 */
	public static String getPreferenceData(String tag) {

		return sp.getString(tag, null);
	}
}
