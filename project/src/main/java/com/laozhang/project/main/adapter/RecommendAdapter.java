package com.laozhang.project.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/13  09:40.
 * description :
 **/
public class RecommendAdapter extends SimpleDataBindingAdapter<RecommendModel, ItemRecommendBinding> {
    public static final String TAG = RecommendAdapter.class.getSimpleName();
    OnChildItemClickListener onChildItemClickListener;
    public RecommendAdapter(Context context) {
        super(context, R.layout.item_recommend, DiffUtils.getInstance().getRecommendItemCallback());
    }

    public void setOnChildItemClickListener(OnChildItemClickListener onChildItemClickListener){
        this.onChildItemClickListener = onChildItemClickListener;
    }


    @Override
    protected void onBindItem(ItemRecommendBinding binding, RecommendModel item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
        binding.setPosition(holder.getPosition());
        binding.voidPlayer.setTag(TAG);
        if (item.joke.imageUrl != null && !"".equals(item.joke.imageUrl)) {
            List<String> imageUrl = new ArrayList<>();
            String[] images = item.joke.imageUrl.split(",");

            if (images.length > 1) {
                ImageAdapter imageAdapter = new ImageAdapter(holder.itemView.getContext());
                binding.imageView.setAdapter(imageAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3);
                gridLayoutManager.setInitialPrefetchItemCount(3);
                binding.imageView.setLayoutManager(gridLayoutManager);

                for (String i : images) {
                    imageUrl.add(i);
                }
                imageAdapter.submitList(imageUrl);
            } else {
                Glide.with(binding.onlyImage.getContext()).load(getUrl(item.joke.imageUrl)).into(binding.onlyImage);

            }

        }else{
            binding.cardView.setVisibility(View.GONE);
        }
        if(onChildItemClickListener != null){
            binding.clickComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildItemClickListener.onItemCommentClick(v,item,holder.getPosition());
                }
            });

            binding.clickShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildItemClickListener.onItemShareClick(v,item,holder.getPosition());
                }
            });
            binding.clickUnlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildItemClickListener.onItemUnLikeClick(v,item,holder.getPosition());
                }
            });
            binding.clickLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildItemClickListener.onItemLikeClick(v,item,holder.getPosition());
                }
            });
        }
    }

    public interface OnChildItemClickListener {
        void onItemLikeClick(View view, RecommendModel item, int position);
        void onItemUnLikeClick(View view, RecommendModel item, int position);
        void onItemCommentClick(View view, RecommendModel item, int position);
        void onItemShareClick(View view, RecommendModel item, int position);
    }


    private static String getUrl(String url) {
        String newUrl = url.replace("ftp://", "");
        return AesUtils.aesDecrypt(newUrl);
    }

}
