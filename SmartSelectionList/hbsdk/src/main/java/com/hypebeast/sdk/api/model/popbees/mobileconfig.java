package com.hypebeast.sdk.api.model.popbees;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.wprest.sliderItem;

import java.util.List;

/**
 * Created by hesk on 15/7/15.
 */
public class mobileconfig {
    @SerializedName("feature_banners")
    public List<sliderItem> featurebanner;

    @SerializedName("feature_posts")
    public List<pbpost> featureposts;
}
