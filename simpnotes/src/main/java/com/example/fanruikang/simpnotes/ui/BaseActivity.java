package com.example.fanruikang.simpnotes.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fanruikang.simpnotes.tool.ActivityCollector;
import com.example.fanruikang.simpnotes.tool.LogUtil;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/6/1 0001 16:04
 * 修改人：FanRuikang
 * 修改时间：2018/6/1 0001 16:04
 * 修改备注：
 */

public  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("runinfo",getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("runinfo", getClass().getSimpleName()+"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("funinfo", getClass().getSimpleName()+"onStop");
    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
        LogUtil.d("runinfo", getClass().getSimpleName()+"onDestroy");
        ActivityCollector.removeActivity(this);
    }


    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume () {
        super.onResume();
        LogUtil.d("runinfo", getClass().getSimpleName()+"onResume");
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("runinfo",getClass().getSimpleName()+"onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("runinfo",getClass().getSimpleName()+"onRestart");
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finishAll();
    }
}
