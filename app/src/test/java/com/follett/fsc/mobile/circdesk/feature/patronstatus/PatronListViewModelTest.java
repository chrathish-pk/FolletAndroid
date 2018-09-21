/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.AssetCheckOut;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Checkout;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Fine;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_INFO_KEY;
import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_TITLE_KEY;
import static org.mockito.Mockito.when;

public class PatronListViewModelTest extends BaseTestClass {
    
    private PatronListViewModel mViewModel;
    
    private PatronInfo mPatronResults;
    
    private AssetCheckOut mAssetCheckOut;
    
    private Checkout mCheckout;
    
    @Mock
    Bundle mBundle;
    
    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    
    @BeforeClass
    public static void setupRxJavaPlugins() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }
    
    @Before
    public void setUp() {
        mPatronResults = generatePatronInfoResult();
        createMockData();
        if (mViewModel == null) {
            mViewModel = new PatronListViewModel(mApplication);
        }
    }
    
    @Test
    public void createCheckoutModelData() {
        mViewModel.createCheckoutModelData(mBundle);
        mViewModel.checkoutLiveData.observeForever(new Observer<List<CustomCheckoutItem>>() {
            @Override
            public void onChanged(@Nullable List<CustomCheckoutItem> customCheckoutItems) {
                verifyCheckoutResult(customCheckoutItems.get(0), mCheckout);
                verifyAssertCheckoutResult(customCheckoutItems.get(1), mAssetCheckOut);
            }
        });
    }
    
    private void verifyCheckoutResult(CustomCheckoutItem actualResult, Checkout expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(actualResult.getBarCode(), expectedResult.getCopyBarcode());
        Assert.assertEquals(actualResult.getDueDate(), expectedResult.getDueDate());
        Assert.assertEquals(actualResult.getIsOverDue(), expectedResult.getOverDue());
    }
    
    private void verifyAssertCheckoutResult(CustomCheckoutItem actualResult, AssetCheckOut expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(actualResult.getBarCode(), expectedResult.getCopyBarcode());
        Assert.assertEquals(actualResult.getDueDate(), expectedResult.getDueDate());
        Assert.assertEquals(actualResult.getIsOverDue(), expectedResult.getOverDue());
    }
    
    private void createMockData() {
        when(mApplication.getString(R.string.item_checkout_label)).thenReturn("Items Checked Out");
        when(mApplication.getString(R.string.on_hold_label)).thenReturn("On Hold");
        when(mBundle.getParcelable(PATRON_INFO_KEY)).thenReturn(mPatronResults);
        when(mBundle.getString(PATRON_TITLE_KEY)).thenReturn("Items Checked Out");
    }
    
    private PatronInfo generatePatronInfoResult() {
        List<Fine> fineList = new ArrayList<>();
        List<Checkout> checkoutList = new ArrayList<>();
        List<AssetCheckOut> assetCheckOutList = new ArrayList<>();
        
        mAssetCheckOut = new AssetCheckOut(14621, "isbn", "copyBarcode", "title", "lexile", true, 0, "dueDate", 1, "siteName", null, null,
                "fountasAndPinnell", "electronicResourceDisplayable", "providerIconLink", "contentImageLink", null, true, false, null, false,
                "electronicResourceURL", "titleDetailsLink", "pubYear", 101, true, 110, 201, null, "reviewAverage", 1, 129, "author", "extent", null,
                "callNumber", "summary", 434, 34234, false, null, null, null, false, true, true, 1212);
        mCheckout = new Checkout(14621, "isbn", "copyBarcode", "title", "lexile", true, 0, "dueDate", 1, "siteName", null, null, "fountasAndPinnell",
                "electronicResourceDisplayable", "providerIconLink", "contentImageLink", null, true, false, null, false, "electronicResourceURL",
                "titleDetailsLink", "pubYear", 101, true, 110, 201, null, "reviewAverage", 1, 129, "author", "extent", null, "callNumber", "summary", 434,
                34234, false, null, null, null, false, true, true, 1212);
        checkoutList.add(mCheckout);
        assetCheckOutList.add(mAssetCheckOut);
        return new PatronInfo(assetCheckOutList, null, null, true, null, null, "\"P 996598", null, "$2.00", fineList, "zxwertgfyuiokpdavbaqnmklspoirw",
                "LongFirstandLastName", checkoutList);
    }
    
    @After
    public void tearDown() {
        mViewModel = null;
        mPreferences = null;
    }
}
