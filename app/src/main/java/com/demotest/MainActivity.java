package com.demotest;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.demotest.Bean.HeFeng;
import com.demotest.utils.CountDownTimerUtils;
import com.demotest.utils.UnitConvert;
import com.google.gson.Gson;
import com.heima.easysp.SharedPreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wang.avi.AVLoadingIndicatorView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.drakeet.support.toast.ToastCompat;
import okhttp3.Call;
import okhttp3.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{

   private static final int REQUEST_CODE_CHOOSE = 23;
   private  static  final String urls = "https://free-api.heweather.com/v5/weather";
    private Button mButton ,button6 ,button7 ,rx , weath,frame;
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
   private Button showIn , hideIn,btn_send;
    NiceDialog fNiceDialog = NiceDialog.init();
   private AnimationDrawable animationDrawable;
   private static final int REQUEST_QR_CODE = 1;
   private ConstraintLayout mConstraintLayout;
   private LinearLayout line4;
   private TextView tv_header;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	   EventBus.getDefault().register(this);

	  LogUtils.d("aaaa");
	  LogUtils.d(new NullPointerException("12345"));
	  initView();

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


    }

    void initView(){


	   tv_header = ( TextView ) findViewById(R.id.tv_header);
	   tv_header.setOnClickListener(new View.OnClickListener() {
		  @Override
		  public void onClick( final View v ) {

		  }
	   });

	   Log.d(TAG, "unitConvert: "+ UnitConvert.px2dip(MainActivity.this,20L));


	   line4 = ( LinearLayout ) findViewById(R.id.line4);
	   btn_send = ( Button ) findViewById(R.id.btn_send);
	   btn_send.setOnClickListener(new View.OnClickListener() {
		  @Override
		  public void onClick( final View v ) {
			final CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btn_send,60000,1000);
			 mCountDownTimerUtils.start();
		  }
	   });
	   mConstraintLayout = ( ConstraintLayout ) findViewById(R.id.main_constraint);


		mEditText = ( EditText ) findViewById(R.id.main_edit);
	   showIn = ( Button ) findViewById(R.id.showIn);
	   hideIn = ( Button ) findViewById(R.id.hideIn);
		showIn.setOnClickListener(this);
	   hideIn.setOnClickListener(this);
	   frame = ( Button ) findViewById(R.id.frame);
	   frame.setOnClickListener(this);
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
	   photo_view.setOnClickListener(this);
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
			  Timber.tag("LifeCycles");
			  Timber.d("Activity Created");
			  Timber.i("aaaaaaaaa");
			  Timber.i(String.format("%s%s"),"aaa","bbb");
		      startActivity(mIntent);
			  break;

            case  R.id.iv_next:
			   RxPermissions rxPermissions = new RxPermissions(this);
			  	rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Observer< Boolean >() {
				   @Override
				   public void onSubscribe( final Disposable d ) {

				   }

				   @Override
				   public void onNext( final Boolean aBoolean ) {
						if(aBoolean){
						   Log.d(TAG, "ok");
						}
				   }

				   @Override
				   public void onError( final Throwable e ) {

				   }

				   @Override
				   public void onComplete() {

				   }
				});

                break;
            case R.id.fab:
			   ToastCompat.makeText(MainActivity.this, "hello world!", Toast.LENGTH_SHORT).show();
//			   ActivitySplitAnimationUtil.startActivity(MainActivity.this, new Intent(MainActivity.this, FullscreenActivity.class));
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

					@Override
					public void onError( final Call call, final Response response, final Exception e ) {
					   Toast.makeText(MainActivity.this, ( CharSequence ) response.body(), Toast.LENGTH_SHORT).show();
					}
				 });
			  }
		      break;
		   case R.id.showIn:
			  SharedPreferencesUtils.init(MainActivity.this).putString("key" , "value");
			  avi.show();
		      break;
		   case R.id.hideIn:
			  avi.hide();
			  startActivity(new Intent(MainActivity.this ,Detail.class));
		      break;
		   case R.id.frame:
			  photo_view.setImageResource(R.drawable.animation1);
			  animationDrawable = (AnimationDrawable) photo_view.getDrawable();
			  animationDrawable.start();
			  break;
		   default:break;
        }
    }

   @Override
   protected void onActivityResult( int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  if (resultCode == RESULT_OK
		  && requestCode == REQUEST_QR_CODE
		  && data != null) {
		 String result = data.getStringExtra("result");
		 Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
	  }
   }


   @Override
   protected void onDestroy() {
	  EventBus.getDefault().unregister(this);
	  super.onDestroy();

   }
}
