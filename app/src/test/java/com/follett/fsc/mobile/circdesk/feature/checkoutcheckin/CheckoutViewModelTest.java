package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.viewmodel.CheckoutViewModel;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckoutInfo;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.Patron;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
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

/**
 * Created by muthulakshmi on 22/09/18.
 */

public class CheckoutViewModelTest extends BaseTestClass implements UpdateUIListener {
    private CheckoutViewModel checkoutViewModel;

    private CheckoutResult mCheckoutResult;
    private ScanPatron mScanPatron;
    private boolean isCheckout;


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
        checkoutViewModel = new CheckoutViewModel(mApplication, this);
        mCheckoutResult = getCheckoutMockResult();
        mScanPatron = getScanPatronMockRestult();
    }

    private CheckoutResult getCheckoutMockResult() {
        return new CheckoutResult(new CheckoutInfo("T 1", "14129", "/passthrough?image=12375/9780061120084.gif", "10/8/2018", "2", "To kill a mockingbird"),
                0, 7, 1, null, null, true);
    }

    public ScanPatron getScanPatronMockRestult() {
        List<Patron> patronList = new ArrayList<>();
        patronList.add(new Patron("P 990836", "Campbell, Tom", "861", "/imagestore/patrons/1534778744727_screenshot20180816at12.43.54pm.jpg"));
        patronList.add(new Patron("P 990785", "Feldmann, Tom", "463", ""));

        return new ScanPatron("0", "0", "0", "0", null, patronList, null, "true",
                "0", "0", "", "", "",
                "", "", "", "", "", false);
    }

    @Test
    public void getCheckoutResult() {
        isCheckout = true;
        createMockSharedPref();
        checkoutViewModel.getCheckoutResult("861", "1", "0",false);
        checkoutViewModel.checkoutResultMutableLiveData.observeForever(new Observer<CheckoutResult>() {
            @Override
            public void onChanged(@Nullable CheckoutResult checkoutResult) {
                Assert.assertNotNull(checkoutResult);
            }
        });
    }

    @Test
    public void getScanPatron() {
        isCheckout = false;
        createMockSharedPref();
        checkoutViewModel.getScanPatron("tom");
        checkoutViewModel.scanPatronMutableLiveData.observeForever(new Observer<ScanPatron>() {
            @Override
            public void onChanged(@Nullable ScanPatron scanPatron) {
                Assert.assertNotNull(scanPatron);
            }
        });
    }

    private void createMockSharedPref() {
        when(mPreferences.getString(KEY_SESSION_ID, "")).thenReturn(SESSION_ID);
        when(mPreferences.getString(KEY_CONTEXT_NAME, "")).thenReturn(CONTEXT_NAME);
        when(mPreferences.getString(KEY_SITE_SHORT_NAME, "")).thenReturn(SITE_NAME);
    }

    @Test
    public void onCallCompleted() {
        if (isCheckout) {
            checkoutViewModel.onCallCompleted(mCheckoutResult);
            Assert.assertEquals(mCheckoutResult, checkoutViewModel.checkoutResultMutableLiveData.getValue());
            checkoutViewModel.checkoutResultMutableLiveData.observeForever(new Observer<CheckoutResult>() {
                @Override
                public void onChanged(@Nullable CheckoutResult checkoutResult) {
                    verifyCheckoutResult(checkoutResult, mCheckoutResult);
                }
            });
        } else {
            checkoutViewModel.onCallCompleted(mScanPatron);
            Assert.assertEquals(mScanPatron, checkoutViewModel.scanPatronMutableLiveData.getValue());
            checkoutViewModel.scanPatronMutableLiveData.observeForever(new Observer<ScanPatron>() {
                @Override
                public void onChanged(@Nullable ScanPatron scanPatron) {
                    verifyScanPatronApi(scanPatron, mScanPatron);
                }
            });
        }
    }

    @Test
    public void onCallFailed() {
        checkoutViewModel.onCallFailed(new Throwable(),"");
        Assert.assertFalse(checkoutViewModel.getIsLoading()
                .get());
    }

    private void verifyCheckoutResult(CheckoutResult actualResult, CheckoutResult expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.getSuccess());
        Assert.assertEquals(expectedResult.getInfo().getBarcode(), actualResult.getInfo().getBarcode());
        Assert.assertEquals(expectedResult.getInfo().getTitle(), actualResult.getInfo().getTitle());
        Assert.assertEquals(expectedResult.getInfo().getMaterialType(), actualResult.getInfo().getMaterialType());
    }

    private void verifyScanPatronApi(ScanPatron actualResult, ScanPatron expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(Boolean.parseBoolean(actualResult.getSuccess()));
        Assert.assertEquals(expectedResult.getPatronList().get(0), actualResult.getPatronList().get(0));
    }

    @Override
    public void updateUI(Object value) {

    }
}
