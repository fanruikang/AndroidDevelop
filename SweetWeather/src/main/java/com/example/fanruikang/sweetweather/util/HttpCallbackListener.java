package com.example.fanruikang.sweetweather.util;

/**
 * 项目名称：AndroidDevelop
 * 类描述：HttpUtil 回调接口
 * 创建人：FanRuikang
 * 创建时间：2018/3/18 0018 10:50
 * 修改人：FanRuikang
 * 修改时间：2018/3/18 0018 10:50
 * 修改备注：
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
