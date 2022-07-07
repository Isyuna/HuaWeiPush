package com.zhy.common.tablayout.listener;

public interface OnTabSelectListener {
    void onTabSelect(int position);
    void onTabSameSelect(int position);
    void onTabReselect(int position);
}