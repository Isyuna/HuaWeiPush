package com.laozhang.project.main.video;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.request.RecommendRequest;
import com.zhy.common.net.ApiResponse;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/18  10:48.
 * description :
 **/
public class VideoViewModel extends AndroidViewModel {

    RecommendRequest request = new RecommendRequest();
    MutableLiveData<List<RecommendModel>> videoData = new MutableLiveData<>();
    public final static String TAG = "VideoViewModel";


    public VideoViewModel(@NonNull Application application) {
        super(application);
    }

    public void refreshData(LifecycleOwner owner) {
        request.getRecommendFresh().observe(owner, new Observer<ApiResponse<List<RecommendModel>>>() {
            @Override
            public void onChanged(ApiResponse<List<RecommendModel>> listApiResponse) {
                Log.d(TAG,"recommendModels"+listApiResponse.toString());
                if(listApiResponse != null){
                    Log.d(TAG,"recommendModels"+listApiResponse.data.size());
                    videoData.setValue(listApiResponse.data);
                }
            }
        });

    }

    //加载数据
    public void loadData(LifecycleOwner owner) {
        request.getRecommendFresh().observe(owner, new Observer<ApiResponse<List<RecommendModel>>>() {
            @Override
            public void onChanged(ApiResponse<List<RecommendModel>> listApiResponse) {
                if (listApiResponse != null) {
                    List<RecommendModel> oldData = videoData.getValue();
                    oldData.addAll(listApiResponse.data);
                    videoData.setValue(oldData);
                }
            }
        });

    }

}
