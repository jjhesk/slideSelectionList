package com.tradlulu.demoCollectionList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintImageView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hkm.layout.Module.NonSwipe;
import com.hkm.slideselection.DynamicAdapter;
import com.hkm.slideselection.SelectChoice;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.bridgeChanger;
import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.model.hypebeaststore.ResponseProductList;
import com.hypebeast.sdk.api.resources.hbstore.Products;
import com.hypebeast.sdk.clients.HBStoreApiClient;
import com.tradlulu.demoCollectionList.MyList.basicSupport;


import java.util.ArrayList;
import java.util.Iterator;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 14/9/15.
 */
public class AppliedHBFilter extends AppCompatActivity implements bridgeChanger, retrofit.Callback<ReponseNormal> {

    private ReponseNormal latest_response;
    private SimpleStepSelectionFragment thecontroller;
    private SelectChoice lv0;
    private Handler uiHandler = new Handler();
    private TintImageView back;
    private ProgressBar mProgress;
    private TextView title_navigation;
    private boolean isInProgress = false;
    private ArrayList<SelectChoice> selection_memory = new ArrayList<>();
    private HBStoreApiClient client;
    private Products products_interface;
    private int level = 0;
    private boolean initialize = false;

    @Override
    public void success(ReponseNormal responseProductList, Response response) {
        latest_response = responseProductList;
        if (initialize) {
            thecontroller = SimpleStepSelectionFragment.firstLevel(basicSupport.byReturnJson(responseProductList));
            bindothers();
            getFragmentManager().beginTransaction().add(R.id.fragment, thecontroller, "TagSliderMain").addToBackStack(null).commit();
            thecontroller.setCallBackListenerBridge(this);
            inProgressDone();
        }

    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public void SelectNow(NonSwipe pager, DynamicAdapter mAdapter, int selected, int level_now, String selected_word) {

        if (!isInProgress) {
            if (level_now == 1) {
                Iterator<SelectChoice> io = selection_memory.iterator();
                while (io.hasNext()) {
                    SelectChoice mSelect = io.next();
                    if (mSelect.isTag(selected_word)) {
                        basicSupport.check_preapply_filter(selection_memory.iterator(), this);



                        return;
                    }
                }


                try {
                    selection_memory.add((SelectChoice) mAdapter.getCurrentLVObject());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // mAdapter.levelBack(pager);
            } else {
                inProgress();
                title_navigation.setText(selected_word);
                mAdapter.levelForward(pager, basicSupport.fromFirstColumn(selection_memory, latest_response, selected_word));
                inProgressDone();
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new HBStoreApiClient();
        products_interface = client.createProducts();
        setContentView(R.layout.fancy_1);
        try {
            initialize = true;
            products_interface.bycate("accessories", "socks", this);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private void bindothers() {
        back = (TintImageView) findViewById(R.id.back_level);
        title_navigation = (TextView) findViewById(R.id.title_navigation);
        mProgress = (ProgressBar) findViewById(R.id.ui_loading_progress_bar_xx);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thecontroller != null && !isInProgress) {
                    thecontroller.onPressBack();
                }
            }
        });
    }

    private void inProgress() {
        mProgress.animate().alpha(1f);
        isInProgress = true;
    }

    private void inProgressDone() {
        mProgress.animate().alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                isInProgress = false;
            }
        });
        initialize = false;
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

    private boolean inList(String selected) {
        Iterator<SelectChoice> io = selection_memory.iterator();
        while (io.hasNext()) {
            SelectChoice mSelect = io.next();
            if (mSelect.isTag(selected)) {
                return true;
            }
        }
        return false;
    }

    private void getlist(final String selected, final NonSwipe pager, final DynamicAdapter mAdapter) {
        Iterator<SelectChoice> io = selection_memory.iterator();
        while (io.hasNext()) {
            SelectChoice mSelect = io.next();
            if (mSelect.isTag(selected)) {
                mAdapter.levelForward(pager, mSelect);
                inProgressDone();
                return;
            }
        }
        /**
         * mock run async
         */
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SelectChoice list_end = new SelectChoice(false, selected);
                list_end.setResourceData(new String[]{"onef", "fwfawf", "wafe", "Ffsfsd", "sfafef", "Fasfe"});
                mAdapter.levelForward(pager, list_end);
                inProgressDone();
            }
        }, 800);

    }

}