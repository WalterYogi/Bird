package com.finance365.bird;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.finance365.bird.model.RequestUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by admin on 2016/5/16.
 */
public class App extends Application {
    public Activity mActivity;
    public static Context mContext;
    public static App app;
    public String host = "";
    public String token;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initOkhttpUtils();
    }

    private void init() {
        app = this;
    }

    /**
     * 初始化okHttpUtils
     */
    private void initOkhttpUtils(){
        CookieJarImpl cookieJar1 = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
//                .cookieJar(cookieJar1)
                .sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null)) //设置可访问所有的https网站
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }



}
