package com.example.fanruikang.simpnotes.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/15 0015 10:51
 * 修改人：FanRuikang
 * 修改时间：2018/5/15 0015 10:51
 * 修改备注：
 */

public class TodoLinerLayout extends LinearLayout implements NestedScrollingParent{
//    private ViewDragHelper mDragHelper;

    int editTextHeight;
    EditText editText ;
    public TodoLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtil.d("TodoLinerLayout","oninit");

//        this.getViewTreeObserver();
//        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
//            @Override
//            public boolean tryCaptureView(View child, int pointerId) {
//                return true;
//            }
//
//            @Override
//            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                return   left-dx;
//            }
//
//            @Override
//            public int clampViewPositionVertical(View child, int top, int dy) {
//                return top;
//            }
//        });

    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed)
    {
        boolean hiddenTop = dy > 0 && getScrollY() < 300;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop)
        {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onStopNestedScroll(View child) {

        if (getScrollY()>(editTextHeight/5)) {
            LogUtil.d("TodoLinerLayout","onhide");
            mhide();
        }else {
            LogUtil.d("TodoLinerLayout","onshou");
            mshow();
        }
        LogUtil.d("TodoLinerLayout","onstop");
        super.onStopNestedScroll(child);


    }
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return mDragHelper.shouldInterceptTouchEvent(ev);
//
//
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mDragHelper.processTouchEvent(event);
//        return true;
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        LogUtil.d("TodoLinerLayout","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LogUtil.d("TodoLinerLayout","Onlayout: "+this.getHeight());
        //取消选中edit_todo
        EditText editText = findViewById(R.id.edit_todo);
        editText.clearFocus();
        String name = String.valueOf(editText.getText());
        editTextHeight = editText.getHeight();
        if( !name.equals("添加ToDo") && ! name.equals("")){
            mhide();
        }
        //增加rv_todo高度
        TodoView todoView = findViewById(R.id.rv_todo);
        todoView.getLayoutParams().height=this.getHeight();


    }
    public void mhide(){
        EditText editText = findViewById(R.id.edit_todo);
        scrollTo(0, editTextHeight);
        editText.clearFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               EditText editText = findViewById(R.id.edit_todo);
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.hideSoftInputFromWindow(editText.getWindowToken(),0);

                           }
                       },
                1);
    }

    public void mshow(){
        LogUtil.d("TodoLinerLayout","showed");
        EditText editText = findViewById(R.id.edit_todo);
        LogUtil.d("TodoLinerLayout",""+editText.getText().toString());
        if (editText.getText().toString().equals("添加ToDo")||editText.getText().toString().equals(""))
        {

            editText.setText("");
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               EditText editText = findViewById(R.id.edit_todo);
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(editText, 0);
                               editText.requestFocus();
                               scrollTo(0, 0);
                           }
                       },
                90);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        LogUtil.d("TodoLinerLayout","ondraw");
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_UP:
//                LogUtil.d("TodoLinerLayout","ondraw"+todoView.getChildAt(0).getY());
//                if (todoView.canScrollVertically(1)==false && todoView.getChildAt(0).getY()>(editText.getHeight()/2)){
//                    this.setScrollY(300);
//                }
//        }
//        return super.onTouchEvent(event);
//    }
}
