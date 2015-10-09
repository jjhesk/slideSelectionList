package com.hkm.slideselection.V1;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.hkm.slideselection.R;
import com.hkm.slideselection.app.HbSelectionFragment;
import com.hkm.slideselection.app.SimpleStepSelectionFragment;
import com.hkm.slideselection.worker.MessageEvent;
import com.hkm.slideselection.worker.SelectChoice;
import com.hkm.slideselection.worker.Util;
import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.uiUtils.ScrollSmoothLineaerLayoutManager;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hesk on 10/9/15.
 */
public class SimpleSingleList extends Fragment implements Filter.FilterListener {
    public static final String SELECTION = "selected";
    public static final String DATASTRING = "strings";
    public static final String LEVEL = "mlevel";
    protected boolean searchable = false;
    protected final FilterAdapter madapter = new FilterAdapter();
    protected List<String> mList = new ArrayList<>();
    protected ArrayList<String> mFilteredData = new ArrayList<>();
    protected ItemTouchListenerAdapter itemTouchListenerAdapter;
    private SelectChoice myLevelConfiguration, filteredConfiguration;
    protected ScrollSmoothLineaerLayoutManager mLayoutManager;
    protected UltimateRecyclerView mRecyclerView;
    protected ProgressBar mProgressBar;
    private Bus mBus;


    public static SimpleSingleList newInstance(int[] selections, String[] list, int order) {
        SimpleSingleList b = new SimpleSingleList();
        b.setArguments(Util.stuffs(selections, list, order));
        return b;
    }

    public static SimpleSingleList newInstance(int selections, String[] list, int order) {
        SimpleSingleList b = new SimpleSingleList();
        b.setArguments(Util.stuffs(selections, list, order));
        return b;
    }

    public static SimpleSingleList newInstance(Bundle args) {
        SimpleSingleList list = new SimpleSingleList();
        list.setArguments(args);
        return list;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        onAttachToContext(activity);
    }

    @Deprecated
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onAttachToContext(Context context) {
        try {
            if (getParentFragment() instanceof SimpleStepSelectionFragment) {
                SimpleStepSelectionFragment parent = (SimpleStepSelectionFragment) getParentFragment();
                mBus = parent.getBusInstance();
                myLevelConfiguration = parent.getLevel(getArguments().getInt(LEVEL) - 1);
            } else if (getParentFragment() instanceof HbSelectionFragment) {
                HbSelectionFragment parent = (HbSelectionFragment) getParentFragment();
                mBus = parent.getBusInstance();
            }
        } catch (Exception e) {
            myLevelConfiguration = null;
            Log.d("noconfig", "noconfig");
        }
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
        public View click_detection;

        public binder(View itemView) {
            super(itemView);
            tvtime = (TextView) itemView.findViewById(getTextViewItem());
            if (itemView.findViewById(R.id.sssl_item_touch) != null)
                click_detection = (View) itemView.findViewById(R.id.sssl_item_touch);
        }
    }

    private class FilterAdapter extends UltimateViewAdapter implements Filterable {

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

        /**
         * Returns a filter that can be used to constrain data with a filtering
         * pattern.This method is usually implemented by {@link Adapter}classes.
         *
         * @return a filter used to constrain data
         */
        @Override
        public android.widget.Filter getFilter() {
            android.widget.Filter filter = new android.widget.Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    final FilterResults filterResults = new FilterResults();
                    mFilteredData.clear();
                    if (!TextUtils.isEmpty(constraint)) {
                        // Retrieve the autocomplete results.
                        List<String> searchData = new ArrayList<>();
                        Iterator<String> typeAheadData = myLevelConfiguration.getSource().iterator();
                        while (typeAheadData.hasNext()) {
                            String str = typeAheadData.next();
                            final boolean startwith = str.toLowerCase().startsWith(constraint.toString().toLowerCase().trim());
                            final boolean contains = str.toLowerCase().contains(constraint.toString().toLowerCase().trim());
                            if (startwith || contains) {
                                searchData.add(str);
                            }
                        }
                        // Assign the data to the FilterResults
                        filterResults.values = searchData;
                        filterResults.count = searchData.size();
                    } else {
                        //perform when the search field is empty
                        //mFilteredData.addAll(mList);
                        //  updateNewList(myLevelConfiguration);
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results.values != null) {
                        ArrayList<String> tries = (ArrayList<String>) results.values;
                        if (tries.size() > 0) {
                            filteredConfiguration = myLevelConfiguration.clone();
                            filteredConfiguration.setResourceData(tries);
                            filteredConfiguration.setLevel(myLevelConfiguration.getLevel());
                            filteredConfiguration.setTag(myLevelConfiguration.getTag());
                            updateNewList(filteredConfiguration);
                        } else {
                            updateNewList(myLevelConfiguration);
                        }
                    }
                }
            };
            return filter;
        }
    }

    protected void doneInitialLoading() {
        mProgressBar.animate().alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }


    private void makeSelection(int position) {
        if (myLevelConfiguration != null) {
            myLevelConfiguration.setSelectedAtPos(position);
            mBus.post(myLevelConfiguration);
        } else {
            MessageEvent mmes;
            if (searchable) {
                mmes = new MessageEvent(myLevelConfiguration.getFromSubFilter(filteredConfiguration));
            } else {
                mmes = new MessageEvent(position);
            }
            mBus.post(mmes);
        }
    }

    protected void onBindHolder(final binder holder, final int position) {
        holder.tvtime.setText(mList.get(position));
        if (holder.click_detection != null && holder.click_detection instanceof MaterialRippleLayout) {
            ((MaterialRippleLayout) holder.click_detection).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeSelection(position);
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
                        if (clickedView != null && clickedView instanceof MaterialRippleLayout) {

                        } else makeSelection(position);
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

        // if (savedInstanceState == null) {
        if (getArguments() != null && getArguments().getStringArray(DATASTRING) != null) {
            bindData(getArguments().getStringArray(DATASTRING));
        } else {
            Log.d("error on null", "not found on null pointer");
        }
        // }
    }

    public void updateNewList(SelectChoice configuration) {
        mList.clear();
        for (int i = 0; i < configuration.getSimpleSource().length; i++) {
            madapter.insert(mList, configuration.getSimpleSource()[i], mList.size());
        }
        mRecyclerView.setAdapter(madapter);
        madapter.notifyDataSetChanged();
        doneInitialLoading();
    }

    public void updateFilterOptionItems(SelectChoice configuration) {
        myLevelConfiguration = configuration;
    }

    protected void bindData(String[] data) {
        // mList.clear();
        for (int i = 0; i < data.length; i++) {
            madapter.insert(mList, data[i], mList.size());
        }
        mRecyclerView.setAdapter(madapter);
        doneInitialLoading();
    }


    public String getItemStringTitle(int position) {
        return getArguments().getStringArray(DATASTRING)[position];
    }

    @Override
    public void onFilterComplete(int count) {
        if (searchable) {
            if (count > 0) {
                //  showSuggestions();
            } else {
                //  dismissSuggestions();
            }
        }
    }

    public void updateSearchWord(CharSequence s) {
        if (madapter != null && madapter instanceof Filterable && searchable) {
            ((Filterable) madapter).getFilter().filter(s, SimpleSingleList.this);
        }
    }

    public void setSearchable(boolean enable) {
        searchable = enable;
    }
}
