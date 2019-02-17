package com.xxb.recyclerview_glide.utils;

import android.util.Log;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 17:00:32
 * @Description:
 */
public class OkHttpUtils {

    //私有构造方法
    private OkHttpUtils() {}

    private static OkHttpUtils okHttpUtils = null;

    //静态工厂
    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    //拦截器
    private static Interceptor myInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx", "log:拦截 " + message);
            }
        });
        return interceptor;
    }

    //执行get方法
    public static void doGet(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(myInterceptor())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //执行post
    public static void doPost(String url, Map<String, String> params, Callback callback) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(myInterceptor())
                .build();
        //请求体
        FormBody.Builder builder = new FormBody.Builder();
        //遍历map集合
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
