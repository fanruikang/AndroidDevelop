package com.example.fanruikang.simpnotes.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/15 0015 15:07
 * 修改人：FanRuikang
 * 修改时间：2018/5/15 0015 15:07
 * 修改备注：
 */

public class TodoView extends RecyclerView {
    public TodoView(Context context) {
        super(context);
    }

    public TodoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TodoView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }
}
