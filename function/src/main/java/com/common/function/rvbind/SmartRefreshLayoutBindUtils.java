package com.common.function.rvbind;

import androidx.databinding.BindingAdapter;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class SmartRefreshLayoutBindUtils {

    @BindingAdapter({"smartRefreshLoadMore"})
    public static void smartRefreshLayoutBind(SmartRefreshLayout smartRefreshLayout, OnRefreshLoadMoreListener refreshLoadMore) {
        smartRefreshLayout.setOnRefreshLoadMoreListener(refreshLoadMore);
    }

    @BindingAdapter({"smartRefresh"})
    public static void smartRefreshLayoutBind(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener) {
        smartRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    @BindingAdapter({"smartLoadMore"})
    public static void smartRefreshLayoutBind(SmartRefreshLayout smartRefreshLayout, OnLoadMoreListener onLoadMoreListener) {
        smartRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);
    }
}
