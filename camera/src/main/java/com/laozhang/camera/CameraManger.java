package com.laozhang.camera;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;
import com.laozhang.camera.ui.BaseCameraFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * author : zhangyun.
 * date  : 2022/6/9  11:22.
 * description : 相机管理类
 **/
public class CameraManger {


    private static final String TAG = CameraManger.class.getSimpleName();
   private static final  CameraManger cameraManger = new CameraManger();

    ImageCapture imageCapture;
    public static CameraManger getInstance(){
        return cameraManger;
    }

    public void startCameraConfig(Context context, PreviewView viewFinder){
        // 将Camera的生命周期和Activity绑定在一起（设定生命周期所有者），这样就不用手动控制相机的启动和关闭。
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(context);

        cameraProviderFuture.addListener(() -> {
            try {
                // 将你的相机和当前生命周期的所有者绑定所需的对象
                ProcessCameraProvider processCameraProvider = cameraProviderFuture.get();

                // 创建拍照所需的实例
                imageCapture = new ImageCapture.Builder().build();

                // 创建一个Preview 实例，并设置该实例的 surface 提供者（provider）。
                Preview preview = new Preview.Builder()
                        .build();
                preview.setSurfaceProvider(viewFinder.getSurfaceProvider());

                // 选择后置摄像头作为默认摄像头
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                // 重新绑定用例前先解绑
                processCameraProvider.unbindAll();

                // 绑定用例至相机
                processCameraProvider.bindToLifecycle((LifecycleOwner) context, cameraSelector, preview,imageCapture);

            } catch (Exception e) {
                Log.e(TAG, "用例绑定失败！" + e);
            }
        }, ContextCompat.getMainExecutor(context));
    }

    public void takePhoto(Context context){
        // 确保imageCapture 已经被实例化, 否则程序将可能崩溃
        if (imageCapture != null) {
            // 创建带时间戳的输出文件以保存图片，带时间戳是为了保证文件名唯一
            File photoFile = new File(getOutputDirectory(context),
                    new SimpleDateFormat(BaseCameraFragment.Configuration.FILENAME_FORMAT,
                            Locale.SIMPLIFIED_CHINESE).format(System.currentTimeMillis())
                            + ".jpg");

            // 创建 output option 对象，用以指定照片的输出方式
            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions
                    .Builder(photoFile)
                    .build();

            // 执行takePicture（拍照）方法
            imageCapture.takePicture(outputFileOptions,
                    ContextCompat.getMainExecutor(context),
                    new ImageCapture.OnImageSavedCallback() {// 保存照片时的回调
                        @Override
                        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                            Uri savedUri = Uri.fromFile(photoFile);
                            String msg = "照片捕获成功! " + savedUri;
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            Log.d(BaseCameraFragment.Configuration.TAG, msg);
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {
                            Log.e(BaseCameraFragment.Configuration.TAG, "Photo capture failed: " + exception.getMessage());
                        }
                    });
        }
    }
    private File getOutputDirectory(Context context) {
        File mediaDir = new File(context.getExternalMediaDirs()[0], context.getString(R.string.app_name));
        boolean isExist = mediaDir.exists() || mediaDir.mkdir();
        return isExist ? mediaDir : null;
    }
}
