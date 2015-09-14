package com.hypebeast.sdk.api.model.hbeditorial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hesk on 1/9/15.
 */
public class configbank {
    @SerializedName("base")
    public String base;
    @SerializedName("menu")
    public ArrayList<Menuitem> menu;
    @SerializedName("featurebanner")
    public ArrayList<Slide> featurebanner;
    @SerializedName("splash_screen")
    public splash splash_screen;
}
