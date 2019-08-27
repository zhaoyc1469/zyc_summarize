package com.camera.magiccamera.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.camera.magiccamera.BR;
import com.camera.magiccamera.R;
import com.camera.magiccamera.databinding.ActivityScreenTestBinding;
import com.camera.magiccamera.utils.Camera2Proxy;
import com.camera.magiccamera.utils.ImageUtils;
import com.camera.magiccamera.utils.NativeTest;
import com.camera.magiccamera.viewModel.ScreenTestViewModel;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.nio.ByteBuffer;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@Route(path = "/camera/ScreenTestActivity")
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ScreenTestActivity extends BaseActivity<ActivityScreenTestBinding, ScreenTestViewModel> {

    private static final String TAG = "ScreenTestActivity";
    private Camera2Proxy mCameraProxy;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_screen_test;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initUI() {
        mCameraProxy = mDataBinding.c2Texture.getCameraProxy();

        NativeTest nativeTest = new NativeTest();
        String s = nativeTest.stringFromJNI();
        Toast.makeText(this, "" + s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getPhotoClickEvent().observe(this, v -> {
            mCameraProxy.setImageAvailableListener(mOnImageAvailableListener);
            mCameraProxy.captureStillPicture(); // 拍照
        });
    }

    @SuppressLint("CheckResult")
    private ImageReader.OnImageAvailableListener mOnImageAvailableListener = reader -> {

//        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Flowable.just(reader.acquireNextImage())
                .map(this::Image2Bitmap)
                .onBackpressureDrop()
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在主线程运行
                .subscribe(bitmap -> mDataBinding.pictureIv.setImageBitmap(bitmap),
                        throwable -> Toast.makeText(this, "保存失败", Toast.LENGTH_LONG).show());
    };


    private Bitmap Image2Bitmap(Image image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        long time = System.currentTimeMillis();
        if (mCameraProxy.isFrontCamera()) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Log.d(TAG, "BitmapFactory.decodeByteArray time: " + (System.currentTimeMillis() - time));
            time = System.currentTimeMillis();
            // 前置摄像头需要左右镜像
            Bitmap rotateBitmap = ImageUtils.rotateBitmap(bitmap, 0, true, true);
            Log.d(TAG, "rotateBitmap time: " + (System.currentTimeMillis() - time));
            time = System.currentTimeMillis();
            ImageUtils.saveBitmap(rotateBitmap);
            Log.d(TAG, "saveBitmap time: " + (System.currentTimeMillis() - time));
            rotateBitmap.recycle();
        } else {
            ImageUtils.saveImage(bytes);
            Log.d(TAG, "saveBitmap time: " + (System.currentTimeMillis() - time));
        }
        image.close();
        return ImageUtils.getLatestThumbBitmap();
    }

}