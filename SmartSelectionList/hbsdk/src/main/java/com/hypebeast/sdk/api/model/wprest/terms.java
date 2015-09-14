package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hesk on 3/7/15.
 */
public class terms {
    @SerializedName("post_tag")
    public List<term> tags;

    @SerializedName("category")
    public List<term> cates;

    public terms() {
    }
}
