/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import junit.framework.Assert;

import static org.mockito.Mockito.when;

public class ItemStatusApiTest extends BaseTestClass {
    
    private final String mContextName = "dvpdt_devprodtest";
    
    private final String mSite = "FDPSA";
    
    private ItemDetails mItemDetails;
    
    @Mock APIInterface mApiInterface;
    
    @Test
    public void VerifyItemStatusApi() {
        mItemDetails = generateItemDetail();
        
        when(mApiInterface.getScanItem(AppUtils.getHeader(mApplication), mContextName, mSite, "1", "0")).thenReturn(Observable.just(mItemDetails));
        
        final Observable<ItemDetails> itemDetailsObservable = mApiInterface.getScanItem(AppUtils.getHeader(mApplication), mContextName, mSite, "1", "0");
        itemDetailsObservable.subscribe(new Consumer<ItemDetails>() {
            @Override
            public void accept(ItemDetails itemDetails) throws Exception {
                verifyItemDetailResult(itemDetails, mItemDetails);
                
            }
        });
    }
    
    private void verifyItemDetailResult(ItemDetails actualResult, ItemDetails expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getLocation(), actualResult.getLocation());
        Assert.assertEquals(expectedResult.getTitle(), actualResult.getTitle());
        Assert.assertEquals(expectedResult.getStatus(), actualResult.getStatus());
        Assert.assertEquals(expectedResult.getDepartment(), actualResult.getDepartment());
        Assert.assertEquals(expectedResult.getAuthor(), actualResult.getAuthor());
    }
    
    private ItemDetails generateItemDetail() {
        
        return new ItemDetails("Math Lab", "ISBN: 978-0-07-663923-6", "Algebra 1", "Available", null, "Mathematics", 0, "Lee, Harper.", null, null, null,
                null, null, true, "00000000000002", null);
    }
}
