package com.hkm.slideselection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.uiUtils.ScrollSmoothLineaerLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 10/9/15.
 */
public class SimpleSingleList extends Fragment {
    public static final String SELECTION = "selected";
    public static final String DATASTRING = "strings";
    protected ScrollSmoothLineaerLayoutManager mLayoutManager;
    protected UltimateRecyclerView mRecyclerView;
    protected ProgressBar mProgressBar;
    protected listSelect mChanger;
    protected List<String> mList = new ArrayList<>();

    public static Bundle stuffs(int selection, String[] list) {
        final Bundle b = new Bundle();
        final int[] single_selection = new int[]{selection};
        b.putIntArray(SELECTION, single_selection);
        b.putStringArray(DATASTRING, list);
        return b;
    }

    public static Bundle stuffs(int[] selections, String[] list) {
        final Bundle b = new Bundle();
        b.putIntArray(SELECTION, selections);
        b.putStringArray(DATASTRING, list);
        return b;
    }

    public static SimpleSingleList newInstance(Bundle args) {
        final SimpleSingleList list = new SimpleSingleList();
        list.setArguments(args);
        return list;
    }

    public static SimpleSingleList newInstance() {
        final SimpleSingleList list = new SimpleSingleList();
        return list;
    }


    protected int getListRecyclerView() {
        return R.id.sssl_list;
    }

    protected int getProgressBar() {
        return R.id.sssl_loading_progress;
    }

    protected int getXml() {
        return R.layout.list;
    }

    protected int getSmoothDuration() {
        return 300;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }

    protected final UltimateViewAdapter madapter = new UltimateViewAdapter() {
        @Override
        public RecyclerView.ViewHolder getViewHolder(View view) {
            return null;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            return null;
        }

        @Override
        public int getAdapterItemCount() {
            return mList.size();
        }

        @Override
        public long generateHeaderId(int i) {
            return 0;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
            return null;
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        }
    };

    protected void doneInitialLoading() {
        mProgressBar.animate().alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
        // mRecyclerView.setRefreshing(false);
        // EBus.getInstance().post(new EBus.ScreenBLK(false));
    }

    public void setChanger(listSelect mChanger) {
        this.mChanger = mChanger;
    }

    protected final ItemTouchListenerAdapter itemTouchListenerAdapter = new ItemTouchListenerAdapter(mRecyclerView.mRecyclerView,
            new ItemTouchListenerAdapter.RecyclerViewOnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View clickedView, int position) {
                    if (mChanger != null) {
                        mChanger.SelectNow(madapter, position);
                    }
                }

                @Override
                public void onItemLongClick(RecyclerView parent, View clickedView, int position) {

                }
            });

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (UltimateRecyclerView) view.findViewById(getListRecyclerView());
        mProgressBar = (ProgressBar) view.findViewById(getProgressBar());
        mLayoutManager = new ScrollSmoothLineaerLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false, getSmoothDuration());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setSaveEnabled(false);
        mRecyclerView.mRecyclerView.addOnItemTouchListener(itemTouchListenerAdapter);
        String[] data = getArguments().getStringArray(DATASTRING);
        if (data != null) {
            bindData(data);
        }
    }

    protected void bindData(String[] data) {
        for (int i = 0; i < data.length; i++) {
            madapter.insert(mList, data[i], mList.size());
        }
        mRecyclerView.setAdapter(madapter);
        doneInitialLoading();
    }

}
