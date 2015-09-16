package com.hkm.slideselection.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.hkm.slideselection.V1.DynamicAdapter;
import com.hkm.slideselection.worker.MessageEvent;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.V1.StringControlAdapter;
import com.hkm.slideselection.worker.Util;
import com.hkm.slideselection.worker.bridgeChanger;
import com.hkm.slideselection.worker.bCR;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * A placeholder fragment containing a simple view.
 */
public class SimpleStepSelectionFragment extends selectionBody {

    protected StringControlAdapter adapter;
    protected bridgeChanger mbridge = new bCR() {

        @Override
        public void SelectNow(ViewPagerHolder mpage, DynamicAdapter mAdapter, SelectChoice mChoice) {
        }
    };


    public static SimpleStepSelectionFragment firstLevel(SelectChoice level) {
        SimpleStepSelectionFragment g = new SimpleStepSelectionFragment();
        try {
            g.setArguments(Util.stuffs(level.getSelection(), level.getSimpleSource(), 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }


    public SimpleStepSelectionFragment() {
        super();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new StringControlAdapter(getChildFragmentManager(), getArguments());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public boolean onPressBack() {
        return adapter.levelBack(mViewPager);
    }

    public SelectChoice getLevel(int lv) throws Exception {
        return adapter.getLevelObjectAt(lv);
    }

    public void setCallBackListenerBridge(bridgeChanger mbridge) {
        this.mbridge = mbridge;
    }

    @Subscribe
    public void onEvent(SelectChoice event_choice) {
        mbridge.SelectNow(
                mViewPager,
                adapter,
                event_choice);
    }

    @Subscribe
    public void onEvent(MessageEvent event_integer) {
        mbridge.HomeSelect(
                mViewPager,
                adapter,
                event_integer.At());
    }

    public Bus getBusInstance() {
        return mBus;
    }


    @Override
    public void onStart() {
        mBus.register(SimpleStepSelectionFragment.this);
        super.onStart();
    }

    @Override
    public void onStop() {
        mBus.unregister(SimpleStepSelectionFragment.this);
        super.onStop();
    }
}
