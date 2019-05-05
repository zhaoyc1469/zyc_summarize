package com.common.function.rvBind;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class BindRvAdapterUtils {


    @BindingAdapter({"bindRvAdapter"})
    public static void smartRefreshLayoutBind(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
