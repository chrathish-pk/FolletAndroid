/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteRecord;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.SchoolListViewModel;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static org.mockito.Mockito.when;

public class SchoolListViewModelTest extends BaseTestClass {
    
    private SchoolListViewModel mViewModel;
    
    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    
    @BeforeClass
    public static void setupRxJavaPlugins() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }
    
    @Before
    public void setUp() {
        createMockData();
        if (mViewModel == null) {
            mViewModel = new SchoolListViewModel(mApplication);
        }
    }
    
    @Test
    public void onCallCompletedWithZeroResult() {
        
        when(mPreferences.getString(KEY_DISTRICT_NAME, "")).thenReturn(DISTRICT_NAME);
        when(mApplication.getString(R.string.double_quote)).thenReturn("");
        when(mApplication.getString(R.string.no_schools, DISTRICT_NAME)).thenReturn(DISTRICT_NAME);
        
        mViewModel.onCallCompleted(generateZeroSchoolResult());
        Assert.assertEquals(DISTRICT_NAME, mViewModel.noSchoolFoundMsg.getValue());
    }
    
    @Test
    public void onCallCompletedWithOneResult() {
        mViewModel.onCallCompleted(generateOneSchoolResult());
        Assert.assertEquals(Status.SUCCESS, mViewModel.getStatus()
                .getValue());
    }
    
    @Test
    public void onCallCompletedWithMultiResult() {
        mViewModel.onCallCompleted(generateMultiSchoolResult());
        Assert.assertNotNull(mViewModel.siteResult.getValue());
    }
    
    @Test
    public void onCallFailed() {
        mViewModel.onCallFailed(new Throwable());
        Assert.assertFalse(mViewModel.getIsLoading()
                .get());
    }
    
    @Test
    public void clearSchoolPref() {
        AppUtils.getInstance().clearSchoolPref();
        Assert.assertNull(AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME));
        Assert.assertNull(AppSharedPreferences.getInstance()
                .getString(KEY_SITE_ID));
        Assert.assertNull(AppSharedPreferences.getInstance()
                .getString(KEY_SITE_NAME));
    }
    
    private void createMockData() {
        when(mPreferences.getString(KEY_CONTEXT_NAME, "")).thenReturn(CONTEXT_NAME);
    }
    
    private SiteResults generateZeroSchoolResult() {
        List<SiteRecord> siteRecordList = new ArrayList<>();
        return new SiteResults(siteRecordList);
    }
    
    private SiteResults generateOneSchoolResult() {
        
        List<SiteRecord> siteRecordList = new ArrayList<>();
        SiteRecord siteRecord = new SiteRecord("true", "true", "false", "100", "Lincoln Elementary", "lincoln", "true");
        siteRecordList.add(siteRecord);
        return new SiteResults(siteRecordList);
    }
    
    private SiteResults generateMultiSchoolResult() {
        
        List<SiteRecord> siteRecordList = new ArrayList<>();
        SiteRecord siteRecord = new SiteRecord("true", "true", "false", "100", "Lincoln Elementary", "lincoln", "true");
        SiteRecord siteRecordSec = new SiteRecord("true", "true", "false", "200", "Lincoln Elementary2", "lincoln", "true");
        
        siteRecordList.add(siteRecord);
        siteRecordList.add(siteRecordSec);
        return new SiteResults(siteRecordList);
    }
    
    @After
    public void tearDown() {
        mViewModel = null;
        mPreferences = null;
    }
}
