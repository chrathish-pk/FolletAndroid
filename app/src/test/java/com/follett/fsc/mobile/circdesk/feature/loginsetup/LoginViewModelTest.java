/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.LoginViewModel;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

public class LoginViewModelTest extends BaseTestClass {
    
    private LoginViewModel mViewModel;
    
    private LoginResults mLoginResults;
    
    private final String mUsername = "pk";
    
    private final String mPassword = "pk";
    
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
        mViewModel = new LoginViewModel(mApplication);
    }
    
    @Test
    public void getLoginData() {
        mViewModel.getLoginResults(CONTEXT_NAME, SITE_NAME, mUsername, mPassword);
        Assert.assertFalse(mViewModel.getIsLoading()
                .get());
        Assert.assertEquals(Status.SUCCESS, mViewModel.getStatus()
                .getValue());
    }
    
    @Test
    public void onCallCompleted() {
        mLoginResults = getLoginResults();
        mViewModel.onCallCompleted(mLoginResults);
        Assert.assertEquals(Status.SUCCESS, mViewModel.getStatus()
                .getValue());
    }
    
    @Test
    public void onCallFailed() {
        mViewModel.onCallFailed(new Throwable());
        Assert.assertFalse(mViewModel.getIsLoading()
                .get());
    }
    
    private LoginResults getLoginResults() {
        return new LoginResults("", "false", "false", "Karma", "1796924", "0", "false", "Pro", "0", "1", "false", "false", null,
                "-Z2NPmvWVLETtrcYkkaNOFt0qxMbrYoBVHeO_TrN", "1800000", "true");
    }
    
    @After
    public void tearDown() {
        mViewModel = null;
        mPreferences = null;
    }
}
