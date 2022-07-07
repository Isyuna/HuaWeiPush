package com.laozhang.project.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * author : zhangyun.
 * date  : 2022/6/21  11:00.
 * description : 贝塞尔弧线3阶练习
 **/
public class BaseBezier3 extends View {

    private Paint mCustonPaint;
    private int weight;
    private int height;

    //波的高度
    private float offset;


    public BaseBezier3(Context context) {
        super(context);
    }

    public BaseBezier3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mCustonPaint = new Paint();
        mCustonPaint.setColor(Color.parseColor("#efb336"));
        mCustonPaint.setAntiAlias(true);
        mCustonPaint.setStrokeWidth(20);
        //空心
//        mCustonPaint.setStyle(Paint.Style.STROKE);


    }

    public BaseBezier3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
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

        int itemWeight = weight/4;

        int startX = -itemWeight*3;
        int startY = height/2;
        path.moveTo(startX+offset, startY);
        for(int i = 1 ; i < 18 ; i = i + 3){
            path.cubicTo(startX+itemWeight*i+offset, getWaveHeight(i), startX+itemWeight*(i+1)+offset, getWaveHeight(i+1),startX+itemWeight*(i+2)+offset, getWaveHeight(i+2));
        }

        path.lineTo(startX+itemWeight*(18)+offset, height);
        path.lineTo(-itemWeight*3+offset,height);
        path.close();
        canvas.drawPath(path, mCustonPaint);
    }
    /**
     * 开启动画
     */
    public void start() {

        ValueAnimator animator = ValueAnimator.ofFloat(0, weight/4*3);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatorValue = (float) animation.getAnimatedValue();
                offset = animatorValue;
                Log.d("onDraw","offset:"+offset);
                postInvalidate();
            }
        });
        animator.setDuration(300);
        animator.setRepeatCount(ValueAnimator.INFINITE);//重复执行 无限次数
        animator.start();

    }
    //根据给定的i 确定是波峰还是波谷
    private int getWaveHeight(int count) {
        if (count % 3 == 1) {
            return height / 2 + 150;
        }
        if (count % 3 == 2) {
            return height / 2 - 150 ;
        }
        return height / 2 ;
    }




}
