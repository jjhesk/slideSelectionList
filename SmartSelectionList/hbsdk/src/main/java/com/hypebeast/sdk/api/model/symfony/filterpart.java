package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hesk on 7/1/2015.
 */
public class filterpart {
    enum FacetType {
        terms, range
    }
    @SerializedName("_type")
    public FacetType filter_name;
    @SerializedName("total")
    public int total;
    @SerializedName("missing")
    public int missing;
    @SerializedName("ranges")
    public List<Range> rangeslist;
    @SerializedName("terms")
    public List<TermWrap> contentlist;
}
