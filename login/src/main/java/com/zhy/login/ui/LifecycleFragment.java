package com.zhy.login.ui;

import android.view.View;

import androidx.lifecycle.Observer;

import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.utils.LocationService;
import com.zhy.common.utils.LocationServiceCallBack;
import com.zhy.common.utils.ToastUtil;
import com.zhy.login.BR;
import com.zhy.login.LoginViewModel;
import com.zhy.login.R;
import com.zhy.login.User;
import com.zhy.login.databinding.FragmentLifecycleBinding;
import com.zhy.login.databinding.FragmentLoginBinding;

/**
 * author : zhangyun.
 * date  : 2022/4/12  15:04.
 * description :
 **/
public class LifecycleFragment extends BaseFragment {
    private LoginViewModel mState;
    @Override
    protected void initViewModel() {
        mState = initViewModelProvider(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_lifecycle, BR.vm,  mState);
    }

    @Override
    protected void initViews(View view) {
       LocationService.getInstance().getLocation().observe(getViewLifecycleOwner(), s ->
               ToastUtil.showShort(getActivity(),s));
    }
}
