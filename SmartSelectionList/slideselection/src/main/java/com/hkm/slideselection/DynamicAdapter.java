package com.hkm.slideselection;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;


import com.hkm.layout.Module.NonSwipe;

import java.util.ArrayList;

/**
 * Created by hesk on 10/9/15.
 */
public abstract class DynamicAdapter<H extends LevelResources> extends FragmentStatePagerAdapter {
    private int levels;
    private ArrayList<H> levelObjects = new ArrayList<>();
    protected Fragment firstPage;
    protected H firstPageListConfiguration;

    public DynamicAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        levels = 0;
        firstPageListConfiguration = null;
    }

    public DynamicAdapter(FragmentManager fragmentManager, Fragment firstPage) {
        super(fragmentManager);
        this.firstPage = firstPage;
        levels = 0;
        firstPageListConfiguration = extractBundle();
    }

    public DynamicAdapter(FragmentManager fragmentManager, Fragment firstPage, H configuration) {
        super(fragmentManager);
        this.firstPage = firstPage;
        levels = 0;
        firstPageListConfiguration = configuration;
    }

    public DynamicAdapter(FragmentManager fragmentManager, H configuration) {
        super(fragmentManager);
        levels = 0;
        firstPageListConfiguration = configuration;
    }

    protected void addConfiguration(H FConfiguration) {
        levelObjects.add(FConfiguration);
        notifyDataSetChanged();
    }

    protected void takeOutConfiguration() {
        levelObjects.remove(levelObjects.size() - 1);
        notifyDataSetChanged();
    }

    public void levelForward(NonSwipe pager, H mH) {
        addConfiguration(mH);
        int now = pager.getCurrentItem() + 1;
        levels++;
        pager.setCurrentItem(now, true);
    }

    public boolean levelBack(NonSwipe pager) {
        int level_step = pager.getCurrentItem();
        if (level_step > 0) {
            int back = level_step - 1;
            pager.setCurrentItem(back, true);
            takeOutConfiguration();
            levels--;
            return true;
        } else return false;
    }


    // Returns total number of pages
    @Override
    public int getCount() {
        return levelObjects.size() + 1;
    }

    protected abstract SimpleSingleList logicBoard(H con);

    protected abstract H extractBundle();

    /**
     * tells if the first page is just a normal page or not
     *
     * @return the type from the level
     */
    protected abstract H FirstConfiguration();

    // Returns the fragment to display for that page
    @Override
    public SimpleSingleList getItem(int position) {
        if (position == 0) {
            if (firstPageListConfiguration != null) {
                return logicBoard(firstPageListConfiguration);
            } else
                return logicBoard(levelObjects.get(0));
        } else {
            return logicBoard(levelObjects.get(position - 1));
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "level " + position;
    }

}
