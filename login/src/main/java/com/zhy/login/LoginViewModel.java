package com.zhy.login;

import static androidx.fragment.app.FragmentManager.TAG;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.arch.core.util.Function;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.zhy.common.net.Api;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;
import com.zhy.login.type.LoginCallBack;
import com.zhy.login.type.LoginFactory;
import com.zhy.login.type.LoginType;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * author : zhangyun.
 * date  : 2022/3/31  15:44.
 * description :
 *
 * 使用androidx.lifecycle.Transformations这个工具类可以将持有一种类型的LiveData转换为另一种LiveData.
 * 他有类似于RxJava的使用方式.
 * 例子：
 * LiveData<Boolean> boolLiveData = getBoolLiveData();
 * LiveData<String> stringLiveData = Transformations.map(boolLiveData,bool->Boolean.toString(bool));
 **/
public class LoginViewModel extends AndroidViewModel {

    /**
     * 可观察字段
     * 在创建实现 Observable 接口的类时要完成一些操作，但如果您的类只有少数几个属性，这样操作的意义不大。
     * 在这种情况下，您可以使用通用 Observable 类和以下特定于基元的类，将字段设为可观察字段：
     */
    public final ObservableField<Integer> isLoading = new ObservableField<>();
    public final ObservableField<User> user = new ObservableField<>();
    private MutableLiveData<String> currentName;
    public static final String TAG = LoginViewModel.class.getSimpleName();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    LoginRequest loginRequest = new LoginRequest();

    private LoginFactory factory = new LoginFactory();

    public void loginByAccount(Activity context){
        isLoading.set(0);
        factory.createLogin(LoginType.ACCOUNT).login(user.get().getAccount(), user.get().getPassword())
        .observe((LifecycleOwner) context, new Observer<BingImg>() {
            @Override
            public void onChanged(BingImg img) {
                if(img != null){
                    Log.d(TAG,img.images.toString());
                }
            }
        });
    }


}
