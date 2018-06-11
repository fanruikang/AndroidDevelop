package com.example.fanruikang.simpnotes.tool;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fanruikang.simpnotes.R;

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

public class notebookRecyclerAdapter extends RecyclerView.Adapter<notebookRecyclerAdapter.VH>  {
    private List<String> mDatas;

    public notebookRecyclerAdapter(List<String> data) {
        this.mDatas = data;

        Log.d("Recycleview", "adapter:"+mDatas.toString());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simpnote_fragment_notebook_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.title.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("TodoRecyclerAdapter","itemViewOnClick");
//                //item 点击事件
            }

        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(final View v) {
//                LogUtil.d("TodoRecyclerAdapter","itemViewOnLongClick");
////
//                return false;
//            }
//        });
    }



    public static class VH extends RecyclerView.ViewHolder{
        public TextView title;
        VH(View v) {
            super(v);
                LogUtil.d("notebookRecyclerAdapter","viewHolder");
            title = v.findViewById(R.id.book_title);
        }
    }
}
