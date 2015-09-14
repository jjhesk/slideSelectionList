package com.hypebeast.sdk.api.retrofitworker;


import com.hypebeast.sdk.config.clientConfig;

public class Interceptor implements retrofit.RequestInterceptor {
    private static final String PARAM_API_KEY = "api_key";
    private static final String PARAM_ACCESS_TOKEN = "access_token";
    private static clientConfig confg;

    public Interceptor(final clientConfig configuration) {
        confg = configuration;
    }


    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(PARAM_API_KEY, confg.getApiKey());
        // add the authenticated user to the request if available
        // if (confg.getAccessToken() != null) {
        // request.addQueryParam(PARAM_ACCESS_TOKEN, confg.getAccessToken());
        // }
    }
}
