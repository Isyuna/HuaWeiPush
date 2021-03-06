package com.laozhang.project.main.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.laozhang.project.BR;
import com.laozhang.project.R;
import com.laozhang.project.databinding.FragmentRecommendBinding;
import com.laozhang.project.main.adapter.RecommendAdapter;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.viewModel.RecommendViewModel;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.utils.EndlessLinearOnScrollListener;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/12  15:21.
 * description :
 **/
public class RecommendFragment extends BaseFragment {

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
                //??????0???????????????
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //?????????????????????
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //?????????????????????TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecommendAdapter.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        if (GSYVideoManager.isFullState(getActivity())) {
                            return;
                        }
                        //??????????????????????????????????????????????????????????????????
                        GSYVideoManager.releaseAllVideos();
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        };
        binding.reView.addOnScrollListener(listener);
        binding.swLayout.setOnRefreshListener(this::refreshData);

        initOnclick();
    }

    /**
     * ??????????????????
     */
    private void initOnclick() {
        mAdapter.setOnChildItemClickListener(new RecommendAdapter.OnChildItemClickListener() {
            @Override
            public void onItemLikeClick(View view, RecommendModel item, int position) {
                Snackbar.make(binding.swLayout, "?????????", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemUnLikeClick(View view, RecommendModel item, int position) {
                Snackbar.make(binding.swLayout, "????????????", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemCommentClick(View view, RecommendModel item, int position) {
                Snackbar.make(binding.swLayout, "?????????", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemShareClick(View view, RecommendModel item, int position) {
                Snackbar.make(binding.swLayout, "?????????", Snackbar.LENGTH_SHORT).show();
                CommentFragment.getInstance().show(getFragmentManager(),"dialog");
            }
        });
    }


    private void refreshData() {
        viewModel.refreshData().observe(getActivity(), listApiResponse -> {
            if (listApiResponse == null || listApiResponse.data == null) {
                Snackbar.make(binding.swLayout, "???????????????", Snackbar.LENGTH_SHORT).show();
                return;
            }
            viewModel.request.consumers.setValue(listApiResponse.data);
            binding.swLayout.setRefreshing(false);
        });
    }

    private void loadData() {
        viewModel.loadData().observe(getActivity(), listApiResponse -> {
            if (listApiResponse == null || listApiResponse.data == null) {
                Snackbar.make(binding.swLayout, "???????????????", Snackbar.LENGTH_SHORT).show();
                return;
            }
            List<RecommendModel> oldData = viewModel.request.consumers.getValue();
            oldData.addAll(listApiResponse.data);
            viewModel.request.consumers.setValue(oldData);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
