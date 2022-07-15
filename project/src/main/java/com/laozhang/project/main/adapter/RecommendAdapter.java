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
public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = RecommendAdapter.class.getSimpleName();

    //文章
    private final int ITEM_ARTICLE = 1;
    //视频
    private final int ITEM_VIDEO = 2;
    //图片
    private final int ITEM_IMAGE = 3;

    private AsyncListDiffer<RecommendModel> mDiffer;
    private Context context;
    ItemRecommendArticleBinding itemRecommendArticleBinding;
    ItemRecommendImageBinding itemRecommendImageBinding;
    ItemRecommendVideoBinding itemRecommendVideoBinding;

    private DiffUtil.ItemCallback<RecommendModel> diffCallback = new DiffUtil.ItemCallback<RecommendModel>() {
        @Override
        public boolean areItemsTheSame(RecommendModel oldItem, RecommendModel newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(RecommendModel oldItem, RecommendModel newItem) {
            return oldItem.joke.jokesId == newItem.joke.jokesId;
        }
    };

    public RecommendAdapter(Context context) {
        this.context = context;
        mDiffer = new AsyncListDiffer<>(this, diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_ARTICLE) {
            itemRecommendArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_recommend_article, parent, false);
            holder = new ViewHolderArticle(itemRecommendArticleBinding);
        } else if (viewType == ITEM_VIDEO) {
            itemRecommendVideoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_recommend_video, parent, false);
            holder = new ViewHolderVideo(itemRecommendVideoBinding);
        } else if (viewType == ITEM_IMAGE) {
            itemRecommendImageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_recommend_image, parent, false);
            holder = new ViewHolderImage(itemRecommendImageBinding);

        }
        assert holder != null;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mDiffer.getCurrentList().get(position).joke.type == 1) {
            itemRecommendArticleBinding.setItem(mDiffer.getCurrentList().get(position));

        } else if (mDiffer.getCurrentList().get(position).joke.type == 2) {
            itemRecommendImageBinding.setItem(mDiffer.getCurrentList().get(position));

        } else if (mDiffer.getCurrentList().get(position).joke.type >= 3) {

            itemRecommendVideoBinding.setItem(mDiffer.getCurrentList().get(position));

            initVideoPlayer(itemRecommendVideoBinding, position);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if (mDiffer.getCurrentList().get(position).joke.type == 1) {
            return ITEM_ARTICLE;
        } else if (mDiffer.getCurrentList().get(position).joke.type == 2) {
            return ITEM_IMAGE;
        } else if (mDiffer.getCurrentList().get(position).joke.type >= 3) {
            return ITEM_VIDEO;
        }
        return super.getItemViewType(position);
    }
    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public void submitList(List<RecommendModel> data) {
        mDiffer.submitList(data);
    }

    public RecommendModel getItem(int position) {
        return mDiffer.getCurrentList().get(position);
    }


    private static class ViewHolderArticle extends RecyclerView.ViewHolder {
        public ViewHolderArticle(ItemRecommendArticleBinding mBinding) {
            super(mBinding.getRoot());
        }
    }

    private static class ViewHolderVideo extends RecyclerView.ViewHolder {
        public ViewHolderVideo(ItemRecommendVideoBinding mBinding) {
            super(mBinding.getRoot());
        }
    }

    private static class ViewHolderImage extends RecyclerView.ViewHolder {
        public ViewHolderImage(ItemRecommendImageBinding mBinding) {
            super(mBinding.getRoot());
        }
    }




    /**
     * 处理视频播放
     *
     * @param mBinding
     */
    private void initVideoPlayer(ItemRecommendVideoBinding mBinding, int position) {
        if (getItem(position).joke.videoUrl == null || "".equals(getItem(position).joke.videoUrl)) {
            mBinding.voidPlayer.setVisibility(View.GONE);
            return;
        } else {
            mBinding.voidPlayer.setVisibility(View.VISIBLE);

        }
        String url = getUrl(getItem(position).joke.videoUrl);
        String imageUrl = getUrl(getItem(position).joke.thumbUrl);
        mBinding.voidPlayer.loadCoverImage(imageUrl, R.mipmap.like);
        mBinding.voidPlayer.setUpLazy(url, true, null, null, "这是title");
        //增加title
        mBinding.voidPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        mBinding.voidPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        mBinding.voidPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.voidPlayer.startWindowFullscreen(context, false, true);
            }
        });
        //防止错位设置
        mBinding.voidPlayer.setPlayTag(TAG);
        mBinding.voidPlayer.setPlayPosition(position);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        mBinding.voidPlayer.setAutoFullWithSize(true);
        //音频焦点冲突时是否释放
        mBinding.voidPlayer.setReleaseWhenLossAudio(false);
        //全屏动画
        mBinding.voidPlayer.setShowFullAnimation(true);
        //小屏时不触摸滑动
        mBinding.voidPlayer.setIsTouchWiget(false);

    }

    private String getUrl(String url) {
        String newUrl = url.replace("ftp://", "");
        return AesUtils.aesDecrypt(newUrl);
    }
}
