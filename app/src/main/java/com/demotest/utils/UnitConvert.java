package com.demotest.utils;

import android.content.Context;

/**
 * Created by a on 2017/12/13.
 */

public class UnitConvert {
   public static int px2dip( Context context, float pxValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (pxValue / scale + 0.5f);
   }
   public static int dip2px( Context context, float dipValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (dipValue * scale + 0.5f);
   }
}
