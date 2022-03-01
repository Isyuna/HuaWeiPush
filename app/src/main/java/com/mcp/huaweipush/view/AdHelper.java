package com.mcp.huaweipush.view;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;

import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * author : zhangYun.
 * date  : 2021/12/13  10:54.
 * description :
 **/
public class AdHelper extends ConstraintHelper {
    public AdHelper(Context context) {
        super(context);
    }

    public AdHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

    }

    public AdHelper(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);

    }

    @Override
    public void updatePostLayout(ConstraintLayout container) {
        super.updatePostLayout(container);
        View[] views = getViews(container);
        for (View view : views) {
            Animator animation = ViewAnimationUtils.
                    createCircularReveal(view, 0, 0, 0f, Float.parseFloat(view.getWidth() + ""));
            animation.setDuration(5000);
            animation.start();
        }

    }
}
