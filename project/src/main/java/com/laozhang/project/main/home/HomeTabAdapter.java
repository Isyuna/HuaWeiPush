package com.laozhang.project.main.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/1  11:01.
 * description :
 **/
public class HomeTabAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    private List<String> mTitles;

    public HomeTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setData(List<Fragment> data, List<String> mTitles) {
        this.mTitles = mTitles;
        this.fragments = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.size();
    }
}
