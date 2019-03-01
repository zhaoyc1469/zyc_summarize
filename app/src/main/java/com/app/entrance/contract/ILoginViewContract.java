package com.app.entrance.contract;


import com.app.frame.contract.IView;
import com.app.frame.contract.IViewModel;

public class ILoginViewContract {

    public interface ILoginViewModel extends IViewModel {

        void loginSuccess();
    }

    public interface ILoginView extends IView {

    }
}
