package com.xxb.recyclerview_glide.presenter;

import com.xxb.recyclerview_glide.model.LoginModel;
import com.xxb.recyclerview_glide.view.LoginView;

import java.util.Map;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 16:49:16
 * @Description:
 */
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenter(LoginView view) {
        loginModel = new LoginModel();
        loginView = view;
    }

    public void onRelated(Map<String, String> params) {
        loginModel.getHttpData(params);
        loginModel.setLoginListener(new LoginModel.onLoginListener() {
            @Override
            public void onResult(String status) {
                loginView.getViewData(status);
            }
        });
    }
}
