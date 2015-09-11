package com.hkm.slideselection.app;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.R;
import com.hkm.slideselection.SimpleSingleList;
import com.hkm.slideselection.StringControlAdapter;
import com.hkm.slideselection.StringLv;
import com.hkm.slideselection.bridgeChanger;
import com.hkm.slideselection.listSelect;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class SimpleStepSelectionFragment extends Fragment {

    protected NonSwipe mViewPager;
    protected StringControlAdapter adapter;
    protected bridgeChanger mbridge = new bridgeChanger() {

        @Override
        public void SelectNow(NonSwipe mpage, DynamicAdapter mAdapter, int selected, int level_now, String selected_word) {
          /*  StringLv hb = new StringLv(selected);
            hb.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
            adapter.levelForward(mViewPager, hb);*/
        }
    };

    public static SimpleStepSelectionFragment firstLevel(StringLv level) {
        SimpleStepSelectionFragment g = new SimpleStepSelectionFragment();
        try {
            g.setArguments(SimpleSingleList.stuffs(level.getSelection(), level.getSimpleSource(), 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }

    public SimpleStepSelectionFragment() {
    }

    protected int getViewPager() {
        return R.id.sssl_pageholder;
    }

    protected int getXml() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }


    public final listSelect listener = new listSelect() {
        @Override
        public void SelectNow(UltimateViewAdapter mAdapter, int selected) {
            int current_level = adapter.getCurrentLevel();
            if (adapter.getItem(current_level) instanceof SimpleSingleList) {
                SimpleSingleList simple1 = (SimpleSingleList) adapter.getItem(current_level);
                mbridge.SelectNow(mViewPager, adapter, selected, current_level,
                        simple1.getItemStringTitle(selected));
            }

        }
    };

    public StringLv getLevel(int lv) throws Exception {

        return adapter.getLevelObjectAt(lv);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (NonSwipe) view.findViewById(getViewPager());
        mViewPager.setOffscreenPageLimit(99);
        adapter = new StringControlAdapter(getChildFragmentManager(), getArguments());
        //=this is not going to work
        mViewPager.setAdapter(adapter);
     /*      mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
        mViewPager.setCurrentItem(0);
    }

    public void setCallBackListenerBridge(bridgeChanger mbridge) {
        this.mbridge = mbridge;
    }

    public boolean onPressBack() {
        return adapter.levelBack(mViewPager);
    }
}
