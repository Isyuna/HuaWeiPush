package com.zhy.login;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * author : zhangyun.
 * date  : 2022/4/1  10:50.
 * description :
 * 可观察对象
 * 实现 Observable 接口的类允许注册监听器，以便它们接收有关可观察对象的属性更改的通知。
 * Observable 接口具有添加和移除监听器的机制，但何时发送通知必须由您决定。为便于开发，
 * 数据绑定库提供了用于实现监听器注册机制的 BaseObservable 类。实现 BaseObservable
 * 的数据类负责在属性更改时发出通知。具体操作过程是向 getter 分配 Bindable 注释，然后在 setter 中调用
 * notifyPropertyChanged() 方法，如以下示例所示：
 **/
public class User extends BaseObservable {
    private String account;
    private String password;

    @Bindable
    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);

    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
