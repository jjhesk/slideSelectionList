package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 2/23/15.
 */
public class VarientContent {
    @SerializedName("option")
    public VariantItem option;
    @SerializedName("value")
    public String value;

}
