package com.demotest;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail extends AppCompatActivity {


   @BindView (R.id.toolbar)
   Toolbar mToolbar;
   @BindView (R.id.collapsing_toolbar)
   CollapsingToolbarLayout mCollapsingToolbar;
   @BindView (R.id.appBar)
   AppBarLayout mAppBar;

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_detail);
	  ButterKnife.bind(this);

	  setSupportActionBar(mToolbar);
	  android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
	  if(mActionBar != null){
		 mActionBar.setDisplayShowTitleEnabled(false);
	  }
   }
}
