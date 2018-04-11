package com.example.fanruikang.simpnotes.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fanruikang.simpnotes.tool.ActivityCollector;
import com.example.fanruikang.simpnotes.tool.LogUtil;
import com.example.fanruikang.simpnotes.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("BaseActivity",getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
