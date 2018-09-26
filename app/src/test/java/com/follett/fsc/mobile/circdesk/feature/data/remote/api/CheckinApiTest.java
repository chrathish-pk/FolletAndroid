/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckinResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.Info;
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
 * Created by muthulakshmi on 19/09/18.
 */

public class CheckinApiTest extends BaseTestClass {

    private CheckinResult mCheckinResult;
    private String checkinBarcode = "2";
    private String collectionType = "0";
    private String isLibraryUse = "false";

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
    public void verifyCheckinApi() {
        mCheckinResult = generateChecinResult();

        when(apiInterface.getCheckinResult(AppUtils.getInstance().getHeader(mApplication), CONTEXT_NAME, SITE_NAME,
                checkinBarcode, collectionType, isLibraryUse)).thenReturn(Observable.just(mCheckinResult));

        Observable<CheckinResult> checkinResultObservable = apiInterface.getCheckinResult(AppUtils.getInstance().getHeader(mApplication), CONTEXT_NAME, SITE_NAME,
                checkinBarcode, collectionType, isLibraryUse);
        checkinResultObservable.blockingForEach(new Consumer<CheckinResult>() {
            @Override
            public void accept(CheckinResult checkinResult) throws Exception {
                verifyCheckinResult(checkinResult, mCheckinResult);
            }
        });
    }

    private void verifyCheckinResult(CheckinResult actualResult, CheckinResult expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.getSuccess());
        Assert.assertEquals(expectedResult.getInfo().getBarcode(), actualResult.getInfo().getBarcode());
        Assert.assertEquals(expectedResult.getInfo().getTitle(), actualResult.getInfo().getTitle());
        Assert.assertEquals(expectedResult.getInfo().getMaterialType(), actualResult.getInfo().getMaterialType());
    }

    private CheckinResult generateChecinResult() {
        return new CheckinResult(
                new Info("To kill a mockingbird", "", 14129, 2, "T 2", "/passthrough?image=12375/9780061120084.gif"),
                null, null, true);
    }
}
