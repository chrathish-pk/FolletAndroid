package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.when;

public class InventoryDetailsApiTest extends BaseTestClass {

    InventoryDetails mInventoryDetails;

    @Mock
    APIInterface mApiInterface;

    @Test
    public void verifyInventoryDetailsApiTest(){
        mInventoryDetails = generateInventoryDetails();

        when(mApiInterface.getInventoryDetails(AppUtils.getInstance().getHeader(mApplication), SITE_NAME_UAT, CONTEXT_NAME_UAT, PARTIAL_ID))
                .thenReturn(Observable.just(mInventoryDetails));

        final Observable<InventoryDetails> inventoryDetailsObservable = mApiInterface.getInventoryDetails(AppUtils.getInstance().getHeader(mApplication), SITE_NAME_UAT, CONTEXT_NAME_UAT, PARTIAL_ID);
        inventoryDetailsObservable.subscribe(new Consumer<InventoryDetails>() {
            @Override
            public void accept(InventoryDetails inventoryDetails) throws Exception {
                verifyInventoryDetails(inventoryDetails, mInventoryDetails);
            }
        });
    }

    private void verifyInventoryDetails(InventoryDetails actualResult, InventoryDetails expectedResult) {
        Assert.assertNotNull(actualResult);

        Assert.assertEquals(expectedResult.getName(), actualResult.getName());
        Assert.assertEquals(expectedResult.getCompletePercentage(), actualResult.getCompletePercentage());
        Assert.assertEquals(expectedResult.getPartialID(), actualResult.getPartialID());
    }

    private InventoryDetails generateInventoryDetails() {
        return new InventoryDetails("Library Inventory", "12.90%", 100);
    }

    @After
    public void tearDown() throws Exception {
        mInventoryDetails = null;
        mApiInterface = null;
    }
}
