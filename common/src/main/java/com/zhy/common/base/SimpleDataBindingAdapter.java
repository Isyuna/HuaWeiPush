package com.zhy.common.base;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

/**
 * author : zhangyun.
 * date  : 2022/7/11  16:00.
 * description :
 **/
public abstract class SimpleDataBindingAdapter<M, B extends ViewDataBinding> extends BaseDataBindingAdapter<M, B> {
    private final int layout;

    public SimpleDataBindingAdapter(Context context, int layout, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(context, diffCallback);
        this.layout = layout;
    }

    @LayoutRes
    protected int getLayoutResId(int viewType) {
        return this.layout;
    }
}

