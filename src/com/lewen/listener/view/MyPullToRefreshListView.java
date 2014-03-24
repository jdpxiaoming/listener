package com.lewen.listener.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MyPullToRefreshListView extends PullToRefreshListView {

	public MyPullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyPullToRefreshListView(
			Context context,
			com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode,
			com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle style) {
		super(context, mode, style);
		// TODO Auto-generated constructor stub
	}

	public MyPullToRefreshListView(Context context,
			com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode) {
		super(context, mode);
		// TODO Auto-generated constructor stub
	}

	public MyPullToRefreshListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	 private float xDistance, yDistance, xLast, yLast;  
	 
	@Override
	public boolean onInterceptHoverEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		 switch (ev.getAction()) {
		    case MotionEvent.ACTION_MOVE:
		        this.getParent().requestDisallowInterceptTouchEvent(true);
		        break;
		    case MotionEvent.ACTION_UP:
		    case MotionEvent.ACTION_CANCEL:
		    	this.getParent().requestDisallowInterceptTouchEvent(false);
		        break;
		    }
		return super.onInterceptHoverEvent(ev);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		// TODO Auto-generated method stub
		 requestDisallowInterceptTouchEvent(true);   
		 
		return super.dispatchTouchEvent(ev);
	}
	 
}
