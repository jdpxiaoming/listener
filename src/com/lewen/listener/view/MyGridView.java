package com.lewen.listener.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;


public class MyGridView extends GridView
  implements AbsListView.OnScrollListener
{
  private AbsListView.OnScrollListener a;
  private loadData b;
  private boolean c = false;
  private int d;

  public MyGridView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public MyGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public MyGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    super.setOnScrollListener(this);
  }

  public void init()
  {
    if (this.b != null)
      this.b.loaded();
  }

  public void init(loadData paramaa)
  {
    this.b = paramaa;
  }

  public void b()
  {
    this.c = false;
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.a != null)
      this.a.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
    if ((this.b == null) || (paramInt2 == paramInt3));
    while (true)
    {
      if (paramInt1 + paramInt2 >= paramInt3)
    	  return;
      
      for (int i = 1; (!this.c) && (i != 0) && (this.d != 0); i = 0)
      {
        this.c = true;
        init();
        return;
      }
    }
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    this.d = paramInt;
    if (this.a != null)
      this.a.onScrollStateChanged(paramAbsListView, paramInt);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(paramListAdapter);
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.a = paramOnScrollListener;
  }
  
  public abstract interface loadData
  {
    public abstract void loaded();
  }
}