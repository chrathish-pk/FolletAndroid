/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Version;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import junit.framework.Assert;

import static org.mockito.Mockito.when;

public class VersionApiTest extends BaseTestClass {
    
    private Version mVersion;
    
    @Mock APIInterface mApiInterface;
    
    @Test
    public void VerifyVersionApi() {
        mVersion = generateVersionResult();
        when(mApiInterface.getVersion()).thenReturn(Observable.just(mVersion));
        
        final Observable<Version> versionObservable = mApiInterface.getVersion();
        versionObservable.subscribe(new Consumer<Version>() {
            @Override
            public void accept(Version version) throws Exception {
                verifyLoginResult(version, mVersion);
            }
        });
    }
    
    private void verifyLoginResult(Version actualResult, Version expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getVersion(), actualResult.getVersion());
    }
    
    private Version generateVersionResult() {
        return new Version("5");
    }
    
    @After
    public void tearDown() throws Exception {
        mApiInterface = null;
        mPreferences = null;
    }
}
