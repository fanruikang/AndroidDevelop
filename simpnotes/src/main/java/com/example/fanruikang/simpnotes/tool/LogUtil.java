package com.example.fanruikang.simpnotes.tool;

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
    private static final int VERBOSE;
    private static final int DEBUG;
    private static final int INFO;
    private static final int WARN;
    private static final int ERROR;
    private static final int NOTHING;
    private static final int LEVEL;

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
