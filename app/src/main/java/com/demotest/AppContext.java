package com.demotest;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by a on 2017/10/7.
 */

public class AppContext extends Application {
   @Override
   public void onCreate() {
	  super.onCreate();
	  Stetho.initializeWithDefaults(this);
   }
}
