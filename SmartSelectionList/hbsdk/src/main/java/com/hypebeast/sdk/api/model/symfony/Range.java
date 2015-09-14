package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 2/3/15.
 */
public class Range {
    @SerializedName("from")
    private int from = 0;
    @SerializedName("to")
    private int to = 0;
    @SerializedName("count")
    private int count = 0;
    @SerializedName("min")
    private int min = 0;
    @SerializedName("max")
    private int max = 0;
    @SerializedName("total_count")
    private int total_count = 0;
    @SerializedName("total")
    private int total = 0;
    @SerializedName("mean")
    private float mean = 0f;

    public Range() {
    }

    public int getfrom() {
        return from;
    }

    public int getto() {
        return to;
    }

    private String tag;

    public String getCallDisplay() {
        if (from == 0) {
            tag = money(to) + " and under";
        } else if (to == 0) {
            tag = money(from) + " and above";
        } else {
            tag = money(from) + " - " + money(to);
        }
        return tag;
    }

    public TermWrap getTerm() {
        return new TermWrap(getCallDisplay(), count);
    }

    private String money(int n) {
        return "$" + (int) n / 100 + ".00";
    }


}
