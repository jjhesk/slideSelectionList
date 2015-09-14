package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hesk on 2/23/15.
 */
public class RelatedGroups {
    @SerializedName("brands")
    public ArrayList<taxonomy> brands;
}
