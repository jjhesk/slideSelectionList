package com.hypebeast.sdk.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hesk on 15/9/15.
 */
public class GsonUtil {
    public static String removeEmptyArraySerialization(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> data = new Gson().fromJson(jsonString, type);
        for (Iterator<Map.Entry<String, Object>> it = data.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getValue() == null) {
                it.remove();
            } else if (entry.getValue().getClass().equals(ArrayList.class)) {
                if (((ArrayList<?>) entry.getValue()).size() == 0) {
                    it.remove();
                }
            }
        }
        String json = new GsonBuilder().create().toJson(data);
        return json;
    }
}
