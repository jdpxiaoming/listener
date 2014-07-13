package com.lewen.listener.activity;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.lewen.listener.bean.Question;
import com.lewen.listener.http.HttpUtil;
import com.lewen.listener.service.XmlToListService;
import com.lewen.listener.util.MediaPlayerUtil;

public class ActivityListenWord extends BaseActivity implements SynthesizerListener {
	
	private ImageButton btnBack;
	private TextView textA, textB, textC, textD, textQuestion;
	/**
	 * 用户选择的答案 A,B,C,D
	 */
	private String answer = null;
	// 合成对象.
	private SpeechSynthesizer mSpeechSynthesizer;
	
	private MediaPlayerUtil mediaUtil = null;
	private RelativeLayout	relativeTips;
	private TextView textTipsAnswer, textTipsRightDes;
	private ImageView imgTipsRW;
	//footer
	private RelativeLayout relativeHelp1,relativeHelp2,relativeHelp3;//服
	
	//题目列表
	private List<Question> qList;
	private ProgressBar progress;
	private int qSelected = 0;//当前进行的题目
	private String qpref;//题目前半部分
	private String qbehand;//题目后半部分
	String url_xml="http://tftp.joysw.cn/Courseware/140710010639983255/20140710010357/wohan.xml";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_guess_word);

		init();
	}

	private void init() {
		progress	=	(ProgressBar) findViewById(R.id.progress);
		btnBack = (ImageButton) findViewById(R.id.imgbtnBack);
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
		
		SpeechUser.getUser().login(ActivityListenWord.this, null, null, "appid=" + getString(R.string.app_id), listener);
		// 初始化合成对象.
		mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
		
		//获取题库
		try{
			if(null!= getIntent()&&getIntent().getStringExtra("xml")!=null){
				url_xml = getIntent().getStringExtra("xml");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		getQuestions(url_xml);
	}

	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch (msg.what) {
			case 101://获取题目列表完毕
				progress.setVisibility(View.GONE);
				controlButton(true);
				updateQuestion();
				break;

			default:
				break;
			}
		}

	};
	
	private void controlButton(boolean state) {
		textA.setEnabled(state);
		textB.setEnabled(state);
		textC.setEnabled(state);
		textD.setEnabled(state);
		relativeHelp1.setEnabled(state);
		relativeHelp2.setEnabled(state);
		relativeHelp3.setEnabled(state);
	}
	
	private void getQuestions(final String url_xml) {
		progress.setVisibility(View.VISIBLE);
		controlButton(false);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					String result = HttpUtil.getConnection(url_xml);
					qList = XmlToListService.GetQuestionList(result);
					
					if(null!=qList&&qList.size()>0){
						//设置第一题
						qSelected = 0;
					}
					
					mHandler.sendEmptyMessage(101);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}

	/**
	 * 根据qSelected来设定题目信息
	 */
	private void updateQuestion() {
		Question q = qList.get(qSelected);
		
		String question = q.getQuestion();
		String ans 		= q.getAnswer();
		
		if(question!=null&&ans!=null&&question.contains(ans)){
			qpref	=	question.substring(0,question.indexOf(ans));
			qbehand	=	question.substring(question.indexOf(ans)+ans.length());
		}
		
		textA.setText(q.getSelectedA());
		textB.setText(q.getSelectedB());
		textC.setText(q.getSelectedC());
		textD.setText(q.getSelectedD());
		//谁是答案，4个item
		setQuestion(answer);
	}
	
	//根据用户选择的答案进行 结果正确答案解释页面展示
	private void showResultTips(boolean isOk,String selectedAnswer){
		relativeTips.setVisibility(View.VISIBLE);
		textTipsAnswer.setText(selectedAnswer);
		textTipsRightDes.setText(qList.get(qSelected).getAnswerDes());
		
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
	 * 设置题目、答案部分用——代替
	 * 
	 * @param answer2
	 */
	private void setQuestion(String answer2) {
		if (mediaUtil == null) {
			mediaUtil = new MediaPlayerUtil(ActivityListenWord.this);
		}
		textQuestion.setText("");
		textQuestion.append(qpref+" ");
		if (null != answer2) {
			if (answer2.trim().equals( qList.get(qSelected).getAnswer())) {
				textQuestion.append(Html.fromHtml("<u><font color=\"green\">  " + answer2 + "</font></u>"));
				// 讯飞读音 回答 正确 +10分
				mediaUtil.PlayRight();
				showResultTips(true, answer2);
				
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						NextQuestion();
					}
				}, 2*1000);
				
			} else {
				textQuestion.append(Html.fromHtml("<u><font color=\"red\">  " +  qList.get(qSelected).getAnswer() + "</font></u>"));
				mediaUtil.PlayWrong();
				synthetizeInSilence( qList.get(qSelected).getAnswer()+"!"+ qList.get(qSelected).getAnswer()+"!"+ qList.get(qSelected).getAnswer()+"!");
				showResultTips(false, answer2);
			}
		} else {
			textQuestion.append(" __ ");
		}
		textQuestion.append("   "+qbehand);
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
			case R.id.imgbtnBack://
				finish();
				break;
			case R.id.textA:
				answer = qList.get(qSelected).getSelectedA();
				setQuestion(answer);
//				textA.setBackgroundColor(getResources().getColor(R.color.gray_1));
				break;
			case R.id.textB:// 猜图片
				answer =  qList.get(qSelected).getSelectedB();
				setQuestion(answer);
//				textB.setBackgroundColor(getResources().getColor(R.color.gray_1));
				break;
			case R.id.textC:// 猜图片
				answer =  qList.get(qSelected).getSelectedC();
				setQuestion(answer);
//				textC.setBackgroundColor(getResources().getColor(R.color.green));
				break;
			case R.id.textD:// 猜图片
				answer =  qList.get(qSelected).getSelectedD();
				setQuestion(answer);
//				textD.setBackgroundColor(getResources().getColor(R.color.gray_1));
				break;
			case R.id.relative1OfGuessFooter:
				setQuestion(qList.get(qSelected).getAnswer());
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

	}

	@Override
	public void onCompleted(SpeechError arg0) {
		NextQuestion();
	}

	private void NextQuestion() {
		
		if(qSelected<qList.size()-1){
			qSelected++;
			answer = null;
			relativeTips.setVisibility(View.GONE);
			updateQuestion();
		}else{
			//跳转到 pk结果页面
//			ToastUtil.throwTipShort("Pk结束，即将进入得分页面~");
			Intent intent = new Intent(ActivityListenWord.this,ActivityPKResult.class);
			intent.putExtra("xml", url_xml);
			startActivity(intent);
			overridePendingTransition(R.anim.do_nothing_animate, R.anim.splashfadeout);
			finish();
		}
	}

	@Override
	public void onSpeakBegin() {

	}

	@Override
	public void onSpeakPaused() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSpeakProgress(int arg0, int arg1, int arg2) {

	}

	@Override
	public void onSpeakResumed() {

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
