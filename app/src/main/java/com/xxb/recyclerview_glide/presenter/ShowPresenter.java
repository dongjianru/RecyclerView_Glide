package com.xxb.recyclerview_glide.presenter;

import com.xxb.recyclerview_glide.model.ShowModel;
import com.xxb.recyclerview_glide.view.ShowView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 19:16:41
 * @Description:
 */
public class ShowPresenter<T> {

    private final ShowModel showModel;
    private final ShowView showView;
    private Reference<T> tReference;

    public ShowPresenter(ShowView view) {
        showModel = new ShowModel();
        showView = view;
    }

    //在p层使用软引用管理外部类对象
    public void attachView(T t) {
        //创建软引用(父类引用子类)
        tReference = new WeakReference<T>(t);
    }

    public void onRelated() {
        showModel.getHttpData();
        //获取m值
        showModel.setShowListener(new ShowModel.onShowListener() {
            @Override
            public void onResult(String json) {
                showView.getViewData(json);
            }
        });
    }

    //解除关联
    public void deatchView(){
        if (tReference!=null){
            tReference.clear();
            tReference = null;
        }
    }
}
