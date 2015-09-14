package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class link {
    @SerializedName("collection")
    public String mcollection;
    @SerializedName("self")
    public String self;
    @SerializedName("archives")
    public String marchives;
}
