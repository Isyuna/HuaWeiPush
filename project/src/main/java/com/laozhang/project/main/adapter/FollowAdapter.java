package com.laozhang.project.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemFollowBinding;
import com.laozhang.project.databinding.ItemRecommendBinding;
import com.laozhang.project.main.model.ConsumerModel;
import com.zhy.common.base.SimpleDataBindingAdapter;

/**
 * author : zhangyun.
 * date  : 2022/7/11  15:52.
 * description :
 **/
public class FollowAdapter extends SimpleDataBindingAdapter<ConsumerModel, ItemFollowBinding>{

    public FollowAdapter(Context context) {
        super(context, R.layout.item_follow, DiffUtils.getInstance().getConsumerItemCallback());
    }

    @Override
    protected void onBindItem(ItemFollowBinding binding, ConsumerModel item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
