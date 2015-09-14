package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 22/4/15.
 */
public class underscorelinks {
    @SerializedName("self")
    private Links self;
    @SerializedName("first")
    private Links first;
    @SerializedName("last")
    private Links last;
    @SerializedName("next")
    private Links next;
    @SerializedName("previous")
    private Links previous;

    public underscorelinks() {
    }
}
