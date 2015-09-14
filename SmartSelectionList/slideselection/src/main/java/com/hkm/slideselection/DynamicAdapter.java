package com.hkm.slideselection;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;


import com.hkm.layout.Module.NonSwipe;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;

/**
 * Created by hesk on 10/9/15.
 */
public abstract class DynamicAdapter<H extends LevelResources> extends FragmentStatePagerAdapter {
    private int level_current;
    private ArrayList<H> levelObjects = new ArrayList<>();
    protected Fragment firstPage;
    protected H firstPageListConfiguration;
    private ArrayList<SimpleSingleList> views = new ArrayList<SimpleSingleList>();
    private Handler hh = new Handler();

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
        hh.postDelayed(new Runnable() {
            @Override
            public void run() {
                levelObjects.remove(levelObjects.size() - 1);
                notifyDataSetChanged();
            }
        }, 800);
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

    /* public Fragment getActiveFragment(ViewPager pager, FragmentManager fragmentManager, int position) {
        final String name = makeFragmentName(pager.getId(), position);
        final Fragment fragmentByTag = fragmentManager.findFragmentByTag(name);
        if (fragmentByTag == null) {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            fragmentManager.dump("", null, new PrintWriter(outputStream, true), null);
            final String s = new String(outputStream.toByteArray(), Charset.forName("UTF-8"));
            throw new IllegalStateException("Could not find fragment via hacky way.\n" +
                    "We were looking for position: " + position + " name: " + name + "\n" +
                    "Fragment at this position does not exists, or hack stopped working.\n" +
                    "Current fragment manager dump is: " + s);
        }
        return fragmentByTag;
    }
    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }
    */

    // Returns total number of pages
    @Override
    public int getCount() {
        return levelObjects.size() + (firstPageListConfiguration == null ? 0 : 1);
        // return h;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
   /*
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleSingleList b = views.get(position);
        View v = b.getView();
        container.addView(v);
        return v;
    }*/

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
            H loca = levelObjects.get(position - 1);
            return logicBoard(loca);
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


    /*   @Override
       public Fragment getItem(int position) {
           return views.get(position);
       }
   */
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }


    public void addView(SimpleSingleList v) {
        views.add(v);
    }

    public SimpleSingleList getFragment(int index) {
        return views.get(index);
    }

    public void removeByIndex(int index) {
        for (int i = views.size() - 1; i > index; i--) {
            views.remove(i);
        }
    }

    public void removeLast() {
        views.remove(views.size() - 1);
    }

    public void removeAll() {
        views = new ArrayList<SimpleSingleList>();
    }
}
