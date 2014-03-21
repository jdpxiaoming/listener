package com.lewen.listener.activity.parent;

import com.lewen.listener.util.LoggerUtil;
import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	private String TAG ="BaseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LoggerUtil.d(TAG, "onCreate");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		LoggerUtil.d(TAG, "onPause");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LoggerUtil.d(TAG, "onStart");
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		
		LoggerUtil.d(TAG, "onPostCreate");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LoggerUtil.d(TAG, "onResume");
	}
	
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		LoggerUtil.d(TAG, "onPostResume");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		LoggerUtil.d(TAG, "onStop");
	}
	
}
