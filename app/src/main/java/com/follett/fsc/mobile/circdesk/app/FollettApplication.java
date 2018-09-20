/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app;

import android.app.Application;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class FollettApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        AppSharedPreferences.getInstance().initializeSharedPreference(this);
    }
}
