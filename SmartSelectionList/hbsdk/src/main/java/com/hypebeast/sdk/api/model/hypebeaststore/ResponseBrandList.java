package com.hypebeast.sdk.api.model.hypebeaststore;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.Alternative;
import com.hypebeast.sdk.api.model.symfony.taxonomy;

import java.util.List;

/**
 * Created by hesk on 30/6/15.
 */
public class ResponseBrandList extends Alternative {
    /**
     * brands
     */
    @SerializedName("brands")
    public List<taxonomy> brands;

    /**
     * allBrands
     */
    @SerializedName("allBrands")
    public List<taxonomy> all;

}
