package com.example.fanruikang.simpnotes.ui;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fanruikang.simpnotes.tool.LogUtil;
import com.example.fanruikang.simpnotes.R;


public class MainActivity extends BaseActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("MainActivity", "App start");
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        //设置标题栏
        toolbar = findViewById(R.id.activity_main_toolbar);
        setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
//        使用其他字体
//        Typeface typeface = Typeface.createFromAsset(this.getAssets(),"font/icomoon.ttf");
//        TextView view = findViewById(R.id.simpnotes_bottom_bar_notes);
//        view.setTypeface(typeface);
    }
}
