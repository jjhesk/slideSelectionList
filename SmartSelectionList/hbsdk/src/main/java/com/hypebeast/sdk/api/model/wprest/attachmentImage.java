package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class attachmentImage {
    @SerializedName("width")
    public int w;
    @SerializedName("height")
    public int h;
    @SerializedName("file")
    public String file;
    @SerializedName("sizes")
    public sizes msizes;
}
