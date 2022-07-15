package com.laozhang.project.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemRecommendArticleBinding;
import com.laozhang.project.databinding.ItemRecommendBinding;
import com.laozhang.project.databinding.ItemRecommendImageBinding;
import com.laozhang.project.databinding.ItemRecommendVideoBinding;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.utils.AesUtils;
import com.zhy.common.base.SimpleDataBindingAdapter;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/13  09:40.
 * description :
 **/
public class RecommendAdapter extends SimpleDataBindingAdapter<RecommendModel, ItemRecommendBinding> {
    public static final String TAG = RecommendAdapter.class.getSimpleName();
    public RecommendAdapter(Context context) {
        super(context, R.layout.item_recommend, DiffUtils.getInstance().getRecommendItemCallback());
    }


    @Override
    protected void onBindItem(ItemRecommendBinding binding, RecommendModel item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
    }

}
