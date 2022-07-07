package com.zhy.common.tablayout.listener;


import androidx.annotation.DrawableRes;

public interface CustomTabEntity {
    String getTabTitle();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();

    String getTabSelectedIconUrl();

    String getTabUnSelectedIconUrl();
}