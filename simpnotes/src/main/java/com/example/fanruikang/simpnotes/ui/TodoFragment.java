package com.example.fanruikang.simpnotes.ui;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class TodoFragment extends Fragment {
    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    private TextView txt;
    private int type;

    public static TodoFragment newInstance(int type) {
        TodoFragment fragment = new TodoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.d("TodoFragment","onCreate");

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
        LogUtil.d("TodoFragment","onCreateView");
            View view = inflater.inflate(R.layout.simpnote_fragment_todo_list,container,false);
    return view;
    }

    @Override
    public void onStart() {
        LogUtil.d("TodoFragment","onStart");
        super.onStart();
        final MainActivity mainActivity = (MainActivity) getActivity();
        Log.d("TodoFragment", "onStart");
        mainActivity.init();
        final EditText editText = (EditText) getView().findViewById(R.id.edit_todo);
        ViewTreeObserver viewTreeObserver = editText.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                EditText editText = (EditText) getView().findViewById(R.id.edit_todo);
                Rect rect = new Rect();
                editText.getGlobalVisibleRect(rect);
                String name = String.valueOf(editText.getText());
                Log.d("TodoFragment.onStart", "viewTreeObserver");
                Log.d("TodoFragment.onStart", "viewTreeObserver"+name);
                Log.d("TodoFragment.onStart", "viewTreeObserver"+rect.bottom);

                if (rect.bottom<50 && !name.equals("添加ToDo") && !name.equals("")){
                    Log.d("TodoFragment", "添加");
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.insert(name);

                    Toast.makeText(mainActivity,"tianjiaotodo",Toast.LENGTH_SHORT).show();
                    mainActivity.query();
                    Log.d("TodoFragment", "edit_hide"+rect.bottom);
                    editText.setText("添加ToDo");
                }
            }});
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
        LogUtil.d("TodoFragment","isVisibleToUser");
            onResume();
        } else if (!isVisibleToUser) {
        LogUtil.d("TodoFragment","isNotVisibleToUser");
            onPause();
        }
    }

    @Override
    public void onPause() {
        LogUtil.d("TodoFragment","onPause");
        super.onPause();

    }

    @Override
    public void onResume() {
        LogUtil.d("TodoFragment","onResume");
        super.onResume();
        if (getUserVisibleHint()) {
            //TODO give the signal that the fragment is visible
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbar_status(0);
            mainActivity.invalidateOptionsMenu();
        }
    }
}
