package com.example.fanruikang.sweetweather;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：AndroidDevelop
 * 类描述：获取全局Context
 * 创建人：FanRuikang
 * 创建时间：2018/3/17 0017 15:45
 * 修改人：FanRuikang
 * 修改时间：2018/3/17 0017 15:45
 * 修改备注：
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
