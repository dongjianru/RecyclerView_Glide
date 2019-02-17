package com.xxb.recyclerview_glide.presenter;

import com.xxb.recyclerview_glide.model.RegistModel;
import com.xxb.recyclerview_glide.view.RegistView;

import java.util.HashMap;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 18:49:21
 * @Description:
 */
public class RegistPresenter {

    private final RegistModel registModel;
    private final RegistView registView;

    public RegistPresenter(RegistView view) {
        registModel = new RegistModel();
        registView = view;
    }

    public void sendParameter(HashMap<String, String> params) {
        registModel.getHttpData(params);
        registModel.setRegistListener(new RegistModel.onRegistListener() {
            @Override
            public void onResult(String status) {
                registView.getViewData(status);
            }
        });
    }
}
