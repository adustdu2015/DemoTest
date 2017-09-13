package com.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.demotest.Bean.Person;

public class Demo2 extends AppCompatActivity {
	private TextView demo2_text;
   private Person mPerson;
   private AppBarLayout appBarLayout2;
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_demo2);
	  initView();
	  Intent mIntent = getIntent();
	  mPerson = mIntent.getParcelableExtra("key");
	  demo2_text.setText("name = " + mPerson.getName() + "  age = " + mPerson.getAge());
   }
   void initView(){
	  demo2_text = ( TextView ) findViewById(R.id.demo2_text);
	  appBarLayout2 = ( AppBarLayout ) findViewById(R.id.demo2_appBarLayout);
   }
}
