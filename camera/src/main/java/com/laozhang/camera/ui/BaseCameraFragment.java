package com.laozhang.camera.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;
import android.util.Rational;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.laozhang.camera.BR;
import com.laozhang.camera.CameraManger;
import com.laozhang.camera.R;
import com.laozhang.camera.databinding.FragmentCameraBinding;
import com.laozhang.camera.model.CameraViewModel;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * author : zhangyun.
 * date  : 2022/6/9  14:10.
 * description :
 **/
public class BaseCameraFragment extends BaseFragment {

    private CameraViewModel cameraViewModel;
    FragmentCameraBinding fragmentCameraBinding;
    PreviewView viewFinder;
    @Override
    protected void initViewModel() {
        cameraViewModel = initViewModelProvider(CameraViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_camera, BR.vm, cameraViewModel)
                .addBindingParam(BR.click, new OnClick());
    }

    @Override
    protected void initViews(View view) {
        fragmentCameraBinding = (FragmentCameraBinding) mBinding;
        mBinding.setLifecycleOwner(this);
        viewFinder = view.findViewById(R.id.viewFinder);
        // 请求相机权限
        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(getActivity(), Configuration.REQUIRED_PERMISSIONS,
                    Configuration.REQUEST_CODE_PERMISSIONS);
        }
    }
    private void startCamera() {
        CameraManger.getInstance().startCameraConfig(getActivity(), viewFinder);
    }

    public class OnClick {

        public void takePhoto() {
            CameraManger.getInstance().takePhoto(getActivity());
        }
    }




    private boolean allPermissionsGranted() {
        for (String permission : Configuration.REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Configuration.REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {// 申请权限通过
                startCamera();
            } else {// 申请权限失败
                Toast.makeText(getActivity(), "用户拒绝授予权限！", Toast.LENGTH_LONG).show();
            }
        }
    }


   public static class Configuration {
        public static final String TAG = "CameraxBasic";
        public static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
        public static final int REQUEST_CODE_PERMISSIONS = 10;
        public static final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.CAMERA};
    }


}
