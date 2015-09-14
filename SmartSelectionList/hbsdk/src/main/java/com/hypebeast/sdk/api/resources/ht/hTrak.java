package com.hypebeast.sdk.api.resources.ht;

import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hypetrak.htpost;
import com.hypebeast.sdk.api.model.popbees.mobileconfig;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by hesk on 8/9/15.
 */
public interface hTrak {
    @GET("/wp-json/posts")
    void search(
            final @Query("filter[s]") String search_keyword,
            final @Query("page") int page,
            final @Query("filter[posts_per_page]") int limit,
            final @Query("filter[order]") String ordering,
            final Callback<List<htpost>> cb) throws ApiException;


    @GET("/wp-json/posts")
    void lateset(
            final @Query("filter[posts_per_page]") int limit,
            final @Query("filter[order]") String ordering,
            final @Query("page") int page,
            final Callback<List<htpost>> cb
    ) throws ApiException;

    @GET("/wp-json/posts")
    void fromLink(
            final @Query("filter[name]") String last_slug,
            final Callback<List<htpost>> cb
    ) throws ApiException;

    @GET("/wp-json/posts")
    void lateset(
            final @Query("filter[posts_per_page]") int limit,
            final Callback<List<htpost>> cb
    ) throws ApiException;

    @GET("/wp-json/posts")
    void category(
            final @Query("filter[category_name]") String tag_cate,
            final Callback<List<htpost>> cb
    ) throws ApiException;

    @GET("/wp-json/posts")
    void category(
            final @Query("filter[category_name]") String tag_cate,
            final @Query("page") int page,
            final @Query("filter[posts_per_page]") int limit,
            final @Query("filter[order]") String ordering,
            final Callback<List<htpost>> cb
    ) throws ApiException;

    @GET("/wp-json/posts")
    void tag(
            final @Query("filter[tag]") String tag_cate,
            final @Query("page") int page,
            final @Query("filter[posts_per_page]") int limit,
            final @Query("filter[order]") String ordering,
            final Callback<List<htpost>> cb
    ) throws ApiException;

    /**
     * only used on installation override
     *
     * @param pid post ID
     * @param cb  call back object
     * @throws ApiException the exceptions
     */
    @GET("/wp-json/posts/{pid}")
    void the_post(
            final @Path("pid") long pid,
            final Callback<htpost> cb
    ) throws ApiException;


    @GET("/wp-json/mobile-config")
    void mobile_config(final Callback<mobileconfig> cb) throws ApiException;

}
