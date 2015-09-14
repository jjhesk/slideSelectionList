package com.hypebeast.sdk.api.model.symfony;


import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 15/4/15.
 */
public class Attributes {
    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public int id;
    @SerializedName("value")
    public String value;
}
