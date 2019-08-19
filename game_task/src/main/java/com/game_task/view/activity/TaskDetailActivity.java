package com.game_task.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.frame.base.BaseActivity;
import com.app.frame.down.InstallUtils;
import com.app.frame.manager.AppManager;
import com.app.frame.utils.SimpleObserver;
import com.game_task.BR;
import com.game_task.R;
import com.game_task.databinding.ActivityTaskDetailBinding;
import com.game_task.viewModel.TaskDetailViewModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@Route(path = "/game_task/TaskDetailActivity")
public class TaskDetailActivity extends BaseActivity<ActivityTaskDetailBinding, TaskDetailViewModel> {

    // 安装路径
    private String apkDownloadPath;
    // 下载路径
    public static final String APK_URL = "http://download.fir.im/v2/app/install/56dd4bb7e75e2d27f2000046?download_token=e415c0fd1ac3b7abcb65ebc6603c59d9&source=update";
    // 下载保存路径
    public static final String APK_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() +
            AppManager.getAppManager().getApp().getPackageName() + "/DownAPK/" + "asdfdsaf" + ".apk";
    private InstallUtils.DownloadCallBack downloadCallBack;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_task_detail;
    }


    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initUI() {
        //先判断有没有安装权限
        //去安装APK
        //弹出弹框提醒用户
        //打开设置页面
        //去安装APK
        //内部做了处理，onLoading 进度转回progress必须是+1，防止频率过快
        downloadCallBack = new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "InstallUtils---onStart");
                mDataBinding.tvProgress.setText("0%");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(String path) {
                Log.i(TAG, "InstallUtils---onComplete:" + path);
                apkDownloadPath = path;
                mDataBinding.tvProgress.setText("100%");

                //先判断有没有安装权限
                InstallUtils.checkInstallPermission(getTheActivity(), new InstallUtils.InstallPermissionCallBack() {
                    @Override
                    public void onGranted() {
                        //去安装APK
                        installApk(apkDownloadPath);
                    }

                    @Override
                    public void onDenied() {
                        //弹出弹框提醒用户
                        AlertDialog alertDialog = new AlertDialog.Builder(getTheActivity())
                                .setTitle("温馨提示")
                                .setMessage("必须授权才能安装APK，请设置允许安装")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //打开设置页面
                                        InstallUtils.openInstallPermissionSetting(getTheActivity(), new InstallUtils.InstallPermissionCallBack() {
                                            @Override
                                            public void onGranted() {
                                                //去安装APK
                                                installApk(apkDownloadPath);
                                            }

                                            @Override
                                            public void onDenied() {
                                                Toast.makeText(getTheActivity(), "用户禁止安装！", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                })
                                .create();
                        alertDialog.show();
                    }
                });
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onLoading(long total, long current) {
                //内部做了处理，onLoading 进度转回progress必须是+1，防止频率过快
                Log.i(TAG, "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
                int progress = (int) (current * 100 / total);
                mDataBinding.tvProgress.setText(progress + "%");
            }

            @Override
            public void onFail(Exception e) {
                Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
            }

            @Override
            public void cancel() {
                Log.i(TAG, "InstallUtils---cancel");
            }
        };
    }

    @Override
    protected void initViewObservable() {
        mViewModel.getDownClickEvent().observe(this, v -> {
            RxPermissions rxPermissions = new RxPermissions(getTheActivity());
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new SimpleObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                InstallUtils.with(getTheActivity())
                                        //必须-下载地址
                                        .setApkUrl(APK_URL)
                                        //非必须-下载保存的文件的完整路径+name.apk
                                        .setApkPath(APK_SAVE_PATH)
                                        //非必须-下载回调
                                        .setCallBack(downloadCallBack)
                                        //开始下载
                                        .startDownload();
                            }
                        }
                    });
        });
    }

    private void installApk(String apkDownloadPath) {
        InstallUtils.installAPK(getTheActivity(), apkDownloadPath, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Toast.makeText(getTheActivity(), "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(getTheActivity(), "安装失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}