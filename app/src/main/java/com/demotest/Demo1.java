package com.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.demotest.Bean.Person;

public class Demo1 extends AppCompatActivity implements View.OnClickListener{
   private static final String TAG = "Demo1";
   private Toolbar mToolbar;
   private Button mButton;
   Person mPerson;
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_demo1);
	  initView();
   }

   void initView(){
	  mToolbar = ( Toolbar ) findViewById(R.id.toolbar3);
	  setSupportActionBar(mToolbar);
	  ActionBar mActionBar = getSupportActionBar();
	  if(mActionBar != null){
		 mActionBar.setDisplayHomeAsUpEnabled(true);
		 mActionBar.setDisplayShowTitleEnabled(true);
		 mActionBar.setTitle("Demo1");
		 mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
	  }
	  mButton = ( Button ) findViewById(R.id.demo1_btn);
	  mButton.setOnClickListener(this);
	  mPerson = new Person("name" ,"0" ,10);
   }

   @Override
   public void onClick( final View v ) {
		switch ( v.getId() ){
		   case R.id.demo1_btn:
		      Intent mIntent = new Intent(Demo1.this,Demo2.class);
			  Bundle mBundle = new Bundle();
			  mBundle.putParcelable("key", mPerson);
			  mIntent.putExtras(mBundle);
			  startActivity(mIntent);
		      break;
		   default:
		      break;
		}
   }
}
