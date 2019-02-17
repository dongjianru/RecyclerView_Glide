package com.xxb.recyclerview_glide.model;

import android.os.Handler;
import android.os.Message;

import com.xxb.recyclerview_glide.utils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 16:59:15
 * @Description:
 */
public class LoginModel {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String json = (String) msg.obj;
//                Log.i("xxx", "handleMessage: " + json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    loginListener.onResult(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    String path = "http://172.17.8.100/small/user/v1/login";

    public void getHttpData(Map<String, String> params) {
        OkHttpUtils.doPost(path, params, new Callback() {
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
    public interface onLoginListener {
        void onResult(String status);
    }

    public onLoginListener loginListener;

    public void setLoginListener(onLoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
