package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.CheckoutInfo;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by muthulakshmi on 22/09/18.
 */

public class CheckoutApiTest extends BaseTestClass {

    private CheckoutResult mCheckoutResult;

    @Mock
    APIInterface apiInterface;

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

    @Test
    public void verifyCheckoutApi() {
        mCheckoutResult = generateCheckoutResult();

        when(apiInterface.getCheckoutResult(AppUtils.getInstance().getHeader(mApplication), CONTEXT_NAME, SITE_NAME, "1","861","0","false")).thenReturn(Observable.just(mCheckoutResult));

        final Observable<CheckoutResult> patronResultsObservable = apiInterface.getCheckoutResult(AppUtils.getInstance().getHeader(mApplication), CONTEXT_NAME, SITE_NAME, "1","861","0","false");
        patronResultsObservable.subscribe(new Consumer<CheckoutResult>() {
            @Override
            public void accept(CheckoutResult checkoutResult) throws Exception {
                verifyCheckoutApi(checkoutResult, mCheckoutResult);
            }
        });

    }

    private void verifyCheckoutApi(CheckoutResult actualResult, CheckoutResult expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.getSuccess());
        Assert.assertEquals(expectedResult.getInfo().getBarcode(), actualResult.getInfo().getBarcode());
        Assert.assertEquals(expectedResult.getInfo().getTitle(), actualResult.getInfo().getTitle());
        Assert.assertEquals(expectedResult.getInfo().getMaterialType(), actualResult.getInfo().getMaterialType());
    }

    public CheckoutResult generateCheckoutResult() {
        return new CheckoutResult(new CheckoutInfo("T 1", "14129", "/passthrough?image=12375/9780061120084.gif", "10/8/2018", "2", "To kill a mockingbird"),
                0, 7, 1, null, null, true);
    }
}
