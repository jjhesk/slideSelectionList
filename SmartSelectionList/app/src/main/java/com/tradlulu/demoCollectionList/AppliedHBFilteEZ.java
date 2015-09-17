package com.tradlulu.demoCollectionList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.V2.TwoLevelPagerAdapter;
import com.hkm.slideselection.app.HbSelectionFragment;
import com.hkm.slideselection.app.ViewPagerHolder;
import com.hkm.slideselection.worker.bridgeEZ;
import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.resources.hbstore.Products;
import com.hypebeast.sdk.clients.HBStoreApiClient;
import com.tradlulu.demoCollectionList.MyList.hbSuport;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 14/9/15.
 */
public class AppliedHBFilteEZ extends AppCompatActivity implements bridgeEZ, retrofit.Callback<ReponseNormal> {

    private ReponseNormal latest_response, default_endpoint;
    private HbSelectionFragment thecontroller;
    private SelectChoice menu_data;
    private boolean isInProgress = false;
    private ArrayList<SelectChoice> selection_memory = new ArrayList<>();
    private HBStoreApiClient client;
    private Products products_interface;
    private int level = 0;
    private boolean initialize = false;
    private TwoLevelPagerAdapter mAdapter;
    private ViewPagerHolder mPager;
    private ProgressBar progresb;

    @Override
    public void success(ReponseNormal responseProductList, Response response) {
        latest_response = responseProductList;
        if (level == 0) {
            menu_data = hbSuport.byReturnJson(responseProductList);
            if (thecontroller == null || default_endpoint == null) {
                default_endpoint = responseProductList;
                thecontroller = HbSelectionFragment.newInstance(menu_data);
                getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "TagSliderMain")
                        .addToBackStack(null).commit();

            }

            progresb.setVisibility(View.GONE);
        } else if (level == 1) {
            menu_data = hbSuport.byReturnJson(responseProductList, selection_memory);
            mAdapter.levelBack(mPager, menu_data);
            thecontroller.inProgressDone();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        final SMessage mss = SMessage.message("Connection error." + error.getMessage());
        mss.show(getFragmentManager(), "con_err");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new HBStoreApiClient();
        products_interface = client.createProducts();
        setContentView(R.layout.frame_oo);
        progresb = (ProgressBar) findViewById(R.id.ui_loading_progress_bar_frame);
        initialize = true;
        request_new_filter();
    }

    @Override
    public void request_applied() {
        if (latest_response.product_list.total > 0) {

        } else {
            final SMessage mss = SMessage.message("There is no result from the filter conditions now.");
            mss.show(getFragmentManager(), "warning");
        }
    }

    @SuppressLint("ValidFragment")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class SMessage extends DialogFragment {
        public static SMessage message(final String mes) {
            Bundle h = new Bundle();
            h.putString("message", mes);
            SMessage e = new SMessage();
            e.setArguments(h);
            return e;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getArguments().getString("message"))
                    .setNeutralButton(R.string.okay, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            return builder.create();
        }
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
        selection_memory.clear();
        if (default_endpoint != null && initialize) {
            menu_data = hbSuport.byReturnJson(default_endpoint);
            latest_response = default_endpoint;
            mAdapter.updateHome(mPager, menu_data);
            thecontroller.inProgressDone();
        } else {
            try {
                products_interface.bysubcate("accessories", "socks", this);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void SelectNow(ViewPager pager, TwoLevelPagerAdapter mAdapter, SelectChoice choice, HbSelectionFragment hb) {
        try {
            this.mAdapter = mAdapter;
            mPager = (ViewPagerHolder) pager;
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
        menu_data.setSelectedAtPos(position);
        thecontroller.setNagviationTitle(menu_data.selected_string());
        mAdapter.levelForward(
                pager,
                hbSuport.fromFirstColumn(
                        selection_memory,
                        latest_response,
                        menu_data.selected_string()
                ));
        return;
    }
}
