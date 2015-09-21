package com.hkm.slideselection.worker;

import android.support.v4.view.ViewPager;

import com.hkm.slideselection.V2.TwoLevelPagerAdapter;
import com.hkm.slideselection.app.HbSelectionFragment;

/**
 * Created by hesk on 16/9/15.
 */
public interface bridgeEZ {
    void dismiss_back();
    void request_applied();

    void request_new_filter();

    void SelectNow(
            final ViewPager pager,
            final TwoLevelPagerAdapter mAdapter,
            final SelectChoice choice,
            final HbSelectionFragment hb);

    void HomeSelect(
            final ViewPager pager,
            final TwoLevelPagerAdapter mAdapter,
            final int position,
            final HbSelectionFragment hb);


}
