package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.Patron;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.utils.BaseTestClass;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
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

public class ScanPatronApiTest extends BaseTestClass {

    private ScanPatron mScanPatron;

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
    public void verifyScanPatronApi(){
        mScanPatron = generateScanPatron();

        when(apiInterface.getScanPatron(AppUtils.getInstance().getHeader(mApplication), CONTEXT_NAME, SITE_NAME, "tom")).thenReturn(Observable.just(mScanPatron));

        final Observable<ScanPatron> patronResultsObservable = apiInterface.getScanPatron(AppUtils.getInstance().getHeader(mApplication), CONTEXT_NAME, SITE_NAME, "tom");
        patronResultsObservable.subscribe(new Consumer<ScanPatron>() {
            @Override
            public void accept(ScanPatron scanPatron) throws Exception {
                verifyScanPatronApi(scanPatron, mScanPatron);
            }
        });

    }

    private void verifyScanPatronApi(ScanPatron actualResult, ScanPatron expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(Boolean.parseBoolean(actualResult.getSuccess()));
        Assert.assertEquals(expectedResult.getPatronList().get(0), actualResult.getPatronList().get(0));
    }

    public ScanPatron generateScanPatron() {
        List<Patron> patronList = new ArrayList<>();
        patronList.add(new Patron("P 990836", "Campbell, Tom", "861", "/imagestore/patrons/1534778744727_screenshot20180816at12.43.54pm.jpg"));
        patronList.add(new Patron("P 990785","Feldmann, Tom","463",""));

        return new ScanPatron("0", "0", "0", "0", null, patronList, null, "true",
                "0", "0", "", "", "",
                "", "", "", "", "", false);
    }
}
