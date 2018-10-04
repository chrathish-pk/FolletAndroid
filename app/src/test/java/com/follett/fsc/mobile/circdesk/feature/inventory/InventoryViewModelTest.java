package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryViewModel;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

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

import static org.mockito.Mockito.when;

public class InventoryViewModelTest extends BaseTestClass implements ItemClickListener, UpdateUIListener {

    private InventoryViewModel inventoryViewModel;
    private InProgressInventoryResults mInprogressInventoryResults;

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
        inventoryViewModel = new InventoryViewModel(mApplication, this, this);
        mInprogressInventoryResults = getInProgressMockResult();
    }

    private InProgressInventoryResults getInProgressMockResult() {
        List<InventoryList> inventoryLists = new ArrayList<>();
        inventoryLists.add(new InventoryList("9/5/2018", "Library Inventory", 100));
        return new InProgressInventoryResults(inventoryLists);
    }


    @Test
    public void getInProgressInventoriesResult() {
        createMockSharedPref();
        inventoryViewModel.getInProgressInventoryResults(SITE_NAME_UAT, CONTEXT_NAME_UAT, COLLECTION_TYPE);
        inventoryViewModel.inventoryListMutableLiveData.observeForever(new Observer<InProgressInventoryResults>() {
            @Override
            public void onChanged(@Nullable InProgressInventoryResults inProgressInventoryResults) {
                Assert.assertNotNull(inProgressInventoryResults);
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
        inventoryViewModel.onCallCompleted(mInprogressInventoryResults);
        Assert.assertEquals(mInprogressInventoryResults, inventoryViewModel.inventoryListMutableLiveData.getValue());
        inventoryViewModel.inventoryListMutableLiveData.observeForever(new Observer<InProgressInventoryResults>() {
            @Override
            public void onChanged(@Nullable InProgressInventoryResults inProgressInventoryResults) {
                verifyInventoriesResult(inProgressInventoryResults, mInprogressInventoryResults);
            }
        });

    }

    @Test
    public void onCallFailed() {
        inventoryViewModel.onCallFailed(new Throwable(),"");
        Assert.assertFalse(inventoryViewModel.getIsLoading()
                .get());
    }

    private void verifyInventoriesResult(InProgressInventoryResults actualResult, InProgressInventoryResults expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getInventoryList().get(0).getDateStarted(), actualResult.getInventoryList().get(0).getDateStarted());
        Assert.assertEquals(expectedResult.getInventoryList().get(0).getName(), actualResult.getInventoryList().get(0).getName());
        Assert.assertEquals(expectedResult.getInventoryList().get(0).getPartialID(), actualResult.getInventoryList().get(0).getPartialID());
    }

    @Override
    public void updateUI(Object value) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
