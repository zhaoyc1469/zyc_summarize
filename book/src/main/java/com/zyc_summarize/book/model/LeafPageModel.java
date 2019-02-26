package com.zyc_summarize.book.model;

import com.app.frame.base.BaseModel;
import com.zyc_summarize.book.contract.ILeafPageViewModel;


public class LeafPageModel extends BaseModel {

    private ILeafPageViewModel viewModel;

    public LeafPageModel(ILeafPageViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }
}
