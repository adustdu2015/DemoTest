package com.demotest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

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
    private Button mButton, button3, button4;
    private ImageView mImageView;
    private static final String TAG = "MainActivity";
    private ImageView imageC;
    private TextView temp;
    IWeather iWeather;
    private EditText edit_text;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Glide.with(MainActivity.this).load("https://static.oschina.net/uploads/img/201708/14090048_hz62.jpg").
                    asBitmap().
					placeholder(R.drawable.placeholder_unknown).//占位符
                    error(R.drawable.error).  //占位错误符
                    diskCacheStrategy(DiskCacheStrategy.RESULT).
                    into(mImageView);


                Log.d(TAG, "onClick: ");
                // TODO: 2017/8/19 下一步
                // FIXME: 2017/8/19
                Log.d(TAG, new Test().getName());
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
//                startActivity(new Intent(MainActivity.this,Demo1.class));
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
                                            .choose(MimeType.allOf())
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
    }

    void initView(){
        toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mImageView = ( ImageView ) findViewById(R.id.iv);
        mButton = (Button) findViewById(R.id.btn);
        imageC = ( ImageView ) findViewById(R.id.img_setting);
        temp = ( TextView ) findViewById(R.id.temp);
        edit_text = ( EditText ) findViewById(R.id.edit_text);
        button3 = ( Button ) findViewById(R.id.button3);
        button4  = ( Button ) findViewById(R.id.button4);
        Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("https://api.thinkpage.cn")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();
        iWeather = retrofit2.create(IWeather.class);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onActivityResult( final int requestCode, final int resultCode, final Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Log.e(TAG, String.valueOf(Matisse.obtainResult(data)));
            Log.e(TAG, String.valueOf(data.getStringArrayListExtra("extra_result_selection_path")));

        }
    }
}
