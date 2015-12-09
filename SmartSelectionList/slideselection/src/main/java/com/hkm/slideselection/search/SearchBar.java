package com.hkm.slideselection.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkm.slideselection.R;
import com.hkm.slideselection.StyleUtil;

/**
 * Created by hesk on 8/10/15.
 */
public class SearchBar implements TextView.OnEditorActionListener, TextWatcher {
    private RelativeLayout container;
    private EditText mEditText;
    private onEnterQuery enter;
    private ImageButton searchButton;
    private TextView mPlaceHolder;
    private onEnterQuery mQuery;
    private Context mContext;
    private boolean enableSearch;

    public SearchBar(View root, onEnterQuery query) {
        this(root);
        mContext = root.getContext();
        String placeholder = StyleUtil.resolveString(mContext, R.attr.selectionsearchbar_placeholder);
        if (placeholder != null) {
            mEditText.setHint(placeholder);
        }
        mEditText.setOnEditorActionListener(this);
        mEditText.addTextChangedListener(this);
        mQuery = query;
    }

    public SearchBar(View root, String placeholder, onEnterQuery query) {
        this(root);
        //mPlaceHolder.setText(placeholder);
        mEditText.setHint(placeholder);
        mEditText.setOnEditorActionListener(this);
        mEditText.addTextChangedListener(this);
        mQuery = query;
    }

    public SearchBar(View root) {
        container = (RelativeLayout) root.findViewById(R.id.sssl_search_container);
        mEditText = (EditText) root.findViewById(R.id.sssl_search_place);
        //mPlaceHolder = (TextView) root.findViewById(R.id.sssl_search_placeholder);
        searchButton = (ImageButton) root.findViewById(R.id.sssl_search_icon);
    }

    public boolean isEnableSubFilter() {
        return enableSearch;
    }

    public void enable(boolean b) {
        enableSearch = b;
        if (enableSearch) {
            if (container.getVisibility() == View.GONE) {
                container.setVisibility(View.VISIBLE);
            }
        } else {
            if (container.getVisibility() == View.VISIBLE) {
                container.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Called when an action is being performed.
     *
     * @param v        The view that was clicked.
     * @param actionId Identifier of the action.  This will be either the
     *                 identifier you supplied, or {@link EditorInfo#IME_NULL
     *                 EditorInfo.IME_NULL} if being called due to the enter key
     *                 being pressed.
     * @param event    If triggered by an enter key, this is the event;
     *                 otherwise, this is null.
     * @return Return true if you have consumed the action, else false.
     */
    @Override
    public boolean onEditorAction(final TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_GO) {
            handled = true;
        }
        return handled;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mQuery != null) {
            mQuery.query(s);
        }
    }


    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface onEnterQuery {
        void query(CharSequence v);
    }
}
