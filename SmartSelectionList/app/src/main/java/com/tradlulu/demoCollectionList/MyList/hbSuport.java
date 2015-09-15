package com.tradlulu.demoCollectionList.MyList;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.SelectChoice;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.model.symfony.FilterGroup;
import com.hypebeast.sdk.api.resources.hbstore.Products;
import com.hypebeast.sdk.application.hbx.FilterApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hesk on 15/9/15.
 */
public class hbSuport {
    public static final String
            SIZE_LABEL = "Size",
            BRAND_LABEL = "Brand",
            CATEGORY_LABEL = "Category",
            COLOR_LABEL = "Color",
            PRICE_LABEL = "By Price";

    public static SelectChoice byReturnJson(ReponseNormal firstJson) {
        SelectChoice lv0 = new SelectChoice(false);
        final List<String> data = new ArrayList<>();
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

    private static boolean inList(String selected, List<SelectChoice> selection_memory) {
        Iterator<SelectChoice> io = selection_memory.iterator();
        while (io.hasNext()) {
            SelectChoice mSelect = io.next();
            if (mSelect.isTag(selected)) {
                return true;
            }
        }
        return false;
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

    private static void getlist(final String selected, final NonSwipe pager, final DynamicAdapter mAdapter) {
      /*  Iterator<SelectChoice> io = selection_memory.iterator();
        while (io.hasNext()) {
            SelectChoice mSelect = io.next();
            if (mSelect.isTag(selected)) {
                mAdapter.levelForward(pager, mSelect);
             inProgressDone();
                return;
            }
        }*/
        /**
         * mock run async
         */

        SelectChoice list_end = new SelectChoice(false, selected);
        list_end.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
        mAdapter.levelForward(pager, list_end);
        //  inProgressDone();
    }

    public static SelectChoice fromFirstColumn(
            final List<SelectChoice> selection_memory,
            final ReponseNormal saved_list,
            final String name) {
        final boolean inDaList = inList(name, selection_memory);
        final FilterGroup fgroup = saved_list.product_list.getfacets();
        SelectChoice lv0 = new SelectChoice(false, name);
        if (name.contains(SIZE_LABEL)) {
            lv0.setResourceData(fgroup.convertToStringList(fgroup.size));
        } else if (name.contains(BRAND_LABEL)) {
            lv0.setResourceData(fgroup.convertToStringList(fgroup.brand));
        } else if (name.contains(CATEGORY_LABEL)) {
            lv0.setResourceData(fgroup.convertToStringList(fgroup.category));
        } else if (name.contains(COLOR_LABEL)) {
            lv0.setResourceData(fgroup.convertToStringList(fgroup.color));
        } else if (name.contains(PRICE_LABEL)) {
            lv0.setResourceData(fgroup.convertToStringList(fgroup.priceRange));
        }
        lv0.setLevel(1);
        return lv0;
    }
}
