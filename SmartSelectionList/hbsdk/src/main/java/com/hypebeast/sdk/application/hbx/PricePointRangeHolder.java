package com.hypebeast.sdk.application.hbx;

/**
 * Created by hesk on 15/9/15.
 */
public class PricePointRangeHolder {
    private final String from;
    private final String to;

    public PricePointRangeHolder(String x1, String x2) {
        from = x1;
        to = x2;
    }

    private static float string_float(String y) {
        if (y.equalsIgnoreCase("")) return 0.0f;
        return Float.parseFloat(y);
    }

    public static PricePointRangeHolder[] gen(String a, String b) {
        float ha = string_float(a) * 100;
        float hb = string_float(b) * 100;
        return new PricePointRangeHolder[]{new PricePointRangeHolder((int) ha + "", (int) hb + "")};
    }

}
