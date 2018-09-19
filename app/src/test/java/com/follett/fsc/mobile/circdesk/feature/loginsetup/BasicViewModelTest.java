/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import org.junit.Before;
import org.junit.Test;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import junit.framework.Assert;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicViewModelTest {
    
    private BasicViewModel mViewModel;
    
    private String mUri = "https://devprodtest.follettdestiny.com";
    
    Application mApplication;
    
    @Before
    public void setUp () throws Exception {
        mApplication = mock(Application.class);
    
        SharedPreferences preferences = mock(SharedPreferences.class);
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(mApplication.getSharedPreferences("Follett", Context.MODE_PRIVATE)).thenReturn(preferences);
        when(preferences.edit()).thenReturn(editor);
        when(preferences.getString(SERVER_URI_VALUE, "")).thenReturn("https://devprodtest.follettdestiny.com");
        mViewModel = new BasicViewModel(mApplication);
    }
    
    
    @Test
    public void savePreferenceTest() {
        Assert.assertNotNull(mViewModel);
        mViewModel.savePreference(mUri, null, null);
//        mViewModel.callAppVersion();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    
}
