package com.hypebeast.sdk.api.model.wprest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hesk on 3/7/15.
 */
public class author {
    @SerializedName("ID")
    public int id;

    @SerializedName("username")
    public String username;

    @SerializedName("name")
    public String name;

    @SerializedName("first_name")
    public String first_name;

    @SerializedName("last_name")
    public String last_name;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("slug")
    public String slug;

    @SerializedName("URL")
    public String URL;

    @SerializedName("avatar")
    public String avatar;

    @SerializedName("registered")
    public String mregistered;

    @SerializedName("meta")
    public meta mmate;

}
