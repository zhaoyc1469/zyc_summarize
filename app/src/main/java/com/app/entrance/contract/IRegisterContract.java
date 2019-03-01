package com.app.entrance.contract;

import com.app.frame.contract.IView;
import com.app.frame.contract.IViewModel;

public class IRegisterContract {
    public interface IRegisterViewModel extends IViewModel {

        void sendSMS();

        void register();
    }

    public interface IRegisterView extends IView {

    }
}
