package com.tradlulu.demoCollectionList.Module;

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

import com.hkm.slideselection.V2.TwoLevelPagerAdapter;
import com.hkm.slideselection.app.HbSelectionFragment;
import com.hkm.slideselection.app.ViewPagerHolder;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.worker.bridgeEZ;
import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.resources.hbstore.Products;
import com.hypebeast.sdk.clients.HBStoreApiClient;
import com.tradlulu.demoCollectionList.R;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 21/9/15.
 */
public class HBFilterEZHelper implements bridgeEZ, retrofit.Callback<ReponseNormal> {
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
    protected AppCompatActivity activity;

    public HBFilterEZHelper(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void init() {
        client = new HBStoreApiClient();
        products_interface = client.createProducts();
        activity.setContentView(R.layout.frame_oo);
        progresb = (ProgressBar) activity.findViewById(R.id.ui_loading_progress_bar_frame);
        initialize = true;
        request_new_filter();
    }

    @Override
    public void dismiss_back() {

    }

    @Override
    public void request_applied() {
        if (latest_response.product_list.total > 0) {

        } else {
            final SMessage mss = SMessage.message("There is no result from the filter conditions now.");
            mss.show(activity.getFragmentManager(), "warning");
        }
    }

    @Override
    public void success(ReponseNormal responseProductList, Response response) {
        latest_response = responseProductList;
        if (level == 0) {
            menu_data = HBDataSupport.byReturnJson(responseProductList);
            if (thecontroller == null || default_endpoint == null) {
                default_endpoint = responseProductList;
                thecontroller = HbSelectionFragment.newInstance(menu_data);

                activity.getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "TagSliderMain")
                        .addToBackStack(null).commit();
                thecontroller.setControlInterface(this);

            }

            progresb.setVisibility(View.GONE);
        } else if (level == 1) {
            menu_data = HBDataSupport.byReturnJson(responseProductList, selection_memory);
            mAdapter.levelBack(mPager, menu_data);
            thecontroller.inProgressDone();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        final SMessage mss = SMessage.message("Connection error." + error.getMessage());
        mss.show(activity.getFragmentManager(), "con_err");
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
    public boolean onBackPressed() {
        if (thecontroller != null) {
            if (!isInProgress && !thecontroller.onPressBack()) {
            }
            return true;
        } else
            return false;
    }

    @Override
    public void request_new_filter() {
        selection_memory.clear();
        if (default_endpoint != null && initialize) {
            menu_data = HBDataSupport.byReturnJson(default_endpoint);
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
                    products_interface.bysubcate("accessories", "socks", HBDataSupport.developJson(selection_memory.iterator()), this);
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
                HBDataSupport.fromFirstColumn(
                        selection_memory,
                        latest_response,
                        menu_data.selected_string()
                ));
        return;
    }
}
