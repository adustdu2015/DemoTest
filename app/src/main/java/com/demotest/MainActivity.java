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
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

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

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CHOOSE = 23;
    private Button mButton, button3, button4 , button5 ,button6 ,button7;
    private ImageView mImageView;
    private static final String TAG = "MainActivity";
    private ImageView imageC , iv_next , more_image;
    private TextView temp;
    IWeather iWeather;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Animation animation = null;
    private QMUITipDialog tipDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
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
        });
        imageC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick( final View v ) {
                Call<WeatherBean> call = iWeather.weather("rot2enzrehaztkdk","shijiazhuang");
                call.enqueue(new Callback<WeatherBean>() {
                    @Override
                    public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                        WeatherBean weatherBean = response.body();
                        Log.d("cylog",weatherBean.results.get(0).now.temperature+"");
                        Log.d("cylog",weatherBean.results.get(0).now.text+"");
                        final String temperature = weatherBean.results.get(0).now.text+"";
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                temp.setText(temperature);
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
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
                HttpParams params =  new HttpParams();
                params.put("userName","adustdu2015");
                OkGo.get("http://www.baidu.com").
                    params(params).execute(new StringCallback() {
                    @Override
                    public void onSuccess( final String pS, final okhttp3.Call call, final okhttp3.Response response ) {
                        Log.e(TAG, pS );
                    }
                });

                Toasty.success(MainActivity.this, "修改成功", Toast.LENGTH_SHORT, true).show();
            }
        });
        //Matisse选择器
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
                RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) { //如果允许
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
//                                }
                            } else {
                                Toast.makeText(MainActivity.this, R.string.permission_request_denied, Toast.LENGTH_LONG)
                                    .show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {

                //上游  ObservableEmitter发射器，可以发送next，但是不能同时使用Complete和onError方法
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onNext("3");
                        emitter.onComplete();
                    }
                }).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        Toasty.error(MainActivity.this ,value ,Toast.LENGTH_SHORT).show();
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
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
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
            }
        });
        //悬浮按钮点击
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
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
        });
        more_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
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
        });
//

        button7.setOnClickListener(new View.OnClickListener() {
            NiceDialog fNiceDialog = NiceDialog.init();
            @Override
            public void onClick( final View v ) {
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
        });
        //toolbar中按钮被点击之后
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
                startActivity(new Intent(MainActivity.this ,Main2Activity.class));
            }
        });
        
    }

    void initView(){
        toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar vActionBar = getSupportActionBar();
        vActionBar.setDisplayHomeAsUpEnabled(true);
        vActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        mImageView = ( ImageView ) findViewById(R.id.iv);
        mButton = (Button) findViewById(R.id.btn);
        imageC = ( ImageView ) findViewById(R.id.img_setting);
        temp = ( TextView ) findViewById(R.id.temp);
        button3 = ( Button ) findViewById(R.id.button3);
        button4  = ( Button ) findViewById(R.id.button4);
        button5  = ( Button ) findViewById(R.id.button5);
        button6 = ( Button ) findViewById(R.id.button6);
        button7 = ( Button ) findViewById(R.id.button7);
        fab = ( FloatingActionButton ) findViewById(R.id.fab);
        Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("https://api.thinkpage.cn")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();
        iWeather = retrofit2.create(IWeather.class);
        iv_next = ( ImageView ) findViewById(R.id.iv_next);
        more_image = ( ImageView ) findViewById(R.id.more_image);

    }


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


}
