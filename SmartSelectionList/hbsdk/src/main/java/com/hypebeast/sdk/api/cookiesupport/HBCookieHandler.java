package com.hypebeast.sdk.api.cookiesupport;

import android.content.Context;


import java.net.HttpCookie;
import java.net.URI;


/**
 * Created by hesk on 1/4/15.
 */
public class HBCookieHandler extends PersistentCookieStore {
    public HBCookieHandler(Context ctx) {
        super(ctx);
    }

    protected void preprocesscookie(HttpCookie c, URI uri) {
       /* Log.d(AddCartManager.TAG, "SESSIONID:" + c.toString() + ", " + "URI ToString: " + uri.toString());
        String check = "_store_item_count=";
        if (c.toString().startsWith(check)) {

            Realm realm = Realm.getInstance(getContext());

            realm.beginTransaction();


            final String countStr = c.toString().substring(check.length(), c.toString().length()).toString();

            retention.appSettings.setShopping_bag_current_item(Integer.parseInt(countStr));

            try {
                APPSettings appSettingsItem = realm.where(APPSettings.class).findAll().last();
                appSettingsItem.setShopping_bag_current_item(retention.appSettings.getShopping_bag_current_item());
            } catch (Exception e) {
                realm.copyToRealm(retention.appSettings);
            }

            realm.commitTransaction();
        }*/
    }
}
