package com.hkm.slideselection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hesk on 10/9/15.
 */
public class SimpleSingleList extends Fragment {
    public static final String SELECTION = "selected";
    public static final String DATASTRING = "strings";


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

    protected RecyclerView mRecyclerView;

    protected int getListRecyclerView() {
        return R.id.sssl_list;
    }

    protected int getXml() {
        return R.layout.list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getXml(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(getListRecyclerView());


    }
}
