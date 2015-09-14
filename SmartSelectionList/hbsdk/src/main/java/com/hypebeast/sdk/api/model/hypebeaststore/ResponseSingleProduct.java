package com.hypebeast.sdk.api.model.hypebeaststore;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.Alternative;
import com.hypebeast.sdk.api.model.symfony.Product;

/**
 * Created by hesk on 7/1/2015.
 */
public class ResponseSingleProduct extends Alternative {
    @SerializedName("product")
    public Product mproduct;
}
