package com.demotest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demotest.Bean.HeFeng;
import com.google.gson.Gson;
import com.heima.easysp.SharedPreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{

   private static final int REQUEST_CODE_CHOOSE = 23;
   private  static  final String urls = "https://free-api.heweather.com/v5/weather";
    private Button mButton ,button6 ,button7 ,rx , weath;
    private static final String TAG = "MainActivity";
   private ImageView iv_next;
   private ImageView more_image;
    private TextView temp;
   private ImageView photo_view;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Animation animation = null;
	private EditText mEditText;
   	private AVLoadingIndicatorView avi;

   private Button showIn , hideIn;
    NiceDialog fNiceDialog = NiceDialog.init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	   EventBus.getDefault().register(this);


	   Observable.create(new ObservableOnSubscribe< Integer >() {
		  @Override
		  public void subscribe( final ObservableEmitter< Integer > e ) throws Exception {
			e.onNext(1);
			 e.onNext(2);
			 e.onNext(3);
			 e.onComplete();
			 e.onNext(4);
		  }
	   }).subscribe(new Observer< Integer >() {
		  Disposable mDisposable;
		  int  i = 0;
		  @Override
		  public void onSubscribe( final Disposable d ) {
			 Log.d(TAG, "onSubscribe");
			 mDisposable = d;
		  }

		  @Override
		  public void onNext( final Integer pInteger ) {
			 Log.d(TAG, pInteger.toString());
			 i++;
			
			 if(i == 2 ){
				mDisposable.dispose();
				Log.d(TAG, "isDisposeable:"+mDisposable.isDisposed());
			 }
			
		  }

		  @Override
		  public void onError( final Throwable e ) {
			 Log.d(TAG, "onError");
		  }

		  @Override
		  public void onComplete() {
			 Log.d(TAG, "onComplete");
		  }
	   });

        initView();
    }

    void initView(){
		mEditText = ( EditText ) findViewById(R.id.main_edit);
	   showIn = ( Button ) findViewById(R.id.showIn);
	   hideIn = ( Button ) findViewById(R.id.hideIn);
		showIn.setOnClickListener(this);
	   hideIn.setOnClickListener(this);

	   avi = ( AVLoadingIndicatorView ) findViewById(R.id.avi);

        toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar vActionBar = getSupportActionBar();
        vActionBar.setDisplayHomeAsUpEnabled(true);
		vActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        mButton = (Button) findViewById(R.id.btn);
	   	mButton.setOnClickListener(this);


        temp = ( TextView ) findViewById(R.id.status);



        fab = ( FloatingActionButton ) findViewById(R.id.fab);
	   	fab.setOnClickListener(this);

        iv_next = ( ImageView ) findViewById(R.id.iv_next);
	   	iv_next.setOnClickListener(this);


	   weath = ( Button ) findViewById(R.id.weath);
	   weath.setOnClickListener(this);

	   photo_view = ( ImageView ) findViewById(R.id.photo_view);
	   photo_view.setImageResource(R.drawable.postsql);
	   photo_view.setOnClickListener(this);
    }


   /**
	* 图片选择的回调函数
	* @param requestCode
	* @param resultCode
	* @param data
	*/
   @Override
    protected void onActivityResult( final int requestCode, final int resultCode, final Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Log.e(TAG, String.valueOf(Matisse.obtainResult(data)));
            Log.d(TAG, String.valueOf(Matisse.obtainPathResult(data)));

        }
    }

    @Override
    public boolean onCreateOptionsMenu( final Menu menu ) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( final MenuItem item ) {

        switch ( item.getItemId() ){

            case android.R.id.home:
//                startActivity(new Intent(MainActivity.this ,Detail.class));
//                finish();
                NiceDialog.init()
                    .setLayoutId(R.layout.share_layout)
                    .setConvertListener(new ViewConvertListener() {
                        @Override
                        public void convertView( ViewHolder holder, final BaseNiceDialog dialog) {
                            holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .setDimAmount(0.3f)
                    .setShowBottom(true)
                    .show(getSupportFragmentManager());
                break;
			default:break;
        }
        return false;
    }

   @Subscriber (tag = "my_tag")
   private void updateUserWithTag(User user) {
	  Log.e(TAG, user.name);
   }


    @Override
    public void onClick( final View v ) {

        switch ( v.getId()  ){

		   case R.id.photo_view:
		      Intent mIntent = new Intent(MainActivity.this ,ImageGallery.class);
			  mIntent.putExtra("keys" ,"postsql");
		      startActivity(mIntent);
			  break;

            case  R.id.iv_next:
			   Toast.makeText(MainActivity.this, "拒绝了权限", Toast.LENGTH_SHORT).show();

                break;
            case R.id.fab:
			   startActivity(new Intent(MainActivity.this ,FullscreenActivity.class));
                break;
		   case R.id.btn:
		      startActivity(new Intent(MainActivity.this,Main3Activity.class));
		      break;
		   case R.id.weath:

		      String city = mEditText.getText().toString();
			  if(city.equals("")){
				 Toast.makeText(this, "请输入城市", Toast.LENGTH_SHORT).show();
			  }else{
				 HttpParams mHttpParams = new HttpParams();
				 mHttpParams.put("city",city);
				 mHttpParams.put("key", "b05097c2282946a38384b6763bf50749");
				 OkGo.get(urls).params(mHttpParams).execute(new StringCallback() {
					@Override
					public void onSuccess( final String pS, final okhttp3.Call call, final okhttp3.Response response ) {
					   Gson mGson =new Gson();
					   HeFeng mHeFeng = mGson.fromJson(pS ,HeFeng.class);
					   temp.setText("城市:"+mHeFeng.getHeWeather5().get(0).getBasic().getCity()+
						   "\n天气情况:"+mHeFeng.getHeWeather5().get(0).getAqi().getCity().getQlty()+
						   "\n当前温度:"+mHeFeng.getHeWeather5().get(0).getNow().getTmp()
					   );
					}
				 });
			  }
		      break;
		   case R.id.showIn:
			  SharedPreferencesUtils.init(MainActivity.this).putString("key" , "value");
			  avi.show();
		      break;
		   case R.id.hideIn:
		      Toast.makeText(MainActivity.this ,SharedPreferencesUtils.init(MainActivity.this).getString("key"),Toast.LENGTH_SHORT).show();
			  avi.hide();
		      break;
		   default:break;
        }
    }


   /**
	* 请求读取外部存储的权限
	*/


   private void selectImg() {
	  Matisse.from(MainActivity.this)
		.choose(MimeType.ofAll())
		.countable(true)
		.capture(true)
		.captureStrategy(
		   new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
		.maxSelectable(9)
//                                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
		.gridExpectedSize(
		   getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
		.restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
		.thumbnailScale(0.85f)
		.imageEngine(new GlideEngine())
		.forResult(REQUEST_CODE_CHOOSE);
   }

    void niceDialog(){

        fNiceDialog
            .setLayoutId(R.layout.loading_layout)
            .setWidth(100)
            .setHeight(100)
            .setDimAmount(0)
            .show(getSupportFragmentManager());
        button7.postDelayed(new Runnable() {
            @Override
            public void run() {
                fNiceDialog.dismiss();
            }
        },1500);
    }


   @Override
   protected void onDestroy() {
	  EventBus.getDefault().unregister(this);
	  super.onDestroy();

   }
}
