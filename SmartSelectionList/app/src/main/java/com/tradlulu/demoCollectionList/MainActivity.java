package com.tradlulu.demoCollectionList;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.StringLv;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.bridgeChanger;
import com.tradlulu.demoCollectionList.MyList.basicSupport;


public class MainActivity extends AppCompatActivity implements bridgeChanger {
    SimpleStepSelectionFragment thecontroller;
    Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thecontroller = SimpleStepSelectionFragment.firstLevel(basicSupport.getListMain());
        getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "newA").addToBackStack(null).commit();
        thecontroller.setCallBackListenerBridge(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public void SelectNow(final NonSwipe pager, final DynamicAdapter mAdapter, final int selected, final int level_now, String title_selected) {

        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StringLv list_end = new StringLv(selected);
                list_end.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
                mAdapter.levelForward(pager, list_end);
            }
        }, 4000);

    }
}
