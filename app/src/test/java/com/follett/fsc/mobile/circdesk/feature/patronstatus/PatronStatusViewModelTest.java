/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.feature.itemstatus.UpdateItemUIListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Fine;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static org.mockito.Mockito.when;

public class PatronStatusViewModelTest extends BaseTestClass implements UpdateItemUIListener {
    
    private PatronStatusViewModel mViewModel;
    
    private PatronInfo mPatronResults;
    
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
            mViewModel = new PatronStatusViewModel(mApplication,this);
            mPatronResults = generatePatronInfoResult();
        }
    }
    
    @Test
    public void getPatronInfo() {
        mViewModel.getPatronInfo("p1");
        Assert.assertNotNull(mViewModel.mPatronInfo.getValue());
    }
    
    @Test
    public void onCallCompleted() {
        mViewModel.onCallCompleted(mPatronResults);
        mViewModel.mPatronInfo.observeForever(new Observer<PatronInfo>() {
            @Override
            public void onChanged(@Nullable PatronInfo patronInfo) {
                verifyPatronResult(patronInfo, mPatronResults);
            }
        });
    }
    
    @Test
    public void onCallFailed() {
        mViewModel.onCallFailed(new Throwable());
        Assert.assertFalse(mViewModel.getIsLoading()
                .get());
    }
    
    private void verifyPatronResult(PatronInfo actualResult, PatronInfo expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(actualResult.getSuccess(), expectedResult.getSuccess());
        Assert.assertEquals(actualResult.getBarcode(), expectedResult.getBarcode());
        Assert.assertEquals(actualResult.getFineTotalString(), expectedResult.getFineTotalString());
        Assert.assertEquals(actualResult.getLastName(), expectedResult.getLastName());
        Assert.assertEquals(actualResult.getFirstName(), expectedResult.getFirstName());
        Assert.assertEquals(actualResult.getFines(), expectedResult.getFines());
    }
    
    private void createMockData() {
        when(mPreferences.getString(KEY_CONTEXT_NAME, "")).thenReturn(CONTEXT_NAME);
        when(mPreferences.getString(KEY_SITE_SHORT_NAME, "")).thenReturn(SITE_NAME);
        when(mPreferences.getString(KEY_SESSION_ID, "")).thenReturn(SESSION_ID);
    }
    
    private PatronInfo generatePatronInfoResult() {
        
        Fine fine = new Fine(null, "The longest title that could", "Damaged", 100, "$2.00");
        List<Fine> fineList = new ArrayList<>();
        fineList.add(fine);
        return new PatronInfo(null, null, null, true, null, null, "\"P 996598", null, "$2.00", fineList, "zxwertgfyuiokpdavbaqnmklspoirw",
                "LongFirstandLastName", null);
    }
    
    @After
    public void tearDown() {
        mViewModel = null;
        mPreferences = null;
    }

    @Override
    public void updateUI(Object value) {

    }
}
