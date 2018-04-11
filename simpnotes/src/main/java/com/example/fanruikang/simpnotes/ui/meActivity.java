package com.example.fanruikang.simpnotes.ui;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.LogUtil;


public class meActivity extends BaseActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("NoteActivity", "App start");
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.simpnote_activity_note);
//        //设置标题栏
        toolbar = findViewById(R.id.activity_main_toolbar);
        setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
//        使用其他字体
//        Typeface typeface = Typeface.createFromAsset(this.getAssets(),"font/icomoon.ttf");
//        TextView widget = findViewById(R.id.simpnotes_bottom_bar_notes);
//        widget.setTypeface(typeface);
    }
}
