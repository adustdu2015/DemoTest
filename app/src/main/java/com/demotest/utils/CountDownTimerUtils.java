package com.demotest.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by a on 2017/11/30.
 */

public class CountDownTimerUtils extends CountDownTimer {
   private Button sign;
   /**
	* @param millisInFuture    The number of millis in the future from the call to {@link #start()}
	*                          until the countdown is done and {@link #onFinish()} is called.
	* @param countDownInterval The interval along the way to receive {@link #onTick(long)} callbacks.
	*/
   public CountDownTimerUtils( Button sign, final long millisInFuture, final long countDownInterval ) {
	  super(millisInFuture, countDownInterval);
	  this.sign = sign;

   }

   @Override
   public void onTick( final long millisUntilFinished ) {
	  sign.setClickable(false);
	  sign.setText(millisUntilFinished/1000+"秒后重发");
   }

   @Override
   public void onFinish() {
	  sign.setText("重新获取验证码");
	  //重新获得点击
	  sign.setClickable(true);

   }
}
