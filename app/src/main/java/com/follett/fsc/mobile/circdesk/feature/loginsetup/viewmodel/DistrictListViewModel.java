/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;

import android.app.Application;
import android.support.annotation.NonNull;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;

public class DistrictListViewModel extends BaseViewModel<CTAButtonListener> {
    
    public DistrictListViewModel(@NonNull Application application) {
        super(application);
    }
    
    public void clearDistrictPref() {
        AppSharedPreferences.getInstance()
                .removeValues(KEY_DISTRICT_NAME);
    }
}
