package com.mylibrary.moments.viewModel;

import android.view.View;

import com.app.frame.binding.command.BindingCommand;
import com.mylibrary.moments.adapter.viewholder.BaseMItemVH;
import com.mylibrary.moments.bean.MomentItemBean;

public class MomentItemViewModel {

    private View itemView;
    private BaseMItemVH.VhMenuClickListener menuClickListener;
    private MomentItemBean momentItemBean;

    public MomentItemViewModel(View itemView) {
        this.itemView = itemView;
    }

    public void setMomentItemBean(MomentItemBean momentItemBean) {
        this.momentItemBean = momentItemBean;
    }

    public void setMenuClickListener(BaseMItemVH.VhMenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    //点击菜单
    public BindingCommand onMenuClick = new BindingCommand(() -> menuClickListener.onMenuClick(itemView, momentItemBean));
}
