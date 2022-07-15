package com.laozhang.project.main.home;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.laozhang.project.BR;
import com.laozhang.project.R;
import com.laozhang.project.databinding.FragmentArticleBinding;
import com.laozhang.project.main.adapter.ArticleAdapter;
import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.viewModel.ArticleViewModel;
import com.zhy.common.base.BaseFragment;
import com.zhy.common.base.DataBindingConfig;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.utils.EndlessLinearOnScrollListener;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/12  15:21.
 * description : 纯文章
 **/
public class ArticleFragment extends BaseFragment {

    ArticleViewModel viewModel;
    ArticleAdapter mAdapter;
    FragmentArticleBinding binding;

    @Override
    protected void initViewModel() {
        viewModel = initViewModelProvider(ArticleViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {

        mAdapter = new ArticleAdapter(getContext());
        return new DataBindingConfig(R.layout.fragment_article, BR.vm, viewModel)
                .addBindingParam(BR.adapter, mAdapter);
    }

    @Override
    protected void initViews(View view) {
        mBinding.setLifecycleOwner(this);
        binding = (FragmentArticleBinding) mBinding;
        binding.reView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.reView.setLayoutManager(linearLayoutManager);
        refreshData();

        viewModel.request.consumers.observe(getActivity(), new Observer<List<RecommendModel>>() {
            @Override
            public void onChanged(List<RecommendModel> recommendModels) {
                mAdapter.submitList(recommendModels);
            }
        });
        EndlessLinearOnScrollListener listener = new EndlessLinearOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadData();
            }
        };
        binding.reView.addOnScrollListener(listener);


        binding.swLayout.setOnRefreshListener(this::refreshData);
    }

    private void refreshData() {
        viewModel.refreshData().observe(getActivity(), new Observer<ApiResponse<List<RecommendModel>>>() {
            @Override
            public void onChanged(ApiResponse<List<RecommendModel>> listApiResponse) {
                if (listApiResponse == null || listApiResponse.data == null) {
                    Snackbar.make(binding.swLayout, "暂无数据！", Snackbar.LENGTH_LONG).show();
                    return;
                }
                viewModel.request.consumers.setValue(listApiResponse.data);
                binding.swLayout.setRefreshing(false);
            }
        });
    }

    private void loadData() {
        viewModel.loadData().observe(getActivity(), new Observer<ApiResponse<List<RecommendModel>>>() {
            @Override
            public void onChanged(ApiResponse<List<RecommendModel>> listApiResponse) {
                if (listApiResponse == null || listApiResponse.data == null) {
                    Snackbar.make(binding.swLayout, "暂无数据！", Snackbar.LENGTH_LONG).show();
                    return;
                }
                List<RecommendModel> oldData = viewModel.request.consumers.getValue();
                oldData.addAll(listApiResponse.data);
                viewModel.request.consumers.setValue(oldData);
            }
        });
    }
}
