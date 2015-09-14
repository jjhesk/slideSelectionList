package com.hypebeast.sdk.api.resources.hbstore;

import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.ResponseSingleProduct;

import retrofit.http.GET;

/**
 * Created by hesk on 7/1/2015.
 */
public interface SingleProduct {
    @GET("")
    ResponseSingleProduct getIt() throws ApiException;

}
