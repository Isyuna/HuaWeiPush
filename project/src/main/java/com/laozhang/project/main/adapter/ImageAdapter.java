package com.laozhang.project.main.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemRecommendBinding;
import com.laozhang.project.databinding.ItemRecommendImageBinding;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.utils.AesUtils;
import com.zhy.common.base.SimpleDataBindingAdapter;

/**
 * author : zhangyun.
 * date  : 2022/7/15  15:09.
 * description :
 **/
public class ImageAdapter extends SimpleDataBindingAdapter<String, ItemRecommendImageBinding> {
    public ImageAdapter(Context context ) {
        super(context, R.layout.item_recommend_image, DiffUtils.getInstance().getStringItemCallback());
    }

    @Override
    protected void onBindItem(ItemRecommendImageBinding binding, String item, RecyclerView.ViewHolder holder) {
        Glide.with(binding.imageView.getContext()).load(getUrl(item)).into(binding.imageView);

    }
    private static String getUrl(String url) {
        String newUrl = url.replace("ftp://", "");
        return AesUtils.aesDecrypt(newUrl);
    }
}
