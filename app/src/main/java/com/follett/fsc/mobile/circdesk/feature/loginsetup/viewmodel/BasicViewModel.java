/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Version;
import com.follett.fsc.mobile.circdesk.utils.AppConstants;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.commons.android.URLHelper;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.net.URL;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.DEFAULT_HTTP_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.DEFAULT_SSL_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.FOLLETT_API_VERSION;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SERVER_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SERVER_SSL_PORT;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;

public class BasicViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {

    public final MutableLiveData<DistrictList> mDistrictList = new MutableLiveData<>();

    private ObservableField<String> storedSchoolUri = new ObservableField<>();

    private ObservableBoolean isAdvancedTabView = new ObservableBoolean();

    private Application mApplication;

    public BasicViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void savePreference(String serverName, String port, String sslPort) {
        setIsLoding(true);
        AppSharedPreferences.getInstance().setString(SERVER_URI_VALUE, serverName);

        SaveContextTask saveTask = new SaveContextTask();
        saveTask.execute(serverName, port, sslPort);
    }

    @Override
    public void onCallCompleted(Object model) {
        try {
            if (model instanceof Version) {
                Version version = (Version) model;
                String lVersion = version.getVersion();
                if (Integer.parseInt(lVersion) < AppConstants.MIN_API_VERSION_SUPPORTED) {
                    setIsLoding(false);
                    setStatus(Status.SCHOOL_NOT_SETUP_ERROR);
                } else {
                    AppSharedPreferences.getInstance()
                            .setString(FOLLETT_API_VERSION, lVersion);
                    AppRemoteRepository.getInstance().getDistrictList(this);
                }
            } else if (model instanceof DistrictList) {
                DistrictList districtList = (DistrictList) model;
                int size = districtList.getDistricts()
                        .size();
                if (size == 0) {
                    setStatus(Status.NO_LIST_FOUND);
                } else if (size == 1) {
                    AppSharedPreferences.getInstance()
                            .setString(KEY_CONTEXT_NAME, districtList.getDistricts()
                                    .get(0)
                                    .getContextName());
                    AppSharedPreferences.getInstance()
                            .setString(KEY_DISTRICT_NAME, districtList.getDistricts()
                                    .get(0)
                                    .getDistrictName());
                    setStatus(Status.SUCCESS);
                } else if (size > 1) {
                    mDistrictList.postValue((DistrictList) model);
                }
                setIsLoding(false);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }

        FollettLog.d("KEY_CONTEXT_NAME", AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME));

    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
    }
    
    @Override
    public void onRefreshToken(int requestCode) {
        // Do Nothing
    }
    
    
    private class SaveContextTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = Boolean.FALSE;
            String serverName = params[0];
            String port = params[1];
            String sslPort = params[2];
            try {
                String url = null;
                try {
                    AppRemoteRepository.getInstance().setString(SERVER_URI_VALUE, serverName);
                    if (port != null) {
                        AppSharedPreferences.getInstance()
                                .setInt(KEY_SERVER_PORT, Integer.parseInt(port));
                    } else {
                        AppSharedPreferences.getInstance()
                                .setInt(KEY_SERVER_PORT, DEFAULT_HTTP_PORT);
                    }
                    if (sslPort != null) {
                        AppSharedPreferences.getInstance()
                                .setInt(KEY_SERVER_SSL_PORT, Integer.parseInt(sslPort));
                    } else {
                        AppSharedPreferences.getInstance()
                                .setInt(KEY_SERVER_SSL_PORT, DEFAULT_SSL_PORT);
                    }

                    try {
                        if (TextUtils.isEmpty(port)) {
                            url = URLHelper.getFinalizedURL(serverName);
                        } else {
                            url = URLHelper.getFinalizedURL(serverName + ":" + port);
                        }
                    } catch (IllegalArgumentException ex) {
                        setIsLoding(false);
                        setStatus(com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status.ERROR);
                    }
                    AppSharedPreferences.getInstance()
                            .populateInfoFromURL(new URL(url));
                    result = Boolean.TRUE;

                } catch (IOException e) {
                    setIsLoding(false);
                    setStatus(com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status.ERROR);
                }
            } catch (Exception ex) {
                setIsLoding(false);
                setStatus(com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status.ERROR);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                AppRemoteRepository.mInstance=null;
                AppRemoteRepository.getInstance().getVersion(BasicViewModel.this);
            } else {
                setIsLoding(false);
            }
        }
    }

    public ObservableField<String> getStoredSchoolUri() {
        return storedSchoolUri;
    }

    public void setStoredSchoolUri(String storedSchoolUri) {
        this.storedSchoolUri.set(storedSchoolUri);
    }

    public ObservableBoolean getAdvancedTabView() {
        return isAdvancedTabView;
    }

    public void setAdvancedTabView(Boolean advancedTabView) {
        this.isAdvancedTabView.set(advancedTabView);
    }
}
