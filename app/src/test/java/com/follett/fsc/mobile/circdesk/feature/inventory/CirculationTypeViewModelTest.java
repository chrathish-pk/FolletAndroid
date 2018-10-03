package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.feature.inventory.model.CircTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.CirculationTypeViewModel;
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

public class CirculationTypeViewModelTest extends BaseTestClass {

    private CirculationTypeViewModel circulationTypeViewModel;
    private CirculationTypeList mCirculationTypeList;

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
        circulationTypeViewModel = new CirculationTypeViewModel(mApplication);
        mCirculationTypeList = getCirculationMockResult();
    }

    private CirculationTypeList getCirculationMockResult() {
        List<CircTypeList> circTypeLists = new ArrayList<>();
        circTypeLists.add(new CircTypeList(100, "Regular", true));
        return new CirculationTypeList(circTypeLists);
    }


    @Test
    public void getCirculationResult() {
        createMockSharedPref();
        circulationTypeViewModel.fetchCirculationTypeList();
        circulationTypeViewModel.circulationTypeListMutableLiveData.observeForever(new Observer<CirculationTypeList>() {
            @Override
            public void onChanged(@Nullable CirculationTypeList circulationTypeList) {
                Assert.assertNotNull(circulationTypeList);
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
        circulationTypeViewModel.onCallCompleted(mCirculationTypeList);
        Assert.assertEquals(mCirculationTypeList, circulationTypeViewModel.circulationTypeListMutableLiveData.getValue());
        circulationTypeViewModel.circulationTypeListMutableLiveData.observeForever(new Observer<CirculationTypeList>() {
            @Override
            public void onChanged(@Nullable CirculationTypeList circulationTypeList) {
                verifyCirculationResult(circulationTypeList, mCirculationTypeList);
            }
        });

    }

    @Test
    public void onCallFailed() {
        circulationTypeViewModel.onCallFailed(new Throwable(),"");
        Assert.assertFalse(circulationTypeViewModel.getIsLoading()
                .get());
    }

    private void verifyCirculationResult(CirculationTypeList actualResult, CirculationTypeList expectedResult) {
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(expectedResult.getCircTypeList().get(0).getCircTypeDescription(), actualResult.getCircTypeList().get(0).getCircTypeDescription());
        Assert.assertEquals(expectedResult.getCircTypeList().get(0).getCircTypeID(), actualResult.getCircTypeList().get(0).getCircTypeID());
    }


}
