package com.tradlulu.demoCollectionList.MyList;

import android.support.v7.app.AppCompatActivity;

import com.hkm.slideselection.StringLv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 11/9/15.
 */
public class basicSupport {

    public static StringLv getListMain() {
        StringLv lv0 = new StringLv(-1);
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
        lv0.setOrder(0);
        return lv0;
    }
}
