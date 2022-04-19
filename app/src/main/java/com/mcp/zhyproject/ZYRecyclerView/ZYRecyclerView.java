package com.mcp.zhyproject.ZYRecyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mcp.zhyproject.R;

/**
 * author : zhangyun.
 * date  : 2021/12/21  14:57.
 * description :
 **/
public class ZYRecyclerView extends ViewGroup implements ZYRecyclerViewPull {

    private static final String TAG = "ZYRecyclerView";

    View mHeader;
    private int mLayoutContentHeight;
    //头高度
    private int mEffectiveHeaderHeight;
    //记录最后一次滑动距离
    private int mLastMoveY;

    RefreshStates states;

    public ZYRecyclerView(@NonNull Context context) {
        super(context);
    }

    public ZYRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZYRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout");

        mLayoutContentHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == mHeader) {
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
                mEffectiveHeaderHeight = child.getHeight();
            } else {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
                if (i < getChildCount()) {
                    if (child instanceof ScrollView) {
                        mLayoutContentHeight += getMeasuredHeight();
                        continue;
                    }
                    mLayoutContentHeight += child.getMeasuredHeight();
                }
            }
        }
    }


    // 当view的所有child从xml中被初始化后调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addHeardView();
    }

    //添加头部布局
    private void addHeardView() {
        mHeader = LayoutInflater.from(getContext()).inflate(R.layout.pull_header_view, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 200);
        addView(mHeader, params);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                mLastMoveY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE");
                int dy = mLastMoveY - y;
                //向下拉
                if (getScrollY() <= 0 && dy <= 0) {
                    if (getScrollY() <= -mEffectiveHeaderHeight) {
                        Log.d(TAG, "到头了");
                        states = RefreshStates.REFRESH_START;
                    } else {
                        scrollBy(0, dy / 3);

                    }
                    //向上滑动
                } else if (getScrollY() >= 0 && dy >= 0) {
                    scrollBy(0, dy);

                } else {
                    scrollBy(0, dy);

                }
                break;
            case MotionEvent.ACTION_UP:
                //达到高度松手释放开始加载
                if (getScrollY() <= -mEffectiveHeaderHeight) {
                    states = RefreshStates.REFRESH_ING;

                }
                break;

        }
        mLastMoveY = y;

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;

        RecyclerView recyclerView = (RecyclerView) getChildAt(0);
        Log.d(TAG, "recyclerView.computeVerticalScrollOffset():" + recyclerView.computeVerticalScrollOffset());
        if (recyclerView.computeVerticalScrollOffset() <= 0) {
            intercept = true;
        }
        return intercept;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
        //获取所以子view数量
        int childCounts = getChildCount();
        //遍历获取所有的子View
        for (int i = 0; i < childCounts; i++) {
            View childView = getChildAt(i);
            //通知子View自自己进行测量自身的宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void noMore() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public void netError() {

    }

    public  interface  refresh {
        void onRefresh();

        void onLoadMore();
    }
}
