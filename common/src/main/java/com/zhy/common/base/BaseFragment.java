package com.zhy.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

/**
 * author : zhangyun.
 * date  : 2022/3/30  14:30.
 * description :
 **/
public abstract class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;
    ViewModelProvider viewModelProvider;
    protected ViewDataBinding mBinding;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (AppCompatActivity) context;
    }

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    protected <T extends ViewModel> T initViewModelProvider(@NonNull Class<T> classModel) {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(this);
        }
        return viewModelProvider.get(classModel);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getDataBindingConfig().getLayout(),
                container, false);
        mBinding.setLifecycleOwner(this.getViewLifecycleOwner());
        mBinding.setVariable(getDataBindingConfig().getVmVariableId(), getDataBindingConfig().getStateViewModel());
        for (int i = 0; i < getDataBindingConfig().getBindingParams().size(); i++) {
            mBinding.setVariable(getDataBindingConfig().getBindingParams().keyAt(i),
                    getDataBindingConfig().getBindingParams().valueAt(i));
        }
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initViews(view);
    }

    protected abstract void initViews(View view);

    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }


}
