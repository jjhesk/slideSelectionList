package com.hkm.slideselection.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.R;
import com.hkm.slideselection.StringControlAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class SimpleStepSelectionFragment extends Fragment {

    public SimpleStepSelectionFragment() {
    }

    protected int getViewPager() {
        return R.id.sssl_pageholder;
    }

    protected int getXml() {
        return R.layout.fragment_main;
    }

    protected int getListItem() {
        return R.id.sssl_item_checkbox_single;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }

    protected NonSwipe mViewPager;
    protected StringControlAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (NonSwipe) view.findViewById(getViewPager());
        mViewPager.setOffscreenPageLimit(99);
        adapter = new StringControlAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
    }

    public void onPressBack() {
        int level_step = mViewPager.getCurrentItem();
        if (level_step > 0) {
            int back = level_step - 1;
            mViewPager.setCurrentItem(back, true);
        }
    }
}
