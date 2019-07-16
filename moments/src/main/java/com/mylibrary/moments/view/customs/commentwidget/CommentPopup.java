package com.mylibrary.moments.view.customs.commentwidget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.mylibrary.moments.R;
import com.mylibrary.moments.bean.LikesInfo;
import com.mylibrary.moments.bean.MomentItemBean;
import com.mylibrary.moments.utils.WeakHandler;

import razerdp.basepopup.BasePopupWindow;

/**
 * 朋友圈点赞
 */
public class CommentPopup extends BasePopupWindow implements View.OnClickListener {
    private static final String TAG = "CommentPopup";

    private ImageView mLikeView;
    private ImageView mLikeViewAnimate;
    private TextView mLikeText;

    private RelativeLayout mLikeClikcLayout;
    private RelativeLayout mCommentClickLayout;

    private MomentItemBean momentItemBean;

    private WeakHandler handler;
    private ScaleAnimation mScaleAnimation;

    private OnPopupClickListener mOnPopupClickListener;

    //是否已经点赞
    private boolean hasLiked;

    public CommentPopup(Context context) {
        super(context);
        handler = new WeakHandler();

        mLikeView = findViewById(R.id.iv_like);
        mLikeViewAnimate = findViewById(R.id.iv_like_blue);
        mLikeText = findViewById(R.id.tv_like);

        mLikeClikcLayout = findViewById(R.id.item_like);
        mCommentClickLayout = findViewById(R.id.item_comment);

        mLikeClikcLayout.setOnClickListener(this);
        mCommentClickLayout.setOnClickListener(this);

        buildAnima();
        setAllowInterceptTouchEvent(false);
        setAllowDismissWhenTouchOutside(true);
        setPopupFadeEnable(false);
        setBackground(0);
        setPopupGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        TranslateAnimation showAnima = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                1f,
                Animation.RELATIVE_TO_PARENT,
                0,
                Animation.RELATIVE_TO_PARENT,
                0,
                Animation.RELATIVE_TO_PARENT,
                0);
        showAnima.setInterpolator(new FastOutSlowInInterpolator());
        showAnima.setDuration(250);
        showAnima.setFillAfter(true);
        return showAnima;
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        TranslateAnimation showAnima = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0,
                Animation.RELATIVE_TO_PARENT,
                1f,
                Animation.RELATIVE_TO_PARENT,
                0,
                Animation.RELATIVE_TO_PARENT,
                0);
        showAnima.setInterpolator(new FastOutSlowInInterpolator());
        showAnima.setDuration(250);
        showAnima.setFillAfter(true);
        return showAnima;
    }

    private void buildAnima() {
        mScaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation.setDuration(250);
        mScaleAnimation.setInterpolator(new SpringInterPolator());
        mScaleAnimation.setFillAfter(false);

        mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mLikeViewAnimate.setAlpha(1f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLikeViewAnimate.setAlpha(0f);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 150);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_comment);
    }

    @Override
    public boolean onOutSideTouch() {
        dismiss(false);
        return super.onOutSideTouch();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.item_like) {
            if (mOnPopupClickListener != null) {
                mOnPopupClickListener.onLikeClick(v, momentItemBean, hasLiked);
                mLikeViewAnimate.clearAnimation();
                mLikeViewAnimate.startAnimation(mScaleAnimation);
            }
        } else if (i == R.id.item_comment) {
            if (mOnPopupClickListener != null) {
                mOnPopupClickListener.onCommentClick(v, momentItemBean);
                dismissWithOutAnimate();
            }
        }
    }
    //=============================================================Getter/Setter

    public OnPopupClickListener getOnCommentPopupClickListener() {
        return mOnPopupClickListener;
    }

    public void setOnCommentPopupClickListener(OnPopupClickListener onPopupClickListener) {
        mOnPopupClickListener = onPopupClickListener;
    }


    public void updateMomentItemBean(@NonNull MomentItemBean momentItemBean) {
        this.momentItemBean = momentItemBean;
        hasLiked = false;
//        if (!ToolUtil.isListEmpty(momentItemBean.getLikesList())) {
//            for (LikesInfo likesInfo : momentItemBean.getLikesList()) {
//                if (TextUtils.equals(likesInfo.getUserId(), LocalHostManager.INSTANCE.getUserid())) {
//                    hasLiked = true;
//                    break;
//                }
//            }
//        }
        mLikeText.setText(hasLiked ? "取消" : "赞");

    }

    //=============================================================InterFace
    public interface OnPopupClickListener {
        void onLikeClick(View v, @NonNull MomentItemBean momentItemBean, boolean hasLiked);

        void onCommentClick(View v, @NonNull MomentItemBean momentItemBean);
    }

    static class SpringInterPolator extends LinearInterpolator {

        public SpringInterPolator() {
        }


        @Override
        public float getInterpolation(float input) {
            return (float) Math.sin(input * Math.PI);
        }
    }
}
