package com.hypebeast.sdk.api.model.symfony;


import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.itemDisplay;

/**
 * Created by hesk on 2/23/15.
 */
public class taxonomy extends itemDisplay {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String taxon_name;
    @SerializedName("slug")
    private String slug;
/*    @SerializedName("permalink")
    private String permalink;*/

    @SerializedName("description")
    private String description;

    public taxonomy() {
    }

    public String getcodename() {
        return taxon_name;
    }

    public String getslug() {
        return slug;
    }

    @Override
    public String toString() {
        return taxon_name;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
