package com.hkm.slideselection.worker;


import com.hkm.slideselection.V1.DynamicAdapter;
import com.hkm.slideselection.app.ViewPagerHolder;

/**
 * Created by hesk on 10/9/15.
 */
public interface bridgeChanger {

    void SelectNow(
            final ViewPagerHolder pager,
            final DynamicAdapter mAdapter,
            final SelectChoice choice);

    void HomeSelect(
            final ViewPagerHolder pager,
            final DynamicAdapter mAdapter,
            final int position);
}
