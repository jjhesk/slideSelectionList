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
    private int level_current;
    private ArrayList<H> levelObjects = new ArrayList<>();
    protected Fragment firstPage;
    protected H firstPageListConfiguration;

    public DynamicAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        level_current = 0;
    }

    public DynamicAdapter(FragmentManager fragmentManager, Fragment firstPage) {
        this(fragmentManager);
        this.firstPage = firstPage;
        firstPageListConfiguration = extractBundle();
    }

    public DynamicAdapter(FragmentManager fragmentManager, Fragment firstPage, H configuration) {
        this(fragmentManager);
        this.firstPage = firstPage;
        firstPageListConfiguration = configuration;
    }

    public DynamicAdapter(FragmentManager fragmentManager, H configuration) {
        this(fragmentManager);
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
        int now = pager.getCurrentItem() + 1;
        level_current++;
        addConfiguration(mH);
        pager.setCurrentItem(now, true);
    }

    public boolean levelBack(NonSwipe pager) {
        int level_step = pager.getCurrentItem();
        if (level_step > 0) {
            int back = level_step - 1;
            pager.setCurrentItem(back, true);
            level_current--;
            takeOutConfiguration();
            return true;
        } else return false;
    }


    // Returns total number of pages
    @Override
    public int getCount() {
        return levelObjects.size() + (firstPageListConfiguration == null ? 0 : 1);
        // return h;
    }

    protected abstract SimpleSingleList logicBoard(H con);

    protected abstract H extractBundle();

    /**
     * tells if the first page is just a normal page or not
     *
     * @return the type from the level
     */
    // protected abstract H FirstConfiguration();

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(final int position) {
        // return logicBoard(firstPageListConfiguration);
        if (position > 0) {
            return logicBoard(levelObjects.get(position - 1));
        } else {
            // if (firstPage instanceof SimpleSingleList) {
            //    return (SimpleSingleList) firstPage;
            //  } else
            return firstPage;
        }
    }

    public int getCurrentLevel() {
        return level_current;
    }

    public H getCurrentLVObject() throws Exception {
        return getLevelObjectAt(level_current - 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "level " + position;
    }

    public H getLevelObjectAt(int n) throws Exception {
        if (levelObjects.size() > 0) {
            return levelObjects.get(n);
        } else {
            throw new Exception("not found in the level object");
        }
    }

}
