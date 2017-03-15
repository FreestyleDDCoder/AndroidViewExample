package com.helloncu.androidexample.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.helloncu.androidexample.R;

/**
 * Created by liangzhan on 17-3-14.
 * 这是自定义复合控件
 */

public class TopBar extends RelativeLayout {

    private int mleftTextColor;
    private Drawable mleftBackground;
    private String mleftText;
    private int mrightTextColor;
    private Drawable mrightBackground;
    private String mrightText;
    private float mtitleTextSize;
    private int mtitleTextColor;
    private String mtitle;
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;
    private LayoutParams mleftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;
    private topBarClickListener mListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitUI(context, attrs);
    }


    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitUI(context, attrs);
    }

    private void InitUI(Context context, AttributeSet attrs) {
        //obtainStyledAttributes（）获取到自定义的属性集合
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        //然后从TypedArray中取出对应的值来为要设置的属性赋值
        mleftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mleftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mleftText = typedArray.getString(R.styleable.TopBar_leftText);
        mrightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mrightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mrightText = typedArray.getString(R.styleable.TopBar_rightText);
        mtitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mtitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        mtitle = typedArray.getString(R.styleable.TopBar_title);

        //获取完TypeArray的值后，一般要调用recycle(循环)方法来避免重新创建的时候的错误
        typedArray.recycle();

        //接下来开始合并控件了，通过动态添加的方式，使用addView（）方法即可
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组件元素赋值
        //值就来源于我们在引用的xml文件中给的对应属性的赋值
        mLeftButton.setTextColor(mleftTextColor);
        mLeftButton.setBackground(mleftBackground);
        mLeftButton.setText(mleftText);

        mRightButton.setTextColor(mrightTextColor);
        mRightButton.setBackground(mrightBackground);
        mRightButton.setText(mrightText);

        mTitleView.setText(mtitle);
        mTitleView.setTextColor(mtitleTextColor);
        mTitleView.setTextSize(mtitleTextSize);
        //Gravity(重力)
        mTitleView.setGravity(Gravity.CENTER);

        //为组件设置相应的元素布局
        mleftParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mleftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //添加到ViewGroup
        addView(mLeftButton, mleftParams);

        mRightParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        //按钮点击事件，不需要具体实现
        //只需调用接口方法，回调的时候，会有具体的实现
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
    }

    //暴露一个方法给调用者来注册接口回调
    //通过接口来获得回调者对接口方法的实现
    public void setOnTopBarClickListener(topBarClickListener mListener) {
        this.mListener = mListener;
    }

    //左右按钮的点击事件
    //由于每个调用者想要实现的功能是不一样的
    //所以不能直接在UI模板中具体的实现逻辑，通过接口回调思想
    public interface topBarClickListener {
        //左边按钮点击事件
        void leftClick();

        //右边按钮点击事件
        void rightClick();
    }

    /**
     * 设置按钮的显示与否是否通过id区分按钮，flag区分是否显示
     * @param id id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id,boolean flag){
        if(flag){
            if(id==0){
                mLeftButton.setVisibility(View.VISIBLE);
            }else{
                mRightButton.setVisibility(View.VISIBLE);
            }
        }else{
            if (id==0){
                mLeftButton.setVisibility(View.GONE);
            }else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }
}
