package com.lewen.listener.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.lewen.listener.util.MediaPlayerUtil;

public class ActivityListenWord extends BaseActivity implements SynthesizerListener {
	private Button btnBack;
	private TextView textA, textB, textC, textD, textQuestion;
	private String answer = null;
	// 合成对象.
	private SpeechSynthesizer mSpeechSynthesizer;
	
	private MediaPlayerUtil mediaUtil = null;
	private RelativeLayout	relativeTips;
	private TextView textTipsAnswer, textTipsRightDes;
	private ImageView imgTipsRW;
	//footer
	private RelativeLayout relativeHelp1,relativeHelp2,relativeHelp3;//服

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_guess_word);

		init();
	}

	private void init() {
		btnBack = (Button) findViewById(R.id.gobackbt);
		btnBack.setOnClickListener(clickListener);

		textA = (TextView) findViewById(R.id.textA);
		textA.setOnClickListener(clickListener);
		textB = (TextView) findViewById(R.id.textB);
		textB.setOnClickListener(clickListener);
		textC = (TextView) findViewById(R.id.textC);
		textC.setOnClickListener(clickListener);
		textD = (TextView) findViewById(R.id.textD);
		textD.setOnClickListener(clickListener);

		textQuestion = (TextView) findViewById(R.id.textQuestion);
		
		relativeTips	=	(RelativeLayout) findViewById(R.id.relativeResult);
		textTipsAnswer	=	(TextView) findViewById(R.id.textTipsAnswer);
		textTipsRightDes=	(TextView) findViewById(R.id.textTipsRightAnserDes);
		imgTipsRW		=	(ImageView) findViewById(R.id.imgTipsRightOrWrong);
		
		relativeHelp1	=	(RelativeLayout) findViewById(R.id.relative1OfGuessFooter);
		relativeHelp2	=	(RelativeLayout) findViewById(R.id.relative2OfGuessFooter);
		relativeHelp3	=	(RelativeLayout) findViewById(R.id.relative3OfGuessFooter);
		
		relativeHelp1.setOnClickListener(clickListener);
		relativeHelp2.setOnClickListener(clickListener);
		relativeHelp3.setOnClickListener(clickListener);
		
		setQuestion(answer);

		SpeechUser.getUser().login(ActivityListenWord.this, null, null, "appid=" + getString(R.string.app_id), listener);
		// 初始化合成对象.
		mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
	}

	//根据用户选择的答案进行 结果正确答案解释页面展示
	private void showResultTips(boolean isOk,String selectedAnswer){
		relativeTips.setVisibility(View.VISIBLE);
		textTipsAnswer.setText(selectedAnswer);
		
		if(isOk){
			imgTipsRW.setImageResource(R.drawable.icon_right);
		}else{
			imgTipsRW.setImageResource(R.drawable.icon_wrong);
		}
	}
	/**
	 * 用户登录回调监听器.
	 */
	private SpeechListener listener = new SpeechListener() {
		@Override
		public void onData(byte[] arg0) {
		}

		@Override
		public void onCompleted(SpeechError error) {
			if (error != null) {
				Toast.makeText(ActivityListenWord.this, "登录失败", Toast.LENGTH_SHORT).show();

			}
		}

		@Override
		public void onEvent(int arg0, Bundle arg1) {
		}
	};

	/**
	 * 设置答案
	 * 
	 * @param answer2
	 */
	private void setQuestion(String answer2) {
		if (mediaUtil == null) {
			mediaUtil = new MediaPlayerUtil(ActivityListenWord.this);
		}
		textQuestion.setText("");
		textQuestion.append("Maybe you should ");
		if (null != answer2) {
			if (answer2.trim().equals("chew")) {
				textQuestion.append(Html.fromHtml("<u><font color=\"green\">  " + answer2 + "</font></u>"));
				// 讯飞读音 回答 正确 +10分
				mediaUtil.PlayRight();
				showResultTips(true, answer2);
			} else {
				textQuestion.append(Html.fromHtml("<u><font color=\"red\">  " + "chew" + "</font></u>"));
				mediaUtil.PlayWrong();
				synthetizeInSilence("chew！chew！chew！");
				showResultTips(false, answer2);
			}
		} else {
			textQuestion.append(" __ ");
		}
		textQuestion.append(" on myface ");

	}

	/**
	 * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
	 * 
	 * @param
	 */
	private void synthetizeInSilence(String source) {
		if (null == mSpeechSynthesizer) {
			// 创建合成对象.
			mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
		}
		// 设置合成发音人.
		String role = "vixq";

		// 设置发音人
		mSpeechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, role);
		// 获取语速
		int speed = 50;
		// 设置语速
		mSpeechSynthesizer.setParameter(SpeechConstant.SPEED, "" + speed);
		// 获取音量.
		int volume = 50;
		// 设置音量
		mSpeechSynthesizer.setParameter(SpeechConstant.VOLUME, "" + volume);
		// 获取语调
		int pitch = 50;
		// 设置语调
		mSpeechSynthesizer.setParameter(SpeechConstant.PITCH, "" + pitch);
		// 获取合成文本.
		// 进行语音合成.
		mSpeechSynthesizer.startSpeaking(source, this);
		// showTip(String.format(getString(R.string.tts_toast_format),0 ,0));
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.gobackbt://
				finish();
				break;
			case R.id.textA:
				answer = " is ";
				setQuestion(answer);
//				textA.setBackgroundColor(getResources().getColor(R.color.gray_1));
				break;
			case R.id.textB:// 猜图片
				answer = " book ";
				setQuestion(answer);
//				textB.setBackgroundColor(getResources().getColor(R.color.gray_1));
				break;
			case R.id.textC:// 猜图片
				answer = " chew ";
				setQuestion(answer);
//				textC.setBackgroundColor(getResources().getColor(R.color.green));
				break;
			case R.id.textD:// 猜图片
				answer = " are ";
				setQuestion(answer);
//				textD.setBackgroundColor(getResources().getColor(R.color.gray_1));
				break;
			case R.id.relative1OfGuessFooter:
				setQuestion("chew");
				break;
			case R.id.relative2OfGuessFooter:
				relativeTips.setVisibility(View.GONE);
				setQuestion(null);
				break;
			case R.id.relative3OfGuessFooter:
				finish();
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mediaUtil.clear();
		mediaUtil = null;
		super.onDestroy();
	}
}
