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

    public DynamicAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        if (FirstConfiguration() != null) {
            levelObjects.add(0, FirstConfiguration());
        }
        levels = 0;
    }

    public DynamicAdapter(FragmentManager fragmentManager, Fragment firstPage) {
        super(fragmentManager);
        this.firstPage = firstPage;
        levels = 0;
    }

    public void levelForward(NonSwipe pager, H mH) {
        levelObjects.add(mH);
        notifyDataSetChanged();
        int now = pager.getCurrentItem() + 1;
        levels++;
        pager.setCurrentItem(now, true);
    }

    public boolean levelBack(NonSwipe pager) {
        int level_step = pager.getCurrentItem();
        if (level_step > 0) {
            int back = level_step - 1;
            pager.setCurrentItem(back, true);
            levelObjects.remove(levelObjects.size() - 1);
            notifyDataSetChanged();
            levels--;
            return true;
        } else return false;
    }

    /**
     * tells if the first page is just a normal page or not
     *
     * @return the type from the level
     */
    protected abstract H FirstConfiguration();

    // Returns total number of pages
    @Override
    public int getCount() {
        return levelObjects.size() + (FirstConfiguration() != null ? 1 : 0);
    }

    protected abstract SimpleSingleList logicBoard(H con);

    // Returns the fragment to display for that page
    @Override
    public SimpleSingleList getItem(int position) {
        try {
            if (position == 0 && firstPage != null && FirstConfiguration() != null) {
                return logicBoard(FirstConfiguration());
            } else {
                return logicBoard(levelObjects.get(position - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "level " + position;
    }

}
