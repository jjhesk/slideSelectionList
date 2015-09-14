package com.hypebeast.sdk.Util;

import com.hypebeast.sdk.clients.PBEditorialClient;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by hesk on 7/5/2015.
 */
public class PrettyTime {
    public static String getMoment(String FORMAT, Date rawdate
    ) throws ParseException {
        org.ocpsoft.prettytime.PrettyTime p = new org.ocpsoft.prettytime.PrettyTime();
        return p.format(rawdate);
    }

    public static String getMoment(String FORMAT, String rawString
    ) throws ParseException {
        org.ocpsoft.prettytime.PrettyTime p = new org.ocpsoft.prettytime.PrettyTime();
        java.util.Date parsedTimeStamp = new SimpleDateFormat(FORMAT,
                new Locale("en", "US")).parse(rawString);
        return p.format(parsedTimeStamp);
    }

    public static String getDDMMYYY(Date mdat) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(mdat);
    }
}
