package com.camera.magiccamera.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.camera.magiccamera.R;

/**
 * author: gdw
 * date:2019/8/21
 * description:倾斜TextView
 */

public class LeanTextView extends AppCompatTextView {
    public static final int DEFAULT_DEGREE = -16;
    public static final float DEFAULT_SKEW_X = -0.26F;

    private int mDegrees;//倾斜度
    private float mSkewX;//错切X

    public LeanTextView(Context context) {
        super(context, null);
    }

    public LeanTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
        this.setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LeanTextView);
        mDegrees = a.getInt(R.styleable.LeanTextView_degree, DEFAULT_DEGREE);
        mSkewX = a.getFloat(R.styleable.LeanTextView_skewX, DEFAULT_SKEW_X);
        a.recycle();
        setIncludeFontPadding(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.skew(mSkewX, 0);
        canvas.translate(-mSkewX * getY(), 0);
        canvas.rotate(mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }


    public int getDegrees() {
        return mDegrees;
    }

    public void setDegrees(int mDegrees) {
        this.mDegrees = mDegrees;
        invalidate();
    }

    public float getSkewX() {
        return mSkewX;
    }

    public void setSkewX(float skewX) {
        this.mSkewX = skewX;
        invalidate();
    }
}