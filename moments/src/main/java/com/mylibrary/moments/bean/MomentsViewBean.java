package com.mylibrary.moments.bean;

import android.view.View;

import com.mylibrary.moments.view.customs.commentwidget.CommentWidget;

public class MomentsViewBean {

    private View viewHolderRootView;
    private int itemPos;
    private String momentid;
    private CommentWidget commentWidget;


    public View getViewHolderRootView() {
        return viewHolderRootView;
    }

    public void setViewHolderRootView(View viewHolderRootView) {
        this.viewHolderRootView = viewHolderRootView;
    }

    public int getItemPos() {
        return itemPos;
    }

    public void setItemPos(int itemPos) {
        this.itemPos = itemPos;
    }

    public String getMomentid() {
        return momentid;
    }

    public void setMomentid(String momentid) {
        this.momentid = momentid;
    }

    public CommentWidget getCommentWidget() {
        return commentWidget;
    }

    public void setCommentWidget(CommentWidget commentWidget) {
        this.commentWidget = commentWidget;
    }
}
