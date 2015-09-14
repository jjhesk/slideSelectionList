package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 9/6/15.
 */
public class Foundation {
    @SerializedName("en")
    public configbank english;
    @SerializedName("cnt")
    public configbank chinese_traditional;
    @SerializedName("cns")
    public configbank chinese_simplified;
    @SerializedName("ja")
    public configbank japanese;
}
