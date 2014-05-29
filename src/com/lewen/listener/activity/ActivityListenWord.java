package com.lewen.listener.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUser;
import com.iflytek.cloud.speech.SynthesizerListener;
import com.lewen.listener.R;
import com.lewen.listener.activity.parent.BaseActivity;

public class ActivityListenWord extends BaseActivity implements SynthesizerListener{
	private Button btnBack;
	private TextView textA,textB,textC,textD,textQuestion;
	private String answer = null;
	//合成对象.
	private SpeechSynthesizer mSpeechSynthesizer;
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
		SpeechUser.getUser().login(ActivityListenWord.this, null, null
				, "appid=" + getString(R.string.app_id), listener);
		//初始化合成对象.
		mSpeechSynthesizer=SpeechSynthesizer.createSynthesizer(this);
		
	}
	/**
	 * 用户登录回调监听器.
	 */
	private SpeechListener listener = new SpeechListener()
	{

		@Override
		public void onData(byte[] arg0) {
		}

		@Override
		public void onCompleted(SpeechError error) {
			if(error != null) {
				Toast.makeText(ActivityListenWord.this, "登录失败"
						, Toast.LENGTH_SHORT).show();
				
			}			
		}

		@Override
		public void onEvent(int arg0, Bundle arg1) {
		}		
	};
	
	/**
	 * 设置答案
	 * @param answer2
	 */
	private void setQuestion(String answer2) {
		
		textQuestion.setText("");
		textQuestion.append("Maybe you should ");
		if(null!=answer2){
			textQuestion.append(Html.fromHtml("<u> " +answer2 +"</u>"));
			
			if(answer2.equals("chew")){
				//讯飞读音 回答 正确 +10分
				synthetizeInSilence("回答正确！");
				
			}else{
				
				synthetizeInSilence("回答错误！");
				
			}
		}else{
			textQuestion.append(" __ ");
		}
		textQuestion.append(" on myface ");
		
	}

	
	/**
	 * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
	 * @param
	 */
	private void synthetizeInSilence(String source) {
		if (null == mSpeechSynthesizer) {
			//创建合成对象.
			mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
		}
		//设置合成发音人.
		String role = "vixq";
		
		//设置发音人
		mSpeechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, role);
		//获取语速
		int speed = 50;
		//设置语速
		mSpeechSynthesizer.setParameter(SpeechConstant.SPEED, ""+speed);
		//获取音量.
		int volume = 50;
		//设置音量
		mSpeechSynthesizer.setParameter(SpeechConstant.VOLUME, ""+volume);
		//获取语调
		int pitch = 50;
		//设置语调
		mSpeechSynthesizer.setParameter(SpeechConstant.PITCH, ""+pitch);
		//获取合成文本.
		//进行语音合成.
		mSpeechSynthesizer.startSpeaking(source, this);
//		showTip(String.format(getString(R.string.tts_toast_format),0 ,0));
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
	@Override
	public void onBufferProgress(int arg0, int arg1, int arg2, String arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted(SpeechError arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeakBegin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeakPaused() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeakProgress(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeakResumed() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		if (null != mSpeechSynthesizer) {
			mSpeechSynthesizer.stopSpeaking();
		}
		super.onStop();
	}
}
