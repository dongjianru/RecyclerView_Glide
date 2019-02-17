package com.xxb.recyclerview_glide.model;

import android.os.Handler;
import android.os.Message;

import com.xxb.recyclerview_glide.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 19:18:57
 * @Description:
 */
public class ShowModel {
    String url = "http://172.17.8.100/small/commodity/v1/bannerShow";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String json = (String) msg.obj;
                //Log.i("xxx", "handleMessage: " + json);
                showListener.onResult(json);
            }
        }
    };

    public void getHttpData() {
        //网络请求
        OkHttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = 0;
                message.obj = json;
                handler.sendMessage(message);
            }
        });
    }

    //接口回调
    public interface onShowListener {
        void onResult(String json);
    }

    public onShowListener showListener;

    public void setShowListener(onShowListener showListener) {
        this.showListener = showListener;
    }
}
