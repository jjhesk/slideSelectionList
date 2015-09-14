package com.hypebeast.sdk.clients;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.google.gson.GsonBuilder;
import com.hypebeast.sdk.Constants;
import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.gson.GsonFactory;
import com.hypebeast.sdk.api.gson.MissingCharacterConversion;
import com.hypebeast.sdk.api.gson.RealmExclusion;
import com.hypebeast.sdk.api.model.hbeditorial.Foundation;
import com.hypebeast.sdk.api.model.popbees.mobileconfig;
import com.hypebeast.sdk.api.resources.ht.hTrak;
import com.hypebeast.sdk.api.resources.pb.pbPost;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by hesk on 21/8/15.
 */
public class HTEditorialClient extends Client {
    /**
     * Base URL for all PB endpoints
     */
    private static final String BASE_URL_PB = "http://hypetrak.com/";
    /**
     * User agent
     */
    private static final String USER_AGENT = "PT/1.0 Android" + Build.VERSION.SDK_INT;
    /**
     * Date format
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    //http://www.datameer.com/documentation/display/DAS20/Date+and+Time+Parse+Patterns
    public static final String REFERENCE_MOBILE_CONFIG = "mConfig";
    public static final String REFERENCE_MOBILE_CONFIG_TIME = "mConfigTime";
    public static final String ISO_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
    public static final String ISO_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String ISO_FORMAT3 = "yyyy-MM-dd HH:mm:ss z";
    public static final String ISO_FORMAT4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String ISO_FORMAT5 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private hTrak interfaceHTrak;

    @Override
    protected void registerAdapter() {
        mAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL_PB)
                .setLogLevel(RestAdapter.LogLevel.HEADERS)
                .setErrorHandler(handlerError)
                .setRequestInterceptor(getIn())
                .setConverter(new GsonConverter(gsonsetup))
                .build();
    }

    @Override
    protected String get_USER_AGENT() {
        return USER_AGENT;
    }

    @Override
    protected void jsonCreate() {
        gsonsetup = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .setExclusionStrategies(new RealmExclusion())
                .registerTypeAdapterFactory(new GsonFactory.NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(String.class, new MissingCharacterConversion(true))
                .create();
    }

    @Override
    protected void createInterfaces() {
        interfaceHTrak = mAdapter.create(hTrak.class);
    }

    public HTEditorialClient() {
        super();
    }

    public hTrak createPostsFeed() {
        return interfaceHTrak;
    }

    public static int totalPages(Response mResp) {
        List<Header> list = mResp.getHeaders();
        Iterator<Header> f = list.iterator();
        while (f.hasNext()) {
            Header h = f.next();
            if (h.getName().equalsIgnoreCase("X-WP-TotalPages")) {
                return Integer.parseInt(h.getValue());
            }
        }
        return -1;
    }


    public void retentData(final Context context, final Runnable callback) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String data = sharedPreferences.getString(REFERENCE_MOBILE_CONFIG, "none");
        final String time = sharedPreferences.getString(REFERENCE_MOBILE_CONFIG_TIME, "none");
        final Runnable n = new Runnable() {
            @Override
            public void run() {

                try {
                    interfaceHTrak.mobile_config(new Callback<mobileconfig>() {
                        @Override
                        public void success(mobileconfig mMobileconfig, Response response) {


                            sharedPreferences
                                    .edit()
                                    .putString(REFERENCE_MOBILE_CONFIG, gsonsetup.toJson(mMobileconfig))
                                    .commit();

                            Date date = new Date();
                            Timestamp timestamp = new Timestamp(date.getTime());

                            sharedPreferences
                                    .edit()
                                    .putString(REFERENCE_MOBILE_CONFIG_TIME, timestamp.toString())
                                    .commit();
                            callback.run();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        };

        if (!data.equalsIgnoreCase("none") && !time.equalsIgnoreCase("none")) {
            final Timestamp past = Timestamp.valueOf(time);
            final Date date = new Date();
            //   Calendar cal1 = Calendar.getInstance();
            final Timestamp now = new Timestamp(date.getTime());
            long pastms = past.getTime();
            long nowms = now.getTime();
            if (nowms - pastms > Constants.ONE_DAY) {
                n.run();
            } else {
                if (data.equalsIgnoreCase("")) {
                    n.run();
                } else {
                    callback.run();
                }
            }
        } else if (data.equalsIgnoreCase("none") || time.equalsIgnoreCase("none")) {
            n.run();
        }


    }

    public mobileconfig readConfig(final Context context) {
        final SharedPreferences msharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String data = msharedPreferences.getString(REFERENCE_MOBILE_CONFIG, "");
        return gsonsetup.fromJson(data, mobileconfig.class);

    }

}
