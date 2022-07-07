package com.laozhang.project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.laozhang.project.main.home.FollowFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/6/14  18:10.
 * description :
 **/
public class HomeViewModel extends AndroidViewModel {


    public final MutableLiveData<List<Fragment>> fragments = new MutableLiveData<>();
    public final MutableLiveData<List<String>> mTittles = new MutableLiveData<>();



    public HomeViewModel(@NonNull Application application) {
        super(application);
//        initData();
    }

    private void initData() {
        List<Fragment> initFragment = new ArrayList<>();
        initFragment.add(new FollowFragment());
        initFragment.add(new FollowFragment());
        initFragment.add(new FollowFragment());
        initFragment.add(new FollowFragment());
        initFragment.add(new FollowFragment());
        fragments.setValue(initFragment);
        List<String> tittles = new ArrayList<>();

        tittles.add("关注");
        tittles.add("推荐");
        tittles.add("新鲜");
        tittles.add("纯文");
        tittles.add("趣图");
        mTittles.setValue(tittles);


    }


}
