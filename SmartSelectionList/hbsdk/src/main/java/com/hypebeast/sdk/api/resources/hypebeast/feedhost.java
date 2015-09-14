package com.hypebeast.sdk.api.resources.hypebeast;

import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hbeditorial.Foundation;
import com.hypebeast.sdk.api.model.hbeditorial.ResponsePostFromSearch;
import com.hypebeast.sdk.api.model.hbeditorial.ResponsePostW;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by hesk on 13/8/15.
 */
public interface feedhost {


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
            final Callback<ResponsePostW> cb
    ) throws ApiException;

    @GET("/page/{page_no}")
    void the_recent_page(
            final @Path("page_no") int page_number,
            final Callback<ResponsePostW> cb
    ) throws ApiException;

    @GET("/{cate_name}/page/{page_no}")
    void cate_list(
            final @Path("page_no") int pagepage_number_no,
            final @Path("cate_name") String tag_keyword,
            final Callback<ResponsePostW> cb
    ) throws ApiException;

    @GET("/tags/{tag_text}/page/{page_no}")
    void tag_list(
            final @Path("page_no") int pagepage_number_no,
            final @Path("tag_text") String tag_keyword,
            final Callback<ResponsePostW> cb
    ) throws ApiException;

    @GET("/search/page/{page_no}")
    void search(
            final @Query("s") String search_keyword,
            final @Path("page_no") int page_number,
            final Callback<ResponsePostFromSearch> cb) throws ApiException;

    @GET("/api/mobile-app-config")
    void mobile_config(
            final Callback<Foundation> cb
    ) throws ApiException;

}
