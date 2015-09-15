package com.hkm.slideselection.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.R;
import com.hkm.slideselection.SimpleSingleList;
import com.hkm.slideselection.StringControlAdapter;
import com.hkm.slideselection.SelectChoice;
import com.hkm.slideselection.bridgeChanger;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * A placeholder fragment containing a simple view.
 */
public class SimpleStepSelectionFragment extends Fragment {
    protected Bus mBus;
    protected NonSwipe mViewPager;
    protected StringControlAdapter adapter;
    protected bridgeChanger mbridge = new bridgeChanger() {

        @Override
        public void SelectNow(NonSwipe mpage, DynamicAdapter mAdapter, SelectChoice mChoice) {
        }
    };

    /**
     * Called when a fragment is first attached to its activity.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBus = new Bus();
    }

    public static SimpleStepSelectionFragment firstLevel(SelectChoice level) {
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }

    public SelectChoice getLevel(int lv) throws Exception {
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

    @Subscribe
    public void onEvent(SelectChoice event_choice) {
        mbridge.SelectNow(
                mViewPager,
                adapter,
                event_choice);
    }

    public Bus getBusInstance() {
        return mBus;
    }


    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBus.unregister(this);
    }
}
