package com.hkm.layout.Module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by hesk on 21/8/15.
 */
public abstract class HidingScrollListener extends OnScrollListener {
    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;

    private int mToolbarOffset = 0;
    private boolean mControlsVisible = true;
    protected int mToolbarHeight;

    public HidingScrollListener(Context context) {
        mToolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == SCROLL_STATE_IDLE) {
            if (mControlsVisible) {
                if (mToolbarOffset > HIDE_THRESHOLD) {
                    setInvisible();
                } else {
                    setVisible();
                }
            } else {
                if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
                    setVisible();
                } else {
                    setInvisible();
                }
            }
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();
        onMoved(mToolbarOffset);

        if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
            mToolbarOffset += dy;
        }

    }

    private void clipToolbarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    private void setVisible() {
        if (mToolbarOffset > 0) {
            onShow();
            mToolbarOffset = 0;
        }
        mControlsVisible = true;
    }

    protected int getDistance() {
        return mToolbarOffset;
    }

    private void setInvisible() {
        if (mToolbarOffset < mToolbarHeight) {
            onHide();
            mToolbarOffset = mToolbarHeight;
        }
        mControlsVisible = false;
    }

    public abstract void onMoved(int distance);

    public abstract void onShow();

    public abstract void onHide();
}
