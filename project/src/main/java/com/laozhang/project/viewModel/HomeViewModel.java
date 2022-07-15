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




    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

}
