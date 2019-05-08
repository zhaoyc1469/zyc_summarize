package com.mylibrary.shopping_mall.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.common.function.utils.UIHelper;
import com.mylibrary.shopping_mall.view.view.CommentBox;

public class FriendCircleHelp {
    private Activity mActivity;

    //评论时对应的参照View
    private View commentAnchorView;
    private int commentItemDataPosition;

    public FriendCircleHelp(Activity activity) {
        mActivity = activity;
    }

    int[] momentsViewLocation;
    int[] commentWidgetLocation;
    int[] commentBoxViewLocation;


    public View getCommentAnchorView() {
        return commentAnchorView;
    }

    public void setCommentAnchorView(View commentAnchorView) {
        this.commentAnchorView = commentAnchorView;
    }

    public int getCommentItemDataPosition() {
        return commentItemDataPosition;
    }

    public void setCommentItemDataPosition(int commentItemDataPosition) {
        this.commentItemDataPosition = commentItemDataPosition;
    }

    /**
     * 定位评论框到点击的view
     *
     * @param commentType
     * @return
     */
    public View alignCommentBoxToView(RecyclerView circleRecyclerView, CommentBox commentBox, int commentType) {
        if (circleRecyclerView == null || commentBox == null) return null;
        View anchorView = commentAnchorView;
        int scrollY = 0;
        switch (commentType) {
            case CommentBox.CommentType.TYPE_CREATE:
                if (anchorView instanceof TextView || anchorView  == null) {
//                    KLog.e("anchorView不符合当前的评论类型");
                    return null;
                }
                scrollY = calcuateMomentsViewOffset(commentBox, anchorView);
                break;
            case CommentBox.CommentType.TYPE_REPLY:
                if (!(anchorView instanceof TextView)) {
//                    KLog.e("anchorView不符合当前的评论类型");
                    return null;
                }
                TextView commentWidget = (TextView) anchorView;
                if (commentWidget == null) return null;
                scrollY = calcuateCommentWidgetOffset(commentBox, commentWidget);
                circleRecyclerView.smoothScrollBy(0, scrollY);
                break;
        }
        circleRecyclerView.smoothScrollBy(0, scrollY);
        return anchorView;

    }

    /**
     * 输入法消退时，定位到与底部相隔一个评论框的位置
     *
     * @param commentType
     * @param anchorView
     */
    public void alignCommentBoxToViewWhenDismiss(RecyclerView circleRecyclerView, CommentBox commentBox, int commentType, View anchorView) {
        if (anchorView == null) return;
        int decorViewHeight = mActivity.getWindow().getDecorView().getHeight();
        int alignScrollY;
        if (commentType == CommentBox.CommentType.TYPE_CREATE) {
            alignScrollY = decorViewHeight - anchorView.getBottom() - commentBox.getHeight();
        } else {
            Rect rect = new Rect();
            anchorView.getGlobalVisibleRect(rect);
            alignScrollY = decorViewHeight - rect.bottom - commentBox.getHeight();
        }
        circleRecyclerView.smoothScrollBy(0, -alignScrollY);
    }

    /**
     * 计算回复评论的偏移
     *
     * @param commentWidget
     * @return
     */
    private int calcuateCommentWidgetOffset(CommentBox commentBox, TextView commentWidget) {
        if (commentWidgetLocation == null) commentWidgetLocation = new int[2];
        if (commentWidget == null) return 0;
        commentWidget.getLocationInWindow(commentWidgetLocation);
        return commentWidgetLocation[1] + commentWidget.getHeight() - getCommentBoxViewTopInWindow(commentBox);
    }

    /**
     * 计算动态评论的偏移
     *
     * @param momentsView
     * @return
     */
    private int calcuateMomentsViewOffset(CommentBox commentBox, View momentsView) {
        if (momentsViewLocation == null) momentsViewLocation = new int[2];
        if (momentsView == null) return 0;
        //reset
        momentsViewLocation[0] = 0;
        momentsViewLocation[1] = 0;
        momentsView.getLocationInWindow(momentsViewLocation);
        if (momentsViewLocation[1] == 0) {
            //如果获取不到view在window里面的y位置，意味着该view没有完全显示出来
            //又因为此时是viewHolder的itemView，所以getTop和状态栏高度相当于momentsViewLocation[1]
            momentsViewLocation[1] = momentsView.getTop() + UIHelper.getStatusBarHeight(mActivity);
        }
        return momentsViewLocation[1] + momentsView.getHeight() - getCommentBoxViewTopInWindow(commentBox);
    }


    /**
     * 获取评论框的顶部（因为getTop不准确，因此采取 getLocationInWindow ）
     *
     * @return
     */
    private int getCommentBoxViewTopInWindow(CommentBox commentBox) {
        if (commentBoxViewLocation == null) commentBoxViewLocation = new int[2];
        if (commentBox == null) return 0;
        if (commentBoxViewLocation[1] != 0) return commentBoxViewLocation[1];
        commentBox.getLocationInWindow(commentBoxViewLocation);
        return commentBoxViewLocation[1];
    }

}
