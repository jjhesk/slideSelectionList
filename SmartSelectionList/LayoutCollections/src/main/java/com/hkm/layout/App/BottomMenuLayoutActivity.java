package com.hkm.layout.App;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.layout.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;

/**
 * Created by hesk on 21/8/15.
 */
public abstract class BottomMenuLayoutActivity<Frag> extends fundamental<Frag> implements SmartTabLayout.TabProvider {
    protected NonSwipe mViewPager;
    protected SmartTabLayout mSmartTabLayout;

    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SimpleLayout.BODY_LAYOUT.bottommenu.getResID();
    }

    protected abstract FragmentPagerItems.Creator addFragmentsToStack(FragmentPagerItems.Creator creator);

    @Override
    protected void initMainContentFragment(Frag fragment, Bundle savestate) throws Exception {
        FragmentPagerItems mCreate = addFragmentsToStack(FragmentPagerItems.with(this)).create();
        mViewPager = (NonSwipe) findViewById(R.id.lylib_main_frame_body);
        mSmartTabLayout = (SmartTabLayout) findViewById(R.id.lylib_bottom_tab_smart_layout);
        mSmartTabLayout.setCustomTabView(this);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), mCreate);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(10);
        mSmartTabLayout.setViewPager(mViewPager);

    }
//remove the action as this is not applicable


}
