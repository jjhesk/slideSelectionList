package com.tradlulu.demoCollectionList;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hkm.slideselection.V1.DynamicAdapter;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.app.ViewPagerHolder;
import com.hkm.slideselection.worker.bridgeChanger;
import com.tradlulu.demoCollectionList.MyList.basicSupport;


public class MainActivity extends AppCompatActivity implements bridgeChanger {
    SimpleStepSelectionFragment thecontroller;
    Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thecontroller = SimpleStepSelectionFragment.firstLevel(basicSupport.DemoData());
        getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "newA").addToBackStack(null).commit();
        thecontroller.setCallBackListenerBridge(this);
    }


    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        if (thecontroller != null) {
            if (!thecontroller.onPressBack())
                super.onBackPressed();
        } else super.onBackPressed();
    }

    @Override
    public void SelectNow(final ViewPagerHolder pager, final DynamicAdapter mAdapter, final SelectChoice choice) {

        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //  SelectChoice list_end = new SelectChoice(selected);
                choice.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
                mAdapter.levelForward(pager, choice);
            }
        }, 4000);

    }

    @Override
    public void HomeSelect(ViewPagerHolder pager, DynamicAdapter mAdapter, int position) {

    }

}
