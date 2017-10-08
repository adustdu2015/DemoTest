package com.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class ImageGallery extends AppCompatActivity {
	private  String urls ="";
   private PhotoView photo_view;
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_image_gallery);
	  Intent mIntent = getIntent();
	  urls = mIntent.getStringExtra("keys");


	  photo_view = ( PhotoView ) findViewById(R.id.photo_view);
	  photo_view.setImageResource(R.drawable.postsql);
   }
}
