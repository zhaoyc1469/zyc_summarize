package com.main.viewModel;

import com.app.frame.base.BaseViewModel;
import com.app.frame.contract.IViewModel;
import com.main.contract.IMsgViewModel;
import com.main.model.MsgModel;

public class MsgFrgViewModel extends BaseViewModel<MsgModel> implements IMsgViewModel {

    @Override
    protected MsgModel initModel(IViewModel ViewModel) {
        return new MsgModel(this);
    }
}
