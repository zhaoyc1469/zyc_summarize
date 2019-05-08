package com.mylibrary.shopping_mall.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mylibrary.shopping_mall.R;
import com.mylibrary.shopping_mall.bean.CircleBean;

import java.util.List;

public class FriendCircleAdapter extends RecyclerView.Adapter {

    private List<CircleBean.DataBean> data;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_list, parent, false);
        return new GoodsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public List<CircleBean.DataBean> getData() {
        return data;
    }

    public void setData(List<CircleBean.DataBean> data) {
        this.data = data;
    }


    class GoodsVH extends RecyclerView.ViewHolder {

        public GoodsVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
