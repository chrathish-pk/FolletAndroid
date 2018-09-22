package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.CheckinResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.CheckinViewModel;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.Info;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class CheckinViewModelTest extends BaseTestClass implements UpdateUIListener {

    private CheckinViewModel checkinViewModel;

    private CheckinResult mCheckinResult;


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
        checkinViewModel = new CheckinViewModel(mApplication, this);
        mCheckinResult = getCheckinMockResult();
    }

    private CheckinResult getCheckinMockResult() {
        return new CheckinResult(
                new Info("To kill a mockingbird", "", 14129, 2, "T 2", "/passthrough?image=12375/9780061120084.gif"),
                null, null, true);
    }

    @Test
    public void getCheckinResult() {
        createMockSharedPref();
        checkinViewModel.getCheckinData("2", "0", false);
        checkinViewModel.checkinResultMutableLiveData.observeForever(new Observer<CheckinResult>() {
            @Override
            public void onChanged(@Nullable CheckinResult checkinResult) {
                Assert.assertNotNull(checkinResult);
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
        checkinViewModel.onCallCompleted(mCheckinResult);
        Assert.assertEquals(mCheckinResult, checkinViewModel.checkinResultMutableLiveData.getValue());
        checkinViewModel.checkinResultMutableLiveData.observeForever(new Observer<CheckinResult>() {
            @Override
            public void onChanged(@Nullable CheckinResult checkinResult) {
                verifyCheckinResult(checkinResult, mCheckinResult);
            }
        });

    }

    @Test
    public void onCallFailed() {
        checkinViewModel.onCallFailed(new Throwable());
        Assert.assertFalse(checkinViewModel.getIsLoading()
                .get());
    }

    private void verifyCheckinResult(CheckinResult actualResult, CheckinResult expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.getSuccess());
        Assert.assertEquals(expectedResult.getInfo().getBarcode(), actualResult.getInfo().getBarcode());
        Assert.assertEquals(expectedResult.getInfo().getTitle(), actualResult.getInfo().getTitle());
        Assert.assertEquals(expectedResult.getInfo().getMaterialType(), actualResult.getInfo().getMaterialType());
    }

    @Override
    public void updateUI(Object value) {

    }
}
