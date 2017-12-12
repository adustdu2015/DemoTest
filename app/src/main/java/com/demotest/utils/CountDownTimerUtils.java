package com.demotest.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by a on 2017/12/12.
 */

public class CountDownTimerUtils extends CountDownTimer {
   private TextView mTextView;

   /**
	* @param millisInFuture    The number of millis in the future from the call to {@link #start()}
	*                          until the countdown is done and {@link #onFinish()} is called.
	* @param countDownInterval The interval along the way to receive {@link #onTick(long)} callbacks.
	*/
   public CountDownTimerUtils( TextView textView, long millisInFuture, final long countDownInterval ) {
	  super(millisInFuture, countDownInterval);
	  this.mTextView = textView;
   }

   @Override
   public void onTick( final long millisUntilFinished ) {
	  mTextView.setClickable(false); //设置不可点击
	  mTextView.setText(millisUntilFinished / 1000 + "秒后可重新发送"); //设置倒计时时间

   }

   @Override
   public void onFinish() {
	  mTextView.setText("重新获取验证码");
	  mTextView.setClickable(true);//重新获得点击
   }
}
