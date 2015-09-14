package com.hypebeast.sdk.api.model.hypetrak;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hesk on 8/9/15.
 */
public class hypetrakconfig {
    @SerializedName("source")
    public String source;
    @SerializedName("custom_post_template")
    public String custom_post_template;
    @SerializedName("gallery")
    public List<String> gallery;
    @SerializedName("video_link")
    public String video_embeded_src;
}
