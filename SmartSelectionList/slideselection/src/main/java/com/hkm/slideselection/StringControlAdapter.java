package com.hkm.slideselection;


import android.app.Fragment;
import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 10/9/15.
 */
public class StringControlAdapter extends DynamicAdapter<StringLv> {

    public StringControlAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public StringControlAdapter(FragmentManager fragmentManager, Fragment firstPage) {
        super(fragmentManager, firstPage);
    }

    public StringLv extractBundle() {
        if (firstPage != null && firstPage.getArguments() != null) {
            int[] selection = firstPage.getArguments().getIntArray(SimpleSingleList.SELECTION);
            String[] data = firstPage.getArguments().getStringArray(SimpleSingleList.DATASTRING);
            StringLv lv0;
            if (selection.length > 1) {
                lv0 = new StringLv(selection);
            } else {
                lv0 = new StringLv(selection[0]);
            }
            lv0.setResourceData(data);
            return lv0;
        }
        return null;
    }

    /**
     * tells if the first page is just a normal page or not
     *
     * @return the type from the level
     */
    @Override
    protected StringLv FirstConfiguration() {
        StringLv lv0 = new StringLv(-1);
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
        return lv0;
    }

    @Override
    protected SimpleSingleList logicBoard(StringLv con) {
        if (con.isMulti()) {
            return SimpleSingleList.newInstance(SimpleSingleList.stuffs(con.getSelections(), con.getSimpleSource()));
        } else {
            return SimpleSingleList.newInstance(SimpleSingleList.stuffs(con.getSelection(), con.getSimpleSource()));
        }
    }
}
