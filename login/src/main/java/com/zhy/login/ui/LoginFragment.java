package com.zhy.login.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
/**
 * 开发缺少全面自测，像我写的问题基本上都是对应的需求写完了
 * 但是有的也会涉及到其他案件类型没有测试，然后会有产生问题的风险
 * 1、代码提交新增需求号
 * 2、开发需求时列出所涉及的模块，或者看看哪块引用到开发模块代码，要对涉及的地方进行测试。
 * 3、
 *
 *
 */
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.utils.ToastUtil;
import com.zhy.login.BR;
import com.zhy.login.LoginViewModel;
import com.zhy.login.R;
import com.zhy.login.User;
import com.zhy.login.databinding.FragmentLoginBinding;
import com.zhy.login.type.LoginByFaceCallBack;
import com.zhy.login.type.LoginCallBack;
import com.zhy.login.type.LoginFactory;
import com.zhy.login.type.LoginType;

import java.util.Objects;


public class LoginFragment extends BaseFragment {


    private LoginViewModel mState;
    private LoginFactory factory = new LoginFactory();
    FragmentLoginBinding loginBinding;
    @Override
    protected void initViewModel() {
        mState = initViewModelProvider(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_login, BR.vm, mState)
                .addBindingParam(BR.click, new LoginClick());
    }


    @Override
    protected void initViews(View view) {
         loginBinding = (FragmentLoginBinding) mBinding;
        mState.user.set(new User("ZY","18"));
        mBinding.setLifecycleOwner(this);

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                ToastUtil.showShort(getActivity(), newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mState.getCurrentName().observe(this, nameObserver);
    }

    /**
     *
     * 监听器绑定
     * 在方法引用中，方法的参数必须与事件监听器的参数匹配。在监听器绑定中，
     * 只有您的返回值必须与监听器的预期返回值相匹配（预期返回值无效除外）
     */
    public class LoginClick {
        public void loginByAccount() {
            mState.getCurrentName().setValue("你好");

            factory.createLogin(LoginType.ACCOUNT)
                    .login("account", "password",
                            new LoginCallBack() {
                                @Override
                                public void success(String data) {

                                }

                                @Override
                                public void error(String error) {

                                }
                            });
        }

        public void loginByFace() {
            factory.createLogin(LoginType.FACE)
                    .loginByFace("image", new LoginByFaceCallBack() {
                        @Override
                        public void success(String data) {

                        }

                        @Override
                        public void error(String error) {

                        }
                    });
        }

        public void toSetting() {
            nav().navigate(R.id.to_setting);
        }
        public void toText() {
            nav().navigate(R.id.action_LoginFragment_to_lifecycleFragment);
        }

    }


}