package com.hypebeast.sdk.api.model.popbees;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.wprest.post;

/**
 * Created by hesk on 6/7/15.
 */
public class pbpost extends post {
    @SerializedName("popbee_specific")
    public popbeeconfig popbee_specific;
}
