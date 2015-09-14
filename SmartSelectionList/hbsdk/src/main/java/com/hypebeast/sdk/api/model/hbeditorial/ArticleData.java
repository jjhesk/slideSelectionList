package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hesk on 21/4/15.
 */
public class ArticleData {
    @SerializedName("id")
    public long id;
    @SerializedName("date")
    public String date;
    @SerializedName("title")
    public String title;
    @SerializedName("slug")
    public String slug;
    @SerializedName("_links")
    public Links _links;
    @SerializedName("commentCount")
    private int commentCount = -1;

    public ArticleData() {
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public Links get_links() {
        return _links;
    }

    public String getCate() {
        try {
            return _links.getCategories().get(0).getName();
        } catch (Exception e) {
            return "Uncategorized";
        }
    }

    public String getMoment() throws ParseException {
        PrettyTime p = new PrettyTime();
        //http://www.datameer.com/documentation/display/DAS20/Date+and+Time+Parse+Patterns
        String ISO_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
        String ISO_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        String ISO_FORMAT3 = "yyyy-MM-dd HH:mm:ss z";
        String ISO_FORMAT4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String ISO_FORMAT5 = "yyyy-MM-dd'T'HH:mm:ssZ";

        Date parsedTimeStamp = new SimpleDateFormat(ISO_FORMAT5, new Locale("en", "US")).parse(getDate());
        return p.format(parsedTimeStamp);
    }


    public String getShortCommentCount() {
        if (commentCount > -1) {
            return " " + commentCount + " comments";
        } else return "";
    }

    public void setCommentCount(int n) {
        commentCount = n;
    }
}
