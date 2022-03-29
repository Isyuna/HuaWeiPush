package com.zhy.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;


public class ToastUtil {
    private static boolean isShow = true;//默认显示  
    private static Toast mToast = null;//全局唯一的Toast
    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast toast = null;

    private static Object synObj = new Object();
    /*private控制不应该被实例化*/
    private ToastUtil() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    /**
     * 全局控制是否显示Toast
     *
     * @param isShowToast
     */
    public static void controlShow(boolean isShowToast) {
        isShow = isShowToast;
    }

    /**
     * 取消Toast显示
     */
    public void cancelToast() {
        if (isShow && mToast != null) {
            mToast.cancel();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                mToast.setText(message);
            }
            mToast.show();
        }
    }
    public static void setToatBytTime(Context c, String info, int time) {
       final Toast toast = Toast.makeText(c, info, Toast.LENGTH_SHORT);
        toast.show();
//        showShort(c,info);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                toast.cancel();
            }
        }, time);
    }


}