package com.hypebeast.sdk.api.model.symfony;


import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.itemDisplay;

/**
 * Created by hesk on 2/3/15.
 */
public class TermWrap extends itemDisplay {
    @SerializedName("term")
    private String term = "";
    @SerializedName("count")
    private int count = 0;

    /**
     * end json
     */
    public TermWrap() {
    }

    public TermWrap(String t) {
        term = t;
    }

    public TermWrap(String t, int c) {
        count = c;
        term = t;
    }

    public String theTerm() {
        return term;
    }

    @Override
    public String toString() {
        return term;
    }

    @Override
    public boolean isEnabled() {
        return count > 0;
    }

    public int getTheCount() {
        return count;
    }
}
