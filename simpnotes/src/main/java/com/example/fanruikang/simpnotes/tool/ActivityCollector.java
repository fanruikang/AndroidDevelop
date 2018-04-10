package com.example.fanruikang.simpnotes.tool;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：AndroidDevelop
 * 类描述：活动管理器,随时退出程序
 * 创建人：FanRuikang
 * 创建时间：2018/4/5 0005 9:02
 * 修改人：FanRuikang
 * 修改时间：2018/4/5 0005 9:02
 * 修改备注：
 */

public final class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
