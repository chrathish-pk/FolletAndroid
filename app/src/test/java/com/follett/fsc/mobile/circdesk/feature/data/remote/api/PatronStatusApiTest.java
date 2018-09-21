/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Fine;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import junit.framework.Assert;

import static org.mockito.Mockito.when;

public class PatronStatusApiTest extends BaseTestClass {
    
    private PatronInfo mPatronResults;
    
    @Mock APIInterface mApiInterface;
    
    @Test
    public void VerifyPatronStatusApi() {
        mPatronResults = generatePatronInfoResult();
        when(mApiInterface.getPatronStatus(AppUtils.getHeader(mApplication), CONTEXT_NAME, SITE_NAME, "p1")).thenReturn(Observable.just(mPatronResults));
        
        final Observable<PatronInfo> patronResultsObservable = mApiInterface.getPatronStatus(AppUtils.getHeader(mApplication), CONTEXT_NAME, SITE_NAME, "p1");
        patronResultsObservable.subscribe(new Consumer<PatronInfo>() {
            @Override
            public void accept(PatronInfo patronInfo) throws Exception {
                verifyPatronResult(patronInfo, mPatronResults);
            }
        });
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
    
    private PatronInfo generatePatronInfoResult() {
        
        Fine fine = new Fine(null, "The longest title that could", "Damaged", 100, "$2.00");
        List<Fine> fineList = new ArrayList<>();
        fineList.add(fine);
        return new PatronInfo(null, null, null, true, null, null, "\"P 996598", null, "$2.00", fineList, "zxwertgfyuiokpdavbaqnmklspoirw",
                "LongFirstandLastName", null);
    }
    
    @After
    public void tearDown() throws Exception {
        mPatronResults = null;
        mApiInterface = null;
    }
}
