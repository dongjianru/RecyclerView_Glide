package com.xxb.recyclerview_glide.model;

import android.os.Handler;
import android.os.Message;

import com.xxb.recyclerview_glide.utils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 18:50:36
 * @Description:
 */
public class RegistModel {

    String url = "http://172.17.8.100/small/user/v1/register";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String json = (String) msg.obj;
                // Log.i("xxx", "handleMessage: " + json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    registListener.onResult(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void getHttpData(HashMap<String, String> params) {
        OkHttpUtils.doPost(url, params, new Callback() {
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

    public interface onRegistListener {
        void onResult(String status);
    }

    public onRegistListener registListener;

    public void setRegistListener(onRegistListener registListener) {
        this.registListener = registListener;
    }
}
