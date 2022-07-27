package com.laozhang.project.main.video;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.laozhang.project.BR;
import com.laozhang.project.R;
import com.laozhang.project.databinding.FragmentVideoBinding;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.view.ZYVideoPlayer;
import com.laozhang.project.viewModel.RecommendViewModel;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.net.ApiResponse;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/18  10:42.
 * description :
 **/
public class VideoFragment extends BaseFragment {
    VideoViewModel viewModel;
    VideoAdapter videoAdapter;
    FragmentVideoBinding binding;
    ZYVideoPlayer videoPlayer;
    private String TAG = VideoFragment.class.getSimpleName();
    int page = 1;
    private boolean isVisible;

    @Override
    protected void initViewModel() {
        viewModel = initViewModelProvider(VideoViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_video, BR.vm, viewModel);
    }

    @Override
    protected void initViews(View view) {
        mBinding.setLifecycleOwner(this);
        binding = (FragmentVideoBinding) mBinding;
        videoAdapter = new VideoAdapter(getContext());
        binding.viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        binding.viewPager2.setAdapter(videoAdapter);
        getListVideo();
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //对应的播放列表TAG
                Log.d(TAG, "getPlayTag:" + GSYVideoManager.instance().getPlayTag());
                if(!isVisible){
                    return;
                }
                playPosition(position);
                if ((position + 1) == page * 10) {
                    loadMoreData();
                }
            }
        });
        videoAdapter.getVideoView(zyVideoPlayer -> videoPlayer = zyVideoPlayer);

        viewModel.videoData.observe(this, recommendModels -> {
            videoAdapter.submitList(recommendModels);
            videoAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadMoreData() {
        page++;
        viewModel.loadData(this);

    }

    private void getListVideo() {
        page = 1;
        viewModel.refreshData(this);

    }

    private void playPosition(int position) {

        RecyclerView.ViewHolder viewHolder = ((RecyclerView) binding.viewPager2.getChildAt(0)).findViewHolderForAdapterPosition(position);
        if(viewHolder != null){
            videoPlayer = viewHolder.itemView.findViewById(R.id.zy_video);
            if (videoPlayer != null) {
                videoPlayer.startPlayLogic();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
