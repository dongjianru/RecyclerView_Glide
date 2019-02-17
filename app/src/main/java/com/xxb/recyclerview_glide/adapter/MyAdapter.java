package com.xxb.recyclerview_glide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xxb.recyclerview_glide.R;
import com.xxb.recyclerview_glide.bean.JsonBean;

import java.util.List;

/**
 * @Auther: Mac
 * @Date: 2019/2/15 19:29:59
 * @Description:
 */
//继承RecycleView适配器
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<JsonBean.ResultBean> result;
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public MyAdapter(Context context, List<JsonBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (TYPE_ONE == i) {
            //引入条目布局
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, null, false);
            //传给自定义Viewholder
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else {
            //引入条目布局
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_expandable_list_item_1, null, false);
            //传给自定义Viewholder
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (TYPE_ONE == itemViewType) {
            //给控件赋值
            viewHolder.title.setText(result.get(i).getTitle());
            Glide.with(context).load(result.get(i).getImageUrl()).into(viewHolder.img);
        } else {
            viewHolder.title2.setText(result.get(i).getTitle());
        }

    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    //自定义ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView title;
        private final TextView title2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //通过布局查找控件
            title = itemView.findViewById(R.id.title1);
            title2 = itemView.findViewById(android.R.id.text1);
            img = itemView.findViewById(R.id.img);
        }
    }
}
