package com.example.fanruikang.simpnotes.tool;

/**
 * 项目名称：AndroidDevelop
 * 类描述：recycle的item删除和交换位置，参考：https://blog.csdn.net/a553181867/article/details/54799391
 * 创建人：FanRuikang
 * 创建时间：2018/5/22 0022 17:01
 * 修改人：FanRuikang
 * 修改时间：2018/5/22 0022 17:01
 * 修改备注：
 */

public interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(int fromPosition,int toPosition);
    //数据删除
    void onItemDissmiss(int position);
}
