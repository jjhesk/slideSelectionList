package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 2/18/15.
 */
public class Image {
    @SerializedName("id")
    public int id;
    @SerializedName("path")
    public String path;
    @SerializedName("position")
    public int position;
    @SerializedName("_links")
    public ImageV2core data;
}
