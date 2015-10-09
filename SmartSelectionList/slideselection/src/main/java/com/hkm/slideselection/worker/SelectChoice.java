package com.hkm.slideselection.worker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hesk on 10/9/15.
 */
public class SelectChoice extends LevelResources<String> {
    public enum ListType {
        MAINFILTER,
        FILTER_ITEM
    }

    private String choice_selection;

    public String selected_string() {
        return choice_selection;
    }

    @Override
    public void setSelectedAtPos(int selectedAtPos) {
        super.setSelectedAtPos(selectedAtPos);
        this.choice_selection = resource.get(selectedAtPos);
    }

    public SelectChoice(int selection) {
        super(selection);
    }

    public SelectChoice(boolean selected, String TAG) {
        super(selected, TAG);
    }

    public SelectChoice(boolean selected) {
        super(selected);
    }

    public SelectChoice(int[] selection) {
        super(selection);
    }

    public String[] getSimpleSource() {
        String[] stockArr = new String[resource.size()];
        stockArr = resource.toArray(stockArr);
        return stockArr;
    }

    public ArrayList<String> getSource() {
        return resource;
    }

    public String toString() {
        return "";
    }


    public SelectChoice clone() {
        if (isMultiSelection) {
            return new SelectChoice(getSelections());
        } else {
            return new SelectChoice(getSelection());
        }

    }

    public int getFromSubFilter(SelectChoice sub) {
        String selected_item = sub.selected_string();
        Iterator<String> k = resource.iterator();
        int u = 0;
        while (k.hasNext()) {
            String e = k.next();
            if (e.equalsIgnoreCase(selected_item)) {
                return u;
            }
            u++;
        }

        return 0;
    }
}
