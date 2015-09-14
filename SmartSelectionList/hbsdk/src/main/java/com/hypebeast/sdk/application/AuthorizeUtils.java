package com.hypebeast.sdk.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.hypebeast.sdk.Constants;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

/**
 * Created by hesk on 21/5/15.
 */
public class AuthorizeUtils {

    /**
     * Create authorize intent
     *
     * @param context     as is
     * @param apiKey      as is
     * @param scopes      as is
     * @param redirectUri as is
     * @return as is
     */
    public static Intent createIntent(Context context, String apiKey, String[] scopes,
                                      String redirectUri) {
      //  Intent intent = new Intent(context, AuthorizeActivity.class);
        Intent intent = new Intent(context, AuthorizeUtils.class);
        Bundle extras = new Bundle();
     /*   extras.putString(AuthorizeActivity.EXTRA_API_KEY, apiKey);
        extras.putStringArray(AuthorizeActivity.EXTRA_SCOPES, scopes);
        extras.putString(AuthorizeActivity.EXTRA_REDIRECT_URI, redirectUri);*/
        intent.putExtras(extras);
        return intent;
    }

    /**
     * Build a scope string from an array of scopes
     *
     * @param scopes as is
     * @return as is
     */
    public static String buildScope(String[] scopes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < scopes.length; i++) {
            builder.append(scopes[i]);
            if (i < scopes.length - 1) {
                builder.append(',');
            }
        }
        return builder.toString();
    }

    /**
     * Build uri for authorize requests
     *
     * @param clientId    as is
     * @param scope       as is
     * @param redirectUri as is
     * @return as is
     */
    public static Uri buildAuthorizeUri(String clientId, String scope, String redirectUri) {
        Uri.Builder builder = Uri.parse(Constants.AUTHORIZE_URL).buildUpon();
        builder.appendQueryParameter(Constants.PARAM_CLIENT_ID, clientId);
        if (scope != null) {
            builder.appendQueryParameter(Constants.PARAM_SCOPE, scope);
        }
        builder.appendQueryParameter(Constants.PARAM_RESPONSE_TYPE, Constants.RESPONSE_TYPE_CODE);
        builder.appendQueryParameter(Constants.PARAM_REDIRECT_URI, redirectUri);
        return builder.build();
    }


    public static String buildCodeUri(String code, String clientId, String secret, String redirect) {
        Uri.Builder bu = Uri.parse(Constants.AUTHORIZE_ACCESS_TOKEN).buildUpon();

        bu.appendQueryParameter(Constants.PARAM_GRANTTYPE, Constants.auth_code);
        bu.appendQueryParameter(Constants.PARAM_CLIENT_ID, clientId);
        bu.appendQueryParameter(Constants.PARAM_CLIENT_SECRET, secret);
        bu.appendQueryParameter(Constants.PARAM_REDIRECT_URI, redirect);
        bu.appendQueryParameter(Constants.PARAM_CODE, code);

        return bu.build().toString();
    }

    public static String buildCodeRequestJustBody(String code, String clientId, String secret, String redirect) {
        String t = buildCodeUri(code, clientId, secret, redirect);
        String replacetarget = Constants.AUTHORIZE_ACCESS_TOKEN + "?";
        String glongthat = t.replace(replacetarget, "");
        return glongthat;
    }

    public static RequestBody buildRequest(String code, String clientId, String secret, String redirect) {
        final RequestBody fmbody = new FormEncodingBuilder()
                .add(Constants.PARAM_CODE, code)
                .add(Constants.PARAM_GRANTTYPE, Constants.auth_code)
                .add(Constants.PARAM_CLIENT_ID, clientId)
                .add(Constants.PARAM_CLIENT_SECRET, secret)
                .add(Constants.PARAM_REDIRECT_URI, redirect)
                .build();
        return fmbody;
    }


}
