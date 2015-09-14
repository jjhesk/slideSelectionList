package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 2/23/15.
 */
public class ImageV2core {
    @SerializedName("full")
    public LinkContainer full;
    @SerializedName("small")
    public LinkContainer small;
    @SerializedName("medium")
    public LinkContainer medium;
    @SerializedName("large")
    public LinkContainer large;
}
