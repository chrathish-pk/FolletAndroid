package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventorySelectionCriteria;
import com.follett.fsc.mobile.circdesk.feature.inventory.SelectionCriteriaItemList;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.when;

public class InventorySelectionCriteriaApiTest extends BaseTestClass {

    InventorySelectionCriteria mInventorySelectionCriteria;

    @Mock
    APIInterface mApiInterface;

    @Test
    public void verifyInventorySelectionApiTest(){
        mInventorySelectionCriteria = generateInventorySelectionResult();

        when(mApiInterface.getSelectedInventoriesList(AppUtils.getInstance().getHeader(mApplication), SITE_NAME_UAT, CONTEXT_NAME_UAT, PARTIAL_ID)).thenReturn(Observable.just(mInventorySelectionCriteria));

        final Observable<InventorySelectionCriteria> inventorySelectionCriteriaObservable = mApiInterface.getSelectedInventoriesList(AppUtils.getInstance().getHeader(mApplication), SITE_NAME_UAT, CONTEXT_NAME_UAT, PARTIAL_ID);
        inventorySelectionCriteriaObservable.subscribe(new Consumer<InventorySelectionCriteria>() {
            @Override
            public void accept(InventorySelectionCriteria inventorySelectionCriteria) throws Exception {
                verifyInventorySelectionResult(inventorySelectionCriteria, mInventorySelectionCriteria);
            }
        });
    }

    private void verifyInventorySelectionResult(InventorySelectionCriteria actualResult, InventorySelectionCriteria expectedResult) {
        Assert.assertNotNull(actualResult);
        expectedResult.getItemList().get(0);
        final SelectionCriteriaItemList expInventoryResult = expectedResult.getItemList().get(0);
        final SelectionCriteriaItemList actInventoryResult = actualResult.getItemList().get(0);

        Assert.assertEquals(expInventoryResult.getValues(), actInventoryResult.getValues());
        Assert.assertEquals(expInventoryResult.getPrompt(), actInventoryResult.getPrompt());
    }

    private InventorySelectionCriteria generateInventorySelectionResult() {
        List<String> selectedInventoryLists = new ArrayList<>();
        selectedInventoryLists.add("");
        List<SelectionCriteriaItemList> selectedInventoryItemLists = new ArrayList<>();
        SelectionCriteriaItemList selectedInventoryList = new SelectionCriteriaItemList(selectedInventoryLists, "Unlimited");
        selectedInventoryItemLists.add(selectedInventoryList);
        return new InventorySelectionCriteria(selectedInventoryItemLists);
    }

    @After
    public void tearDown() throws Exception {
        mInventorySelectionCriteria = null;
        mApiInterface = null;
    }
}
