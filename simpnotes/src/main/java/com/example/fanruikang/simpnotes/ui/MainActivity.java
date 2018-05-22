package com.example.fanruikang.simpnotes.ui;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    final TodoDatabase todoDatabase = new TodoDatabase(this,"TodoList.db",null,1 );

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        rightToInsert();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpnote_activity_main);

        Button button_note = findViewById(R.id.note);
        button_note.setSelected(true);
//        for (int i = 0; i<15;i++)
//        {
//            String name = "下拉添加todo";
//            insert(name);
//        }

        query();

        final EditText editText = findViewById(R.id.edit_todo);
        ViewTreeObserver viewTreeObserver = editText.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                EditText editText = findViewById(R.id.edit_todo);
                Rect rect = new Rect();
                editText.getGlobalVisibleRect(rect);
                String name = String.valueOf(editText.getText());

                if (rect.bottom==0 && !name.equals("添加ToDo") && !name.equals("")){
                    insert(name);
                    Log.d("MainActivity", "添加ToDo");

                    Toast.makeText(MainActivity.this,"tianjiaotodo",Toast.LENGTH_SHORT).show();
                    query();
                    Log.d("MainActivity", "edit_hide"+rect.bottom);
                    editText.setText("添加ToDo");
                }

        }});

        final ConstraintLayout constraintLayout = findViewById(R.id.cl_root);
        ViewTreeObserver viewTreeObserverll = constraintLayout.getViewTreeObserver();

//        viewTreeObserverll.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                String name = String.valueOf(editText.getText());
//                Rect rect = new Rect();
//                constraintLayout.getWindowVisibleDisplayFrame(rect);
//                int screenHeight = constraintLayout.getRootView().getHeight();
//                Log.d("MainActivity", ""+rect.bottom+"关闭输入法"+screenHeight+name);
//                if((screenHeight==rect.bottom) && ! name.equals("添加ToDo") && ! name.equals("")){
////                    insert(name);
//                    Log.d("MainActivity", "关闭输入法自动保存");
//                    View view = getLayoutInflater().inflate(R.layout.simpnote_fragment_item_list,null);
////                    view.scrollTo(0,300);
//                    Log.d("MainActivity", "RootLayoutchange"+view.getScrollY());
//
//                }
//
//
//            }
//        });

//        if (editText.getWindowVisibility())
//        //弹出软键盘
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(v.isFocused()){
//                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                }else {
//                }
//                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }
//        });

//        RecyclerView recyclerView = findViewById(R.id.listview);
//        recyclerView.scrollTo(0,700);
//        recyclerView.scrollToPosition(2);
//        recyclerView.setTranslationY(0);


    }
    private void rightToInsert(){
        EditText editText = findViewById(R.id.edit_todo);
        Rect rect = new Rect();
        editText.getGlobalVisibleRect(rect);
        String name = String.valueOf(editText.getText());

        if (rect.bottom==0 && !name.equals("添加ToDo") && !name.equals("")){
            insert(name);
            Log.d("MainActivity", "添加ToDo");
            editText.setText("添加ToDo");
            Toast.makeText(MainActivity.this,"tianjiaotodo",Toast.LENGTH_SHORT).show();
            query();
            Log.d("MainActivity", "edit_hide"+rect.bottom);
        }


    }
    private void insert(String name) {//插入
        SQLiteDatabase dbwriter = todoDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", name);

        dbwriter.insert("todo", null, values);
        Log.d("MainActivity", "insert:");

        dbwriter.close();
    }
    private void query() { //查询
        SQLiteDatabase dbreader = todoDatabase.getReadableDatabase();
        Cursor cursor = dbreader.query("todo", null, null, null, null, null,
                        null);
//        String[] datas = new String[cursor.getCount()];
        List<String> datas = new ArrayList<String>();
//        if(cursor.moveToFirst()){
//            for( int i = 0; cursor.moveToNext(); i++){
////                datas[i] = cursor.getString(cursor.getColumnIndex("content"));
//
//
//            }
//        }
        if (cursor.moveToFirst()) {
            do {
                datas.add(cursor.getString(cursor.getColumnIndex("content")));
            } while (cursor.moveToNext());
        }
        RecyclerView View = findViewById(R.id.rv_todo);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        View.setLayoutManager(linearLayoutManager);
//        View.setAdapter(new RecyclerAdapter(Arrays.asList(datas)));
        View.setAdapter(new RecyclerAdapter(datas));
        dbreader.close();
        Log.d("MainActivity", "query:");
    }
    class TodoDatabase extends SQLiteOpenHelper {
        private Context mContext;
        public TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            mContext = context;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table todo( id integer primary key autoincrement,content text)");
            Toast.makeText(mContext,"create succeeded",Toast.LENGTH_SHORT).show();
            String[] strings = new String[]{"下拉添加ToDo","左滑删除","右滑提醒"};

            for (int i = 0;i<strings.length;i++){
                ContentValues values = new ContentValues();
                values.put("content", strings[i]);
                db.insert("todo", null,values );
            }
        }
    }

}

