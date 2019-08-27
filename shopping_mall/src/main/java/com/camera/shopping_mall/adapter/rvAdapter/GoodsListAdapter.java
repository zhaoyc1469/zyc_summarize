package com.camera.shopping_mall.adapter.rvAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.camera.shopping_mall.R;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsVH> {

    @NonNull
    @Override
    public GoodsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_list, parent, false);
        return new GoodsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class GoodsVH extends RecyclerView.ViewHolder {

        public GoodsVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
