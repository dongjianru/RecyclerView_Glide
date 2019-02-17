package com.xxb.recyclerview_glide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xxb.recyclerview_glide.R;
import com.xxb.recyclerview_glide.presenter.RegistPresenter;
import com.xxb.recyclerview_glide.utils.TelUtils;
import com.xxb.recyclerview_glide.view.RegistView;

import java.util.HashMap;

public class RegistActivity extends AppCompatActivity implements RegistView {
    private EditText ed_num, ed_pwd;
    private Button regist;
    private TextView return_btu;
    private RegistPresenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        //初始化控件
        return_btu = findViewById(R.id.return_regist);
        ed_num = findViewById(R.id.num_regist);
        ed_pwd = findViewById(R.id.pwd_regist);
        regist = findViewById(R.id.regist_regist);
        //实例化persenter
        persenter = new RegistPresenter(this);
        //点击返回按钮返回登录界面
        return_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转登录页面
                startActivity(new Intent(RegistActivity.this, MainActivity.class));
                finish();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String phone = ed_num.getText().toString();
                String pwd = ed_pwd.getText().toString();
                boolean mobileNO = TelUtils.isMobileNO(phone);
                //判断手机号
                if (!mobileNO) {
                    Toast.makeText(RegistActivity.this, "手机号格式不正确!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断密码
                if (pwd.length() < 3) {
                    Toast.makeText(RegistActivity.this, "密码格式不正确!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //传值给presenter
                HashMap<String, String> params = new HashMap<>();
                params.put("phone",phone);
                params.put("pwd",pwd);
                persenter.sendParameter(params);
            }
        });

    }

    @Override
    public void getViewData(String status) {
        if (status.equals("0000")){
            startActivity(new Intent(RegistActivity.this, MainActivity.class));
            finish();
        }
    }
}
