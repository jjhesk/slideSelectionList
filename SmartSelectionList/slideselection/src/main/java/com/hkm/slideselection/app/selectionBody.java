package com.hkm.slideselection.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.slideselection.R;
import com.hkm.slideselection.worker.MessageEvent;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.V1.SimpleSingleList;
import com.hkm.slideselection.worker.Util;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by hesk on 16/9/15.
 */
public abstract class selectionBody extends Fragment {

    protected static Bus mBus;
    protected ViewPagerHolder mViewPager;


    /**
     * Called when a fragment is first attached to its activity.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mBus == null)
            mBus = new Bus("fragmentcontrol");
    }

    public static SimpleStepSelectionFragment firstLevel(SelectChoice level) {
        SimpleStepSelectionFragment g = new SimpleStepSelectionFragment();
        try {
            g.setArguments(Util.stuffs(level.getSelection(), level.getSimpleSource(), 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }


    public selectionBody() {
    }

    protected int getViewPager() {
        return R.id.sssl_pageholder;
    }

    protected int getXml() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPagerHolder) view.findViewById(getViewPager());
        mViewPager.setOffscreenPageLimit(99);
    }


    public abstract boolean onPressBack();

    public abstract void onEvent(SelectChoice event_choice);

    public abstract void onEvent(MessageEvent event_integer);

    public Bus getBusInstance() {
        return mBus;
    }


}
