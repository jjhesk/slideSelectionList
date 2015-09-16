package com.hkm.slideselection.worker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 10/9/15.
 */
public abstract class LevelResources<T extends Serializable> {
    public final boolean isMultiSelection;
    private int singleSelection = -1;
    private int[] multiSelection;
    private int level_list = 0;
    protected ArrayList<T> resource = new ArrayList<T>();
    private int selectedAtPos;
    private String tag;

    public static final String TAG = "base";

    public LevelResources() {
        isMultiSelection = false;
    }

    /**
     * creating a new level resource
     *
     * @param isMultiSelection multi support
     * @param Tag              tag name
     */
    public LevelResources(boolean isMultiSelection, String Tag) {
        this.isMultiSelection = isMultiSelection;
        this.tag = Tag;
        if (isMultiSelection) {
            multiSelection = new int[]{-1};
        } else {
            singleSelection = -1;
        }
    }

    /**
     * creating a new level resource
     *
     * @param isMultiSelection multi support
     */
    public LevelResources(boolean isMultiSelection) {
        this.isMultiSelection = isMultiSelection;
        this.tag = TAG;
        if (isMultiSelection) {
            multiSelection = new int[]{-1};
        } else {
            singleSelection = -1;
        }
    }

    /**
     * loading the previous data
     *
     * @param selection with the selection
     */
    public LevelResources(int selection) {
        isMultiSelection = false;
        singleSelection = selection;
    }

    /**
     * loading the previous  data
     *
     * @param selections with the selection
     */
    public LevelResources(int[] selections) {
        isMultiSelection = true;
        multiSelection = selections;
    }

    public boolean isTag(String name) {
        return tag == null ? false : name.equalsIgnoreCase(tag) || name.startsWith(tag);
    }

    public String getTag() {
        return tag;
    }

    /**
     * the level number
     *
     * @param g the number supposed to show the level
     */
    public void setLevel(int g) {
        this.level_list = g;
    }

    public int getLevel() {
        return level_list;
    }

    public void setResourceData(T[] h) {
        resource.clear();
        //  final List<T> data = new ArrayList<>();
        for (int i = 0; i < h.length; i++) {
            resource.add(h[i]);
        }
        //resource.addAll(data);
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
