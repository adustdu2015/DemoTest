package com.demotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

   @BindView (R.id.main3_image)
   ImageView mMain3Image;
   @BindView (R.id.cancel)
   ImageView mCancel;
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main3);
	  ButterKnife.bind(this);
		initView();
   }

   void initView(){
	  mMain3Image.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick( final View v ) {
//			startActivity(new Intent(Main3Activity.this,Main4Activity.class));
		 }
	  });
   }

   @OnClick ({R.id.cancel})
   public void onViewClicked() {
	  mMain3Image.setVisibility(View.GONE);
	  mCancel.setVisibility(View.GONE);
   }

   @Override
   public void onBackPressed() {
	  super.onBackPressed();
	  Main3Activity.this.finish();
   }
}
