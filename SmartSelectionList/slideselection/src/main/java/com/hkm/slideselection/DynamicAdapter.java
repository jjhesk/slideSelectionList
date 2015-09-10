package com.hkm.slideselection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by hesk on 10/9/15.
 */
public abstract class DynamicAdapter<H extends LevelResources> extends FragmentPagerAdapter {
    private int levels = 1;
    private ArrayList<H> levelObjects = new ArrayList<>();
    private Fragment firstPage;

    public DynamicAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        if (FirstConfiguration() != null) {
            levelObjects.add(0, FirstConfiguration());
        }
    }

    public DynamicAdapter(FragmentManager fragmentManager, Fragment firstPage) {
        super(fragmentManager);
        this.firstPage = firstPage;
    }

    public void levelForward(H mH) {
        levelObjects.add(mH);

        notifyDataSetChanged();
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
        return levels;
    }
    protected abstract SimpleSingleList logicBoard(H con);

    // Returns the fragment to display for that page
    @Override
    public SimpleSingleList getItem(int position) {
        try {
            if (position == 0 && firstPage != null) {
                if (FirstConfiguration() != null) {
                    return logicBoard(FirstConfiguration());
                } else
                    return SimpleSingleList.newInstance();
            } else {
                return logicBoard(levelObjects.get(position));
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
