package com.laozhang.project.main.home;

import android.widget.HorizontalScrollView;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.laozhang.project.R;
import com.zhy.common.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/1  10:50.
 * description : home模块自定义adapter
 **/
public class HomeBindingAdapter {


    @BindingAdapter(value = {"initTabAndPage"}, requireAll = false)
    public static void initTabAndPage(SlidingTabLayout slidingTabLayout, Boolean isLoading){

    }
}
