package com.zyc_summarize.demo.viewModel;

import com.zyc_summarize.base.base.BaseViewModel;
import com.zyc_summarize.base.contract.IViewModel;
import com.zyc_summarize.demo.contract.IMainViewModel;
import com.zyc_summarize.demo.model.MainModel;

public class MainViewModel extends BaseViewModel<MainModel> implements IMainViewModel {

    @Override
    protected MainModel initModel(IViewModel ViewModel) {
        return new MainModel(this);
    }
}
