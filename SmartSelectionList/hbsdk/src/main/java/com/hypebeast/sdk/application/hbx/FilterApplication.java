package com.hypebeast.sdk.application.hbx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hesk on 15/9/15.
 */
public class FilterApplication {
    public static final String new_price = "((?:[0-9]{1,3}[\\.,]?)*[\\.,]?[0-9]+)";
    /* private int
             icat = -1, ibrand = -1, isize = -1,
             price_range_index = -1;
    */
    private boolean newFilter = false;
    private FilterQueryHolder mQuery = new FilterQueryHolder();
    private filterTrigger trigger = new filterTrigger() {
        @Override
        public void applyFilter() {

        }
    };

    public static FilterApplication newFilter() {
        FilterApplication mFilter = new FilterApplication();
        return mFilter;
    }

    public FilterApplication() {
        mQuery = new FilterQueryHolder();
    }

    public void setFilterApplyCallBack(filterTrigger t) {
        trigger = t;
    }

    public void filterapply() {
        if (trigger != null) trigger.applyFilter();
    }

    /**
     * from TermWrap to string
     *
     * @param TermWrap_string_label the data string from the object TermWrap
     * @return bool for success or failure from applying the filter
     */
    public boolean setPrice(String TermWrap_string_label) {
        final Pattern p = Pattern.compile(new_price, Pattern.DOTALL);
        final Matcher m = p.matcher(TermWrap_string_label);
        //Log.d(TAG, "found! good start");
        int i = 0;
        // isize = which;
        String e1 = "", e2 = "";
        while (m.find()) {
            i++;
            if (i == 1) e1 = m.group();
            if (i == 2) e2 = m.group();
        }
        if (i > 0) {
            if (TermWrap_string_label.matches("(?i).*under.*")) {
                mQuery.price = PricePointRangeHolder.gen("", e1);
                //newfilter();
                return true;
            }
            if (TermWrap_string_label.matches("(?i).*above.*")) {
                mQuery.price = PricePointRangeHolder.gen(e1, "");
                // newfilter();
                return true;
            }
            mQuery.price = PricePointRangeHolder.gen(e1, e2);
            //  newfilter();
            return true;
        } else
            return false;
    }

    public void setCate(String TermWrap_string_label) {
        mQuery.category = new String[]{TermWrap_string_label};
    }

    public void setSize(String TermWrap_string_label) {
        mQuery.size = new String[]{TermWrap_string_label};
    }

    public void setBrand(String TermWrap_string_label) {
        mQuery.brand = new String[]{TermWrap_string_label};
    }

    public void setColor(String TermWrap_string_label) {
        mQuery.color = new String[]{TermWrap_string_label};
    }

    public String getJson() {
        final GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        String filter = gson.toJson(this.mQuery);
        // String filter_url = Uri.encode(filter);
        // if (newFilter) newFilter = false;
        return filter;
    }

    public void reset() {
        mQuery = new FilterQueryHolder();
    }

    public void unsetBrand() {
        mQuery.brand = new String[0];
    }

    public void unsetCate() {
        mQuery.category = new String[0];
    }

    public void unsetColor() {
        mQuery.size = new String[0];
    }

    public void unsetSize() {
        mQuery.color = new String[0];
    }

    public void unsetPrice() {
        mQuery.price = new PricePointRangeHolder[0];
    }

    public boolean justSet() {
        final boolean g = newFilter;
        if (newFilter) newFilter = false;
        return g;
    }

    public boolean isEmpty() {
        final boolean a = mQuery.brand.length == 0;
        final boolean b = mQuery.category.length == 0;
        final boolean c = mQuery.size.length == 0;
        final boolean d = mQuery.price.length == 0;
        final boolean e = mQuery.color.length == 0;
        return a && b && c && d && e;
    }

}
