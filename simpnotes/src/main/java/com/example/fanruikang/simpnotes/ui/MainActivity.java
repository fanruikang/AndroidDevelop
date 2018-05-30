package com.example.fanruikang.simpnotes.ui;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.RecyclerAdapter;
import com.example.fanruikang.simpnotes.tool.SimpleItemTouchHelperCallback;
import com.example.fanruikang.simpnotes.tool.TabAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends NoteBookFragment.BaseActivity {
    private TabLayout tab;
    private ViewPager viewpager;
    private TabAdapter adapter;
    private RecyclerAdapter recyclerAdapter;
    List<String> datas = new ArrayList<String>();
    public static final String[] tabTitle = new String[]{"ToDo", "笔记本","我的"};
//    private int[] selectors = new int[]{R.drawable.simpnote_buttombar_note_selector,R.drawable.simpnote_buttombar_habit_selector,R.drawable.simpnote_buttombar_notebook_selector,R.drawable.simpnote_buttombar_me_selector};
    private int[] selectors = new int[]{R.drawable.simpnote_buttombar_note_selector,R.drawable.simpnote_buttombar_notebook_selector,R.drawable.simpnote_buttombar_me_selector};

    final TodoDatabase todoDatabase = new TodoDatabase(this,"TodoList.db",null,7 );

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
        initviews();
//
//        query();
//
//        final EditText editText = findViewById(R.id.edit_todo);
//        ViewTreeObserver viewTreeObserver = editText.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                EditText editText = findViewById(R.id.edit_todo);
//                Rect rect = new Rect();
//                editText.getGlobalVisibleRect(rect);
//                String name = String.valueOf(editText.getText());
//
//                if (rect.bottom==0 && !name.equals("添加ToDo") && !name.equals("")){
//                    insert(name);
//                    Log.d("MainActivity", "添加ToDo");
//
//                    Toast.makeText(MainActivity.this,"tianjiaotodo",Toast.LENGTH_SHORT).show();
//                    query();
//                    Log.d("MainActivity", "edit_hide"+rect.bottom);
//                    editText.setText("添加ToDo");
//                }
//
//        }});
//
//        final ConstraintLayout constraintLayout = findViewById(R.id.cl_root);
//        ViewTreeObserver viewTreeObserverll = constraintLayout.getViewTreeObserver();
//
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
//                    View view = getLayoutInflater().inflate(R.layout.simpnote_fragment_todo_list,null);
////                    view.scrollTo(0,300);
//                    Log.d("MainActivity", "RootLayoutchange"+view.getScrollY());
//
//                }
//
//
//            }
//        });
//
////        if (editText.getWindowVisibility())
////        //弹出软键盘
////        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////            @Override
////            public void onFocusChange(View v, boolean hasFocus) {
////                if(v.isFocused()){
////                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
////                }else {
////                }
////                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
////            }
////        });
//
////        RecyclerView recyclerView = findViewById(R.id.listview);
////        recyclerView.scrollTo(0,700);
////        recyclerView.scrollToPosition(2);
////        recyclerView.setTranslationY(0);


    }

    private void initviews() {
        tab = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new ItemFragment());
        fragments.add(new NoteBookFragment());
        fragments.add(new MeFragment());


        adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        //给ViewPager设置适配器
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(4);
        tab.setTabsFromPagerAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tab.setupWithViewPager(viewpager);
        //设置可以滑动
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewpager.setCurrentItem(position, false);
                switch (position){
                    case 0:
                        query();
//                        TextView textView = findViewById(R.id.tab_text);
//                        textView.setTextColor(0xe500ff00);
                        break;

                    case 1:
//                        TextView textView1 = findViewById(R.id.tab_text);
//                        textView1.setTextColor(0xe500ff00);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tab.getTabCount(); i++) {
            TabLayout.Tab tabs = tab.getTabAt(i);
//            View view = getLayoutInflater().inflate(R.layout.tabhost_tab, null);
//            ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
//            TextView textView = (TextView) view.findViewById(R.id.tab_text);
//            textView.setText(tabTitle[i]);
////            imageView.setImageResource(select[i]);
//            //设置自定义的tab布局
//            tabs.setCustomView(view);
            tabs.setIcon(selectors[i]);
        }

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
    public void insert(String name) {//插入
        SQLiteDatabase dbwriter = todoDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", name);
        values.put("isdeleted","0");
        dbwriter.insert("todo", null, values);
        Log.d("MainActivity", "insert:");
        dbwriter.close();
        datas.add(name);
    }
    public void init(){
        datas.clear();
        Log.d("MainActivity", "init");

        SQLiteDatabase dbreader = todoDatabase.getReadableDatabase();
        Cursor cursor = dbreader.rawQuery(getString(R.string.selectingtodo),
                new String[]{"0"});

        if (cursor.moveToFirst()) {
            do {
                datas.add(cursor.getString(cursor.getColumnIndex("content")));
            } while (cursor.moveToNext());
        }
        RecyclerView View = findViewById(R.id.rv_todo);
        View.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        View.setLayoutManager(linearLayoutManager);

        //设置item事件
        Log.d("MainActivity", "query:"+datas.toString());
        recyclerAdapter = new RecyclerAdapter(datas,todoDatabase) ;

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(View);
        View.setAdapter(recyclerAdapter);
        dbreader.close();
        Log.d("MainActivity", "query:");
    }

    public void query() { //查询
        recyclerAdapter.notifyDataSetChanged();
    }
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
                    db.execSQL("alter table todo add column isdeleted boolean ");
                default:

            }

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table todo( id integer primary key autoincrement,content text,isdeleted integer)");
            db.execSQL("create table notebook( id integer primary key autoincrement,content text,isdeleted integer)");
            Toast.makeText(mContext,"create succeeded",Toast.LENGTH_SHORT).show();
            String[] strings = new String[]{"下拉添加ToDo","左滑删除","右滑提醒"};

            for (int i = 0;i<strings.length;i++){
                ContentValues values = new ContentValues();
                values.put("content", strings[i]);
                values.put("isdeleted","0");
                db.insert("todo", null,values );
            }
        }
    }

}

