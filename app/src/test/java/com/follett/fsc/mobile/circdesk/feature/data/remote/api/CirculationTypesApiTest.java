package com.follett.fsc.mobile.circdesk.feature.data.remote.api;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CircTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
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

public class CirculationTypesApiTest extends BaseTestClass {

    private CirculationTypeList mCirculationTypeList;

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
    public void verifyCirculationApi() {
        mCirculationTypeList = generateCirculationMockResult();

        when(apiInterface.getCirculationTypeList(AppUtils.getInstance().getHeader(mApplication),SITE_NAME,CONTEXT_NAME)).thenReturn(Observable.just(mCirculationTypeList));

        Observable<CirculationTypeList> circulationTypesApiTestObservable = apiInterface.getCirculationTypeList(AppUtils.getInstance().getHeader(mApplication),SITE_NAME,CONTEXT_NAME);
        circulationTypesApiTestObservable.subscribe(new Consumer<CirculationTypeList>() {
            @Override
            public void accept(CirculationTypeList circulationTypeList) throws Exception {
                verifyCirculationApiResult(circulationTypeList,mCirculationTypeList);
            }
        });
    }

    private void verifyCirculationApiResult(CirculationTypeList actualResult, CirculationTypeList expectedResult){
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getCircTypeList().get(0).getCircTypeDescription(), actualResult.getCircTypeList().get(0).getCircTypeDescription());
        Assert.assertEquals(expectedResult.getCircTypeList().get(0).getCircTypeID(), actualResult.getCircTypeList().get(0).getCircTypeID());
    }

    private CirculationTypeList generateCirculationMockResult() {
        List<CircTypeList> circTypeLists = new ArrayList<>();
        circTypeLists.add(new CircTypeList(100, "Regular", true));
        return new CirculationTypeList(circTypeLists);
    }


}
