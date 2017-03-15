package com.helloncu.androidexample.Activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.helloncu.androidexample.R;
import com.helloncu.androidexample.View.CircleView;

/**
 * Created by liangzhan on 17-3-15.
 * 用于展示圆形的活动
 */

public class CricleActivity extends AppCompatActivity {

    private CircleView cricle;
    private float v;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cricle);
        cricle = (CircleView) findViewById(R.id.cricle);
        cricle.setShowText("Android Skill");
        v = (float) (Math.random() * 100 + 1);
        cricle.setSweepValue(v);
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    SystemClock.sleep(300);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            v = (float) (Math.random() * 100 + 1);
                            cricle.setSweepValue(v);
                        }
                    });
                }
            }
        }.start();
    }
}
