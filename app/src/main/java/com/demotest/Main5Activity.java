package com.demotest;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main5Activity extends AppCompatActivity {

   @BindView (R.id.toolbar)
   Toolbar mToolbar;
   @BindView (R.id.btn_one)
   Button mBtnOne;
   @BindView (R.id.btn_two)
   Button mBtnTwo;
   @BindView (R.id.btn_three)
   Button mBtnThree;
   @BindView (R.id.btn_four)
   Button mBtnFour;
   @BindView (R.id.img_babi)
   ImageView img_babi;
   @BindView (R.id.linear)
   LinearLayout ly_root;
   private int width;
   private int height;

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main5);
	  ButterKnife.bind(this);
   }

   @OnClick ({ R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four })
   public void onViewClicked( View view ) {
	  switch ( view.getId() ) {
		 case R.id.btn_one:
			lineAnimator();
			break;
		 case R.id.btn_two:

			break;
		 case R.id.btn_three:
			break;
		 case R.id.btn_four:
			break;
		 default:
			break;
	  }
   }
   //定义一个修改ImageView位置的方法
   private void moveView(View view, int rawX, int rawY) {
	  int left = rawX - img_babi.getWidth() / 2;
	  int top = rawY - img_babi.getHeight();
	  int width = left + view.getWidth();
	  int height = top + view.getHeight();
	  view.layout(left, top, width, height);
   }
   //按轨迹方程来运动
   private void lineAnimator() {
	  width = ly_root.getWidth();
	  height = ly_root.getHeight();
	  ValueAnimator xValue = ValueAnimator.ofInt(height , 0, height/4 , height /2 , height / 4*3 ,height);
	  xValue.setDuration(3000L);
	  xValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		 @Override
		 public void onAnimationUpdate( final ValueAnimator animation ) {
			int y = ( int ) animation.getAnimatedValue();
			int x = width/2;
			moveView(img_babi , x, y);
		 }
	  });
	  xValue.setInterpolator(new LinearInterpolator());
	  xValue.start();
   }

}
