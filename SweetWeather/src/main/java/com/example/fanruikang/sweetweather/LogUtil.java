package com.example.fanruikang.sweetweather;

import android.util.Log;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/3/17 0017 15:45
 * 修改人：FanRuikang
 * 修改时间：2018/3/17 0017 15:45
 * 修改备注：
 */
public class LogUtil {
    public static final int VERBOSE;
    public static final int DEBUG;
    public static final int INFO;
    public static final int WARN;
    public static final int ERROR;
    public static final int NOTHING;
    public static final int LEVEL;

    static {
        VERBOSE = 1;
        DEBUG = 2;
        INFO = 3;
        WARN = 4;
        ERROR = 5;
        NOTHING = 6;
        LEVEL = VERBOSE;
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }


}
