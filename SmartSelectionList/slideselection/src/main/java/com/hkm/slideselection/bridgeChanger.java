package com.hkm.slideselection;

import com.hkm.layout.Module.NonSwipe;

/**
 * Created by hesk on 10/9/15.
 */
public interface bridgeChanger {
    void SelectNow(
            final NonSwipe pager,
            final DynamicAdapter mAdapter,
            final int selected,
            final int level_now,
            final String selected_word);
}
