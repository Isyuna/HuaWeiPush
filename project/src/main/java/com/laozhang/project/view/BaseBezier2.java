package com.laozhang.project.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;

/**
 * author : zhangyun.
 * date  : 2022/6/21  11:00.
 * description : 贝塞尔弧线2阶练习
 **/
public class BaseBezier2 extends View {
    private int mCustomSize;
    private int mCustomColor;
    private Paint mCustonPaint;
    private Paint mCustomCirlePaint;
    private int mSize;
    private int weight;
    private int height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Paint mCustomBollPaint;
    private Boolean isClean = false;


    private int startX1;
    private int startY1;
    private int endX1;
    private int endY1;
    private float eventX;
    private float eventY;
    private float bollHeight;
    private float endHeight = 100;



    public BaseBezier2(Context context) {
        super(context);
    }

    public BaseBezier2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mCustonPaint = new Paint();
        mCustonPaint.setColor(Color.parseColor("#efb336"));
        mCustonPaint.setAntiAlias(true);
        mCustonPaint.setStrokeWidth(20);
        //空心
        mCustonPaint.setStyle(Paint.Style.STROKE);

        mCustomCirlePaint = new Paint();
        mCustomCirlePaint.setColor(Color.parseColor("#efb336"));
        mCustomCirlePaint.setAntiAlias(true);
        mCustomCirlePaint.setStrokeWidth(20);
        //空心
        mCustomCirlePaint.setStyle(Paint.Style.STROKE);

        mCustomBollPaint = new Paint();
        mCustomBollPaint.setColor(Color.parseColor("#FF4848"));
        mCustomBollPaint.setAntiAlias(true);
        mCustomBollPaint.setStrokeWidth(20);

    }

    public BaseBezier2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        weight = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(weight, height);
        startX = 0;
        startY = height - 160;

        endX = weight/2-160;
        endY = height-160;


        startX1 = weight/2-500;
        startY1 = height/2;

        endX1 = weight/2+500;
        endY1 = height/2;
        eventX = weight/2;
        eventY = height/2;
        bollHeight = 50;



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(0,height);
        path.lineTo(startX,startY);
        path.lineTo(endX,startY);
        //x1 相当于贝赛尔弧线 两切线交点
        path.quadTo(weight/2,height-300,weight/2+160,startY);
        path.lineTo(weight,height-160);
        path.lineTo(weight,height);
        path.close();
        canvas.drawPath(path,mCustonPaint);

//        Path path1 = new Path();
//        path1.moveTo(startX1,startY1);
//        path1.quadTo(eventX,eventY,endX1,endY1);
//        canvas.drawPath(path1,mCustomCirlePaint);
//
//
//        canvas.drawCircle(weight/2,bollHeight,50,mCustomBollPaint);

    }

     public  void  startDownAnimator(){
         ValueAnimator animator = ValueAnimator.ofFloat(50,height/2+endHeight);
         animator.setInterpolator(new AccelerateInterpolator());
         animator.setDuration(1500);
         animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
             @Override
             public void onAnimationUpdate(ValueAnimator animation) {
                 bollHeight = (float) animation.getAnimatedValue();
                 if(bollHeight >= height/2){
                     eventY = bollHeight + 50;
                 }
                 postInvalidate();
             }
         });
         animator.start();

     }
    public  void  startUpAnimator(){
        ValueAnimator animator = ValueAnimator.ofFloat(weight/2,height/2-50);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bollHeight = (float) animation.getAnimatedValue();
                eventY = (float) animation.getAnimatedValue();
                if(bollHeight <= height/2+endHeight){

                }
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                eventX = (int) event.getX();
                eventY = (int) event.getY();
                postInvalidate();
                break;
        }
        return true;
    }
}
