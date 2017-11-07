package com.demotest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demotest.R;

/**
 *
 * @author a
 * @date 2017/11/7
 */

public class FragmentCommons extends android.support.v4.app.Fragment {
   @Nullable
   public static FragmentCommons newInstance( String text){
	  FragmentCommons fragmentCommon=new FragmentCommons();
	  Bundle bundle=new Bundle();
	  bundle.putString("text",text);
	  fragmentCommon.setArguments(bundle);
	  return fragmentCommon;
   }
   @Override
   public View onCreateView( final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState ) {
	  View view = inflater.inflate(R.layout.fg_content,container,false);
	  TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
	  txt_content.setText(getArguments().getString("text"));
	  return view;
   }
}
