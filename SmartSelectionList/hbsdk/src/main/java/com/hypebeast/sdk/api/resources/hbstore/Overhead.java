package com.hypebeast.sdk.api.resources.hbstore;

import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.MobileConfig;
import com.hypebeast.sdk.api.model.popbees.mobileconfig;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by hesk on 10/9/15.
 */
public interface Overhead {

    @GET("/wp-json/mobile-config")
    void mobile_config(final Callback<MobileConfig> cb) throws ApiException;

}
