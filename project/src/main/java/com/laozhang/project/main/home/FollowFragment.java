package com.laozhang.project.main.home;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.laozhang.project.BR;
import com.laozhang.project.MainApi;
import com.laozhang.project.R;
import com.laozhang.project.databinding.FragmentFollowBinding;
import com.laozhang.project.main.adapter.DiffUtils;
import com.laozhang.project.main.adapter.FollowAdapter;
import com.laozhang.project.main.model.ConsumerModel;
import com.laozhang.project.viewModel.FollowViewModel;
import com.laozhang.project.viewModel.HomeViewModel;
import com.zhy.common.base.BaseDataBindingAdapter;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.RetrofitManager;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/1  10:46.
 * description :
 **/
public class FollowFragment extends BaseFragment {

    FollowViewModel followViewModel;
    private final String TAG = FollowFragment.class.getSimpleName();
    FragmentFollowBinding binding;
    private FollowAdapter mAdapter;

    @Override
    protected void initViewModel() {
        followViewModel = initViewModelProvider(FollowViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        mAdapter = new FollowAdapter(getContext());
        return new DataBindingConfig(R.layout.fragment_follow, BR.vm, followViewModel)
                .addBindingParam(BR.adapter, mAdapter);
    }

    @Override
    protected void initViews(View view) {
        mBinding.setLifecycleOwner(this);
        binding = (FragmentFollowBinding) mBinding;
        refreshData();
        initAnimation();
        binding.followRv.setAdapter(mAdapter);
        binding.followRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnItemClickListener((viewId, item, position) -> {

        });
        binding.animationView.pauseAnimation();
        binding.swipeRefresh.setOnRefreshListener(this::refreshData);
        binding.toRefresh.setOnClickListener(v -> refreshData());

    }

    private void initAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.inder_anim);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimationController.setDelay(0.2f);
        binding.toRefresh.setLayoutAnimation(layoutAnimationController);
    }
    private void refreshData() {
        binding.animationView.playAnimation();
        followViewModel.followRequest.getConsumerList().observe(this, listApiResponse -> {
            binding.animationView.pauseAnimation();
            if(listApiResponse == null || listApiResponse.data == null){
                Snackbar.make(binding.swipeRefresh, "暂无数据！", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (listApiResponse != null) {
                mAdapter.submitList(listApiResponse.data);
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }
}
