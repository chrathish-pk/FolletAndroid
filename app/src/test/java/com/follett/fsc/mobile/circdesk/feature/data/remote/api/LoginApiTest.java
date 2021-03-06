/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

import static org.mockito.Mockito.when;

public class LoginApiTest extends BaseTestClass {
    
    private final String mUsername = "pk";
    
    private final String mPassword = "pk";
    
    private LoginResults mLoginResults;
    
    @Mock APIInterface mApiInterface;
    
    @BeforeClass
    public static void setupRxJavaPlugins() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }
    
    @Test
    public void VerifyLoginApi() {
        mLoginResults = generateLoginResult();
        when(mApiInterface.getLoginResults(CONTEXT_NAME, SITE_NAME, mUsername, mPassword)).thenReturn(Observable.just(mLoginResults));
        
        Observable<LoginResults> loginResultsObservable = mApiInterface.getLoginResults(CONTEXT_NAME, SITE_NAME, mUsername, mPassword);
        loginResultsObservable.blockingForEach(new Consumer<LoginResults>() {
            @Override
            public void accept(LoginResults loginResults) throws Exception {
                verifyLoginResult(loginResults, mLoginResults);
            }
        });
    }
    
    private void verifyLoginResult(LoginResults actualResult, LoginResults expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(Boolean.parseBoolean(actualResult.getSuccess()));
        Assert.assertEquals(expectedResult.getFirstName(), actualResult.getFirstName());
        Assert.assertEquals(expectedResult.getLastName(), actualResult.getLastName());
        Assert.assertEquals(expectedResult.getSessionID(), actualResult.getSessionID());
    }
    
    private LoginResults generateLoginResult() {
        return new LoginResults("", "false", "false", "Karma", "1796924", "0", "false", "Pro", "0", "1", "false", "false", null,
                "-Z2NPmvWVLETtrcYkkaNOFt0qxMbrYoBVHeO_TrN", "1800000", "true");
    }
    
    @After
    public void tearDown() throws Exception {
        mLoginResults = null;
        mApiInterface = null;
        mPreferences = null;
    }
}
