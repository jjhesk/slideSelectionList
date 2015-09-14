package com.hypebeast.sdk.clients;

import com.google.gson.Gson;
import com.hypebeast.sdk.api.exception.ErrorHandlerResponseCode;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by hesk on 2/7/15.
 */
public abstract class Client {
    protected Gson gsonsetup;
    protected final ErrorHandler handlerError = new ErrorHandlerResponseCode();
    /**
     * Rest adapter
     */
    protected RestAdapter mAdapter;

    protected abstract void registerAdapter();

    protected abstract String get_USER_AGENT();

    protected abstract void jsonCreate();

    protected void createInterfaces() {
    }

    protected RequestInterceptor getIn() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", get_USER_AGENT());
                request.addHeader("Accept", "application/json");
                request.addHeader("X-Api-Version", "2.0");
            }
        };
    }

    public Client() {
        jsonCreate();
        registerAdapter();
        createInterfaces();
    }
}
