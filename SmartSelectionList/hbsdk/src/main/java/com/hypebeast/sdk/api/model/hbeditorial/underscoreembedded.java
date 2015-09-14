package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hesk on 22/4/15.
 */
public class underscoreembedded {
    @SerializedName("items")
    public ArrayList<ArticleData> items;
    @SerializedName("disqus_identifier")
    public String disqus_identifier;
    @SerializedName("video_embed_code")
    public String video_embed_code;
    @SerializedName("author")
    public String author;
    @SerializedName("attachments")
    public ArrayList<Attachment> attachments;

    public underscoreembedded() {
    }


    public String getVideo_embed_code() throws NullPointerException {
        return video_embed_code;
    }

    public String getDisqus_identifier() {
        return disqus_identifier;
    }

    public ArrayList<ArticleData> getItems() {
        return items;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public String getAuthor() {
        return author;
    }
}
