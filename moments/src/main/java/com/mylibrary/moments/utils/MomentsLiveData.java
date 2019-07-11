package com.mylibrary.moments.utils;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.app.frame.bus.event.SingleLiveEvent;
import com.mylibrary.moments.bean.MomentsViewBean;

public class MomentsLiveData extends SingleLiveEvent {

    private SingleLiveEvent<MomentsViewBean> showCommentEvent;

    public SingleLiveEvent<MomentsViewBean> getShowCommentEvent() {
        return showCommentEvent = createLiveData(showCommentEvent);
    }


    private SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent();
        }
        return liveData;
    }

    @Override
    public void observe(LifecycleOwner owner, Observer observer) {
        super.observe(owner, observer);
    }

}
