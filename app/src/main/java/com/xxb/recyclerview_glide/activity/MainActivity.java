package com.xxb.recyclerview_glide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xxb.recyclerview_glide.R;
import com.xxb.recyclerview_glide.presenter.LoginPresenter;
import com.xxb.recyclerview_glide.utils.TelUtils;
import com.xxb.recyclerview_glide.view.LoginView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText ed_pwd, ed_num;
    private Button regist, login;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件
        ed_num = findViewById(R.id.num_login);
        ed_pwd = findViewById(R.id.pwd_login);
        login = findViewById(R.id.login_login);
        regist = findViewById(R.id.regist_login);
        //实例化p
        presenter = new LoginPresenter(this);
        //跳转注册
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistActivity.class));
                finish();
            }
        });
        //登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String phone = ed_num.getText().toString();
                String pwd = ed_pwd.getText().toString();
                boolean mobileNO = TelUtils.isMobileNO(phone);
                //判断格式
                if (!mobileNO) {
                    Toast.makeText(MainActivity.this, "手机号格式不对", Toast.LENGTH_SHORT);
                    return;
                }
                if (pwd.length() < 3) {
                    Toast.makeText(MainActivity.this, "密码格式不对", Toast.LENGTH_SHORT);
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("pwd", pwd);

                presenter.onRelated(params);
            }
        });
    }

    @Override
    public void getViewData(String status) {
        if (status.equals("0000")) {
            startActivity(new Intent(MainActivity.this, ShowActivity.class));
            finish();
        }
    }
}
