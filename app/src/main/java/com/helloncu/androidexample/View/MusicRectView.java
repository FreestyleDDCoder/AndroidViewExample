package com.helloncu.androidexample.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by liangzhan on 17-3-15.
 * 音频条的实现
 */

public class MusicRectView extends View {

    private int mMeasureWidth;
    private int mMeasureHeight;
    private int offset = 5;
    private Paint mPaint;
    private double mRandom;
    private int mRectCount;
    private int mWidth;
    private int mRectHeight;
    private float mRectWidth;
    private LinearGradient mLinearGradient;

    public MusicRectView(Context context) {
        super(context);
    }

    public MusicRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void InitView() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        //进度条数量
        mRectCount = 12;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            mRandom = Math.random();
            float currentHeight = (float) (mRectHeight * mRandom);
            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth,mMeasureHeight);
        mWidth = mMeasureWidth;
        mRectHeight = mMeasureHeight;
        InitView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRectWidth = (float) (mWidth * 0.6 / mRectCount);
        /**
         * @param x0 The x-coordinate for the start of the gradient line(渐变线启动X坐标)
         * @param y0 The y-coordinate for the start of the gradient line(渐变线启动y坐标)
         * @param x1 The x-coordinate for the end of the gradient line(渐变线末端的X坐标)
         * @param y1 The y-coordinate for the end of the gradient line(渐变线末端的y坐标)
         * @param color0 The color at the start of the gradient line.(渐变线开始时的颜色)
         * @param color1 The color at the end of the gradient line.(渐变线结束时的颜色)
         * @param tile The Shader tiling mode（着色器平铺模式）
         */
        mLinearGradient = new LinearGradient(
                0,
                0,
                mRectWidth,
                mRectHeight,
                Color.RED,
                Color.BLUE,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }
}
