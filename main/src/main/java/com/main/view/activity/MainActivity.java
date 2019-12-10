package com.main.view.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.main.BR;
import com.main.R;
import com.main.databinding.ActivityMainBinding;
import com.main.view.fragment.MsgFragment;
import com.main.view.fragment.MineFragment;
import com.main.viewModel.MainViewModel;
import com.app.frame.base.BaseActivity;
import com.app.frame.base.BaseFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    private BaseFragment currentFragment;
    private MineFragment mineFragment;
    private MsgFragment msgFragment;
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
                if (msgFragment == null) {

                    mDataBinding.tvMessage.setTextColor(getResources().getColor(R.color.colorTextPressed));
                    mDataBinding.tvMessage.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_message_pressed,0,0);

                    msgFragment = new MsgFragment();
                    currentFragment = msgFragment;
                    fragTrn.add(R.id.fl_content, msgFragment);
                } else {
                    currentFragment = msgFragment;
                    fragTrn.show(msgFragment);
                }
                fragTrn.commit();
                break;
            case 2:

                mDataBinding.tvTask.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mDataBinding.tvTask.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_task_pressed,0,0);

                break;
            case 3:

                mDataBinding.tvProject.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mDataBinding.tvProject.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_project_pressed,0,0);

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
            case 5:
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

}
