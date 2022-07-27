package com.laozhang.project.main.home;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.laozhang.project.BR;
import com.laozhang.project.R;
import com.laozhang.project.databinding.FragmentArticleBinding;
import com.laozhang.project.databinding.FragmentRecommendBinding;
import com.laozhang.project.main.adapter.ArticleAdapter;
import com.laozhang.project.main.adapter.RecommendAdapter;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.viewModel.ArticleViewModel;
import com.laozhang.project.viewModel.RecommendViewModel;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.utils.EndlessLinearOnScrollListener;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/15  11:38.
 * description :
 **/
public class ImageFragment extends BaseFragment {
    RecommendViewModel viewModel;
    RecommendAdapter mAdapter;
    FragmentRecommendBinding binding;

    @Override
    protected void initViewModel() {
        viewModel = initViewModelProvider(RecommendViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {

        mAdapter = new RecommendAdapter(getActivity());
        return new DataBindingConfig(R.layout.fragment_recommend, BR.vm, viewModel)
                .addBindingParam(BR.adapter, mAdapter);
    }

    @Override
    protected void initViews(View view) {
        mBinding.setLifecycleOwner(this);
        binding = (FragmentRecommendBinding) mBinding;
        initAnimation();
        refreshData();
        binding.reView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.reView.setLayoutManager(linearLayoutManager);

        viewModel.request.consumers.observe(getActivity(), recommendModels -> mAdapter.submitList(recommendModels));
        EndlessLinearOnScrollListener listener = new EndlessLinearOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadData();
            }

            @Override
            public void onScrollStateChanged(int firstVisibleItem, int lastVisibleItem, int totalItemCount) {
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecommendAdapter.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        if(GSYVideoManager.isFullState(getActivity())) {
                            return;
                        }
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos();
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        };
        binding.reView.addOnScrollListener(listener);
        binding.swLayout.setOnRefreshListener(this::refreshData);
    }



    private void initAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.inder_anim);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimationController.setDelay(0.2f);
        binding.reView.setLayoutAnimation(layoutAnimationController);
    }


    private void refreshData() {
        viewModel.refreshImageData().observe(getActivity(), listApiResponse -> {
            if(listApiResponse == null || listApiResponse.data == null){
                Snackbar.make(binding.swLayout, "暂无数据！", Snackbar.LENGTH_LONG).show();
                return;
            }
            viewModel.request.consumers.setValue(listApiResponse.data);
            binding.swLayout.setRefreshing(false);
        });
    }
    private void loadData(){
        viewModel.loadImageData().observe(getActivity(), listApiResponse -> {
            if(listApiResponse == null || listApiResponse.data == null){
                Snackbar.make(binding.swLayout, "暂无数据！", Snackbar.LENGTH_LONG).show();
                return;
            }
            List<RecommendModel> oldData = viewModel.request.consumers.getValue();
            oldData.addAll(listApiResponse.data);
            viewModel.request.consumers.setValue(oldData);
        });
    }

}
