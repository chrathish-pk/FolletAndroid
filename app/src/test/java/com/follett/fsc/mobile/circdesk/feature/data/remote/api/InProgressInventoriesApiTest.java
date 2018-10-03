package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryList;
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

public class InProgressInventoriesApiTest extends BaseTestClass {

    InProgressInventoryResults mInProgressInventoryResults;

    @Mock
    APIInterface mApiInterface;

    @Test
    public void verifyInProgressInventoriesApiTest(){
        mInProgressInventoryResults = generateInProgressInventoriesResult();

        when(mApiInterface.getInProgressInventoryResults(AppUtils.getInstance().getHeader(mApplication), SITE_NAME_UAT, CONTEXT_NAME_UAT, COLLECTION_TYPE)).thenReturn(Observable.just(mInProgressInventoryResults));

        final Observable<InProgressInventoryResults> inProgressInventoryResultsObservable = mApiInterface.getInProgressInventoryResults(AppUtils.getInstance().getHeader(mApplication), SITE_NAME_UAT, CONTEXT_NAME_UAT, COLLECTION_TYPE);
        inProgressInventoryResultsObservable.subscribe(new Consumer<InProgressInventoryResults>() {
            @Override
            public void accept(InProgressInventoryResults inProgressInventoryResults) throws Exception {
                verifyInProgressInventoriesResult(inProgressInventoryResults, mInProgressInventoryResults);
            }
        });
    }

    private void verifyInProgressInventoriesResult(InProgressInventoryResults actualResult, InProgressInventoryResults expectedResult) {
        Assert.assertNotNull(actualResult);
        expectedResult.getInventoryList().get(0);
        final InventoryList expInventoryResult = expectedResult.getInventoryList().get(0);
        final InventoryList actInventoryResult = actualResult.getInventoryList().get(0);

        Assert.assertEquals(expInventoryResult.getDateStarted(), actInventoryResult.getDateStarted());
        Assert.assertEquals(expInventoryResult.getName(), actInventoryResult.getName());
        Assert.assertEquals(expInventoryResult.getPartialID(), actInventoryResult.getPartialID());
    }

    private InProgressInventoryResults generateInProgressInventoriesResult() {
        List<InventoryList> inventoryLists = new ArrayList<>();
        InventoryList inventoryList = new InventoryList("9/5/2018", "Library Inventory", 100);
        inventoryLists.add(inventoryList);
        return new InProgressInventoryResults(inventoryLists);
    }

    @After
    public void tearDown() throws Exception {
        mInProgressInventoryResults = null;
        mApiInterface = null;
    }
}
