/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import com.follett.fsc.mobile.circdesk.feature.itemstatus.model.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static org.mockito.Mockito.when;

public class ItemStatusViewModelTest extends BaseTestClass {

    private ItemStatusViewModel mViewModel;

    private ItemDetails mItemDetails;

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
        mViewModel = new ItemStatusViewModel(mApplication);
        mItemDetails = getItemDetail();
    }

    @Test
    public void onCallCompleted() {
        mViewModel.onCallCompleted(mItemDetails);
        Assert.assertEquals(mItemDetails, mViewModel.itemDetailsInfo.getValue());
        mViewModel.itemDetailsInfo.observeForever(new Observer<ItemDetails>() {
            @Override
            public void onChanged(@Nullable ItemDetails itemDetails) {
                verifyItemDetailResult(itemDetails, mItemDetails);
            }
        });
    }

    @Test
    public void getScanItem() {
        createMockSharedPref();
        mViewModel.getScanItem("6", "0");
        mViewModel.itemDetailsInfo.observeForever(new Observer<ItemDetails>() {
            @Override
            public void onChanged(@Nullable ItemDetails itemDetails) {
                Assert.assertNotNull(itemDetails);
            }
        });
    }

    @Test
    public void onCallFailed() {
        mViewModel.onCallFailed(new Throwable());
        Assert.assertFalse(mViewModel.getIsLoading()
                .get());
    }

    private void createMockSharedPref() {
        when(mPreferences.getString(KEY_SESSION_ID, "")).thenReturn(SESSION_ID);
        when(mPreferences.getString(KEY_CONTEXT_NAME, "")).thenReturn(CONTEXT_NAME);
        when(mPreferences.getString(KEY_SITE_SHORT_NAME, "")).thenReturn(SITE_NAME);
    }


    private ItemDetails getItemDetail() {
        return new ItemDetails("Math Lab", "ISBN: 978-0-07-663923-6", "Algebra 1", "Available", null, "Mathematics", 0, "Lee, Harper.", null, null, null,
                null, null, true, "00000000000002", null);
    }

    private void verifyItemDetailResult(ItemDetails actualResult, ItemDetails expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getLocation(), actualResult.getLocation());
        Assert.assertEquals(expectedResult.getTitle(), actualResult.getTitle());
        Assert.assertEquals(expectedResult.getStatus(), actualResult.getStatus());
        Assert.assertEquals(expectedResult.getDepartment(), actualResult.getDepartment());
        Assert.assertEquals(expectedResult.getAuthor(), actualResult.getAuthor());
    }

    @After
    public void tearDown() {
        mViewModel = null;
        mPreferences = null;
    }
}
