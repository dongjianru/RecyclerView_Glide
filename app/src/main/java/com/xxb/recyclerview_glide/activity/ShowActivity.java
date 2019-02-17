package com.xxb.recyclerview_glide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.xxb.recyclerview_glide.R;
import com.xxb.recyclerview_glide.adapter.MyAdapter;
import com.xxb.recyclerview_glide.bean.JsonBean;
import com.xxb.recyclerview_glide.presenter.ShowPresenter;
import com.xxb.recyclerview_glide.view.ShowView;

import java.util.List;

public class ShowActivity extends AppCompatActivity implements ShowView {

    private RecyclerView rlv;
    private ShowPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //控件
        rlv = findViewById(R.id.rlv);
        //实例化p
        presenter = new ShowPresenter(this);
        //为了方便p管理内存泄漏
        presenter.attachView(this);
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置RecycleView展示类型
        rlv.setLayoutManager(linearLayoutManager);
        //传参
        presenter.onRelated();
    }

    @Override
    public void getViewData(String json) {
        if (json != null) {
            Gson gson = new Gson();
            JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
            List<JsonBean.ResultBean> result = jsonBean.getResult();
            rlv.setAdapter(new MyAdapter(ShowActivity.this, result));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deatchView();
    }
}
