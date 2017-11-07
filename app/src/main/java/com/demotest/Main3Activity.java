package com.demotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.demotest.fragment.FragmentCommons;
import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

   @BindView (R.id.main3_image)
   ImageView mMain3Image;
   @BindView (R.id.cancel)
   ImageView mCancel;
   @BindView (R.id.tabView)
   TabView mTabView;
   List<TabViewChild > tabViewChildList=new ArrayList<>();
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main3);
	  ButterKnife.bind(this);
		initView();
   }

   void initView(){
	  mTabView = ( TabView ) findViewById(R.id.tabView);
	  TabViewChild tabViewChild01=new TabViewChild(R.drawable.home_hight,R.drawable.home_normal,"首页",  FragmentCommons.newInstance("首页"));
	  TabViewChild tabViewChild02=new TabViewChild(R.drawable.category_hight,R.drawable.category_normal,"分类",  FragmentCommons.newInstance("分类"));
	  TabViewChild tabViewChild03=new TabViewChild(R.drawable.discovery_hight,R.drawable.discovery_normal,"资讯",  FragmentCommons.newInstance("资讯"));
	  TabViewChild tabViewChild04=new TabViewChild(R.drawable.shop_hight,R.drawable.shop_normal,"购物车", FragmentCommons.newInstance("购物车"));
	  TabViewChild tabViewChild05=new TabViewChild(R.drawable.profile_hight,R.drawable.profile_normal,"我的",  FragmentCommons.newInstance("我的"));
	  tabViewChildList.add(tabViewChild01);
	  tabViewChildList.add(tabViewChild02);
	  tabViewChildList.add(tabViewChild03);
	  tabViewChildList.add(tabViewChild04);
	  tabViewChildList.add(tabViewChild05);
	  mTabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
   }

   @OnClick (R.id.cancel)
   public void onViewClicked() {
	  mMain3Image.setVisibility(View.GONE);
	  mCancel.setVisibility(View.GONE);
   }

   @Override
   public void onBackPressed() {
	  super.onBackPressed();
	  Main3Activity.this.finish();
   }
}
