package com.mylibrary.moments.view.customs.commentwidget;


import androidx.annotation.NonNull;

/**
 * <p>
 * 评论控件点击
 */

public interface OnCommentUserClickListener {
    void onCommentClicked(@NonNull IComment comment, CharSequence text);
}
