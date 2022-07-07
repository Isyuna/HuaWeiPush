package com.laozhang.project.main.home;

import android.view.View;

import com.laozhang.project.BR;
import com.laozhang.project.R;
import com.laozhang.project.viewModel.FollowViewModel;
import com.laozhang.project.viewModel.HomeViewModel;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;

/**
 * author : zhangyun.
 * date  : 2022/7/1  10:46.
 * description :
 **/
public class FollowFragment extends BaseFragment {

    FollowViewModel followViewModel;
    @Override
    protected void initViewModel() {
        followViewModel = initViewModelProvider(FollowViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return  new DataBindingConfig(R.layout.fragment_follow, BR.vm, followViewModel);
    }

    @Override
    protected void initViews(View view) {
        mBinding.setLifecycleOwner(this);
    }
}
