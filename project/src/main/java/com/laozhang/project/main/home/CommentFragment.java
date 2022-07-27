package com.laozhang.project.main.home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.laozhang.project.R;
import com.laozhang.project.main.adapter.CommentAdapter;
import com.laozhang.project.viewModel.CommentViewModel;
import com.zhy.common.utils.EndlessLinearOnScrollListener;

/**
 * author : zhangyun.
 * date  : 2022/7/27  10:06.
 * description : 评论共用fragment
 **/
public class CommentFragment extends BottomSheetDialogFragment {


    private Context mContext;
    private View view;
    CommentAdapter adapter;
    CommentViewModel viewModel;

    public static CommentFragment getInstance() {
        return new CommentFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.e("TAG", "onCreateDialog: ");

        viewModel =  new ViewModelProvider(this).get(CommentViewModel.class);
        return new BottomSheetDialog(this.getContext());
    }

    @Override
    public void onStart() {
        Log.e("TAG", "onStart: ");
        super.onStart();
        //获取dialog对象
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        //把windowsd的默认背景颜色去掉，不然圆角显示不见
        dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //获取diglog的根部局
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            //获取根部局的LayoutParams对象
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getPeekHeight();
            //修改弹窗的最大高度，不允许上滑（默认可以上滑）
            bottomSheet.setLayoutParams(layoutParams);
            final BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
            //peekHeight即弹窗的最大高度
            behavior.setPeekHeight(getPeekHeight());
            // 初始为展开状态
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        }
    }

    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 3;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        view = inflater.inflate(R.layout.layout_comment_bottom, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.re_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapter = new CommentAdapter(mContext);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        EndlessLinearOnScrollListener listener = new EndlessLinearOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadData();
            }

            @Override
            public void onScrollStateChanged(int firstVisibleItem, int lastVisibleItem, int totalItemCount) {

            }
        };
        recyclerView.addOnScrollListener(listener);
        loadData();
    }

    private void loadData() {

    }


}
