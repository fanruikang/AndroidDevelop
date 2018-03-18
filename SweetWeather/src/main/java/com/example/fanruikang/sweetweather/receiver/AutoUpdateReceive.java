package com.example.fanruikang.sweetweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.fanruikang.sweetweather.service.AutoUpdateService;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/3/18 0018 15:25
 * 修改人：FanRuikang
 * 修改时间：2018/3/18 0018 15:25
 * 修改备注：
 */

public class AutoUpdateReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }

}
