package com.example.fanruikang.simpnotes.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.LogUtil;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/13 0013 16:03
 * 修改人：FanRuikang
 * 修改时间：2018/5/13 0013 16:03
 * 修改备注：
 */

public class MeFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.d("MeFragment","onCreate");
        super.onCreate(savedInstanceState);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            LogUtil.d("MeFragment","onCreateView");
            View view = inflater.inflate(R.layout.simpnote_fragment_me,container,false);
    return view;
    }

    @Override
    public void onStart() {
        LogUtil.d("MeFragment","onStart");

        super.onStart();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            LogUtil.d("MeFragment","isVisibleToUser");
            onResume();
        } else if (!isVisibleToUser) {
            LogUtil.d("MeFragment","isNotVisibleToUser");
            onPause();
        }
    }

    @Override
    public void onPause() {
        LogUtil.d("MeFragment","onPause");
        super.onPause();

    }

    @Override
    public void onResume() {
        LogUtil.d("MeFragment","onResume");
        super.onResume();
        if (getUserVisibleHint()) {
            //TODO give the signal that the fragment is visible
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbar_status(2);
            mainActivity.invalidateOptionsMenu();
        }
    }
}
