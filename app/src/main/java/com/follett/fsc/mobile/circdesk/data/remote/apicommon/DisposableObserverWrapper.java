/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.data.remote.apicommon;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.IO_EXCEPTION;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.PERMISSION_DENIED;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.SOCKET_EXCEPTION;

public abstract class DisposableObserverWrapper<T> extends DisposableObserver<T> {
    
    protected abstract void onSuccess(T t);
    
    protected abstract void onFailed(Throwable throwable, String errorMessage);
    
    protected abstract void onRefreshToken();
    
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
    
    @Override
    public void onError(Throwable e) {
        String errorMessage;
        if (e instanceof HttpException) {
            checkSessionExpire(e, ((HttpException) e).response()
                    .errorBody());
            return;
        } else if (e instanceof SocketTimeoutException) {
            errorMessage = SOCKET_EXCEPTION;
        } else if (e instanceof IOException) {
            errorMessage = IO_EXCEPTION;
        } else {
            errorMessage = e.getMessage();
        }
        onFailed(e, errorMessage);
    }
    
    @Override
    public void onComplete() {
    }
    
    private void checkSessionExpire(Throwable e, ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            String code = jsonObject.getString("code");
            if (!TextUtils.isEmpty(code) && code.equalsIgnoreCase("3")) {
                onRefreshToken();
            } else {
                onFailed(e, PERMISSION_DENIED);
            }
        } catch (Exception expception) {
            onFailed(expception, expception.getMessage());
        }
    }
}
