package com.camera.magiccamera.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

import com.camera.magiccamera.R;


public class ParallelogramImageView extends AppCompatImageView {
    public static final String TAG = "ParallelogramImageView";
    public static final float DEFAULT_SCALE = 0.13F;
    private ShapeDrawable mShapeDrawable;
    private QuadrangleShape mShape;
    private boolean mIsShape;
    private boolean mRebuildShape;
    private float mScale;

    public ParallelogramImageView(Context context) {
        this(context, null);
    }

    public ParallelogramImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallelogramImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParallelogramImageView);
        mScale = a.getFloat(R.styleable.ParallelogramImageView_scale, DEFAULT_SCALE);
        a.recycle();
        QuadrangleShape quadrangleShape = new QuadrangleShape(context);
        quadrangleShape.setScale(mScale);
        setShape(quadrangleShape);
    }

    //设置shape
    public void setShape(QuadrangleShape shape) {
        mShape = shape;
        mIsShape = true;
        mRebuildShape = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mShape == null) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mShape.checkIn(event.getX(), event.getY())) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mShape.checkIn(event.getX(), event.getY())) {
                    this.performClick();
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
        }
        return super.onTouchEvent(event);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (mIsShape) {
            //获取ImageView的drawable，当调用过setShape方法时，走下面的流程
            Drawable oldDrawable = getDrawable();
            if (oldDrawable == null || mShape == null) {
                return;
            }
            Rect bounds = oldDrawable.getBounds();
            if (bounds == null || bounds.width() == 0 || bounds.height() == 0) {
                return;
            }
            if (mShapeDrawable == null) {
                mShapeDrawable = new ShapeDrawable();
            }
            //设置shape drawable的bounds
            mShapeDrawable.setBounds(bounds);

            if (mRebuildShape) {
                mRebuildShape = false;
                //获取bitmap
                Bitmap bm = drawableToBitmap(oldDrawable);
                if (bm == null) {
                    return;
                }
                //创建一个bitmap shader，shape drawable设置这个位图渲染
                BitmapShader bitmapShader = new BitmapShader(bm,
                        TileMode.CLAMP, TileMode.CLAMP);
                mShapeDrawable.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                mShapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                mShapeDrawable.getPaint().setShader(bitmapShader);
                mShapeDrawable.setShape(mShape.getShape());
            }
            mShape.setRect(bounds);
            int paddingTop = getPaddingTop();
            int paddingLeft = getPaddingLeft();

            //将mShapeDrawable画在canvas
            if (paddingTop == 0 && paddingLeft == 0) {
                mShapeDrawable.draw(canvas);
            } else {
                int saveCount = canvas.getSaveCount();
                canvas.save();
                canvas.translate(paddingLeft, paddingTop);
                mShapeDrawable.draw(canvas);
                canvas.restoreToCount(saveCount);
            }
        } else {
            super.onDraw(canvas);
        }

    }

    //此方法用于创建一个bitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null || drawable.getBounds() == null) {
            return null;
        }
        Bitmap bitmap;
        int width = drawable.getBounds().width();
        int height = drawable.getBounds().height();
        try {
            bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.draw(canvas);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }
}
