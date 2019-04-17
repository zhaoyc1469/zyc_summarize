package com.mylibrary.magiccamera.view.activity;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.TextureView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.mylibrary.magiccamera.BR;
import com.mylibrary.magiccamera.R;
import com.mylibrary.magiccamera.databinding.ActivityScreenTestBinding;
import com.mylibrary.magiccamera.viewModel.ScreenTestViewModel;

@Route(path = "/camera/ScreenTestActivity")
public class ScreenTestActivity extends BaseActivity<ActivityScreenTestBinding, ScreenTestViewModel> {


    private CameraManager mCameraManager;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_screen_test;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIdList = mCameraManager.getCameraIdList();
            if (cameraIdList.length <= 0) {
//                mActivity.toast("没有可用相机")
                return;
            }
            for (String id : cameraIdList) {
                CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(id);
//                Integer integer = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void openCamera(){
//        mCameraManager.openCamera("0", null, null);
    }


    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };
}