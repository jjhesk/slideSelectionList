package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hesk on 21/4/15.
 */
public class Links {
    @SerializedName("self")
    public href self;
    @SerializedName("thumbnail")
    public href thumbnail;
    @SerializedName("categories")
    public ArrayList<href> categories;
    @SerializedName("tags")
    public ArrayList<href> tags;

    public Links() {
    }

    public String getSelf() {
        return self.getHref();
    }

    public String getThumbnail() {
        return thumbnail.getHref();
    }

    public ArrayList<href> getCategories() {
        return categories;
    }

    public ArrayList<href> getTags() {
        return tags;
    }
}
