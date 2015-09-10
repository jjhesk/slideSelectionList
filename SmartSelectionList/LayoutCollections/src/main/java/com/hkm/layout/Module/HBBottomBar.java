package com.hkm.layout.Module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hkm.layout.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;

/**
 * Created by hesk on 21/8/15.
 */
public class HBBottomBar extends RelativeLayout implements SmartTabLayout.TabProvider {
    private Context res;
    private LayoutInflater inflater;
    private boolean enable_block = false;

    public HBBottomBar(Context context) {
        super(context);
        init();
    }

    public HBBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return enable_block;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setDimDrawble(@DrawableRes int dr) {
        //setForeground(getContext().getDrawable(dr));
        enable_block = true;
    }

    public void setDimColor(@ColorRes int n_color) {
        // setForeground(new ColorDrawable(getContext().getResources().getColor(n_color)));
        enable_block = true;
    }

    public void noBlock() {
        /// setForeground(null);
        enable_block = false;
    }

    private void init() {
     /*   FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.titleA, PageFragment.class)
                .add(R.string.titleB, PageFragment.class)
                .create());*/
        res = getContext();
        inflater = (LayoutInflater) res.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.menu_items, null);
        final SmartTabLayout layout = (SmartTabLayout) view.findViewById(R.id.lylib_bottom_tab_smart_layout);
        layout.setCustomTabView(this);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
        ImageView icon = (ImageView) inflater.inflate(R.layout.menu_bottom_item, container, false);
        switch (position) {
            case 0:
                icon.setImageDrawable(res.getDrawable(R.drawable.mu_camera_old));
                break;
            case 1:
                icon.setImageDrawable(res.getDrawable(R.drawable.mu_camera_old));
                break;
            case 2:
                icon.setImageDrawable(res.getDrawable(R.drawable.mu_camera_old));
                break;
            case 3:
                icon.setImageDrawable(res.getDrawable(R.drawable.mu_camera_old));
                break;
            default:
                throw new IllegalStateException("Invalid position: " + position);
        }
        return icon;
    }

}
