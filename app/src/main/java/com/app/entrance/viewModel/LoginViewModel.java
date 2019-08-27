package com.app.entrance.viewModel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import android.view.View;

import com.app.entrance.contract.ILoginViewModel;
import com.app.entrance.model.LoginModel;
import com.main.view.activity.MainActivity;
import com.app.frame.base.BaseViewModel;
import com.app.frame.binding.command.BindingCommand;
import com.app.frame.contract.IViewModel;

public class LoginViewModel extends BaseViewModel<LoginModel> implements ILoginViewModel {

    @Override
    protected LoginModel initModel(IViewModel ViewModel) {
        return new LoginModel(this);
    }

    @Override
    protected void initData() {
        userName.set(mModel.getUserName());
        password.set(mModel.getPassword());
    }

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //密码开关观察者
    public ObservableBoolean pSwitchObservable = new ObservableBoolean(false);


    //清除用户名的点击事件
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(() -> userName.set(""));

    //密码显示开关
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(() -> pSwitchObservable.set(!pSwitchObservable.get()));

    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(hasFocus -> clearBtnVisibility.set(hasFocus?View.VISIBLE:View.INVISIBLE));

    //跳过按钮的点击事件
    public BindingCommand loginClickCommand = new BindingCommand(() -> mModel.login());

    @Override
    public void loginSuccess() {
        mModel.setUserName(userName.get());
        mModel.setPassword(password.get());
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void requestFail(String f) {

    }
}
