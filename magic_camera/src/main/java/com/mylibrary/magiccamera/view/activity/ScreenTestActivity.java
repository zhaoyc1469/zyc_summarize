package com.mylibrary.magiccamera.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.media.ImageReader;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.TextureView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.mylibrary.magiccamera.BR;
import com.mylibrary.magiccamera.R;
import com.mylibrary.magiccamera.databinding.ActivityScreenTestBinding;
import com.mylibrary.magiccamera.utils.Camera2Proxy;
import com.mylibrary.magiccamera.utils.ImageUtils;
import com.mylibrary.magiccamera.viewModel.ScreenTestViewModel;

import java.nio.ByteBuffer;

@Route(path = "/camera/ScreenTestActivity")
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ScreenTestActivity extends BaseActivity<ActivityScreenTestBinding, ScreenTestViewModel> {

    private static final String TAG = "TextureCameraActivity";
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
    protected void initData() {
        mCameraProxy = mDataBinding.c2Texture.getCameraProxy();
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getPhotoClickEvent().observe(this, v -> {
            mCameraProxy.setImageAvailableListener(mOnImageAvailableListener);
            mCameraProxy.captureStillPicture(); // 拍照
        });
    }

    private ImageReader.OnImageAvailableListener mOnImageAvailableListener = reader -> {
        new ImageSaveTask().execute(reader.acquireNextImage()); // 保存图片
    };

    private class ImageSaveTask extends AsyncTask<Image, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Image... images) {
            ByteBuffer buffer = images[0].getPlanes()[0].getBuffer();
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
            images[0].close();
            return ImageUtils.getLatestThumbBitmap();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mDataBinding.pictureIv.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}