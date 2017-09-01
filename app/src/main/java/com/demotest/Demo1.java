package com.demotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Demo1 extends AppCompatActivity {
   private static final String TAG = "Demo1";
   private Switch mSwitch;
	private Button  mButton;
   private Toolbar mToolbar;
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_demo1);
	  initView();
	  mButton.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick( final View v ) {
			finish();
		 }
	  });
//	  mSwitch.setText(R.string.opening);
	  mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		 @Override
		 public void onCheckedChanged( final CompoundButton buttonView, final boolean isChecked ) {
				if ( isChecked ){
				   Toast.makeText(Demo1.this, "已打开", Toast.LENGTH_SHORT).show();
				   Log.d(TAG, "已打开");
				}else{
				   Log.d(TAG, "已关闭");
				   Toast.makeText(Demo1.this, "已关闭", Toast.LENGTH_SHORT).show();
				}
		 }
	  });
   }

   void initView(){
	  mButton = ( Button ) findViewById(R.id.button);
	  mSwitch = ( Switch ) findViewById(R.id.switch1);
	  mToolbar = ( Toolbar ) findViewById(R.id.toolbar3);
	  setSupportActionBar(mToolbar);
	  mToolbar.setTitle("Demo1");
   }
}
