/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;
import static org.mockito.Mockito.when;

public class BaseTestClass {
    
    @Mock
    public Application mApplication;
    
    @Mock public SharedPreferences mPreferences;
    
    @Mock SharedPreferences.Editor mEditor;
   
    
    @Before
    public void baseSetUp() {
        MockitoAnnotations.initMocks(this);
        setUpSharedPref();
    }
    
    public void setUpSharedPref() {
        when(mApplication.getSharedPreferences("Follett", Context.MODE_PRIVATE)).thenReturn(mPreferences);
        when(mPreferences.edit()).thenReturn(mEditor);
        when(mPreferences.getString(SERVER_URI_VALUE, "")).thenReturn("https://devprodtest.follettdestiny.com");
    }
}
