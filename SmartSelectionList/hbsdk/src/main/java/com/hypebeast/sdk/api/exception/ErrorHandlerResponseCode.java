package com.hypebeast.sdk.api.exception;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 3/7/15.
 */
public class ErrorHandlerResponseCode implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
        Response response = cause.getResponse();
        if (response != null) {
            switch (response.getStatus()) {
                case 400:
                    return new BadRequestException(cause);
                case 401:
                    return new ForbiddenException(cause);
                case 404:
                    return new NotFoundException(cause);
                case 500:
                    return new InternalServerError(cause);
                case 502:
                    return new BadGateWayException(cause);
            }
        }
        return new ApiException(cause);
    }
}
