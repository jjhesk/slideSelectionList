package com.hkm.slideselection.worker;

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


    public String toString() {
        return "";
    }
}
