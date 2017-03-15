package com.helloncu.androidexample.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liangzhan on 17-3-15.
 * 这是自定义的圆类
 */

public class CircleView extends View {

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private float mShowTextSize;
    private float mCircleXY;
    private float mRadius;
    private float mSweepValue;
    private RectF mArcF;
    private String mShowText;
    private int mMeasureWidth;
    private int mMeasureHeight;
    private float mSweepAngle;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //在构造方法中完成必要对象的初始化工作，如初始化画笔等
    private void InitUI() {
        float length = 0;
        if (mMeasureHeight >= mMeasureWidth) {
            length = mMeasureWidth;
        } else {
            length = mMeasureHeight;
        }
        //首先在初始化时绘制三种图形的参数
        //圆心坐标和半径
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);

        //画圆的笔
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);//抗锯齿
        mPaint1.setColor(Color.GREEN);
        mPaint1.setStyle(Paint.Style.FILL);//表示填充
        //画弧形的笔
        mPaint2 = new Paint();
        mPaint2.setColor(Color.GREEN);
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth((float) (length * 0.1));//Stroke（划）
        mPaint2.setStyle(Paint.Style.STROKE);
        //画文本的笔
        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mPaint3 = new Paint();
        mPaint3.setTextSize(mShowTextSize);
        mPaint3.setTextAlign(Paint.Align.CENTER);//Align(排列)

        //绘制弧线，需要指定其椭圆的外接矩形
        /**
         * RectF()
         *
         * @param left - The X coordinate of the left side of the rectangle（矩形左侧的x坐标）
         * @param top - The Y coordinate of the top of the rectangle
         * @param right - The X coordinate of the right side of the rectangle
         * @param bottom - The Y coordinate of the bottom of the rectangle
         */
        mArcF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9)
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mSweepAngle = (mSweepValue / 100f) * 360f;//(弧度)
        //绘制圆
        /**
         * RectF()
         *
         * @param cx The x-coordinate of the center of the cirle to be drawn（对圆中心的x坐标来绘制）
         * @param cy The y-coordinate of the center of the cirle to be drawn
         * @param radius The radius of the cirle to be drawn
         * @param paint The paint used to draw the circle
         */
        canvas.drawCircle(
                mCircleXY,
                mCircleXY,
                mRadius,
                mPaint1
        );
        //绘制弧线
        /**
         * @param oval The bounds of oval used to define the shape and size of the arc（用于定义圆弧形状和尺寸的椭圆的边界）
         * @param startAngle Starting angle (in degrees) where the arc begins(起始角度（度）弧开始的地方)
         * @param sweepAngle Sweep angle (in degrees) measured clockwise(顺时针扫描角度（度）)
         * @param useCenter If true, include the center of the oval in the arc, and close it if it is being stroked. This will draw a wedge
         * @param paint The paint used to draw the arc
         */
        canvas.drawArc(
                mArcF,
                270,
                mSweepAngle,
                false,
                mPaint2
        );

        //绘制文字
        /**
         * @param text The text to be drawn（要绘制的文本）
         * @param start The index of the first character in text to draw(文本中第一个字符的索引)
         * @param end (end - 1) is the index of the last character in text to draw(文本中最后一个字符的索引)
         * @param x The x-coordinate of the origin of the text being drawn(对文字起源的x坐标绘制)
         * @param y The y-coordinate of the baseline of the text being drawn(对文字起源的y坐标绘制)
         * @param paint The paint used for the text (e.g. color, size, style)
         */
        canvas.drawText(
                mShowText,
                0,
                mShowText.length(),
                mCircleXY,
                mCircleXY + (mShowTextSize / 4),
                mPaint3
        );

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = mWidthMeasureSpec(widthMeasureSpec);
        mMeasureHeight = mHeightMeasureSpec(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);
        InitUI();
    }

    private int mHeightMeasureSpec(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 100;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private int mWidthMeasureSpec(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 200;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private String setShowText() {
        this.invalidate();
        return "Android Skill";
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    public void setShowText(String mShowText) {
        this.invalidate();
        this.mShowText = mShowText;
    }

    public void forceInvalidate() {
        this.invalidate();
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        this.invalidate();
    }
}
