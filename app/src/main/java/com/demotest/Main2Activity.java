package com.demotest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity
	implements NavigationView.OnNavigationItemSelectedListener {
	private RecyclerView recyclerImage;
   private MyAdapter mAdapter;
   private List<Fruit> mFruits = new ArrayList<>();
   private SwipeRefreshLayout swipeRefreshLayout;
   @Override
   protected void onCreate( Bundle savedInstanceState ) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main2);
	  Toolbar toolbar = ( Toolbar ) findViewById(R.id.toolbar);
	  setSupportActionBar(toolbar);
	  initFruit();
	  recyclerImage = ( RecyclerView ) findViewById(R.id.recyclerImage);
	  LinearLayoutManager vManager = new LinearLayoutManager(this);
	  recyclerImage.setLayoutManager(vManager);
	  mAdapter = new MyAdapter(mFruits);
	  recyclerImage.setAdapter(mAdapter);


	  swipeRefreshLayout = ( SwipeRefreshLayout ) findViewById(R.id.swipeRefreshLayout);
	  swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent , android.R.color.holo_green_light ,android.R.color.background_light , android.R.color.holo_blue_bright);
	  swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
		 @Override
		 public void onRefresh() {
			new Thread(new Runnable() {
			   @Override
			   public void run() {
				  try {
					 Thread.sleep(5000);
				  } catch ( InterruptedException pE ) {
					 pE.printStackTrace();
				  }
				  runOnUiThread(new Runnable() {
					 @Override
					 public void run() {
						refreshFruit();
					 }
				  });
			   }
			}).start();
		 }
	  });



	  DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
		  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
	  drawer.setDrawerListener(toggle);
	  toggle.syncState();

	  NavigationView navigationView = ( NavigationView ) findViewById(R.id.nav_view);
	  navigationView.setNavigationItemSelectedListener(this);
   }

   private void initFruit() {
	  for( int i = 0 ;i < 2 ;i ++){
		 Fruit mfruit = new Fruit(R.drawable.ic_build_green_600_24dp ,"头像一"+i , "平平凡凡才是真");
		 Fruit mfruit2 = new Fruit(R.drawable.ic_alarm_add_red_400_36dp ,"头像二"+i , "平平凡凡才是真");
		 mFruits.add(mfruit);
		 mFruits.add(mfruit2);
	  }

   }

   @Override
   public void onBackPressed() {
	  DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	  if ( drawer.isDrawerOpen(GravityCompat.START) ) {
		 drawer.closeDrawer(GravityCompat.START);
	  } else {
		 super.onBackPressed();
	  }
   }
   void refreshFruit(){
	  for( int i = 4;i < 6 ;i ++){
		 Fruit mfruit4 = new Fruit(R.drawable.ic_build_green_600_24dp ,"头像一"+i , "平平凡凡才是真");
		 Fruit mfruit5 = new Fruit(R.drawable.ic_alarm_add_red_400_36dp ,"头像二"+i , "平平凡凡才是真");
		 mFruits.add(0 ,mfruit4);
		 mFruits.add(0 ,mfruit5);
	  }
	  mAdapter.notifyDataSetChanged();
	  swipeRefreshLayout.setRefreshing(false);
   }
   @Override
   public boolean onCreateOptionsMenu( Menu menu ) {
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.main2, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected( MenuItem item ) {
	  // Handle action bar item clicks here. The action bar will
	  // automatically handle clicks on the Home/Up button, so long
	  // as you specify a parent activity in AndroidManifest.xml.
	  int id = item.getItemId();

	  //noinspection SimplifiableIfStatement
	  if ( id == R.id.action_settings ) {
		 for( int i = 10 ;i < 12 ;i ++){
			Fruit mfruit = new Fruit(R.drawable.ic_build_green_600_24dp ,"头像一:"+i , "平平凡凡才是真");
			Fruit mfruit2 = new Fruit(R.drawable.ic_alarm_add_red_400_36dp ,"头像二:"+i , "平平凡凡才是真");
			mFruits.add(0 ,mfruit);
			mFruits.add(0 ,mfruit2);
		 }
		 mAdapter.notifyDataSetChanged();

		 Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
		 return true;
	  }
	  return super.onOptionsItemSelected(item);
   }

   @SuppressWarnings ("StatementWithEmptyBody")
   @Override
   public boolean onNavigationItemSelected( MenuItem item ) {
	  // Handle navigation view item clicks here.
	  int id = item.getItemId();

	  DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	  drawer.closeDrawer(GravityCompat.START);
	  return true;
   }
}
