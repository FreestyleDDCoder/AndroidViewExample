package com.helloncu.androidexample.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by liangzhan on 17-3-14.
 * 对TextView进行拓展
 */

public class ExtentTextView extends TextView {

    private Paint mPaint1;
    private Paint mPaint2;
    private int mViewWidth;
    private TextPaint mPaint;
    private LinearGradient mlinearGradient;
    private Matrix mGradientmatrix;
    private int mTranslate;

    public ExtentTextView(Context context) {
        super(context);
        InitUI();
    }

    public ExtentTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        InitUI();
    }

    public ExtentTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitUI();
    }

    private void InitUI() {
        //在构造方法中完成必要对象的初始化工作，如初始化画笔等
        //paint(绘画);FILL(装满)
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //rect（矩形）
        //外层
        canvas.drawRect(
                0,
                0,
                getMeasuredWidth(),
                getMeasuredHeight(),
                mPaint1);
        //内层矩形
        canvas.drawRect(
                10,
                10,
                getMeasuredWidth() - 10,
                getMeasuredHeight() - 10,
                mPaint2);
        canvas.save();
        //绘制文字时前平移10个像素
        canvas.translate(10, 0);
        //在回调父类方法前，实现自己的逻辑；对TextView来说即是在绘制文本内容前
        super.onDraw(canvas);
        //在回调父类方法后，实现自己的逻辑；对TextView来说即是在绘制文本内容后
        //restore(恢复)
        canvas.restore();

        if (mGradientmatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            //matrix（矩阵）
            mGradientmatrix.setTranslate(mTranslate, 0);
            mlinearGradient.setLocalMatrix(mGradientmatrix);
            postInvalidateDelayed(100);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measurewidth(widthMeasureSpec), measureheight(heightMeasureSpec));

    }

    //党组件大小发生改变时回调这个方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                //getPaint()获取当前绘制TextView的Paint对象
                mPaint = getPaint();
                //Gradient(梯度);Shader(着色器)；Matrix（矩阵）
                //LinearGradient属性存在于Paint对象中
                mlinearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{
                                Color.BLUE, 0xffffffff,
                                Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mlinearGradient);
                mGradientmatrix = new Matrix();
            }
        }
    }

    private int measureheight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measurewidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
