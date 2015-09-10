package com.hkm.slideselection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

    /**
     * tells if the first page is just a normal page or not
     *
     * @return the type from the level
     */
    @Override
    protected StringLv FirstConfiguration() {
        return null;
    }

    @Override
    protected SimpleSingleList logicBoard(StringLv con) {
        if (con.isMulti()) {
            return SimpleSingleList.newInstance(SimpleSingleList.stuffs(con.getSelections(), con.getSimpleSource())
            );
        } else {
            return SimpleSingleList.newInstance(SimpleSingleList.stuffs(con.getSelection(), con.getSimpleSource())
            );
        }
    }
}
