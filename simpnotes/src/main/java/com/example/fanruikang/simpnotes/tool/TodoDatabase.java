package com.example.fanruikang.simpnotes.tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/6/6 0006 22:59
 * 修改人：FanRuikang
 * 修改时间：2018/6/6 0006 22:59
 * 修改备注：
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private Context mContext;
    public TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL("create  table notebook( id integer primary key autoincrement,content text,isdeleted boolean)");
            case 2:
                db.execSQL("alter table todo add column isdeleted integer ");
            case 3:
                db.execSQL("alter table todo add column time text");
            default:

        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todo( id integer primary key autoincrement,content text,isdeleted integer,time text)");
        db.execSQL("create table notebook( id integer primary key autoincrement,content text,isdeleted integer)");
        Toast.makeText(mContext,"create succeeded",Toast.LENGTH_SHORT).show();
        String[] strings = new String[]{"下拉添加ToDo","左滑删除","右滑提醒"};

        for (int i = 0;i<strings.length;i++){
            ContentValues values = new ContentValues();
            values.put("content", strings[i]);
            values.put("isdeleted","0");
            values.put("time",System.currentTimeMillis());
            db.insert("todo", null,values );
        }
    }
}

