package com.lewen.listener.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;

public class ActivityListenWord extends BaseActivity {
	private Button btnBack;
	private TextView textA,textB,textC,textD,textQuestion;
	private String answer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_guess_word);
		
		init();
	}

	private void init() {
		btnBack	=	(Button) findViewById(R.id.gobackbt);
		btnBack.setOnClickListener(clickListener);
		
		textA	=	(TextView) findViewById(R.id.textA);
		textA.setOnClickListener(clickListener);
		textB	=	(TextView) findViewById(R.id.textB);
		textB.setOnClickListener(clickListener);
		textC	=	(TextView) findViewById(R.id.textC);
		textC.setOnClickListener(clickListener);
		textD	=	(TextView) findViewById(R.id.textD);
		textD.setOnClickListener(clickListener);
		
		
		textQuestion	=	(TextView) findViewById(R.id.textQuestion);
		
		setQuestion(answer);
		
//		String source = "这只是一个测试字符串，测试<b>黑体字</b>、<i>斜体字</i>、<u>下划线</u>、<font color='red'>红色字</font>的显示。";  
//		textQuestion.setText(Html.fromHtml(source));
	}

	/**
	 * 设置答案
	 * @param answer2
	 */
	private void setQuestion(String answer2) {
		
		textQuestion.setText("");
		textQuestion.append("Maybe you should ");
		if(null!=answer2){
			textQuestion.append(Html.fromHtml("<u> " +answer2 +"</u>"));
		}else{
			textQuestion.append(" __ ");
		}
		textQuestion.append(" on myface ");
		
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.gobackbt://
				finish();
				break;
			case R.id.textA: 
				answer  = " is ";
				setQuestion(answer);
				break;
			case R.id.textB://猜图片
				answer  = " book ";
				setQuestion(answer);
				break;
			case R.id.textC://猜图片
				answer  = " chew ";
				setQuestion(answer);
				break;
			case R.id.textD://猜图片
				answer  = " are ";
				setQuestion(answer);
				break;
			default:
				break;
			}
		}
	};
	
}
