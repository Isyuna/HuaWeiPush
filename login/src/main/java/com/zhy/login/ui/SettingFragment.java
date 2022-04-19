package com.zhy.login.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.login.BR;
import com.zhy.login.LoginViewModel;
import com.zhy.login.R;

/**
 * author : zhangyun.
 * date  : 2022/3/30  14:14.
 * description :
 **/
public class SettingFragment extends BaseFragment {

    private LoginViewModel mState;
    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initViewModel() {
        mState = initViewModelProvider(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_login, BR.vm, mState);
    }



}
