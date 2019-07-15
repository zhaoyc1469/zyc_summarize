package com.mylibrary.moments.viewModel;

import android.view.View;

import com.app.frame.binding.command.BindingCommand;
import com.mylibrary.moments.adapter.viewholder.BaseMItemVH;
import com.mylibrary.moments.bean.TextBean;

public class MomentItemViewModel {

    private View itemView;
    private BaseMItemVH.VhMenuClickListener menuClickListener;
    private TextBean textBean;

    public MomentItemViewModel(View itemView) {
        this.itemView = itemView;
    }

    public void setTextBean(TextBean textBean) {
        this.textBean = textBean;
    }

    public void setMenuClickListener(BaseMItemVH.VhMenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    //点击菜单
    public BindingCommand onMenuClick = new BindingCommand(() -> menuClickListener.onMenuClick(itemView, textBean));
}
