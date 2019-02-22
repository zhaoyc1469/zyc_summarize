package com.app.entrance.contract;

import com.app.frame.contract.IViewModel;

public interface IRegisterViewModel extends IViewModel {

    void sendSMS();

    void register();
}
