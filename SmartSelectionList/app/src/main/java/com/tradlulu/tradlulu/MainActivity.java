package com.tradlulu.tradlulu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.hkm.slideselection.StringLv;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SimpleStepSelectionFragment thecontroller;
    StringLv lv0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv0 = new StringLv(-1);
        List<String> data = new ArrayList<>();
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
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "newA").addToBackStack(null).commit();
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
        super.onBackPressed();
        if (thecontroller != null)
            thecontroller.onPressBack();
    }
}
