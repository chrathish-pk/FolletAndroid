/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.app.Application;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;

public class DistrictListViewModel extends BaseViewModel<CTAButtonListener> {
    private Application mApplication;
    
    public DistrictListViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }
    
    public void clearDistrictPref() {
        AppSharedPreferences.getInstance()
                .removeValues(KEY_CONTEXT_NAME);
        AppSharedPreferences.getInstance()
                .removeValues(KEY_DISTRICT_NAME);
    }
}
