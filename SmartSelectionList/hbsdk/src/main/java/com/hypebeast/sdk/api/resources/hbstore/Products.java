package com.hypebeast.sdk.api.resources.hbstore;

import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypebeaststore.ReponseNormal;
import com.hypebeast.sdk.api.model.hypebeaststore.ResponseSingleProduct;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by hesk on 30/6/15.
 */
public interface Products {

    @GET("/products/{product_identification_no}")
    void PIDReq(
            final @Path("product_identification_no") long product_id, final Callback<ResponseSingleProduct> result) throws ApiException;


    @GET("/categories/{catename}")
    void bycate(
            final @Path("catename") String category_name,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}")
    void bycate(
            final @Path("catename") String category_name,
            final @Query("filter") String json,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}")
    void bycate(
            final @Path("catename") String category_name,
            final @Query("page") int page_num,
            final @Query("limit") int limit,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}")
    void bycate(
            final @Path("catename") String category_name,
            final @Query("filter") String json,
            final @Query("page") int page_num,
            final @Query("limit") int limit,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}")
    void bycate(
            final @Path("catename") String category_name,
            final @Query("page") int page_num,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}")
    void bycate(
            final @Path("catename") String category_name,
            final @Query("filter") String json,
            final @Query("page") int page_num,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}/{subcate}")
    void bysubcate(
            final @Path("catename") String category_name,
            final @Path("subcate") String subcate,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}/{subcate}")
    void bysubcate(
            final @Path("catename") String category_name,
            final @Path("subcate") String subcate,
            final @Query("filter") String json,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}/{subcate}")
    void bysubcate(
            final @Path("catename") String category_name,
            final @Path("subcate") String subcate,
            final @Query("page") int page_num,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}/{subcate}")
    void bysubcate(
            final @Path("catename") String category_name,
            final @Path("subcate") String subcate,
            final @Query("filter") String json,
            final @Query("page") int page_num,
            final Callback<ReponseNormal> result) throws ApiException;


    @GET("/categories/{catename}/{subcate}")
    void bysubcate(
            final @Path("catename") String category_name,
            final @Path("subcate") String subcate,
            final @Query("page") int page_num,
            final @Query("limit") int limit,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/categories/{catename}/{subcate}")
    void bysubcate(
            final @Path("catename") String category_name,
            final @Path("subcate") String subcate,
            final @Query("filter") String json,
            final @Query("page") int page_num,
            final @Query("limit") int limit,
            final Callback<ReponseNormal> result) throws ApiException;

    @GET("/search")
    void search(final @Query("q") String keyword,
                final Callback<ReponseNormal> result) throws ApiException;

    @GET("/search")
    void search(final @Query("q") String keyword,
                final @Query("page") int page_num,
                final Callback<ReponseNormal> result) throws ApiException;

    @GET("/search")
    void search(final @Query("q") String keyword,
                final @Query("page") int page_num,
                final @Query("limit") int limit,
                final Callback<ReponseNormal> result) throws ApiException;


    @GET("/{special_item}")
    void mainList(final @Path("special_item") String special_entry,
                  final Callback<ReponseNormal> result) throws ApiException;

    @GET("/{special_item}")
    void mainList(final @Path("special_item") String special_entry,
                  final @Query("page") int page_num,
                  final @Query("limit") int limit,
                  final Callback<ReponseNormal> result) throws ApiException;

    @GET("/{special_item}")
    void mainList(final @Path("special_item") String special_entry,
                  final @Query("filter") String jsonString,
                  final @Query("page") int page_num,
                  final @Query("limit") int limit,
                  final Callback<ReponseNormal> result) throws ApiException;


    @GET("/{special_item}")
    void mainList(final @Path("special_item") String special_entry,
                  final @Query("filter") String jsonString,
                  final Callback<ReponseNormal> result) throws ApiException;


    @GET("/brands/{brand_name}")
    void bybrand(final @Path("brand_name") String brand_name,
                 final Callback<ReponseNormal> result) throws ApiException;

    @GET("/brands/{brand_name}")
    void bybrand(final @Path("brand_name") String brand_name,
                 final @Query("filter") String jsonString,
                 final Callback<ReponseNormal> result) throws ApiException;


}
