package com.hkm.layout.App;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hkm.layout.Module.ControllableFrame;
import com.hkm.layout.R;

/**
 * Created by hesk on 20/8/15.
 */
public abstract class SimpleLayout<Frag> extends fundamental<Frag> {

    /**
     * the control frame to display the blocked content or not.
     */
    protected ControllableFrame content_frame;

    /**
     * setting the default main activity layout ID and this is normally had presented in the library and no need change unless there is a customization need for different layout ID
     *
     * @return resource id
     */
    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SimpleLayout.BODY_LAYOUT.singelsimple.getResID();
    }

    @Override
    protected void afterInitContentViewToolBar() {
        content_frame = (ControllableFrame) findViewById(R.id.lylib_main_frame_body);
    }

    protected void setUnblock() {
        if (content_frame != null) {
            content_frame.noBlock();
        }
    }

    protected void setBlockEnableWithColor(@ColorRes int mdrawble) {
        if (content_frame != null) {
            content_frame.setDimColor(mdrawble);
        }
    }

    protected void setBlockEnableWithDrawable(@DrawableRes int mdrawble) {
        if (content_frame != null) {
            content_frame.setDimDrawble(mdrawble);
        }
    }


}
