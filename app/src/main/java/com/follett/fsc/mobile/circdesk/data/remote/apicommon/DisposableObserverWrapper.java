/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.data.remote.apicommon;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class DisposableObserverWrapper<T> extends DisposableObserver<T> {
    
    protected abstract void onSuccess(T t);
    
    protected abstract void onFailed(Throwable throwable, String errorMessage);
    
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
    
    @Override
    public void onError(Throwable e) {
        String errorMessage;
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response()
                    .errorBody();
            errorMessage = getErrorMessage(responseBody);
        } else if (e instanceof SocketTimeoutException) {
            errorMessage = "Time out error. Please try again later";
        } else if (e instanceof IOException) {
            errorMessage = "Internet issue. Please try again when you have a better connection.";
        } else {
            errorMessage = e.getMessage();
        }
        onFailed(e, errorMessage);
    }
    
    @Override
    public void onComplete() {
    }
    
    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
