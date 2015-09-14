package com.hypebeast.sdk.application.hypebeast;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hypebeast.sdk.Constants;
import com.hypebeast.sdk.api.exception.ApiException;
import com.hypebeast.sdk.api.model.hbeditorial.Foundation;
import com.hypebeast.sdk.api.model.hbeditorial.configbank;
import com.hypebeast.sdk.api.resources.hypebeast.feedhost;
import com.hypebeast.sdk.clients.HBEditorialClient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 1/9/15.
 */
public class ConfigurationSync {
    public static ConfigurationSync instance;
    private final Application app;
    private Realm realm;
    public static final String PREFERENCE_FOUNDATION = "foundationfile";
    public static final String PREFERENCE_FOUNDATION_REGISTRATION = "regtime";
    private feedhost clientRequest;
    private Foundation mFoundation;
    private HBEditorialClient client;
    private ArrayList<sync> mListeners = new ArrayList<>();
    private sync mListener;

    public static ConfigurationSync with(Application app, sync mListener) {
        if (instance == null) {
            instance = new ConfigurationSync(app, mListener);
            instance.init();
        } else {
            instance.addInterface(mListener);
            instance.init();
        }

        return instance;
    }

    public static ConfigurationSync getInstance() throws Exception {
        if (instance == null) {
            throw new Exception("please init a new instance");
        }
        return instance;
    }

    public ConfigurationSync(Application app, sync mListener) {
        this.app = app;
        this.realm = Realm.getInstance(app);
        client = new HBEditorialClient();
        //client.setLanguageBase(HBEditorialClient.BASE_EN);
        clientRequest = client.createFeedInterface();
        addInterface(mListener);
    }

    public void switchToLanguage(String mLanguage) {
        if (mLanguage.equals("en")) {
            client.setLanguageBase(HBEditorialClient.BASE_EN);
        } else if (mLanguage.equals("zh_CN")) {
            client.setLanguageBase(HBEditorialClient.BASE_CN);
        } else if (mLanguage.equals("ja")) {
            client.setLanguageBase(HBEditorialClient.BASE_JP);
        } else if (mLanguage.equals("zh_TW")) {
            client.setLanguageBase(HBEditorialClient.BASE_CN);
        }
    }

    public HBEditorialClient getInstanceHBClient() {
        return client;
    }

    private void addInterface(sync listenerSync) {
        // mListeners.add(listenerSync);
        mListener = listenerSync;
    }

    public void clearListeners() {
        //mListeners.clear();
    }

    private void executeListeners() {
       /*   if (mListeners.size() > 0) {
            Iterator<sync> lis = mListeners.iterator();
            while (lis.hasNext()) {
                sync listener = lis.next();
                listener.syncDone(ConfigurationSync.this, mFoundation);
            }
        }*/
        if (mListener != null) mListener.syncDone(instance, mFoundation);
    }

    private void syncWorkerThread() {
        try {
            clientRequest.mobile_config(new Callback<Foundation>() {
                @Override
                public void success(Foundation foundation, Response response) {
                    mFoundation = foundation;


                    PreferenceManager.getDefaultSharedPreferences(app)
                            .edit()
                            .putString(PREFERENCE_FOUNDATION, client.fromJsonToString(foundation))
                            .commit();

                    Date date = new Date();
                    Timestamp timestamp = new Timestamp(date.getTime());

                    PreferenceManager.getDefaultSharedPreferences(app)
                            .edit()
                            .putString(PREFERENCE_FOUNDATION_REGISTRATION, timestamp.toString())
                            .commit();

                    executeListeners();
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
        String data = sharedPreferences.getString(PREFERENCE_FOUNDATION, "none");
        String time = sharedPreferences.getString(PREFERENCE_FOUNDATION_REGISTRATION, "none");
        if (!data.equalsIgnoreCase("none") && !time.equalsIgnoreCase("none")) {
            Timestamp past = Timestamp.valueOf(time);
            Date date = new Date();
            //   Calendar cal1 = Calendar.getInstance();
            Timestamp now = new Timestamp(date.getTime());
            long pastms = past.getTime();
            long nowms = now.getTime();
            if (nowms - pastms > Constants.ONE_DAY) {
                syncWorkerThread();
            } else {
                if (data.equalsIgnoreCase("")) {
                    syncWorkerThread();
                } else {
                    mFoundation = client.fromsavedConfiguration(data);
                    executeListeners();
                }
            }
        } else if (data.equalsIgnoreCase("none") || time.equalsIgnoreCase("none")) {
            syncWorkerThread();
        }
    }

    public Foundation getFoundation() {
        return mFoundation;
    }


    public configbank getByLanguage(String lang) {
        if (lang.equals("en")) {
            return mFoundation.english;
        } else if (lang.equals("zh_CN")) {
            return mFoundation.chinese_simplified;
        } else if (lang.equals("ja")) {
            return mFoundation.japanese;
        } else if (lang.equals("zh_TW")) {
            return mFoundation.chinese_traditional;
        } else
            return mFoundation.english;
    }
}
