package com.hypebeast.sdk.clients;

import android.os.Build;

import com.google.gson.GsonBuilder;
import com.hypebeast.sdk.Constants;
import com.hypebeast.sdk.api.gson.GsonFactory;
import com.hypebeast.sdk.api.gson.MissingCharacterConversion;
import com.hypebeast.sdk.api.gson.RealmExclusion;
import com.hypebeast.sdk.api.model.hbeditorial.Foundation;
import com.hypebeast.sdk.api.resources.hypebeast.feedhost;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by hesk on 2/7/15.
 */
public class HBEditorialClient extends Client {
    /**
     * Base URL for all Disqus endpoints
     */
    public static final String BASE_EN = "http://hypebeast.com/";
    public static final String BASE_JP = "http://jp.hypebeast.com/";
    public static final String BASE_CN = "http://cn.hypebeast.com/";
    public static final String BASE_LOGIN = "https://disqus.com/api";
    private String endpoint;
    /**
     * User agent
     */
    private static final String USER_AGENT = "HypebeastStoreApp/1.0 Android" + Build.VERSION.SDK_INT;
    /**
     * login adapter
     */
    private RestAdapter mLoginAdapter;

    public HBEditorialClient() {
        super();
    }

    @Override
    protected String get_USER_AGENT() {
        return USER_AGENT;
    }

    @Override
    protected void jsonCreate() {
        gsonsetup = new GsonBuilder()
                .setDateFormat(Constants.DATE_FORMAT)
   /*             .registerTypeAdapter(Usage.class, new ApplicationsUsageDeserializer())
                .registerTypeAdapterFactory(new BlacklistsEntryTypeAdapterFactory())
                .registerTypeAdapterFactory(new PostTypeAdapterFactory())
                .registerTypeAdapterFactory(new ThreadTypeAdapterFactory())*/
                .registerTypeAdapterFactory(new GsonFactory.NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(String.class, new MissingCharacterConversion())
                .setExclusionStrategies(new RealmExclusion())
                .create();
    }

    public Foundation fromsavedConfiguration(String data) {
        return gsonsetup.fromJson(data, Foundation.class);
    }

    public String fromJsonToString(Foundation mfound) {
        return gsonsetup.toJson(mfound);
    }

    @Override
    protected void registerAdapter() {
        if (endpoint == null) {
            this.endpoint = BASE_EN;
        }
        mAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.HEADERS)
                .setErrorHandler(handlerError)
                .setRequestInterceptor(getIn())
                .setConverter(new GsonConverter(gsonsetup))
                .build();
    }


    public HBEditorialClient setLanguageBase(String from_hb_editorial_base_url) {
        this.endpoint = from_hb_editorial_base_url;
        registerAdapter();
        return this;
    }

    public HBEditorialClient build() {
        jsonCreate();
        registerAdapter();
        return this;
    }


    public feedhost createFeedInterface() {
        return mAdapter.create(feedhost.class);
    }


}
