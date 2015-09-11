package com.tradlulu.tradlulu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.StringLv;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.bridgeChanger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements bridgeChanger {
    SimpleStepSelectionFragment thecontroller;
    StringLv lv0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv0 = new StringLv(-1);
        final List<String> data = new ArrayList<>();
        data.add("one1");
        data.add("one2");
        data.add("one3");
        data.add("one4");
        data.add("one5");
        data.add("one6");
        data.add("one7");
        data.add("one8");
        data.add("one9");
        data.add("one10");
        lv0.setResourceData(data);
        // thecontroller = (SimpleStepSelectionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        thecontroller = SimpleStepSelectionFragment.firstLevel(lv0);
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
    public void SelectNow(NonSwipe pager, DynamicAdapter mAdapter, int selected, int level_now) {
        StringLv list_end = new StringLv(selected);
        list_end.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
        mAdapter.levelForward(pager, list_end);
        /*  StringLv hb = new StringLv(selected);
            hb.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
            adapter.levelForward(mViewPager, hb);*/
    }
}
