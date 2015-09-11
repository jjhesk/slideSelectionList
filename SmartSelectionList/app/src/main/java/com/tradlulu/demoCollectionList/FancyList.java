package com.tradlulu.demoCollectionList;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintImageView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.StringLv;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.bridgeChanger;
import com.tradlulu.demoCollectionList.MyList.basicSupport;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by hesk on 11/9/15.
 */
public class FancyList extends AppCompatActivity implements bridgeChanger {
    private SimpleStepSelectionFragment thecontroller;
    private StringLv lv0;
    private Handler uiHandler = new Handler();
    private TintImageView back;
    private ProgressBar mProgress;
    private TextView title_navigation;
    private boolean isInProgress = false;
/*
    @Bind(R.id.back_level)
    TintImageView back;

    @Bind(R.id.ui_loading_progress_bar_xx)
    ProgressBar mProgress;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife.bind(this);
        setContentView(R.layout.fancy_1);
        thecontroller = SimpleStepSelectionFragment.firstLevel(basicSupport.getListMain());
        bindothers();
        getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "newA").addToBackStack(null).commit();
        thecontroller.setCallBackListenerBridge(this);
        inProgressDone();
    }

    private void bindothers() {
        back = (TintImageView) findViewById(R.id.back_level);
        title_navigation = (TextView) findViewById(R.id.title_navigation);
        mProgress = (ProgressBar) findViewById(R.id.ui_loading_progress_bar_xx);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thecontroller != null && !isInProgress) {
                    thecontroller.onPressBack();
                }
            }
        });
    }

    private void inProgress() {
        mProgress.animate().alpha(1f);
        isInProgress = true;
    }

    private void inProgressDone() {
        mProgress.animate().alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                isInProgress = false;
            }
        });
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        if (thecontroller != null) {
            if (!isInProgress && !thecontroller.onPressBack())
                super.onBackPressed();
        } else super.onBackPressed();
    }

    @Override
    public void SelectNow(final NonSwipe pager, final DynamicAdapter mAdapter, final int selected, final int level_now, final String eles) {
        if (!isInProgress) {
            inProgress();
            title_navigation.setText(eles);
            uiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    StringLv list_end = new StringLv(selected);
                    list_end.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
                    mAdapter.levelForward(pager, list_end);
                    inProgressDone();
                }
            }, 800);
        }

    }


}
