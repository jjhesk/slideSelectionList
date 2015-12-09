package com.tradlulu.demoCollectionList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hkm.slideselection.V1.DynamicAdapter;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.app.ViewPagerHolder;
import com.hkm.slideselection.worker.bridgeChanger;
import com.tradlulu.demoCollectionList.MyList.basicSupport;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hesk on 11/9/15.
 */
public class FancyList extends AppCompatActivity implements bridgeChanger {
    private SimpleStepSelectionFragment thecontroller;
    private SelectChoice lv0;
    private Handler uiHandler = new Handler();
    private ImageButton back;
    private ProgressBar mProgress;
    private TextView title_navigation;
    private boolean isInProgress = false;
    private ArrayList<SelectChoice> selection_memory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife.bind(this);
        setContentView(R.layout.fancy_1);
        thecontroller = SimpleStepSelectionFragment.firstLevel(basicSupport.DemoData());
        bindothers();
        getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "newA").addToBackStack(null).commit();
        thecontroller.setCallBackListenerBridge(this);
        inProgressDone();
    }

    private void bindothers() {
        back = (ImageButton) findViewById(R.id.back_level);
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

    private boolean inList(String selected) {
        Iterator<SelectChoice> io = selection_memory.iterator();
        while (io.hasNext()) {
            SelectChoice mSelect = io.next();
            if (mSelect.isTag(selected)) {
                return true;
            }
        }
        return false;
    }

    private void getlist(final String selected, final ViewPagerHolder pager, final DynamicAdapter mAdapter) {
        Iterator<SelectChoice> io = selection_memory.iterator();
        while (io.hasNext()) {
            SelectChoice mSelect = io.next();
            if (mSelect.isTag(selected)) {
                mAdapter.levelForward(pager, mSelect);
                inProgressDone();
                return;
            }
        }
        /**
         * mock run async
         */
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SelectChoice list_end = new SelectChoice(false, selected);
                list_end.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
                mAdapter.levelForward(pager, list_end);
                inProgressDone();
            }
        }, 800);

    }

    @Override
    public void SelectNow(final ViewPagerHolder pager, final DynamicAdapter mAdapter, SelectChoice choice) {
        if (!isInProgress) {
            if (choice.getLevel() == 1) {
                if (!inList(choice.selected_string())) {
                    try {
                        selection_memory.add((SelectChoice) mAdapter.getCurrentLVObject());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.levelBack(pager);
            } else {
                inProgress();
                title_navigation.setText(choice.selected_string());
                getlist(choice.selected_string(), pager, mAdapter);
            }
        }
    }

    @Override
    public void HomeSelect(ViewPagerHolder pager, DynamicAdapter mAdapter, int position) {

    }


}
