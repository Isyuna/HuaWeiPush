package com.laozhang.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.laozhang.project.main.home.HomeFragment;
import com.laozhang.project.main.video.VideoFragment;
import com.laozhang.project.utils.ViewPagerWithLimit;
import com.zhy.common.tablayout.CommonTabLayout;
import com.zhy.common.tablayout.entity.TabEntity;
import com.zhy.common.tablayout.listener.CustomTabEntity;
import com.zhy.common.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class ProjectMainActivity extends AppCompatActivity {

    CommonTabLayout slidingTabLayout;
    ViewPagerWithLimit viewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"首页", "划一划", "添加", "消息", "我的"};

    private MainAdapter adapter;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int[] mIconUnselectIds = {
            R.mipmap.home, R.mipmap.find, R.mipmap.mine,
            R.mipmap.message, R.mipmap.mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_main);

        initViews();
    }

    private void initViews() {
        slidingTabLayout = findViewById(R.id.sliding_tab);
        viewPager = findViewById(R.id.view_pager);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconUnselectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new HomeFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        adapter = new MainAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        slidingTabLayout.setTabData(mTabEntities);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                slidingTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabSameSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private class MainAdapter extends FragmentPagerAdapter {


        public MainAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}