package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hesk on 7/1/2015.
 */
public class embededList {
    @SerializedName("products")
    public List<Product> productswrap;
 /*
 since we dont know what to do with the taxon
    @SerializedName("taxon")
    public ResponseProductList productswrap;*/
}
