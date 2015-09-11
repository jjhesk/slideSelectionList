package com.hkm.slideselection;

/**
 * Created by hesk on 10/9/15.
 */
public class StringLv extends LevelResources<String> {
    public StringLv(int selection) {
        super(selection);
    }

    public StringLv(int[] selection) {
        super(selection);
    }

    public String[] getSimpleSource() {
        String[] stockArr = new String[resource.size()];
        stockArr = resource.toArray(stockArr);
        return stockArr;
    }
}
