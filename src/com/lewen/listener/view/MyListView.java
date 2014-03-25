package com.lewen.listener.view;

import com.lewen.listener.util.LoggerUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {

	private static final String Tag = "MyListView";

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
////		getParent().requestDisallowInterceptTouchEvent(true);
//		return super.onInterceptTouchEvent(ev);
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		LoggerUtil.i(Tag, ev.getAction()+"!");
////		getParent().requestDisallowInterceptTouchEvent(true);
//		
//		return super.onTouchEvent(ev);
//	}
//	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) {
//		// TODO Auto-generated method stub
//		getParent().requestDisallowInterceptTouchEvent(true);
//		return super.dispatchKeyEvent(event);
//	}
}
