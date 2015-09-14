package com.hypebeast.sdk.api.model.popbees;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hesk on 6/7/15.
 */
public class popbeeconfig {
    @SerializedName("source")
    public String source;
    @SerializedName("source_url")
    public String sourceurl;
    @SerializedName("custom_post_template")
    public String custom_post_template;
    @SerializedName("gallery")
    public List<String> gallery;
    @SerializedName("video_source")
    public String video_embeded_src;
}
