package com.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends AppCompatActivity {

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_detail);
	  initView();
   }

   void initView(){
	  Intent vIntent = getIntent();
	  String title = vIntent.getStringExtra("2");
	  int fruitId = vIntent.getIntExtra("1" ,0);
	  Toolbar vToolbar = ( Toolbar ) findViewById(R.id.toolBar_detail);
		setSupportActionBar(vToolbar);
	  ActionBar vActionBar = getSupportActionBar();
	  if(vActionBar != null){
		 vActionBar.setDisplayHomeAsUpEnabled(true);
	  }
	  ImageView image_detail = ( ImageView ) findViewById(R.id.image_detail);
	  TextView textView_detail = ( TextView ) findViewById(R.id.textView_detail);
	  CollapsingToolbarLayout collapsingToolbarLayout = ( CollapsingToolbarLayout ) findViewById(R.id.collapsingToolbarLayout);
	  Glide.with(this).load(fruitId).into(image_detail);
	  textView_detail.setText(title+"111111111111111111111111111111111111111");
   }
}
