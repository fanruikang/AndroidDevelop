package com.example.fanruikang.simpnotes.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.LogUtil;
import com.example.fanruikang.simpnotes.tool.RecyclerAdapter;
import com.example.fanruikang.simpnotes.tool.SimpleItemTodoHistoryTouchHelperCallback;
import com.example.fanruikang.simpnotes.tool.TodoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/6/6 0006 16:33
 * 修改人：FanRuikang
 * 修改时间：2018/6/6 0006 16:33
 * 修改备注：
 */

public class TodoHistory extends BaseActivity {
    final TodoDatabase TodoDatabase = new TodoDatabase(this,"TodoList.db",null,7 );
    List<String> datas = new ArrayList<String>();
    private RecyclerAdapter recyclerAdapter;
    private static final int UPDATE_TODOLIST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.d("ToDoHistory", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpnote_activity_todohistory);
        final Toolbar toolbar = findViewById(R.id.simpnote_todohistory_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.history);
        initDatas();
    }
    public void initDatas(){

        datas.clear();
        SQLiteDatabase dbreader = TodoDatabase.getReadableDatabase();
        Cursor cursor = dbreader.rawQuery(getString(R.string.selectingtodo),
                new String[]{"1"});

        if (cursor.moveToFirst()) {
            do {
                datas.add(cursor.getString(cursor.getColumnIndex("content")));
            } while (cursor.moveToNext());
        }

        dbreader.close();
        LogUtil.d("TodoHistory", "initDatas");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(UPDATE_TODOLIST);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(UPDATE_TODOLIST);
        finish();
    }

    @Override
    protected void onStart() {
        init();
        super.onStart();
    }

    public void init() {
        LogUtil.d("TodoHistory", "init");
        RecyclerView View = findViewById(R.id.rv_todo_history);
        View.addItemDecoration(new DividerItemDecoration(View.getContext(), LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(View.getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        View.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(datas, TodoDatabase) ;

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTodoHistoryTouchHelperCallback(recyclerAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(View);
        View.setAdapter(recyclerAdapter);
    }
}
