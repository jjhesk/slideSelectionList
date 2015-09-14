package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class imagefile {
    @SerializedName("file")
    public String file;
    @SerializedName("width")
    public int w;
    @SerializedName("height")
    public int h;
    @SerializedName("mime-type")
    public String mime;
    @SerializedName("url")
    public String imageURL;
}
