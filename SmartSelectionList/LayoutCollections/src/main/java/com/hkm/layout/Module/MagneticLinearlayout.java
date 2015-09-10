package com.hkm.layout.Module;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by hesk on 25/8/15.
 */
public class MagneticLinearlayout extends ScrollView {
    public MagneticLinearlayout(Context context) {
        super(context);
    }

    public MagneticLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MagneticLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Compute the amount to scroll in the Y direction in order to get
     * a rectangle completely on the screen (or, if taller than the screen,
     * at least the first screen size chunk of it).
     *
     * @param rect The rect.
     * @return The scroll delta.
     */
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return super.computeScrollDeltaToGetChildRectOnScreen(rect);
    }

    public void scroolToTop() {
        int x = 0;
        int y = 0;
        ObjectAnimator xTranslate = ObjectAnimator.ofInt(this, "scrollX", x);
        ObjectAnimator yTranslate = ObjectAnimator.ofInt(this, "scrollY", y);

        AnimatorSet animators = new AnimatorSet();
        animators.setDuration(1000L);
        animators.playTogether(xTranslate, yTranslate);
        animators.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub

            }
        });
        animators.start();
    }

}
