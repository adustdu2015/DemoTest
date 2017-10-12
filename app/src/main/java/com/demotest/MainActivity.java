package com.demotest;

import android.Manifest;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demotest.Bean.HeFeng;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

   private static final int REQUEST_CODE_CHOOSE = 23;
   private  static  final String urls = "https://free-api.heweather.com/v5/weather";
    private Button mButton ,button6 ,button7 ,rx , weath ,imageC;
    private static final String TAG = "MainActivity";
   private ImageView iv_next;
   private ImageView more_image;
    private TextView temp;
    IWeather iWeather;
   private ImageView photo_view;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Animation animation = null;
    private QMUITipDialog tipDialog;

   	private AVLoadingIndicatorView avi;

   private Button showIn , hideIn;
    NiceDialog fNiceDialog = NiceDialog.init();

   Retrofit retrofit2 = new Retrofit.Builder()
	   .baseUrl("https://api.thinkpage.cn")
	   .addConverterFactory(GsonConverterFactory.create())
	   .client(new OkHttpClient())
	   .build();

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

        imageC = ( Button ) findViewById(R.id.img_setting);
	   	imageC.setOnClickListener(this);

        temp = ( TextView ) findViewById(R.id.temp);


        button6 = ( Button ) findViewById(R.id.button6);
	   	button6.setOnClickListener(this);

        button7 = ( Button ) findViewById(R.id.button7);
	   	button7.setOnClickListener(this);

        fab = ( FloatingActionButton ) findViewById(R.id.fab);
	   	fab.setOnClickListener(this);



        iWeather = retrofit2.create(IWeather.class);



        iv_next = ( ImageView ) findViewById(R.id.iv_next);
	   	iv_next.setOnClickListener(this);

        more_image = ( ImageView ) findViewById(R.id.more_image);
	   	more_image.setOnClickListener(this);

	   rx = ( Button ) findViewById(R.id.rx);
		rx.setOnClickListener(this);

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
		   case R.id.btn:
		      //QMUI的提示Dialog
			  tips();
			  break;

            case R.id.img_setting:
                retro();
                break;

            case R.id.button6:
                final String[] items = new String[]{"选项1", "选项2", "选项3"};
                new QMUIDialog.MenuDialogBuilder(MainActivity.this)
                    .addItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    })
                    .show();
			   reTest();
                break;
            case R.id.button7:
                niceDialog();
                break;
            case R.id.more_image:
                qmuiDialog();
                break;

            case  R.id.iv_next:
               RxPermissions mPermissions =new RxPermissions(MainActivity.this);
			   mPermissions.request(Manifest.permission.READ_SMS).subscribe(new Observer< Boolean >() {
				  @Override
				  public void onSubscribe( final Disposable d ) {

				  }

				  @Override
				  public void onNext( final Boolean pBoolean ) {
						if(pBoolean){
						   startActivity(new Intent(MainActivity.this ,Main2Activity.class));
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
			   startActivity(new Intent(MainActivity.this ,FullscreenActivity.class));
                break;
		   case R.id.rx:
		      //申请读取SMS
			 RxPermissions mRxPermissions = new RxPermissions(MainActivity.this);
			  mRxPermissions.request(Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.BLUETOOTH_PRIVILEGED
				  ,Manifest.permission.READ_CONTACTS).
				  subscribe(new Observer< Boolean >() {
					 @Override
					 public void onSubscribe( final Disposable d ) {

					 }

					 @Override
					 public void onNext( final Boolean pBoolean ) {
							if(pBoolean){
							   qDialog();
							}else{
							   Log.d(TAG, "error");
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
		   case R.id.weath:
			  HttpParams mHttpParams = new HttpParams();
			  mHttpParams.put("city","北京");
			  mHttpParams.put("key", "b05097c2282946a38384b6763bf50749");
		      OkGo.get(urls).params(mHttpParams).execute(new StringCallback() {
				 @Override
				 public void onSuccess( final String pS, final okhttp3.Call call, final okhttp3.Response response ) {
					Gson mGson =new Gson();
					HeFeng mHeFeng = mGson.fromJson(pS ,HeFeng.class);
					Toast.makeText(MainActivity.this, mHeFeng.getHeWeather5().get(0).getAqi().getCity().getQlty(),Toast.LENGTH_SHORT).show();
				 }
			  });
		      break;
		   case R.id.showIn:
			  avi.show();
		      break;
		   case R.id.hideIn:
			  avi.hide();
		      break;
        }
    }

   /**
	* 获取retrofit天气
	*/
   private void retro() {
	   //retrofit的使用
        Call<WeatherBean> call = iWeather.weather("rot2enzrehaztkdk","shijiazhuang");
        call.enqueue(new Callback<WeatherBean>() {
			@Override
			public void onResponse( Call<WeatherBean> call, Response<WeatherBean> response) {
				WeatherBean weatherBean = response.body();
				Log.d("cylog",weatherBean.results.get(0).now.temperature+"");
				Log.d("cylog",weatherBean.results.get(0).now.text+"");
				final String text = weatherBean.results.get(0).now.text+"";
				MainActivity.this.runOnUiThread(new Runnable() {
													@Override
													public void run() {
														temp.setText("天气情况:"+text);
													}
												}

				);
			}

			@Override
			public void onFailure(Call<WeatherBean> call, Throwable t) {
				Log.d("cylog", "Error" + t.toString());
			}
		});


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

   private void tips() {
        tipDialog = new QMUITipDialog.Builder(MainActivity.this)
			.setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
			.setTipWord("发送成功")
			.create();
        tipDialog.show();
//                tipDialog.dismiss();
        imageC.postDelayed(new Runnable() {
			@Override
			public void run() {
				tipDialog.dismiss();
			}
		},1500);
    }

    private void qDialog() {

	   new QMUIDialog.MessageDialogBuilder(MainActivity.this)
			.setTitle("标题")
			.setMessage("确定要发送吗？")
			.addAction("取消", new QMUIDialogAction.ActionListener() {
				@Override
				public void onClick(QMUIDialog dialog, int index) {
					dialog.dismiss();
				}
			})
			.addAction("确定", new QMUIDialogAction.ActionListener() {
				@Override
				public void onClick(QMUIDialog dialog, int index) {
					dialog.dismiss();
					Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
				}
			})
			.show();
    }

    private void reTest() {
        //上游  ObservableEmitter发射器，可以发送next，但是不能同时使用Complete和onError方法
        Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("1");
				emitter.onNext("2");
				emitter.onComplete();
			}
		}).subscribe(new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				Log.d(TAG, "subscribe");
			}

			@Override
			public void onNext(String value) {
				Toasty.error(MainActivity.this ,value , Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(Throwable e) {
				Log.d(TAG, "error");
			}

			@Override
			public void onComplete() {
				Log.d(TAG, "complete");
			}
		});
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


    void qmuiDialog(){

	   new QMUIDialog.MessageDialogBuilder(MainActivity.this)
            .setTitle("标题")
            .setMessage("确定要删除吗？")
            .addAction("取消", new QMUIDialogAction.ActionListener() {
                @Override
                public void onClick(QMUIDialog dialog, int index) {
                    dialog.dismiss();
                }
            })
            .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                @Override
                public void onClick(QMUIDialog dialog, int index) {
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            })
            .show();
    }

   @Override
   protected void onDestroy() {
	  EventBus.getDefault().unregister(this);
	  super.onDestroy();

   }
}
