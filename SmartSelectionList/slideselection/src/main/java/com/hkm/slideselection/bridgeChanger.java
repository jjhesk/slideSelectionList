package com.hkm.slideselection;

import com.hkm.layout.Module.NonSwipe;

/**
 * Created by hesk on 10/9/15.
 */
public interface bridgeChanger {
    void SelectNow(
            final NonSwipe pager,
            final DynamicAdapter mAdapter,
            final SelectChoice choice);

    void HomeSelect(final NonSwipe pager,
                    final DynamicAdapter mAdapter,
                    final int position);
}
