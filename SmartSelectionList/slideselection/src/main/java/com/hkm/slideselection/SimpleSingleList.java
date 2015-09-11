package com.hkm.slideselection;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
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
    protected ItemTouchListenerAdapter itemTouchListenerAdapter;
    private listSelect listener;

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getParentFragment() instanceof SimpleStepSelectionFragment) {
            SimpleStepSelectionFragment f = (SimpleStepSelectionFragment) getParentFragment();
            this.listener = f.listener;
        }
    }

    public static SimpleSingleList newInstance() {
        final SimpleSingleList list = new SimpleSingleList();
        return list;
    }


    protected int getListRecyclerView() {
        return R.id.sssl_list;
    }

    protected int getTextViewItem() {
        return R.id.sssl_item_textview;
    }

    protected int getProgressBar() {
        return R.id.sssl_loading_progress;
    }

    protected int getListItemLayout() {
        return R.layout.so_simple_item_rippletouch;
    }

    protected int getXml() {
        return R.layout.list;
    }

    protected int getSmoothDuration() {
        return 300;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }


    public class binder extends UltimateRecyclerviewViewHolder {
        public final TextView tvtime;
        public final View click_detection;

        public binder(View itemView) {
            super(itemView);
            tvtime = (TextView) itemView.findViewById(getTextViewItem());
            click_detection = (View) itemView.findViewById(R.id.sssl_item_touch);

        }
    }

    protected final UltimateViewAdapter madapter = new UltimateViewAdapter() {
        @Override
        public UltimateRecyclerviewViewHolder getViewHolder(View view) {
            return new UltimateRecyclerviewViewHolder(view);
        }

        protected binder newViewHolder(View var1) {
            return new binder(var1);
        }

        @Override
        public binder onCreateViewHolder(ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext()).inflate(getListItemLayout(), parent, false);
            return newViewHolder(v);
        }

        @Override
        public int getAdapterItemCount() {
            return mList.size();
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            onBindHolder((binder) holder, position);
        }


        @Override
        public long generateHeaderId(int position_item) {
            return 0;
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

    //use dragger in here
    public void setChanger(listSelect mChanger) {
        this.mChanger = mChanger;
    }

    protected void onBindHolder(final binder holder, final int position) {
        holder.tvtime.setText(mList.get(position));
        if (holder.click_detection instanceof MaterialRippleLayout) {
            MaterialRippleLayout m = (MaterialRippleLayout) holder.click_detection;
            m.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.SelectNow(madapter, position);
                    }
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (UltimateRecyclerView) view.findViewById(getListRecyclerView());
        itemTouchListenerAdapter = new ItemTouchListenerAdapter(mRecyclerView.mRecyclerView,
                new ItemTouchListenerAdapter.RecyclerViewOnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View clickedView, int position) {
                        if (listener != null) {
                            if (clickedView instanceof MaterialRippleLayout) {
                            } else listener.SelectNow(madapter, position);
                        }
                    }

                    @Override
                    public void onItemLongClick(RecyclerView parent, View clickedView, int position) {

                    }
                });
        mProgressBar = (ProgressBar) view.findViewById(getProgressBar());
        mLayoutManager = new ScrollSmoothLineaerLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false, getSmoothDuration());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setSaveEnabled(false);
        mRecyclerView.mRecyclerView.addOnItemTouchListener(itemTouchListenerAdapter);
        if (getArguments() != null && getArguments().getStringArray(DATASTRING) != null) {
            bindData(getArguments().getStringArray(DATASTRING));
        } else {
            Log.d("error on null", "not found on null pointer");
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
