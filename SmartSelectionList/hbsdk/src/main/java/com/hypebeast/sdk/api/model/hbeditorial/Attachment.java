package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 29/4/15.
 */
public class Attachment {
    @SerializedName("full")
    private String fullattachment;

    public Attachment() {
    }

    public String getFull() {
        return fullattachment;
    }
}
