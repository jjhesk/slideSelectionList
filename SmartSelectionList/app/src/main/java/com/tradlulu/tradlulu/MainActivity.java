package com.tradlulu.tradlulu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.hkm.slideselection.app.SimpleStepSelectionFragment;

public class MainActivity extends AppCompatActivity {
    SimpleStepSelectionFragment thecontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thecontroller = (SimpleStepSelectionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
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
