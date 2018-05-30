package com.example.fanruikang.simpnotes.tool;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/22 0022 17:10
 * 修改人：FanRuikang
 * 修改时间：2018/5/22 0022 17:10
 * 修改备注：
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        mAdapter = adapter;
        LogUtil.d("SimpleItemTouchHelperCallback","New");
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        LogUtil.d("SimpleItemTouchHelperCallback","getMovementFlags");
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//        int swipeFlags = ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.RIGHT ;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        LogUtil.d("SimpleItemTouchHelperCallback","isLongPressDragEnabled");
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        LogUtil.d("SimpleItemTouchHelperCallback","isItemSwipeEnabled");
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        LogUtil.d("SimpleItemTouchHelperCallback","Onmove");
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        LogUtil.d("SimpleItemTouchHelperCallback","OnSwiped");
        mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }
}

