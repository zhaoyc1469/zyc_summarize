package com.main.view.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.main.BR;
import com.main.R;
import com.main.databinding.ActivityMainBinding;
import com.main.view.fragment.HomeFragment;
import com.main.view.fragment.MineFragment;
import com.main.viewModel.MainViewModel;
import com.app.frame.base.BaseActivity;
import com.app.frame.base.BaseFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    private BaseFragment currentFragment;
    private MineFragment mineFragment;
    private HomeFragment homeFragment;
    private FragmentManager fragManager;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initUI() {
        fragManager = getSupportFragmentManager();
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getBottomClickEvent().observe(this, position -> {
            if (position == null) return;
            tabFragment(position);
        });
    }

    private void tabFragment(Integer position) {
        FragmentTransaction fragTrn = fragManager.beginTransaction();
        if (currentFragment != null) {
            fragTrn.hide(currentFragment);
        }
        switch (position) {
            case 1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    currentFragment = homeFragment;
                    fragTrn.add(R.id.fl_content, homeFragment);
                } else {
                    currentFragment = homeFragment;
                    fragTrn.show(homeFragment);
                }
                fragTrn.commit();
                break;
            case 2:
//                startActivity("/shop/GoodsListActivity");
                startActivity("/camera/PlayActivity");
                break;
            case 3:
                startActivity("/moments/MomentsMainActivity");
                break;
            case 4:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    currentFragment = mineFragment;
                    fragTrn.add(R.id.fl_content, mineFragment);
                } else {
                    currentFragment = mineFragment;
                    fragTrn.show(mineFragment);
                }
                fragTrn.commit();
                break;
        }
    }


//    private void wxpay() {
//        //实例化微信支付策略
//        String wxAppId = "";
//        WXPay wxPay = WXPay.getInstance(this, wxAppId);
//        //构造微信订单实体。一般都是由服务端直接返回。
//        WXPayInfoImpli wxPayInfoImpli = new WXPayInfoImpli();
//        wxPayInfoImpli.setTimestamp("");
//        wxPayInfoImpli.setSign("");
//        wxPayInfoImpli.setPrepayId("");
//        wxPayInfoImpli.setPartnerid("");
//        wxPayInfoImpli.setAppid("");
//        wxPayInfoImpli.setNonceStr("");
//        wxPayInfoImpli.setPackageValue("");
//        //策略场景类调起支付方法开始支付，以及接收回调。
//        EasyPay.pay(wxPay, this, wxPayInfoImpli, new IPayCallback() {
//            @Override
//            public void success() {
//            }
//
//            @Override
//            public void failed() {
//            }
//
//            @Override
//            public void cancel() {
//            }
//        });
//    }
//
//    private void alipay() {
//        //实例化支付宝支付策略
//        AliPay aliPay = new AliPay();
//        //构造支付宝订单实体。一般都是由服务端直接返回。
//        AlipayInfoImpli alipayInfoImpli = new AlipayInfoImpli();
//        alipayInfoImpli.setOrderInfo("");
//        //策略场景类调起支付方法开始支付，以及接收回调。
//        EasyPay.pay(aliPay, this, alipayInfoImpli, new IPayCallback() {
//            @Override
//            public void success() {
//            }
//
//            @Override
//            public void failed() {
//            }
//
//            @Override
//            public void cancel() {
//            }
//        });
//    }
}
