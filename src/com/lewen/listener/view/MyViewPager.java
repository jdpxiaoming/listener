package com.lewen.listener.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MyViewPager extends ViewPager{
	private float xDistance, yDistance, xLast, yLast; 
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		 switch (ev.getAction()) {  
         case MotionEvent.ACTION_DOWN:  
             xDistance = yDistance = 0f;  
             xLast = ev.getX();  
             yLast = ev.getY();  
             break;  
         case MotionEvent.ACTION_MOVE:  
             final float curX = ev.getX();  
             final float curY = ev.getY();             
             xDistance += Math.abs(curX - xLast);  
             yDistance += Math.abs(curY - yLast);  
             xLast = curX;  
             yLast = curY;  
             if(xDistance < yDistance){  
                 return false;  
             }    
     }  
		 return super.onInterceptTouchEvent(ev);
	}

	@Override
	public void postInvalidateDelayed(long delayMilliseconds, int left,
			int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.postInvalidateDelayed(delayMilliseconds, left, top, right, bottom);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
//		int i=MotionEventCompat.ACTION_MASK;
		return super.onTouchEvent(arg0);
	}
	
}
