package com.demotest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import io.github.xudaojie.qrcodelib.CaptureActivity;

/**
 * Created by a on 2017/11/11.
 */

public class SimpleCaptureActivity extends CaptureActivity {
   protected Activity mActivity = this;

   private AlertDialog mDialog;

   @Override
   public void onCreate(Bundle savedInstanceState) {
	  mActivity = this;
	  super.onCreate(savedInstanceState);
   }

   @Override
   protected void handleResult(String resultString) {
	  if ( TextUtils.isEmpty(resultString)) {
		 Toast.makeText(mActivity, io.github.xudaojie.qrcodelib.R.string.scan_failed, Toast.LENGTH_SHORT).show();
		 restartPreview();
	  } else {
		 if (mDialog == null) {
			mDialog = new AlertDialog.Builder(mActivity)
				.setMessage(resultString)
				.setPositiveButton("确定", null)
				.create();
			mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
			   @Override
			   public void onDismiss(DialogInterface dialog) {
				  restartPreview();
			   }
			});
		 }
		 if (!mDialog.isShowing()) {
			mDialog.setMessage(resultString);
			mDialog.show();
		 }
	  }
   }
}