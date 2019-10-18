package com.camera.magiccamera.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

public class QuadrangleShape extends Shape {
    protected Path path = new Path();
    protected Rect rect = new Rect();
    protected int offset;
    protected float scale = -1f;
    protected Context context;

    public QuadrangleShape(Context context) {
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (rect == null || rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        if (scale > 0.0f && scale < 1.0f) {
            offset = (int) (scale * rect.height());
            path.reset();
            path.moveTo(rect.left, offset);
            path.lineTo(rect.right, rect.top);
            path.lineTo(rect.right, rect.bottom - offset);
            path.lineTo(rect.left, rect.bottom);
        } else if (scale > -1.0f && scale < 0.0f) {
            offset = (int) (-scale * rect.height());
            path.reset();
            path.moveTo(rect.left, rect.top);
            path.lineTo(rect.right, offset);
            path.lineTo(rect.right, rect.bottom);
            path.lineTo(rect.left, rect.bottom - offset);
        } else {
            path.reset();
            path.moveTo(rect.left, rect.top);
            path.lineTo(rect.right, rect.top);
            path.lineTo(rect.right, rect.bottom);
            path.lineTo(rect.left, rect.bottom);
        }
        canvas.drawPath(path, paint);
    }

    public boolean checkIn(float x, float y) {
        if (scale > 0.0f && scale < 1.0f) {
            float offset = (scale * rect.height());
            if (y >= offset && y <= (rect.bottom - offset)) {
                return x >= rect.left && x <= rect.right;
            } else if (y < offset && (y / (rect.width() - x)) >= (offset / (float) rect.width())) {
                return x >= rect.left && x <= rect.right;
            } else if (y > (rect.bottom - offset) && (rect.height() - y) / x >= (offset / (float) rect.width())) {
                return x >= rect.left && x <= rect.right;
            }
        } else if (scale > -1.0f && scale < 0.0f) {
            float offset = (-scale * rect.height());
            if (y >= offset && y <= (rect.bottom - offset)) {
                return x >= rect.left && x <= rect.right;
            } else if (y < offset && (y / x) >= (offset / (float) rect.width())) {
                return x >= rect.left && x <= rect.right;
            } else if (y > (rect.bottom - offset) && (rect.height() - y) / (rect.width() - x) >= (offset / (float) rect.width())) {
                return x >= rect.left && x <= rect.right;
            }
        } else if (scale == 0) {
            return x <= rect.right && x >= rect.left && y <= rect.bottom && y >= rect.top;
        }
        return false;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Shape getShape() {
        return this;
    }
}
