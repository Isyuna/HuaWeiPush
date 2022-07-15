package com.zhy.common.utils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : zhangyun.
 * date  : 2022/7/13  10:12.
 * description :
 **/
public abstract class EndlessLinearOnScrollListener extends RecyclerView.OnScrollListener {

    public static String TAG = EndlessLinearOnScrollListener.class.getSimpleName();

    public int previousTotal = 0; // The total number of items in the dataset after the last load  总数据
    private boolean loading = true; // True if we are still waiting for the last set of data to load. 是否提前加载
    public int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount,lastVisibleItem;
    private int current_page = 1;
    private LinearLayoutManager linearLayoutManager;

    public EndlessLinearOnScrollListener(LinearLayoutManager layoutManager) {
        this.linearLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

        if((totalItemCount - lastVisibleItem ) < 3){
            onLoadMore(current_page);
        }
    }

    public abstract void onLoadMore(int current_page);

    //重置
    public void reset() {
        this.previousTotal = 0;
        this.loading = true;
    }
}

