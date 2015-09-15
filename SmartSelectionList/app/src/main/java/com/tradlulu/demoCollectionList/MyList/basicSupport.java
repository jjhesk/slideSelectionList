package com.tradlulu.demoCollectionList.MyList;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.SelectChoice;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.model.symfony.FilterGroup;
import com.hypebeast.sdk.api.resources.hbstore.Products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hesk on 11/9/15.
 */
public class basicSupport {


    public static SelectChoice DemoData() {
        SelectChoice lv0 = new SelectChoice(false);
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
        lv0.setLevel(0);

        return lv0;
    }
}
