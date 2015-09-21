package com.tradlulu.demoCollectionList.Module;

import android.util.Log;

import com.hkm.slideselection.V1.DynamicAdapter;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.app.ViewPagerHolder;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.model.symfony.FilterGroup;
import com.hypebeast.sdk.application.hbx.FilterApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hesk on 15/9/15.
 */
public class HBDataSupport {
    public static final String
            SIZE_LABEL = "Size",
            BRAND_LABEL = "Brand",
            CATEGORY_LABEL = "Category",
            COLOR_LABEL = "Color",
            PRICE_LABEL = "By Price";

    public static SelectChoice byReturnJson(ReponseNormal firstJson) {
        SelectChoice lv0 = new SelectChoice(false);
        final List<String> data = new ArrayList<String>();
        if (firstJson.product_list.getfacets().size.total > 0) {
            data.add(SIZE_LABEL);
        }
        if (firstJson.product_list.getfacets().brand.total > 0) {
            data.add(BRAND_LABEL);
        }
        if (firstJson.product_list.getfacets().category.total > 0) {
            data.add(CATEGORY_LABEL);
        }
        if (firstJson.product_list.getfacets().color.total > 0) {
            data.add(COLOR_LABEL);
        }
        if (firstJson.product_list.getfacets().priceRange.rangeslist.size() > 0) {
            data.add(PRICE_LABEL);
        }
        lv0.setResourceData(data);
        lv0.setLevel(0);
        return lv0;
    }

    private static void additionalLabel(final ArrayList<String> display, String tag, final Iterator<SelectChoice> saved, boolean hasChildMenuList) {
        while (saved.hasNext()) {
            final SelectChoice se = saved.next();
            if (se.isTag(tag)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(tag);
                sb.append(": ");
                sb.append(se.selected_string());
                display.add(sb.toString());
                return;
            }
        }

        if (hasChildMenuList) {
            display.add(tag);
        }
    }

    public static SelectChoice byReturnJson(final ReponseNormal firstJson, final ArrayList<SelectChoice> mem) {
        final SelectChoice lv0 = new SelectChoice(false);
        final ArrayList<String> data = new ArrayList<String>();
        additionalLabel(data, SIZE_LABEL, mem.iterator(), firstJson.product_list.getfacets().size.total > 0);
        additionalLabel(data, BRAND_LABEL, mem.iterator(), firstJson.product_list.getfacets().brand.total > 0);
        additionalLabel(data, CATEGORY_LABEL, mem.iterator(), firstJson.product_list.getfacets().category.total > 0);
        additionalLabel(data, COLOR_LABEL, mem.iterator(), firstJson.product_list.getfacets().color.total > 0);
        additionalLabel(data, PRICE_LABEL, mem.iterator(), firstJson.product_list.getfacets().priceRange.rangeslist.size() > 0);
        lv0.setResourceData(data);
        lv0.setLevel(0);
        return lv0;
    }


    public static SelectChoice fromFirstColumn(
            final List<SelectChoice> selection_memory,
            final ReponseNormal saved_list,
            final String name) {
        //final boolean inDaList = inList(name, selection_memory);
        final FilterGroup gp = saved_list.product_list.getfacets();
        final SelectChoice selection_ch = new SelectChoice(false, name);
        if (name.contains(SIZE_LABEL)) {
            selection_ch.setResourceData(FilterGroup.convertToStringList(gp.size));
        } else if (name.contains(BRAND_LABEL)) {
            selection_ch.setResourceData(FilterGroup.convertToStringList(gp.brand));
        } else if (name.contains(CATEGORY_LABEL)) {
            selection_ch.setResourceData(FilterGroup.convertToStringList(gp.category));
        } else if (name.contains(COLOR_LABEL)) {
            selection_ch.setResourceData(FilterGroup.convertToStringList(gp.color));
        } else if (name.contains(PRICE_LABEL)) {
            selection_ch.setResourceData(FilterGroup.convertToStringList(gp.priceRange));
        }
        selection_ch.setLevel(1);
        return selection_ch;
    }

    public static void check_preapply_filter(FilterApplication filter_data, SelectChoice mSelec) {
        String name = mSelec.getTag();
        if (name.contains(SIZE_LABEL)) {
            filter_data.setSize(mSelec.selected_string());
        } else if (name.contains(BRAND_LABEL)) {
            filter_data.setBrand(mSelec.selected_string());
        } else if (name.contains(CATEGORY_LABEL)) {
            filter_data.setCate(mSelec.selected_string());
        } else if (name.contains(COLOR_LABEL)) {
            filter_data.setColor(mSelec.selected_string());
        } else if (name.contains(PRICE_LABEL)) {
            filter_data.setPrice(mSelec.selected_string());
        }
    }

    public static String developJson(Iterator<SelectChoice> io) {
        FilterApplication filter = FilterApplication.newFilter();
        while (io.hasNext()) {
            HBDataSupport.check_preapply_filter(filter, io.next());
        }
        String json = filter.getJson();
        Log.d("check_f_result", json);
        return json;
    }
}
