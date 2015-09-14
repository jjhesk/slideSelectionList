package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hesk on 2/23/15.
 */
public class connection {
    @SerializedName("self")
    public LinkContainer self;
    @SerializedName("brand")
    public LinkContainer brand;
    @SerializedName("group_products")
    public ArrayList<ProductGroupContainer> group_products = new ArrayList<>();

}
