package com.hypebeast.sdk.api.model.hypebeaststore;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.api.model.Alternative;
import com.hypebeast.sdk.api.model.symfony.Product;
import com.hypebeast.sdk.api.model.symfony.embededList;
import com.hypebeast.sdk.api.model.symfony.FilterGroup;

import java.util.List;

/**
 * Created by hesk on 2/6/15.
 */
public class ResponseProductList extends Alternative {
    @SerializedName("page")
    private int page;
    @SerializedName("limit")
    private int limit;
    @SerializedName("pages")
    private int pages;
    @SerializedName("total")
    public int total;
    @SerializedName("_embedded")
    private embededList embededitems;
    @SerializedName("facets")
    private FilterGroup filters;

    public int totalpages() {
        return pages;
    }

    public int current_page() {
        return page;
    }

    public List<Product> getlist() {
        return embededitems.productswrap;
    }

    public FilterGroup getfacets() {
        return filters;
    }
}
