package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 15/7/15.
 */
public class sliderItem {
    @SerializedName("image")
    public String image;
    @SerializedName("url")
    public String url;
    @SerializedName("post_id")
    public long post_pid;
}
