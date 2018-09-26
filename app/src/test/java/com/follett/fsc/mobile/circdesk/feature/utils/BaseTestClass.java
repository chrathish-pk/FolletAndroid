/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;
import static org.mockito.Mockito.when;

public class BaseTestClass {
    
    @Mock
    public Application mApplication;
    
    @Mock
    public SharedPreferences mPreferences;
    
    @Mock
    public SharedPreferences.Editor mEditor;
    
    public static final String CONTEXT_NAME = "dvpdt_devprodtest";

    public static final String CONTEXT_NAME_UAT = "prokarma";

    public static final String DISTRICT_NAME = "Follett";
    
    public static final String SITE_NAME = "FDPSA";

    public static final String SITE_NAME_UAT = "lincoln";

    public static final String SESSION_ID = "F1GEEiXvg_BZk7GTTu6q2mgMcCMxLrvbd40mHjXI";
    
    
    
    @Before
    public void baseSetUp() {
        MockitoAnnotations.initMocks(this);
        //setUpSharedPref();
        setUpUATSharedPref();
    }
    
    public void setUpSharedPref() {
        when(mApplication.getSharedPreferences("Follett", Context.MODE_PRIVATE)).thenReturn(mPreferences);
        when(mPreferences.edit()).thenReturn(mEditor);
        when(mPreferences.getString(SERVER_URI_VALUE, "")).thenReturn("https://devprodtest.follettdestiny.com");
        AppSharedPreferences.getInstance().initializeSharedPreference(mApplication);
    }

    public void setUpUATSharedPref() {
        when(mApplication.getSharedPreferences("Follett", Context.MODE_PRIVATE)).thenReturn(mPreferences);
        when(mPreferences.edit()).thenReturn(mEditor);
        when(mPreferences.getString(SERVER_URI_VALUE, "")).thenReturn("https://uat-destiny.follettsoftware.com");
        AppSharedPreferences.getInstance().initializeSharedPreference(mApplication);
    }
}
