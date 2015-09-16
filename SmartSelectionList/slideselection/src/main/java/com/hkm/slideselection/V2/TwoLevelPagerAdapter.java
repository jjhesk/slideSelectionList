package com.hkm.slideselection.V2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.V1.SimpleSingleList;

import java.util.ArrayList;

/**
 * Created by hesk on 16/9/15.
 */
public class TwoLevelPagerAdapter extends FragmentStatePagerAdapter {
    protected ArrayList<SelectChoice> levelObjects = new ArrayList<>();
    protected SimpleSingleList firstPage, secondPage;
    protected SelectChoice firstPageListConfiguration;
    private ArrayList<SimpleSingleList> views = new ArrayList<SimpleSingleList>();
    private Handler hh = new Handler();
    protected TwoLevelPagerAdapter adapter;
    protected int level_current;

    public TwoLevelPagerAdapter(FragmentManager fm, Bundle input) {
        super(fm);
        firstPage = SimpleSingleList.newInstance(input);
        secondPage = new SimpleSingleList();
        level_current = 0;
    }


    public void levelForward(ViewPager pager, SelectChoice mH) {
        level_current = 1;
        secondPage.setSelectionConfiguration(mH);
        pager.setCurrentItem(1, true);
    }

    public boolean levelBack(ViewPager pager, SelectChoice mh) {
        if (level_current == 0) {
            pager.setCurrentItem(0, true);
            level_current = 0;
            firstPage.updateNewList(mh.getSimpleSource());
            return false;
            // firstPage.setSelectionConfiguration(mh);
        } else {
            pager.setCurrentItem(0, true);
            return true;
        }

    }

    public boolean levelBack(ViewPager pager) {
        if (level_current == 0) {
            return false;
        } else {
            level_current = 0;
            pager.setCurrentItem(0, true);
            return true;
        }
    }

    /*  @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }*/

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(final int position) {
        if (position == 0) {
            return firstPage;
        } else if (position == 1) {
            return secondPage;
        }
        return null;
    }


    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }
}
