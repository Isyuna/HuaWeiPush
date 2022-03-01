package com.mcp.huaweipush.ZYRecyclerView;

/**
 * author : zhangyun.
 * date  : 2021/12/21  17:27.
 * description :
 **/
public interface ZYRecyclerViewPull {

    void onRefresh();

    void onLoadMore();

    void onComplete();

    void noMore();

    void isEmpty();

    void netError();

}
