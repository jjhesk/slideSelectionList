package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public static String[] convertToStringList(filterpart item) {
        List<String> j = new ArrayList<>();
        if (item.filter_name == filterpart.FacetType.range) {
            Iterator<Range> twi = item.rangeslist.iterator();
            while (twi.hasNext()) {
                Range tw = twi.next();
                j.add(tw.getCallDisplay());
            }
        } else {
            Iterator<TermWrap> twi = item.contentlist.iterator();
            while (twi.hasNext()) {
                TermWrap tw = twi.next();
                j.add(tw.theTerm());
            }
        }
        return j.toArray(new String[j.size()]);
    }

    public static int getSelectedPositionByName(filterpart item, String name) {
        if (item.filter_name == filterpart.FacetType.range) {
            return -1;
        }
        List<String> j = new ArrayList<>();
        Iterator<TermWrap> twi = item.contentlist.iterator();
        int i = 0;
        while (twi.hasNext()) {
            TermWrap tw = twi.next();
            if (tw.theTerm().equalsIgnoreCase(name)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
