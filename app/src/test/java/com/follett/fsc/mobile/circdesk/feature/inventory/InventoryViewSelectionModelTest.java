package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static org.mockito.Mockito.when;

public class InventoryViewSelectionModelTest extends BaseTestClass implements UpdateUIListener {

    private InventoryViewSelectionModel selectionTypeViewModel;
    private InventorySelectionCriteria mInventorySelectionCriteria;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @BeforeClass
    public static void setupRxJavaPlugins() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setUp() {
        selectionTypeViewModel = new InventoryViewSelectionModel(mApplication, this);
        mInventorySelectionCriteria = getSelectionMockResult();
    }

    private InventorySelectionCriteria getSelectionMockResult() {
        List<String> selectedInventoryLists = new ArrayList<>();
        selectedInventoryLists.add("");
        List<SelectionCriteriaItemList> selectionTypeLists = new ArrayList<>();
        selectionTypeLists.add(new SelectionCriteriaItemList(selectedInventoryLists, "Regular"));
        return new InventorySelectionCriteria(selectionTypeLists);
    }


    @Test
    public void getViewSelectionResult() {
        createMockSharedPref();
        selectionTypeViewModel.fetchSelectedInventoryList();
        selectionTypeViewModel.inventorySelectionCriteriaMutableLiveData.observeForever(new Observer<InventorySelectionCriteria>() {
            @Override
            public void onChanged(@Nullable InventorySelectionCriteria selectionTypeList) {
                Assert.assertNotNull(selectionTypeList);
            }
        });
    }

    private void createMockSharedPref() {
        when(mPreferences.getString(KEY_SESSION_ID, "")).thenReturn(SESSION_ID);
        when(mPreferences.getString(KEY_CONTEXT_NAME, "")).thenReturn(CONTEXT_NAME_UAT);
        when(mPreferences.getString(KEY_SITE_SHORT_NAME, "")).thenReturn(SITE_NAME_UAT);
    }

    @Test
    public void onCallCompleted() {
        selectionTypeViewModel.onCallCompleted(mInventorySelectionCriteria);
        Assert.assertEquals(mInventorySelectionCriteria, selectionTypeViewModel.inventorySelectionCriteriaMutableLiveData.getValue());
        selectionTypeViewModel.inventorySelectionCriteriaMutableLiveData.observeForever(new Observer<InventorySelectionCriteria>() {
            @Override
            public void onChanged(@Nullable InventorySelectionCriteria inventorySelectionCriteria) {
                verifyViewSelectionResult(inventorySelectionCriteria, mInventorySelectionCriteria);
            }
        });

    }

    @Test
    public void onCallFailed() {
        selectionTypeViewModel.onCallFailed(new Throwable(),"");
        Assert.assertFalse(selectionTypeViewModel.getIsLoading()
                .get());
    }

    private void verifyViewSelectionResult(InventorySelectionCriteria actualResult, InventorySelectionCriteria expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getItemList().get(0).getValues(), actualResult.getItemList().get(0).getValues());
        Assert.assertEquals(expectedResult.getItemList().get(0).getPrompt(), actualResult.getItemList().get(0).getPrompt());
    }

    @Override
    public void updateUI(Object value) {

    }


}
