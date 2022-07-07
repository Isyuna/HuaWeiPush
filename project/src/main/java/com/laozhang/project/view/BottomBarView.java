package com.laozhang.project.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laozhang.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/6/21  11:00.
 * description :
 **/
public class BottomBarView extends FrameLayout {


    private int weight;
    private int height;
    private Paint bottomPath;
    private int childViewSize = 5;
    private int bottomHeight = 150;

    private List<View> tabViews = new ArrayList<>();

    public BottomBarView(Context context) {
        super(context);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPath();
        initViews();
    }

    private void initViews() {
    }


    private void initPath() {
        bottomPath = new Paint();
        bottomPath.setColor(Color.parseColor("#F7F8F9"));
        bottomPath.setAntiAlias(true);
        bottomPath.setStrokeWidth(5);
        bottomPath.setShadowLayer(2,0,0,Color.parseColor("#FF000000"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        weight = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(weight, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();

        path.moveTo(0,height-bottomHeight);
        path.lineTo(weight/childViewSize*2,height-bottomHeight);
        path.quadTo(weight/2,height-bottomHeight-100,
                weight/childViewSize*3,height-bottomHeight);
        path.lineTo(weight,height-bottomHeight);
        path.lineTo(weight,height);
        path.lineTo(0,height);
        path.close();
        canvas.drawPath(path,bottomPath);



    }
}
