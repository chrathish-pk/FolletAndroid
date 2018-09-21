/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.District;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.DistrictList;
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

public class DistrictListApiTest extends BaseTestClass {
    
    private DistrictList mDistrictResults;
    
    @Mock APIInterface mApiInterface;
    
    @Test
    public void VerifyDistrictApi() {
        mDistrictResults = generateDistrictResult();
        when(mApiInterface.getDistrictList()).thenReturn(Observable.just(mDistrictResults));
        
        final Observable<DistrictList> districtListObservable = mApiInterface.getDistrictList();
        districtListObservable.subscribe(new Consumer<DistrictList>() {
            @Override
            public void accept(DistrictList districtList) throws Exception {
                verifyLoginResult(districtList, mDistrictResults);
            }
        });
    }
    
    private void verifyLoginResult(DistrictList actualResult, DistrictList expectedResult) {
        Assert.assertNotNull(actualResult);
        final District expDistrict = expectedResult.getDistricts()
                .get(0);
        final District actDistrict = actualResult.getDistricts()
                .get(0);
        Assert.assertEquals(expDistrict.getContextName(), actDistrict.getContextName());
        Assert.assertEquals(expDistrict.getDistrictName(), actDistrict.getDistrictName());
    }
    
    private DistrictList generateDistrictResult() {
        
        List<District> districtList = new ArrayList<>();
        District district = new District("follettdevuatdistrict1", "Follett Dev UAT District 1");
        districtList.add(district);
        return new DistrictList(districtList);
    }
    
    @After
    public void tearDown() throws Exception {
        mDistrictResults = null;
        mApiInterface = null;
    }
}
