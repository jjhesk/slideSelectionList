package com.hypebeast.sdk.api.model.symfony;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hesk on 2/18/15.
 */
public class Variant {
    @SerializedName("id")
    private int id;
    @SerializedName("master")
    private boolean master;
    @SerializedName("options")
    private ArrayList<VarientContent> options;
    @SerializedName("on_hand")
    private int on_hand;
    @SerializedName("on_hold")
    private int on_hold;

    enum Type {
        OUT_OF_STOCK,
        LAST_ONE,
        NORMAL
    }

    //below this line will be the internal use valuables not directly parsed from the json
    //these are post process and handled by the program and they are not from the json
    private String variant_type_presentation;
    private String option_name;
    private String stock_config;
    private boolean instock;
    private int stock_status;

    public Type stockStatus;
    public VariantItem.VType variant_type;
    public static int OUT_OF_STOCK = -1, LAST_ONE = 1, NORMAL = 0;

    public Variant() {

    }

    public int getId() {
        return id;
    }

    /**
     * some pre-processing from the data
     *
     * @return the object Variant
     */
    public Variant init() {
        variant_type_presentation = options.get(0).option.presentation;
        variant_type = options.get(0).option.name;

        final int delta = on_hand - on_hold;
        if (delta == 1) {
            stock_config = " (Last One Left)";
            stock_status = LAST_ONE;
            stockStatus = Type.LAST_ONE;
        } else if (delta == 0) {
            stock_config = " (Out of Stock)";
            stock_status = OUT_OF_STOCK;
            stockStatus = Type.OUT_OF_STOCK;
        } else {
            stock_config = "";
            stock_status = NORMAL;
            stockStatus = Type.NORMAL;
        }
        instock = delta > 0;

        return this;
    }

    public int stockStatus() {
        return stock_status;
    }

    public String getType() {
        return variant_type_presentation;
    }

    public VariantItem.VType getTypeConstant() {
        return variant_type;
    }

    public String getMetaValueFromType() {
        return option_name;
    }

    public String stock_logic_configuration() {
        return stock_config;
    }

    public String displayLastItem() {
        return instock ? "Last Item" : "";
    }

    public boolean instock() {
        return stock_status == LAST_ONE || stock_status == NORMAL;
    }
}
