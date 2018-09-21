/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JunitHelper {
    
    public static void inItSetUp() {
        setSharedPref();
    }
    
    public static void setSharedPref() {
        Application application = mock(Application.class);
        SharedPreferences preferences = mock(SharedPreferences.class);
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(application.getSharedPreferences("Follett", Context.MODE_PRIVATE)).thenReturn(preferences);
        when(preferences.edit()).thenReturn(editor);
        when(preferences.getString(SERVER_URI_VALUE, "")).thenReturn("https://devprodtest.follettdestiny.com");
    }
}
