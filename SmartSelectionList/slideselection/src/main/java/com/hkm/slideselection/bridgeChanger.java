package com.hkm.slideselection;

import com.hkm.layout.Module.NonSwipe;

/**
 * Created by hesk on 10/9/15.
 */
public interface bridgeChanger {
    void SelectNow(NonSwipe pager, DynamicAdapter mAdapter, int selected, int level_now);
}
