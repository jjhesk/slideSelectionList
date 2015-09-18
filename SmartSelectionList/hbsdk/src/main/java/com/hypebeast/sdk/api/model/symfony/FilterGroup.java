package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Created by hesk on 2/9/15.
 */
public class FilterGroup {
    @SerializedName("size")
    public filterpart size;
    @SerializedName("brand")
    public filterpart brand;
    @SerializedName("category")
    public filterpart category;
    @SerializedName("color")
    public filterpart color;
    @SerializedName("price")
    public filterpart priceRange;

    public static String[] convertToStringList(filterpart items) {
        final ArrayList<String> itemlist = new ArrayList<String>();
        if (items.filter_name == filterpart.FacetType.range) {
            Iterator<Range> ri = items.rangeslist.iterator();
            while (ri.hasNext()) {
                Range r = ri.next();
                itemlist.add(r.getCallDisplay());
            }
        } else if (items.filter_name == filterpart.FacetType.terms) {
            final Iterator<TermWrap> itemwrap = items.contentlist.iterator();
            while (itemwrap.hasNext()) {
                TermWrap f = itemwrap.next();
                itemlist.add(f.toString());
            }
        }
        return itemlist.toArray(new String[itemlist.size()]);
    }
}
