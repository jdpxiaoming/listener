package com.lewen.listener;

import java.io.IOException;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

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
	public static	String mStrKey = "D62C7A77394E09E9AA004C7F3B8C589D2F96746D";

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
		
       
	}

	/**
	 * 提示正在建设中
	 */
	public static void showBuildTip(Context c){
		//弹出提示框
		new AlertDialog.Builder(c)
					.setTitle("提示")
					.setMessage("正在建设中...")
					.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					}).show();
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
