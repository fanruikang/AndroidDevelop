package com.example.fanruikang.simpnotes.tool;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.ui.MainActivity;

import java.util.Collections;
import java.util.List;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/15 0015 14:55
 * 修改人：FanRuikang
 * 修改时间：2018/5/15 0015 14:55
 * 修改备注：
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> implements ItemTouchHelperAdapter {
    private List<String> mDatas;
    private MainActivity.TodoDatabase mDatabase;
    public RecyclerAdapter(List<String> data, MainActivity.TodoDatabase Database) {
        this.mDatas = data;
        this.mDatabase = Database;
        Log.d("Recycleview", "adapter:"+mDatas.toString());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simpnote_fragment_item_note, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.title.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //item 点击事件
//                TextView textView = (TextView)v.findViewById(R.id.content);
//                textView.getPaint().setFlags((textView.getPaint().getFlags() == Paint.STRIKE_THRU_TEXT_FLAG) ? 0
//                        : Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(mDatas,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        ContentValues values = new ContentValues();
        values.put("isdeleted",1);

        SQLiteDatabase dbwriter=mDatabase.getWritableDatabase();
        dbwriter.update("todo", values,"content =?",new String[]{mDatas.get(position)});
        dbwriter.close();


//        boolean isDeleteAble = true;//初始值为true,当点击删除按钮以后，休息0.5秒钟再让他为true,起到让数据源刷新完成的作用
//        if (isDeleteAble) {//此时为增加动画效果，刷新部分数据源，防止删除错乱
//         isDeleteAble = true;//初始值为true,当点击删除按钮以后，休息0.5秒钟再让他为true,起到让数据源刷新完成的作用
        Log.d("Recycleview", "delete:"+position);
        Log.d("Recycleview", "delete:"+mDatas.toString());

        Log.d("Recycleview", "delete:"+mDatas.get(position));
            mDatas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mDatas.size()-position);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(500);//休息
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        //可点击按钮
//                        isDeleteAble = true;
//                    }
//
//                }
//            }).start();

//        }
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.content);
        }
    }
}
