package com.laozhang.project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.laozhang.project.main.model.CommentModel;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/27  14:43.
 * description :
 **/
public class CommentViewModel extends AndroidViewModel {

    public MutableLiveData<List<CommentModel>> data = new MutableLiveData<>();

    public CommentViewModel(@NonNull Application application) {
        super(application);
    }
}
