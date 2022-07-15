package com.laozhang.project.main.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemRecommendArticleBinding;
import com.laozhang.project.main.model.RecommendModel;
import com.zhy.common.base.SimpleDataBindingAdapter;

/**
 * author : zhangyun.
 * date  : 2022/7/14  10:58.
 * description :
 **/
public class ArticleAdapter extends SimpleDataBindingAdapter<RecommendModel, ItemRecommendArticleBinding> {


    public ArticleAdapter(Context context) {
        super(context, R.layout.item_recommend_article, DiffUtils.getInstance().getRecommendItemCallback());
    }

    @Override
    protected void onBindItem(ItemRecommendArticleBinding binding, RecommendModel item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
        binding.animationView.pauseAnimation();
        binding.animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.animationView.playAnimation();
            }
        });
    }
}