/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.common.AppConstants;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.Version;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.BasicNavigator;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;
import com.follett.fsc.mobile.commons.android.URLHelper;

import android.app.Application;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.IOException;
import java.net.URL;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.DEFAULT_HTTP_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.DEFAULT_SSL_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SERVER_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SERVER_SSL_PORT;

public class BasicViewModel extends BaseViewModel<BasicNavigator> {
    
    private Application mApplication;
    
    private AppRemoteRepository mAppRemoteRepository;
    
    public BasicViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
    }
    
    public void connectToServerOnClick() {
        getNavigator().connectOnClick();
    }
    
    public void savePreference(String serverName, String port, String sslPort) {
        SaveContextTask saveTask = new SaveContextTask();
        saveTask.execute(serverName, port, sslPort);
    }
    
    
    public class SaveContextTask extends AsyncTask<String, Void, Boolean> {
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        
        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = Boolean.FALSE;
            String serverName = params[0];
            String port = params[1];
            String sslPort = params[2];
            try {
                String url = null;
                try {
                    AppSharedPreferences.getInstance(mApplication)
                            .removeAllSession();
                    if (port != null) {
                        AppSharedPreferences.getInstance(mApplication)
                                .setInt(KEY_SERVER_PORT, Integer.parseInt(port));
                    } else {
                        AppSharedPreferences.getInstance(mApplication)
                                .setInt(KEY_SERVER_PORT, DEFAULT_HTTP_PORT);
                    }
                    if (sslPort != null) {
                        AppSharedPreferences.getInstance(mApplication)
                                .setInt(KEY_SERVER_SSL_PORT, Integer.parseInt(sslPort));
                    } else {
                        AppSharedPreferences.getInstance(mApplication)
                                .setInt(KEY_SERVER_SSL_PORT, DEFAULT_SSL_PORT);
                    }
                    
                    try {
                        if (TextUtils.isEmpty(port)) {
                            url = URLHelper.getFinalizedURL(serverName);
                        } else {
                            url = URLHelper.getFinalizedURL(serverName + ":" + port);
                        }
                    } catch (IllegalArgumentException ex) {
                        getNavigator().displayErrorDialog(AppConstants.SSL_ERROR);
                    }
                    AppSharedPreferences.getInstance(mApplication)
                            .populateInfoFromURL(new URL(url));
                    result = Boolean.TRUE;

//                    int version = API.getInstance().getVersion(AppInfo.mPrefs);
//                    if (version < API.MIN_API_VERSION_SUPPORTED) {
//                        throw new APIException(new Exception(), R.string.error_sorry_school_not_setup);
//                    } else {
//                        result = Boolean.TRUE;
//                    }
                } catch (IOException e) {
                    getNavigator().displayErrorDialog(AppConstants.SSL_ERROR);
                }
            } catch (Exception ex) {
                getNavigator().displayErrorDialog(AppConstants.SSL_ERROR);
            }
            return result;
        }
        
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                getNavigator().asyncTaskResult(result);
            }
        }
    }
    
    public void getVersion() {
        mAppRemoteRepository.getVersion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Version>() {
                    @Override
                    public void onNext(Version value) {
                        if (Integer.parseInt(value.getVersion()) < AppConstants.MIN_API_VERSION_SUPPORTED) {
                            getNavigator().displayErrorDialog(AppConstants.SCHOOL_NOT_SETUP_ERROR);
                        } else {
                            getNavigator().navigationToNextFragment(0);
                        }
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                    
                    }
                    
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
