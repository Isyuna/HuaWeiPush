package com.laozhang.project.main.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;

import com.laozhang.project.BR;
import com.laozhang.project.R;
import com.laozhang.project.databinding.FragmentHomeBinding;
import com.laozhang.project.viewModel.HomeViewModel;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author : zhangyun.
 * date  : 2022/6/14  18:07.
 * description :
 **/
public class HomeFragment extends BaseFragment {

    HomeViewModel homeViewModel;
    FragmentHomeBinding binding;
    List<Fragment> initFragment = new ArrayList<>();
    List<String> tittles = new ArrayList<>();

    @Override
    protected void initViewModel() {
        homeViewModel = initViewModelProvider(HomeViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm, homeViewModel);
    }

    @Override
    protected void initViews(View view) {
        binding = (FragmentHomeBinding) mBinding;
        mBinding.setLifecycleOwner(this);
        initFragment.clear();
        tittles.clear();
        initFragment.add(new FollowFragment());
        initFragment.add(new RecommendFragment());
        initFragment.add(new FreshFragment());
        initFragment.add(new ArticleFragment());
        initFragment.add(new ImageFragment());

        tittles.add("关注");
        tittles.add("推荐");
        tittles.add("新鲜");
        tittles.add("纯文");
        tittles.add("趣图");
        HomePagerAdapter mAdapter= new HomePagerAdapter(getChildFragmentManager(),
                0);
        binding.homePager.setAdapter(mAdapter);
        binding.homePager.setOffscreenPageLimit(3);
        binding.stlTab.setViewPager(binding.homePager);
        binding.stlTab.setTextSelectColor(getResources().getColor(R.color.theme));

    }
    private class HomePagerAdapter extends FragmentPagerAdapter {


        public HomePagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @Override
        public int getCount() {
            return initFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tittles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return initFragment.get(position);
        }
    }
}
