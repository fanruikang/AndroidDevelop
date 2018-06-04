package com.example.fanruikang.simpnotes.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.LogUtil;

import java.io.IOException;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/30 0030 8:55
 * 修改人：FanRuikang
 * 修改时间：2018/5/30 0030 8:55
 * 修改备注：
 */

public class AlarmActivity extends BaseActivity{
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("AlarmActivity","onCreate");
        mMediaPlayer = MediaPlayer.create(this, getSystemDefultRingtoneUri());
        mMediaPlayer.setLooping(false);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
        new AlertDialog.Builder(AlarmActivity.this, R.style.MyAlertDialogStyle)
                .setTitle("ToDo")
                .setMessage(getIntent().getStringExtra("ToDo").toString())
                .setCancelable(false)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
//                        AlarmActivity.this.finish();
//                        mMediaPlayer.stop();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlarmActivity.this.finish();
                        mMediaPlayer.stop();
                    }
                }).show();

    }
    private Uri getSystemDefultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_RINGTONE);
    }
}
