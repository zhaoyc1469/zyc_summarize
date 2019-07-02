package com.mylibrary.moments.view.customs.commentwidget;

/**
 *
 */

public interface IComment<T> {

    /**评论创建者*/
    String getCommentCreatorName();
    /**评论回复人*/
    String getReplyerName();
    /**评论内容*/
    String getCommentContent();

    T getData();

}
