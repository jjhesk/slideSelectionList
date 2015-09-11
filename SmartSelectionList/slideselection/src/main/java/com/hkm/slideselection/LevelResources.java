package com.hkm.slideselection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 10/9/15.
 */
public abstract class LevelResources<T extends Serializable> {
    final boolean isMultiSelection;
    private int singleSelection = -1;
    private int[] multiSelection;
    private int setOrder = 0;
    protected ArrayList<T> resource = new ArrayList<T>();
    private int selectedAtPos;

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

    public void setOrder(int g) {
        this.setOrder = g;
    }

    public void setResourceData(T[] h) {
        resource.clear();
        final List<T> data = new ArrayList<>();
        for (int i = 0; i < h.length; i++) {
            data.add(h[i]);
        }
        resource.addAll(data);
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

    public abstract T[] getSimpleSource();

    public void setSelectedAtPos(int selectedAtPos) {
        if (!isMultiSelection) {
            this.selectedAtPos = selectedAtPos;
        } else {

        }
    }
}
