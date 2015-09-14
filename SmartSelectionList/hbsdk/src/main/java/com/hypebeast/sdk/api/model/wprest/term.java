package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class term {
    @SerializedName("ID")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("slug")
    public String slug;
    @SerializedName("description")
    public String desc;
    @SerializedName("taxonomy")
    public String taxonomy;
    @SerializedName("count")
    public int count;
    @SerializedName("link")
    public String link;
    @SerializedName("meta")
    public meta mmeta;
    @SerializedName("parent")
    public term mparent;

    public term() {
    }

    public boolean hasParent() {
        return mparent != null;
    }
}
