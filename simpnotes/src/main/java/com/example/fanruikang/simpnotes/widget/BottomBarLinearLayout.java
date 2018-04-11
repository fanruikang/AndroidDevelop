package com.example.fanruikang.simpnotes.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.ui.BaseActivity;
import com.example.fanruikang.simpnotes.ui.HabitActivity;
import com.example.fanruikang.simpnotes.ui.NoteActivity;
import com.example.fanruikang.simpnotes.ui.NotebookActivity;
import com.example.fanruikang.simpnotes.ui.meActivity;

/**
 * 项目名称：AndroidDevelop
 * 类描述：底部导航工具栏控件
 * 创建人：FanRuikang
 * 创建时间：2018/4/11 0011 9:01
 * 修改人：FanRuikang
 * 修改时间：2018/4/11 0011 9:01
 * 修改备注：
 */

public class BottomBarLinearLayout extends LinearLayout {
    public BottomBarLinearLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.simpnotes_bottom_bar,this);
        Button note = findViewById(R.id.note);
        Button habit = findViewById(R.id.habit);
        Button notebook = findViewById(R.id.notebook);
        Button me = findViewById(R.id.me);

        note.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_note = new Intent(context, NoteActivity.class);
                context.startActivity(intent_note);
            }
        });

        habit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_habit = new Intent(context, HabitActivity.class);
                context.startActivity(intent_habit);
            }
        });

        notebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_notebook = new Intent(context, NotebookActivity.class);
                context.startActivity(intent_notebook);
            }
        });

        me.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_me = new Intent(context,meActivity.class);
                context.startActivity(intent_me);
            }
        });
    }
}