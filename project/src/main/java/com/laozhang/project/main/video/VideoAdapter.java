package com.laozhang.project.main.video;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemVideoBinding;
import com.laozhang.project.main.adapter.DiffUtils;
import com.laozhang.project.main.adapter.RecommendAdapter;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.view.ZYVideoPlayer;
import com.zhy.common.base.SimpleDataBindingAdapter;

/**
 * author : zhangyun.
 * date  : 2022/7/18  11:02.
 * description :
 **/
public class VideoAdapter extends SimpleDataBindingAdapter<RecommendModel, ItemVideoBinding> {
    public static final String TAG = RecommendAdapter.class.getSimpleName();

    GetVideoView getVideoView;
    public VideoAdapter(Context context) {
        super(context, R.layout.item_video, DiffUtils.getInstance().getRecommendItemCallback());
    }

    @Override
    protected void onBindItem(ItemVideoBinding binding, RecommendModel item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
        binding.setPosition(holder.getPosition());
        binding.zyVideo.setTag(TAG);
        if(getVideoView != null){
            getVideoView.getVideoView(binding.zyVideo);
        }
    }
    public void getVideoView(GetVideoView getVideoView){
        this.getVideoView = getVideoView;
    }

    public interface GetVideoView{
        void getVideoView(ZYVideoPlayer zyVideoPlayer);
    }
}
