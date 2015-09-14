package com.hypebeast.sdk.api.model.symfony;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.hypebeast.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 2/18/15.
 */
public class Product {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private int price;
    // @SerializedName("sale_price")
    //  private int sale_price;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("_links")
    private connection _links;
    @SerializedName("_embedded")
    private RelatedGroups _embedded;
    @SerializedName("images")
    private List<Image> images = new ArrayList<Image>();
    @SerializedName("variants")
    private List<Variant> variants = new ArrayList<Variant>();
    @SerializedName("attributes")
    private List<Attributes> attributes = new ArrayList<Attributes>();

    public final static String trick = "<style type=\"text/css\">\n" +
            "html, body {\n" +
            "width:100%;\n" +
            "height: 100%;\n" +
            "margin: 0px;\n" +
            "padding: 0px;\n" +
            "}\n" +
            "</style>";

    public int getId() {
        return id;
    }

    public List<Image> get_product_images() {
        return images;
    }

    public String get_cover_image() {
        return images.get(0).data.medium.href;
    }

    public String get_brand_name() {
        return _embedded.brands.get(0).getcodename();
    }

    public String price_sale() {
        return "";
    }
    public String price() {
        return price(String.valueOf((float) price / (float) 100));
    }

    private String price(final String p) {
        return "$" + p + " USD";
    }

    @SuppressLint("StringFormatMatches")
    public static String readPriceTag(Context c, final String price) {
        String formate = c.getResources().getString(R.string.price_tag_format_usd);
        return String.format(formate, price);
    }

    public String getSingleEndPoint() {
        return _links == null ? "" : _links.self.href;
    }

    public String getTitle() {
        return name;
    }

    public String get_desc() {
        return description;
    }



    public boolean hasVariance() {
        return variants.size() > 1;
    }

    public String getBrandUrl() {
        return _links.brand.href;
    }

    public boolean matchCurrentHref(String url) {
        return _links.self.href.equalsIgnoreCase(url);
    }

    public ArrayList<Variant> getMappedVariants() throws Exception {
        if (!hasVariance()) throw new Exception("variance not found");
        ArrayList<Variant> h = new ArrayList<Variant>();
        for (int i = 1; i < variants.size(); i++) {
            Variant m = variants.get(i).init();
            h.add(m);
        }
        return h;
    }

    public List<Attributes> getAttributes() {
        return attributes;
    }

    public ArrayList<ProductGroupContainer> getProductGroupContainer() {
        return _links.group_products;
    }

    /**
     * n can only be bigger than 1
     * n greater than 1
     *
     * @param n the input integer
     * @return the integer number.
     */
    public int getVariantID(int n) {
        return variants.get(n).getId();
    }
}
