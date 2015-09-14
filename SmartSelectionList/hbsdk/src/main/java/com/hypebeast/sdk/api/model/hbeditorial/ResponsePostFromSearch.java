package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 1/9/15.
 */
public class ResponsePostFromSearch {
    @SerializedName("posts")
    public PostsObject posts;
    @SerializedName("keyword")
    public String keyword_search;
}
