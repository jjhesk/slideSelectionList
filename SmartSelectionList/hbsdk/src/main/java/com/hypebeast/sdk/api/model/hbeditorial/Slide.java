package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 1/9/15.
 */
public class Slide {
    @SerializedName("image")
    public String image;
    @SerializedName("text")
    public String text;
    @SerializedName("href")
    public String href;

    public Slide() {
    }
}
