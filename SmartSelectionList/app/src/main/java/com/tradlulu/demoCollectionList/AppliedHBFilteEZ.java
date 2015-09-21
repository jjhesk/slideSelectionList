package com.tradlulu.demoCollectionList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tradlulu.demoCollectionList.Module.HBFilterEZHelper;

/**
 * Created by hesk on 14/9/15.
 */
public class AppliedHBFilteEZ extends AppCompatActivity {

    private static HBFilterEZHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new HBFilterEZHelper(this);
        helper.init();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        if (!helper.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
