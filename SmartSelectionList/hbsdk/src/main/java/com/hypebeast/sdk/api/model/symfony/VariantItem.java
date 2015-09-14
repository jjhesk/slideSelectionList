package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 2/23/15.
 */
public class VariantItem {
    enum VType {
        Size,
        Color
    }

    @SerializedName("name")
    public VType name;
    @SerializedName("presentation")
    public String presentation;

}
