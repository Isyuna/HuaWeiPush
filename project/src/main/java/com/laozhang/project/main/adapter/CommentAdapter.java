package com.laozhang.project.main.adapter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemCommentBinding;
import com.laozhang.project.main.model.CommentModel;
import com.laozhang.project.viewModel.CommentViewModel;
import com.zhy.common.base.SimpleDataBindingAdapter;

/**
 * author : zhangyun.
 * date  : 2022/7/27  14:42.
 * description :
 **/
public class CommentAdapter extends SimpleDataBindingAdapter<CommentModel, ItemCommentBinding> {


    public CommentAdapter(Context context) {
        super(context, R.layout.item_comment, DiffUtils.getInstance().getCommentModelItemCallback());
    }

    @Override
    protected void onBindItem(ItemCommentBinding binding, CommentModel item, RecyclerView.ViewHolder holder) {

    }
}
