package com.example.fanruikang.simpnotes.ui;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.LogUtil;
import com.example.fanruikang.simpnotes.tool.RecyclerAdapter;
import com.example.fanruikang.simpnotes.tool.SimpleItemTouchHelperCallback;
import com.example.fanruikang.simpnotes.tool.TabAdapter;
import com.example.fanruikang.simpnotes.tool.TodoDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private TabLayout tab;
    private ViewPager viewpager;
    private TabAdapter adapter;
    private RecyclerAdapter recyclerAdapter;
    private static final int UPDATE_TODOLIST = 1;
    private static final int START_TODOHISTORY=2;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case UPDATE_TODOLIST:
                   RecyclerView View = findViewById(R.id.rv_todo);
                   View.addItemDecoration(new DividerItemDecoration(View.getContext(), LinearLayoutManager.VERTICAL));
                   LinearLayoutManager linearLayoutManager=new LinearLayoutManager(View.getContext());
                   linearLayoutManager.setStackFromEnd(true);
                   linearLayoutManager.setReverseLayout(true);
                   View.setLayoutManager(linearLayoutManager);

                   //设置item事件
                   LogUtil.d("MainActivity", "query:"+datas.toString());
                   recyclerAdapter = new RecyclerAdapter(datas, TodoDatabase) ;

                   //先实例化Callback
                   ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerAdapter);
                   //用Callback构造ItemtouchHelper
                   ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                   //调用ItemTouchHelper的attachToRecyclerView方法建立联系
                   touchHelper.attachToRecyclerView(View);
                   View.setAdapter(recyclerAdapter);
                   break;
           }
        }
    };
    List<String> datas = new ArrayList<String>();
    public static final String[] tabTitle = new String[]{"ToDo", "笔记本","我的"};
//    private int[] selectors = new int[]{R.drawable.simpnote_buttombar_note_selector,R.drawable.simpnote_buttombar_habit_selector,R.drawable.simpnote_buttombar_notebook_selector,R.drawable.simpnote_buttombar_me_selector};
    private int[] selectors = new int[]{R.drawable.simpnote_buttombar_note_selector,R.drawable.simpnote_buttombar_notebook_selector,R.drawable.simpnote_buttombar_me_selector};
    final TodoDatabase TodoDatabase = new TodoDatabase(this,"TodoList.db",null,7 );

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case START_TODOHISTORY:
                if(resultCode == UPDATE_TODOLIST){
                    initDatas();
                    init();
                    break;
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpnote_activity_main);
        initDatas();
        final Toolbar toolbar = findViewById(R.id.simpnote_note_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
//                case R.id.action_search://因为使用android.support.v7.widget.SearchView类，可以在onCreateOptionsMenu(Menu menu)中直接设置监听事件
//                    Snackbar.make(toolbar,"Click Search",Snackbar.LENGTH_SHORT).show();
//                    break;
                    case R.id.action_share:
//                        Snackbar.make(toolbar,"no history",Snackbar.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,TodoHistory.class);
                        startActivityForResult(intent,START_TODOHISTORY);
                        break;
                }
                return true;
            }
        });
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
//                    LogUtil.d("MainActivity", "添加ToDo");
//
//                    Toast.makeText(MainActivity.this,"tianjiaotodo",Toast.LENGTH_SHORT).show();
//                    query();
//                    LogUtil.d("MainActivity", "edit_hide"+rect.bottom);
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
//                LogUtil.d("MainActivity", ""+rect.bottom+"关闭输入法"+screenHeight+name);
//                if((screenHeight==rect.bottom) && ! name.equals("添加ToDo") && ! name.equals("")){
////                    insert(name);
//                    LogUtil.d("MainActivity", "关闭输入法自动保存");
//                    View view = getLayoutInflater().inflate(R.layout.simpnote_fragment_todo_list,null);
////                    view.scrollTo(0,300);
//                    LogUtil.d("MainActivity", "RootLayoutchange"+view.getScrollY());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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
//        RecyclerView View = findViewById(R.id.rv_todo);
//        View.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);
//        linearLayoutManager.setReverseLayout(true);
//        View.setLayoutManager(linearLayoutManager);
//
//        //设置item事件
//        LogUtil.d("MainActivity", "query:"+datas.toString());
//        recyclerAdapter = new RecyclerAdapter(datas,TodoDatabase) ;
//
//        //先实例化Callback
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerAdapter);
//        //用Callback构造ItemtouchHelper
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
//        touchHelper.attachToRecyclerView(View);
//        View.setAdapter(recyclerAdapter);

    }
    private void rightToInsert(){
        EditText editText = findViewById(R.id.edit_todo);
        Rect rect = new Rect();
        editText.getGlobalVisibleRect(rect);
        String name = String.valueOf(editText.getText());

        if (rect.bottom==0 && !name.equals("添加ToDo") && !name.equals("")){
            insert(name);
            LogUtil.d("MainActivity", "添加ToDo");
            editText.setText("添加ToDo");
//            Toast.makeText(MainActivity.this,"tianjiaotodo",Toast.LENGTH_SHORT).show();
            query();
            LogUtil.d("MainActivity", "edit_hide"+rect.bottom);
        }


    }
    public void insert(String name) {//插入
        SQLiteDatabase dbwriter = TodoDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", name);
        values.put("isdeleted","0");
        dbwriter.insert("todo", null, values);
        LogUtil.d("MainActivity", "insert:");
        dbwriter.close();
        datas.add(name);
    }
    public void initDatas(){

                datas.clear();
                SQLiteDatabase dbreader = TodoDatabase.getReadableDatabase();
                Cursor cursor = dbreader.rawQuery(getString(R.string.selectingtodo),
                        new String[]{"0"});

                if (cursor.moveToFirst()) {
                    do {
                        datas.add(cursor.getString(cursor.getColumnIndex(


                                "content")));
                    } while (cursor.moveToNext());
                }

                dbreader.close();
                LogUtil.d("MainActivity", "initDatas");

            }
    public void init(){
        LogUtil.d("MainActivity","Init");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                datas.clear();
//                LogUtil.d("MainActivity", "init");
//
//                SQLiteDatabase dbreader = TodoDatabase.getReadableDatabase();
//                Cursor cursor = dbreader.rawQuery(getString(R.string.selectingtodo),
//                        new String[]{"0"});
//
//                if (cursor.moveToFirst()) {
//                    do {
//                        datas.add(cursor.getString(cursor.getColumnIndex("content")));
//                    } while (cursor.moveToNext());
//                }
//
//                dbreader.close();
//                LogUtil.d("MainActivity", "query:");
//                Message message = new Message();
//                message.what = UPDATE_TODOLIST;
//                handler.sendMessage(message);
//            }
//        }).start();
        RecyclerView View = findViewById(R.id.rv_todo);
        View.addItemDecoration(new DividerItemDecoration(View.getContext(), LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(View.getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        View.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(datas, TodoDatabase) ;

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(View);
        View.setAdapter(recyclerAdapter);
//        Message message = new Message();
//        message.what = UPDATE_TODOLIST;
//        handler.sendMessage(message);
    }

    public void query() { //查询
        recyclerAdapter.notifyDataSetChanged();
    }

}

