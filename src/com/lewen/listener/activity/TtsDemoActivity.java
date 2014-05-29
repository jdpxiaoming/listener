package com.lewen.listener.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUser;
import com.iflytek.cloud.speech.SynthesizerListener;
import com.lewen.listener.R;


/**
 * 合成页面,调用SDK的SynthesizerDialog实现语音合成.
 * @author iFlytek
 * @since 20120823
 */
public class TtsDemoActivity extends Activity implements OnClickListener,
		SynthesizerListener {
	private static final String TAG = "TtsDemoActivity";
	//合成的文本
	private EditText mSourceText;
	//缓存对象.
	private SharedPreferences mSharedPreferences;
	//合成对象.
	private SpeechSynthesizer mSpeechSynthesizer;
	//弹出提示
	private Toast mToast;	
	//缓冲进度
	private int mPercentForBuffering = 0;	
	//播放进度
	private int mPercentForPlaying = 0;
	/*
	 * TTS状态。
	 * 0->初始状态，
	 * 1->已经合成，播放状态，
	 * 2->暂停播放状态
	 * 初始状态为0
	 */
	private int state=0; 
	
	
	/**
	 * 合成界面入口函数
	 * @param savedInstanceState
	 */
	@SuppressLint("ShowToast")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "[onCreate]" + savedInstanceState);
		//设置窗口属性，用户自定义标题
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.demo);
		
		
		SpeechUser.getUser().login(TtsDemoActivity.this, null, null
				, "appid=" + getString(R.string.app_id), listener);
		
		//“合成”按钮初始化
		Button ttsButton = (Button) findViewById(android.R.id.button1);
		ttsButton.setOnClickListener(this);
		ttsButton.setText(R.string.text_tts);
		
		//“停止”按钮初始化
		Button cancelButton = (Button) findViewById(android.R.id.button2);
		cancelButton.setOnClickListener(this);
		cancelButton.setVisibility(View.VISIBLE);
		cancelButton.setText(R.string.text_tts_cancel);
		
		//“清空”按钮初始化
		Button clearButton = (Button) findViewById(android.R.id.button3);
		clearButton.setOnClickListener(this);
		clearButton.setText(R.string.text_tts_clear);
		clearButton.setVisibility(View.VISIBLE);
		
		//合成结果初始化设置
		mSourceText = (EditText) findViewById(R.id.txt_result);
		mSourceText.setText(R.string.text_tts_source);
		mSourceText.setKeyListener(TextKeyListener.getInstance());	
		
		//设置EditText的输入方式.
		mSourceText.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
		mSharedPreferences = getSharedPreferences(getPackageName(),
				MODE_PRIVATE);	
		mToast = Toast.makeText(this,
				String.format(getString(R.string.tts_toast_format), 0, 0),
				Toast.LENGTH_LONG);
		//初始化合成对象.
		mSpeechSynthesizer=SpeechSynthesizer.createSynthesizer(this);
	}

	/**
	 * SynthesizerPlayerListener的"停止播放"回调接口.
	 * 退出时自动停止播放
	 * @param
	 */
	@Override
	protected void onStop() {
		mToast.cancel();
		if (null != mSpeechSynthesizer) {
			mSpeechSynthesizer.stopSpeaking();
			//button1显示文本“合成”
			((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts);
			//设置状态为初始状态
			state=0;
			mToast.cancel();
		}
		super.onStop();
	}

	/**
	 * 按钮点击事件.
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//“合成”按钮
		case android.R.id.button1:
			//开始合成
			if(state==0){
				//按钮文字变为“暂停”
//				Log.d(TAG, "init state");
				((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts_suspend);
				//不显示Dialog，后台合成语音.
				synthetizeInSilence();
				state=1;
			}else if(state==1){			//已合成，播放状态，
				//按钮文字变为“继续”
//				Log.d(TAG, "playing");
				((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts_resume);
				//点击实现暂停播放
				mSpeechSynthesizer.pauseSpeaking();
				state=2;
			}else if(state==2){			//暂停播放状态
				//按钮文字变为“继续”
//				Log.d(TAG, "suspend");
				((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts_suspend);
				//点击实现继续播放
				mSpeechSynthesizer.resumeSpeaking();
				state=1;
			}
			
			break;
		//停止合成.
		case android.R.id.button2:
			mSpeechSynthesizer.stopSpeaking();
			//button1显示文本“合成”
			((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts);
			//设置状态为初始状态
			state=0;
			mToast.cancel();
			break;
		//清屏
		case android.R.id.button3:
			//清空合成文本
			mSourceText.setText("");
			////button1显示文本“合成”
			((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts);
			//设置状态为初始状态
			state=0;
			mToast.cancel();
			break;
		//设置按钮点击事件
		default:
			break;
		}
	}


	/**
	 * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
	 * @param
	 */
	private void synthetizeInSilence() {
		if (null == mSpeechSynthesizer) {
			//创建合成对象.
			mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
		}
		//设置合成发音人.
		String role = "xiaoyan";
		
		//设置发音人
		mSpeechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, role);
		//获取语速
		int speed =50;
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
		Editable editable = mSourceText.getText();
		String source = null;
		if (null != editable) {
			source = editable.toString();
		}
		//进行语音合成.
		mSpeechSynthesizer.startSpeaking(source, this);
	}
	

	/**
	 * SynthesizerListener的"开始播放"回调接口.
	 * @param 
	 */
	@Override
	public void onSpeakBegin() {
		showTip("开始播放");
	}

	/**
	 * SynthesizerListener的"暂停播放"回调接口.
	 * @param 
	 */
	@Override
	public void onSpeakPaused() {
		((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts_resume);
		state=2;
		showTip("暂停播放");
	}

	/**
	 * SynthesizerListener的"播放进度"回调接口.
	 * @param percent,beginPos,endPos
	 */
	@Override
	public void onSpeakProgress(int percent,int beginPos,int endPos) {
		mPercentForPlaying = percent;
//		showTip(String.format(getString(R.string.tts_toast_format),
//				mPercentForBuffering, mPercentForPlaying));
	}

	/**
	 * SynthesizerListener的"恢复播放"回调接口，对应onPlayPaused
	 * @param 
	 */
	@Override
	public void onSpeakResumed() {
		((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts_suspend);
		showTip("继续播放");
	}

	/**
	 * SynthesizerListener的"结束会话"回调接口.
	 * @param error
	 */
	@Override
	public void onCompleted(SpeechError error) {
		
		if(error == null)
		{
			showTip("播放完成");
		}
		else if(error != null)
		{
			showTip(error.getPlainDescription(true));
		}
		
		((Button) findViewById(android.R.id.button1)).setText(R.string.text_tts);
		//设置状态为初始状态
		state=0;
		
		//mSpeechSynthesizer.replaySpeaking();
			
	}

	/**
	 * SynthesizerListener的"播放进度"回调接口.
	 * @param percent,beginPos,endPos,event
	 */
	public void onBufferProgress(int progress,int beginPos,int endPos, String spellInfo) {
		mPercentForBuffering = progress;
//		mToast.setText(String.format(getString(R.string.tts_toast_format),
//				mPercentForBuffering, mPercentForPlaying));
		
		mToast.show();
	}
	
	private void showTip(String str)
	{
		if(!TextUtils.isEmpty(str))
		{
			mToast.setText(str);
			mToast.show();
		}
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
				Toast.makeText(TtsDemoActivity.this, "登录失败"
						, Toast.LENGTH_SHORT).show();
				
			}			
		}

		@Override
		public void onEvent(int arg0, Bundle arg1) {
		}		
	};
	
}
