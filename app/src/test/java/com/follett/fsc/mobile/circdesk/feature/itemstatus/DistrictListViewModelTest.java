/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import com.follett.fsc.mobile.circdesk.feature.loginsetup.DistrictListViewModel;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static org.mockito.Mockito.when;

public class DistrictListViewModelTest extends BaseTestClass {
    
    private DistrictListViewModel mViewModel;
  
    
    
    
    @Before
    public void setUp() {
        mViewModel = new DistrictListViewModel(mApplication);
    }
    
//    @Test
//    public void clearDistrictPref() {
//        createMockPref();
//    }
//
//    private void createMockPref() {
//        when(mEditor.putString(KEY_CONTEXT_NAME, "context")).thenReturn(mEditor.putString("value", "dublicate"));
//        when(mEditor.putString(KEY_DISTRICT_NAME, "district")).thenReturn(mEditor.putString("district", "dblicatedistrict"));
//
//
//    }
    

}
