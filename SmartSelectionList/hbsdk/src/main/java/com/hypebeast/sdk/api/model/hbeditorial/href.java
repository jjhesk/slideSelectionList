package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 21/4/15.
 */
public class href {
    @SerializedName("href")
    public String href;
    @SerializedName("name")
    public String name;

    public href() {
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        return name;
    }
}
