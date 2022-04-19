package com.zhy.common.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * author : zhangyun.
 * date  : 2022/4/11  15:46.
 * description :自定义 dataBinding适配器
 **/
public class DataBindingUtils {

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
    }

    @BindingAdapter({"addNum"})
    public static void addNum(TextView view, int num) {
        view.setText(String.valueOf(num+100));
    }
    @BindingAdapter("android:paddingLeft")
    public static void setPaddingLeft(View view, int oldPadding, int newPadding) {
        if (oldPadding != newPadding) {
            view.setPadding(newPadding,
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBottom());
        }
    }




}
