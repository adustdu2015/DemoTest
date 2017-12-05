package com.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {

   @BindView (R.id.btn_alpha)
   Button mBtnAlpha;
   @BindView (R.id.btn_scale)
   Button mBtnScale;
   @BindView (R.id.btn_tran)
   Button mBtnTran;
   @BindView (R.id.btn_rotate)
   Button mBtnRotate;
   @BindView (R.id.btn_set)
   Button mBtnSet;
   @BindView (R.id.img_show)
   ImageView mImgShow;
   @BindView (R.id.enter)
   ImageView mEnter;
   private Animation mAnimation = null;

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main4);
	  ButterKnife.bind(this);
   }

   @OnClick ({ R.id.btn_alpha, R.id.btn_scale, R.id.btn_tran, R.id.btn_rotate, R.id.btn_set,R.id.enter })
   public void onViewClicked( View view ) {
	  switch ( view.getId() ) {
		 case R.id.btn_alpha:
			mAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.anim_alpha);
			mImgShow.startAnimation(mAnimation);
			break;
		 case R.id.btn_scale:
			mAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.anim_scale);
			mImgShow.startAnimation(mAnimation);
			break;
		 case R.id.btn_tran:
			mAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.anim_translate);
			mImgShow.startAnimation(mAnimation);
			break;
		 case R.id.btn_rotate:
			mAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.anim_rotate);
			mImgShow.startAnimation(mAnimation);
			break;
		 case R.id.btn_set:
			mAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.anim_set);
			mImgShow.startAnimation(mAnimation);
			break;
		 case R.id.enter:
		    startActivity(new Intent(Main4Activity.this, Main5Activity.class));
		    break;
		 default:
			break;
	  }
   }
}
