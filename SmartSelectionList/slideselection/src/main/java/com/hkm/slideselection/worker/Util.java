package com.hkm.slideselection.worker;

import android.os.Bundle;

import com.hkm.slideselection.V1.SimpleSingleList;

/**
 * Created by hesk on 16/9/15.
 */
public class Util {
    public static final String SELECTION = "selected";
    public static final String DATASTRING = "strings";
    public static final String LEVEL = "mlevel";

    public static SelectChoice fromBundle(Bundle fromData) {
        int[] selection = fromData.getIntArray(SimpleSingleList.SELECTION);
        String[] data = fromData.getStringArray(SimpleSingleList.DATASTRING);
        SelectChoice lv0;
        if (selection.length > 1) {
            lv0 = new SelectChoice(selection);
        } else {
            lv0 = new SelectChoice(selection[0]);
        }
        lv0.setResourceData(data);
        lv0.setLevel(0);
        return lv0;
    }

    public static Bundle stuffs(SelectChoice option) {
        Bundle b = new Bundle();
        int[] single_selection = new int[]{option.getSelection()};
        b.putIntArray(SELECTION, single_selection);
        b.putStringArray(DATASTRING, option.getSimpleSource());
        b.putInt(LEVEL, option.getLevel());
        return b;
    }

    public static Bundle stuffs(int selection, String[] list, int order) {
        Bundle b = new Bundle();
        int[] single_selection = new int[]{selection};
        b.putIntArray(SELECTION, single_selection);
        b.putStringArray(DATASTRING, list);
        b.putInt(LEVEL, order);
        return b;
    }

    public static Bundle stuffs(int[] selections, String[] list, int order) {
        Bundle b = new Bundle();
        b.putIntArray(SELECTION, selections);
        b.putStringArray(DATASTRING, list);
        b.putInt(LEVEL, order);
        return b;
    }

}
