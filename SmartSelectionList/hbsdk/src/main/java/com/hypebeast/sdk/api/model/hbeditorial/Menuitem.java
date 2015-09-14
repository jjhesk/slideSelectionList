package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 9/6/15.
 */
public class Menuitem {
    @SerializedName("name")
    private String name;
    @SerializedName("display")
    private String display;
    @SerializedName("href")
    private String href;

    public Menuitem() {
    }

    public String getDisplay() {
        return display;
    }

    public String getHref() {
        return href;
    }

    public boolean hasName() {
        return name != null;
    }

    public String getName() {
        return name;
    }
}
