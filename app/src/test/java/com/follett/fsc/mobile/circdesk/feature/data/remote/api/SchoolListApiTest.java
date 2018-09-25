/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteRecord;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import junit.framework.Assert;

import static org.mockito.Mockito.when;

public class SchoolListApiTest extends BaseTestClass {
    
    private SiteResults mSiteResults;
    
    @Mock APIInterface mApiInterface;
    
    @Test
    public void VerifySchoolListApi() {
        mSiteResults = generateSchoolResult();
        when(mApiInterface.getSchoolList(CONTEXT_NAME)).thenReturn(Observable.just(mSiteResults));
        
        final Observable<SiteResults> siteResultsObservable = mApiInterface.getSchoolList(CONTEXT_NAME);
        siteResultsObservable.subscribe(new Consumer<SiteResults>() {
            @Override
            public void accept(SiteResults siteResult) throws Exception {
                verifySchoolResult(siteResult, mSiteResults);
            }
        });
    }
    
    private void verifySchoolResult(SiteResults actualResult, SiteResults expectedResult) {
        Assert.assertNotNull(actualResult);
        expectedResult.sites.get(0);
        final SiteRecord expSiteResult = expectedResult.sites.get(0);
        final SiteRecord actSiteResult = actualResult.sites.get(0);
        
        Assert.assertEquals(expSiteResult.getAsset(), actSiteResult.getAsset());
        Assert.assertEquals(expSiteResult.getLibrary(), actSiteResult.getLibrary());
        Assert.assertEquals(expSiteResult.getMedia(), actSiteResult.getMedia());
        Assert.assertEquals(expSiteResult.getSiteID(), actSiteResult.getSiteID());
        Assert.assertEquals(expSiteResult.getSiteName(), actSiteResult.getSiteName());
        Assert.assertEquals(expSiteResult.getSiteShortName(), actSiteResult.getSiteShortName());
        Assert.assertEquals(expSiteResult.getTextbook(), actSiteResult.getTextbook());
    }
    
    private SiteResults generateSchoolResult() {
        
        List<SiteRecord> siteRecordList = new ArrayList<>();
        SiteRecord siteRecord = new SiteRecord("true", "true", "false", "100", "Lincoln Elementary", "lincoln", "true");
        siteRecordList.add(siteRecord);
        return new SiteResults(siteRecordList);
    }
    
    @After
    public void tearDown() throws Exception {
        mSiteResults = null;
        mApiInterface = null;
    }
}
