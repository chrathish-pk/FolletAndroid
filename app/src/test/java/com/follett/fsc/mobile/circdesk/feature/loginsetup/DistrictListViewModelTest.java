/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.DistrictListViewModel;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;
import static org.mockito.Mockito.when;

public class DistrictListViewModelTest extends BaseTestClass {
    
    private DistrictListViewModel mViewModel;
    
    @Before
    public void setUp() {
        mViewModel = new DistrictListViewModel(mApplication);
    }
    
    @Test
    public void clearDistrictPref() {
        createMockPref();
        insertData();
        mViewModel.clearDistrictPref();
        Assert.assertNull(AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME));
        Assert.assertNull(AppSharedPreferences.getInstance()
                .getString(KEY_DISTRICT_NAME));
    }
    
    private void createMockPref() {
        when(mEditor.remove(KEY_CONTEXT_NAME)).thenReturn(mEditor);
        when(mEditor.remove(KEY_DISTRICT_NAME)).thenReturn(mEditor);
    }
    
    private void insertData() {
        AppSharedPreferences.getInstance()
                .initializeSharedPreference(mApplication);
        AppSharedPreferences.getInstance()
                .setString(KEY_CONTEXT_NAME, CONTEXT_NAME);
        AppSharedPreferences.getInstance()
                .setString(KEY_DISTRICT_NAME, DISTRICT_NAME);
    }
    
    @After
    public void tearDown() {
        mViewModel = null;
        mEditor = null;
    }
}
