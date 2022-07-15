package com.zhy.common.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.zhy.common.view.XCRoundImageView;

/**
 * author : zhangyun.
 * date  : 2022/7/11  17:31.
 * description : 用于自定义 adapter
 **/
public class CommonBindingAdapter {
    @BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
    public static void imageUrl(XCRoundImageView view, String url, Drawable placeHolder) {
        Glide.with(view.getContext()).load(url).placeholder(placeHolder).into(view);
    }
    @BindingAdapter(value = { "jokesText"}, requireAll = false)
    public static void jokesTextView(TextView view,String jokesText) {
//        String text = (String) view.getText();
        view.setText("发表 "+jokesText);
    }
    @BindingAdapter(value = { "fansText"}, requireAll = false)
    public static void fansTextView(TextView view,String fansText) {
        view.setText("粉丝 "+fansText);
    }
}
