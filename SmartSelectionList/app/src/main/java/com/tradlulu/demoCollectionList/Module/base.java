package com.tradlulu.demoCollectionList.Module;

import android.support.multidex.MultiDexApplication;

import me.drakeet.library.CrashWoodpecker;

/**
 * Created by hesk on 9/10/15.
 */
public class base extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashWoodpecker.fly().to(this);
    }
}
