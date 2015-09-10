package com.hkm.slideselection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 10/9/15.
 */
public class LevelResources<T> {
    final boolean isMultiSelection;
    private int singleSelection = -1;
    private int[] multiSelection;

    private ArrayList<T> resource = new ArrayList<>();

    public LevelResources() {
        isMultiSelection = false;
    }

    public LevelResources(int selection) {
        isMultiSelection = false;
        singleSelection = selection;
    }

    public LevelResources(int[] selections) {
        isMultiSelection = true;
        multiSelection = selections;
    }

    public void setResourceData(List<T> data) {
        resource.clear();
        resource.addAll(data);
    }

    public boolean isMulti() {
        return isMultiSelection;
    }

    public int getSelection() {
        return singleSelection;

    }

    public int[] getSelections() {
        return multiSelection;
    }

    public T[] getSimpleSource() {
        return (T[]) resource.toArray();
    }
}
