package com.hkm.slideselection.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.internal.widget.TintImageView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hkm.slideselection.R;
import com.hkm.slideselection.worker.MessageEvent;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.V2.TwoLevelPagerAdapter;
import com.hkm.slideselection.worker.Util;
import com.hkm.slideselection.worker.bEZ;
import com.hkm.slideselection.worker.bridgeEZ;

import java.util.ArrayList;

/**
 * Created by hesk on 16/9/15.
 */
public class HbSelectionFragment extends selectionBody {
    private SelectChoice lv0;
    private Handler uiHandler = new Handler();
    private TintImageView back, apply, reset;
    private ProgressBar mProgress;
    private TextView title_navigation;
    private ArrayList<SelectChoice> selection_memory = new ArrayList<>();
    private int level = 0;
    private boolean initialize = false, isInProgress = false;
    private bridgeEZ mInterface = new bEZ();

    protected TwoLevelPagerAdapter adapter;

    public static HbSelectionFragment newInstance(SelectChoice data) {
        final HbSelectionFragment b = new HbSelectionFragment();
        b.setArguments(Util.stuffs(data));
        return b;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TwoLevelPagerAdapter(getChildFragmentManager(), getArguments());
        mViewPager.setAdapter(adapter);
        /*
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
        mViewPager.setCurrentItem(0);
        bindothers(view);
        mInterface.request_new_filter();
    }

    @Override
    public boolean onPressBack() {
        return adapter.levelBack(mViewPager);
    }

    @Override
    public void onEvent(SelectChoice event_choice) {
        mInterface.SelectNow(
                mViewPager,
                adapter,
                event_choice,
                this);
    }

    @Override
    public void onEvent(MessageEvent event_integer) {

        mInterface.HomeSelect(
                mViewPager,
                adapter,
                event_integer.At(),
                this);
    }

    @Override
    protected int getXml() {
        return R.layout.hb_controlla;
    }

    public void setNagviationTitle(String mTitle) {
        title_navigation.setText(mTitle);
    }

    private void bindothers(View mv) {
        title_navigation = (TextView) mv.findViewById(R.id.sssl_title_navigation);
        back = (TintImageView) mv.findViewById(R.id.sssl_b_back);
        apply = (TintImageView) mv.findViewById(R.id.sssl_b_filter_apply);
        reset = (TintImageView) mv.findViewById(R.id.sssl_b_filter_clear);
        mProgress = (ProgressBar) mv.findViewById(R.id.sssl_ui_loading_progress_bar_xx);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInProgress) {
                    onPressBack();
                }
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("result_f", "here u go");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("result_f", "remove the filter and reset");
                start_new_filter();
            }
        });
        inProgress();
    }

    private void reveal_apply(boolean enabled) {
        if (enabled) {
            apply.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);
        } else {
            apply.setVisibility(View.GONE);
            reset.setVisibility(View.GONE);
        }
    }

    public void inProgress() {
        mProgress.animate().alpha(1f);
        isInProgress = true;
        reveal_apply(false);
    }

    public void inProgressDone() {
        mProgress.animate().alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                isInProgress = false;
                reveal_apply(true);
            }
        });
        initialize = false;
    }

    public void setInterfaceListener(bridgeEZ listener) {
        mInterface = listener;
    }

    private void start_new_filter() {
        initialize = true;
        mInterface.request_new_filter();
    }

}