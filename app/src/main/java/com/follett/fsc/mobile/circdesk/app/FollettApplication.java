/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app;

import android.app.Application;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;

public class FollettApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppSharedPreferences.getInstance().initializeSharedPreference(this);
    }
}
