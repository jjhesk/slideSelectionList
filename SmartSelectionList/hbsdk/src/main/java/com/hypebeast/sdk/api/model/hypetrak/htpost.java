package com.hypebeast.sdk.api.model.hypetrak;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.wprest.post;

/**
 * Created by hesk on 8/9/15.
 */
public class htpost extends post {
    @SerializedName("hypetrak_specific")
    public hypetrakconfig hypetrak;
}
