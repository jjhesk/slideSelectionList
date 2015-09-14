package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.clients.PBEditorialClient;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by hesk on 3/7/15.
 */
public class post {
    @SerializedName("ID")
    public long id;
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
    @SerializedName("title")
    public String title;
    @SerializedName("link")
    public String finalenpoint;
    @SerializedName("slug")
    public String slug;
    @SerializedName("guid")
    public String guid;
    @SerializedName("excerpt")
    public String excerpt;
    @SerializedName("sticky")
    public boolean isSticky;
    @SerializedName("date")
    public Date mdate;
    @SerializedName("modified")
    public Date mmodified;
    @SerializedName("date_tz")
    public String date_tz;
    @SerializedName("date_gmt")
    public Date date_gmt;
    @SerializedName("featured_image")
    public featuredImage FImage;
    @SerializedName("terms")
    public terms terms;

    /**
     * this is the simple version for reading only
     */


}
