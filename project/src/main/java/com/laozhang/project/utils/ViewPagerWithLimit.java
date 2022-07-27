package com.laozhang.project.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author : zhangyun.
 * date  : 2022/7/27  09:27.
 * description :
 **/
public class ViewPagerWithLimit extends ViewPager {
    private boolean isCanScroll = false;

    public ViewPagerWithLimit(@NonNull Context context) {
        super(context);
    }

    public ViewPagerWithLimit(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 设置其是否能滑动换页
     *
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);

    }

    @Override
    public void setOffscreenPageLimit(int limit) {

        if (limit != getOffscreenPageLimit()) {
            try {
                Class c = Class.forName("android.support.v4.view.ViewPager");
                Field field = c.getDeclaredField("mOffscreenPageLimit");
                field.setAccessible(true);
                field.setInt(this, limit);
                Class clazz = this.getClass();
                Method m1 = clazz.getDeclaredMethod("populate");
                m1.invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
