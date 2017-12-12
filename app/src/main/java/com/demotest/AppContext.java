package com.demotest;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demotest.utils.FakeCrashLibrary;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Created by a on 2017/10/7.
 */

public class AppContext extends Application {
   @Override
   public void onCreate() {
	  super.onCreate();
	  Stetho.initializeWithDefaults(this);
	  if ( LeakCanary.isInAnalyzerProcess(this)) {
		 // This process is dedicated to LeakCanary for heap analysis.
		 // You should not init your app in this process.
		 return;
	  }
	  LeakCanary.install(this);

	  if(BuildConfig.DEBUG){
		 Timber.plant(new Timber.DebugTree());
	  }else{
		 Timber.plant(new CrashReportingTree());
	  }
   }
   private static class CrashReportingTree extends Timber.Tree {
	  @Override protected void log( int priority, String tag, @NonNull String message, Throwable t) {
		 if (priority == Log.VERBOSE || priority == Log.DEBUG) {
			return;
		 }

		 FakeCrashLibrary.log(priority, tag, message);

		 if (t != null) {
			if (priority == Log.ERROR) {
			   FakeCrashLibrary.logError(t);
			} else if (priority == Log.WARN) {
			   FakeCrashLibrary.logWarning(t);
			}
		 }
	  }
   }
}
