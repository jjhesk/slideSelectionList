package com.hypebeast.sdk.api.model;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.symfony.ErrorWrap;

/**
 * Created by hesk on 30/6/15.
 */
public abstract class Alternative {
    @SerializedName("error")
    public ErrorWrap error;
}
