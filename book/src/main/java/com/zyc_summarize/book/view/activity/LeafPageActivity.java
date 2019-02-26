package com.zyc_summarize.book.view.activity;

import android.os.Bundle;

import com.app.frame.base.BaseActivity;
import com.zyc_summarize.book.BR;
import com.zyc_summarize.book.R;
import com.zyc_summarize.book.databinding.ActivityLeafPageBinding;
import com.zyc_summarize.book.viewModel.LeafPageViewModel;


public class LeafPageActivity extends BaseActivity<ActivityLeafPageBinding, LeafPageViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_leaf_page;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
