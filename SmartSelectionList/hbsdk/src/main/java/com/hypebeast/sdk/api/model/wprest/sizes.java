package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class sizes {
    @SerializedName("thumbnail")
    public imagefile thumb;
    @SerializedName("medium")
    public imagefile med;
}
