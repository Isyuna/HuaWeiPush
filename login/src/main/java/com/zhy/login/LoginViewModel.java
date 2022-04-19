package com.zhy.login;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/**
 * author : zhangyun.
 * date  : 2022/3/31  15:44.
 * description :
 **/
public class LoginViewModel extends ViewModel {

    /**
     * 可观察字段
     * 在创建实现 Observable 接口的类时要完成一些操作，但如果您的类只有少数几个属性，这样操作的意义不大。
     * 在这种情况下，您可以使用通用 Observable 类和以下特定于基元的类，将字段设为可观察字段：
     */
    public final ObservableField<String> account = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> isLoading = new ObservableField<>();
    public final ObservableField<User> user = new ObservableField<>();
    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

}
