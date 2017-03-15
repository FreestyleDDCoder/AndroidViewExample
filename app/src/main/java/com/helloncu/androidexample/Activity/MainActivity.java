package com.helloncu.androidexample.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.helloncu.androidexample.R;
import com.helloncu.androidexample.View.TopBar;

public class MainActivity extends AppCompatActivity {

    private TopBar topBar;
    private TopBar topBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitUI();
    }

    private void InitUI() {
        topBar = (TopBar) findViewById(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "this is left Button !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MusicRectActivity.class);
                startActivity(intent);
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "this is right Button !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CricleActivity.class);
                startActivity(intent);
            }
        });

        topBar2 = (TopBar) findViewById(R.id.topBar2);
        topBar2.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });
    }
}
