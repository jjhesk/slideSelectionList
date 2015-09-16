package com.tradlulu.demoCollectionList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintImageView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hkm.slideselection.V1.DynamicAdapter;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.V2.TwoLevelPagerAdapter;
import com.hkm.slideselection.app.HbSelectionFragment;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.app.ViewPagerHolder;
import com.hkm.slideselection.worker.bridgeEZ;
import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.resources.hbstore.Products;
import com.hypebeast.sdk.application.hbx.FilterApplication;
import com.hypebeast.sdk.clients.HBStoreApiClient;
import com.tradlulu.demoCollectionList.MyList.hbSuport;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 14/9/15.
 */
public class AppliedHBFilteEZ extends AppCompatActivity implements bridgeEZ, retrofit.Callback<ReponseNormal> {

    private ReponseNormal latest_response;
    private HbSelectionFragment thecontroller;
    private SelectChoice lv0;
    private boolean isInProgress = false;
    private ArrayList<SelectChoice> selection_memory = new ArrayList<>();
    private HBStoreApiClient client;
    private Products products_interface;
    private int level = 0;
    private boolean initialize = false;
    private TwoLevelPagerAdapter mAdapter;
    private ViewPagerHolder mPager;

    @Override
    public void success(ReponseNormal responseProductList, Response response) {
        latest_response = responseProductList;
        if (initialize) {
            selection_memory.clear();
            lv0 = hbSuport.byReturnJson(responseProductList);
            thecontroller = HbSelectionFragment.newInstance(lv0);
            getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "TagSliderMain").addToBackStack(null).commit();
            thecontroller.setInterfaceListener(this);
        } else if (level == 1) {
            lv0 = hbSuport.byReturnJson(responseProductList, selection_memory.iterator());
            mAdapter.levelBack(mPager, lv0);
        }
        thecontroller.inProgressDone();
    }

    @Override
    public void failure(RetrofitError error) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new HBStoreApiClient();
        products_interface = client.createProducts();
        setContentView(R.layout.frame_oo);
        request_new_filter();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        if (thecontroller != null) {
            if (!isInProgress && !thecontroller.onPressBack())
                super.onBackPressed();
        } else super.onBackPressed();
    }

    @Override
    public void request_new_filter() {
        try {
            initialize = true;
            products_interface.bysubcate("accessories", "socks", this);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SelectNow(ViewPager pager, TwoLevelPagerAdapter mAdapter, SelectChoice choice, HbSelectionFragment hb) {
        try {
            this.mAdapter = mAdapter;
            this.mPager = (ViewPagerHolder) pager;
            level = choice.getLevel();
            if (!isInProgress) {
                if (level == 1) {
                    selection_memory.add(choice);
                    products_interface.bysubcate("accessories", "socks", hbSuport.developJson(selection_memory.iterator()), this);
                    thecontroller.inProgress();
                    return;
                }
            }
        } catch (ApiException e) {
            e.printStackTrace();
            Log.d("check_f_result_err", e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("check_f_result_err", e.getMessage());
        }
    }

    @Override
    public void HomeSelect(ViewPager pager, TwoLevelPagerAdapter mAdapter, int position, HbSelectionFragment hb) {
        lv0.setSelectedAtPos(position);
        thecontroller.setNagviationTitle(lv0.selected_string());
        mAdapter.levelForward(
                pager,
                hbSuport.fromFirstColumn(
                        selection_memory,
                        latest_response,
                        lv0.selected_string()
                ));
        return;
    }
}
