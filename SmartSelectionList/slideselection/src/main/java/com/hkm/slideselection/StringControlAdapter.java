package com.hkm.slideselection;

import android.app.FragmentManager;

/**
 * Created by hesk on 10/9/15.
 */
public class StringControlAdapter extends DynamicAdapter<StringLv> {

    public StringControlAdapter(FragmentManager fragmentManager, StringLv configuration) {
        super(fragmentManager, configuration);
    }

    public StringControlAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
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
        return firstPageListConfiguration;
    }

    @Override
    protected SimpleSingleList logicBoard(StringLv con) {
        if (con.isMulti()) {
            return SimpleSingleList.newInstance(con.getSelections(), con.getSimpleSource());
        } else {
            return SimpleSingleList.newInstance(con.getSelection(), con.getSimpleSource());
        }
    }
}
