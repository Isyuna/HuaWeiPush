package com.zhy.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/11  15:55.
 * description :
 **/
public abstract class BaseDataBindingAdapter<M, B extends ViewDataBinding> extends ListAdapter<M, ViewHolder> {
    protected Context mContext;
    protected BaseDataBindingAdapter.OnItemClickListener<M> mOnItemClickListener;
    protected BaseDataBindingAdapter.OnItemLongClickListener<M> mOnItemLongClickListener;

    public void setOnItemClickListener(BaseDataBindingAdapter.OnItemClickListener<M> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(BaseDataBindingAdapter.OnItemLongClickListener<M> onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public BaseDataBindingAdapter(Context context, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.mContext = context;
    }

    public void submitList(@Nullable List<M> list) {
        super.submitList(list, () -> {
            super.submitList(list == null ? new ArrayList() : new ArrayList(list));
        });
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), this.getLayoutResId(viewType), parent, false);
        BaseDataBindingAdapter.BaseBindingViewHolder holder = new BaseDataBindingAdapter.BaseBindingViewHolder(binding.getRoot());
        holder.itemView.setOnClickListener((v) -> {
            if (this.mOnItemClickListener != null) {
                int position = holder.getBindingAdapterPosition();
                this.mOnItemClickListener.onItemClick(holder.itemView.getId(), this.getItem(position), position);
            }

        });
        holder.itemView.setOnLongClickListener((v) -> {
            if (this.mOnItemLongClickListener != null) {
                int position = holder.getBindingAdapterPosition();
                this.mOnItemLongClickListener.onItemLongClick(holder.itemView.getId(), this.getItem(position), position);
                return true;
            } else {
                return false;
            }
        });
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, this.getItem(position), holder);
        if (binding != null) {
            binding.executePendingBindings();
        }

    }

    @LayoutRes
    protected abstract int getLayoutResId(int viewType);

    protected abstract void onBindItem(B binding, M item, ViewHolder holder);

    public interface OnItemLongClickListener<M> {
        void onItemLongClick(int viewId, M item, int position);
    }

    public interface OnItemClickListener<M> {
        void onItemClick(int viewId, M item, int position);
    }

    public static class BaseBindingViewHolder extends ViewHolder {
        BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
