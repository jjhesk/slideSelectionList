package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class featuredImage {
    @SerializedName("ID")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("menu_order")
    public int menu_order;
    @SerializedName("status")
    public status mstatus;
    @SerializedName("type")
    public posttype mposttype;
    @SerializedName("author")
    public author author;
    @SerializedName("content")
    public String htmlcontent;
    @SerializedName("link")
    public String finalenpoint;
    @SerializedName("slug")
    public String slug;
    @SerializedName("guid")
    public String guid;
    @SerializedName("date_tz")
    public String date_tz;
    @SerializedName("date_gmt")
    public String date_gmt;
    @SerializedName("attachment_meta")
    public attachmentImage image;
    @SerializedName("source")
    public String imageSrcUrl;
}
