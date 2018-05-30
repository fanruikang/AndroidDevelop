package com.example.fanruikang.simpnotes.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanruikang.simpnotes.R;
import com.example.fanruikang.simpnotes.tool.ActivityCollector;
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

public class NoteBookFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
            View view = inflater.inflate(R.layout.simpnote_fragment_notebook,container,false);
    return view;
    }

    @Override
    public void onStart() {
        super.onStart();
       ;


    }

    public static class BaseActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LogUtil.d("runinfo",getClass().getSimpleName());
            ActivityCollector.addActivity(this);
        }
        @Override
        protected void onStart() {
            super.onStart();
            LogUtil.d("runinfo", getClass().getSimpleName()+"onStart");
        }

        @Override
        protected void onStop() {
            super.onStop();
            LogUtil.d("funinfo", getClass().getSimpleName()+"onStop");
        }
            @Override
            protected void onDestroy () {
                super.onDestroy();
                LogUtil.d("runinfo", getClass().getSimpleName()+"onDestroy");
                ActivityCollector.removeActivity(this);
            }


            /**
             * Dispatch onResume() to fragments.  Note that for better inter-operation
             * with older versions of the platform, at the point of this call the
             * fragments attached to the activity are <em>not</em> resumed.  This means
             * that in some cases the previous state may still be saved, not allowing
             * fragment transactions that modify the state.  To correctly interact
             * with fragments in their proper state, you should instead override
             * {@link #onResumeFragments()}.
             */
            @Override
            protected void onResume () {
                super.onResume();
                LogUtil.d("runinfo", getClass().getSimpleName()+"onResume");
            }

        /**
         * Dispatch onPause() to fragments.
         */
        @Override
        protected void onPause() {
            super.onPause();
            LogUtil.d("runinfo",getClass().getSimpleName()+"onPause");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            LogUtil.d("runinfo",getClass().getSimpleName()+"onRestart");
        }

        /**
         * Take care of popping the fragment back stack or finishing the activity
         * as appropriate.
         */
        @Override
        public void onBackPressed() {
            super.onBackPressed();
            ActivityCollector.finishAll();
        }
    }
}
