package com.zyc_summarize.book.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.zyc_summarize.book.contract.ILeafPageViewModel;
import com.zyc_summarize.book.model.LeafPageModel;

public class LeafPageViewModel extends BaseViewModel<LeafPageModel> implements ILeafPageViewModel {

    @Override
    protected LeafPageModel initModel(IViewModel ViewModel) {
        return new LeafPageModel(this);
    }


}
