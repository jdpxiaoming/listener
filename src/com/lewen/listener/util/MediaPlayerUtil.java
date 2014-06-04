package com.lewen.listener.util;

import java.io.IOException;

import com.lewen.listener.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * 播放个类提示音
 * @author Administrator
 *
 */
public class MediaPlayerUtil {

	
	private MediaPlayer mediaplayer;
	private Context mContext;
	
	
	public MediaPlayerUtil(Context mContext) {
		super();
		this.mContext = mContext;
		
	}
	
	
	public void PlayRight(){
		mediaplayer	=	MediaPlayer.create(mContext, R.raw.right);
		mediaplayer.start();
	}
	public void PlayWrong(){
		mediaplayer	=	MediaPlayer.create(mContext, R.raw.wrong);
		
		mediaplayer.start();
	}
	public void PlayWinner(){
		mediaplayer	=	MediaPlayer.create(mContext, R.raw.winner);
		mediaplayer.start();
	}


	public void clear() {
		// TODO Auto-generated method stub
		mediaplayer.stop();
		mediaplayer.release();
		mediaplayer = null;
	}
	
	
	
}
