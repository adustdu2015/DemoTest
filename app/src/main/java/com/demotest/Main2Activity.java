package com.demotest;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity
	implements NavigationView.OnNavigationItemSelectedListener {
   @BindView (R.id.toolbar)
   Toolbar fToolbar;
   @BindView (R.id.recyclerImage)
   XRecyclerView fRecyclerImage;
   @BindView (R.id.nav_view)
   NavigationView fNavView;
   @BindView (R.id.drawer_layout)
   DrawerLayout fDrawerLayout;
   private  MyAdapter mAdapter;
   private List< Fruit > mFruits = new ArrayList<>();

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main2);
	  /**
	   * 发送事件
	   */
	  EventBus.getDefault().post(new User("android"), "my_tag");

	  ButterKnife.bind(this);

	  initView();

	  initFruit();

	  //recycleView刷新
	  mAdapter = new MyAdapter(mFruits);
	  fRecyclerImage.setAdapter(mAdapter);
	  fRecyclerImage.refresh();

	  fRecyclerImage.setLoadingListener(new XRecyclerView.LoadingListener() {
		 @Override
		 public void onRefresh() {
			new Handler().postDelayed(new Runnable() {
			   @Override
			   public void run() {

				  refreshFruit();
				  fRecyclerImage.refreshComplete();
			   }
			}, 1500);
		 }

		 @Override
		 public void onLoadMore() {
			new Handler().postDelayed(new Runnable() {
			   public void run() {
				  refreshFruit();
				  fRecyclerImage.refreshComplete();
			   }
			}, 1000);
		 }
	  });

   }


   private void initFruit() {
	  for ( int i = 0; i < 2; i++ ) {
		 Fruit mfruit = new Fruit(R.drawable.ic_build_green_600_24dp, "头像一" + i, "平平凡凡才是真");
		 Fruit mfruit2 = new Fruit(R.drawable.ic_alarm_add_red_400_36dp, "头像二" + i, "平平凡凡才是真");
		 mFruits.add(mfruit);
		 mFruits.add(mfruit2);
	  }

   }

   @Override
   public void onBackPressed() {
//	  DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	  if ( fDrawerLayout.isDrawerOpen(GravityCompat.START) ) {
		 fDrawerLayout.closeDrawer(GravityCompat.START);
	  } else {
		 super.onBackPressed();
	  }
   }

   void refreshFruit() {
	  for ( int i = 4; i < 6; i++ ) {
		 Fruit mfruit4 = new Fruit(R.drawable.ic_build_green_600_24dp, "头像一" + i, "平平凡凡才是真");
		 Fruit mfruit5 = new Fruit(R.drawable.ic_alarm_add_red_400_36dp, "头像二" + i, "平平凡凡才是真");
		 mFruits.add(0, mfruit4);
		 mFruits.add(0, mfruit5);
	  }
	  mAdapter.notifyDataSetChanged();
   }

   void initView() {

	  setSupportActionBar(fToolbar);
	  LinearLayoutManager vManager = new LinearLayoutManager(this);
	  fRecyclerImage.setLayoutManager(vManager);

	  fRecyclerImage.setRefreshProgressStyle(ProgressStyle.SquareSpin);
	  fRecyclerImage.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
	  fRecyclerImage.setArrowImageView(R.drawable.iconfont_downgrey);


	  fNavView.setNavigationItemSelectedListener(this);



   }


   @Override
   public boolean onCreateOptionsMenu( Menu menu ) {
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.main2, menu);
	  MenuItem item = menu.findItem(R.id.action_search);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected( MenuItem item ) {
	  // Handle action bar item clicks here. The action bar will
	  // automatically handle clicks on the Home/Up button, so long
	  // as you specify a parent activity in AndroidManifest.xml.
	  int id = item.getItemId();
	  switch ( id ){
		 case R.id.action_settings:
			for ( int i = 10; i < 12; i++ ) {
			   Fruit mfruit = new Fruit(R.drawable.ic_build_green_600_24dp, "头像一:" + i, "平平凡凡才是真");
			   Fruit mfruit2 = new Fruit(R.drawable.ic_alarm_add_red_400_36dp, "头像二:" + i, "平平凡凡才是真");
			   mFruits.add(0, mfruit);
			   mFruits.add(0, mfruit2);
			}
			mAdapter.notifyDataSetChanged();

			Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
			break;
		 case R.id.action_search:

		    break;

	  }
	  return super.onOptionsItemSelected(item);
   }

   @SuppressWarnings ("StatementWithEmptyBody")
   @Override
   public boolean onNavigationItemSelected( MenuItem item ) {
	  // Handle navigation view item clicks here.
	  int id = item.getItemId();

	  fDrawerLayout.closeDrawer(GravityCompat.START);
	  return true;
   }


}
