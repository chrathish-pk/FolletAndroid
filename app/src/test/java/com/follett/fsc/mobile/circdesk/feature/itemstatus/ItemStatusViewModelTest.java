/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.LoginViewModel;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import junit.framework.Assert;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemStatusViewModelTest {
    
    private final String mContextName = "dvpdt_devprodtest";
    
    private final String mSite = "FDPSA";
    
    private final String mUsername = "pk";
    
    private final String mPassword = "pk";
    
    private ItemStatusViewModel mViewModel;
    
    private Application mApplication;
    
    private APIInterface mApiInterface;
    
    private ItemDetails mItemDetails;
    
    private LoginResults mLoginResults;
    
    private SharedPreferences preferences;
    
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
        mApplication = mock(Application.class);
        mApiInterface = mock(APIInterface.class);
        
        preferences = mock(SharedPreferences.class);
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(mApplication.getSharedPreferences("Follett", Context.MODE_PRIVATE)).thenReturn(preferences);
        when(preferences.edit()).thenReturn(editor);
        when(preferences.getString(SERVER_URI_VALUE, "")).thenReturn("https://devprodtest.follettdestiny.com");
        mViewModel = new ItemStatusViewModel(mApplication);
    
        mLoginResults = generateLoginResult();
//        doLogin();
        mItemDetails = generateItemDetail();
    }
    
    private ItemDetails generateItemDetail() {
        
        return new ItemDetails("", "", "", "", null, "", 0, "Lee, Harper.", "", "", "", "", null, true, "", "");
    }
    
    private LoginResults generateLoginResult () {
        return new LoginResults("", "false", "false", "Karma",
                "1796924", "0", "false", "Pro",
                "0", "1", "false", "false",
                null, "-Z2NPmvWVLETtrcYkkaNOFt0qxMbrYoBVHeO_TrN", "1800000", "true");
        
    }
    
    @Test
    public void doLogin() {
        
        setUpLogin();
//        LoginViewModel loginViewModel = new LoginViewModel(mApplication);
//        loginViewModel.getLoginResults(mContextName,
//                mSite, mUsername, mPassword);
    
    
        Observable<LoginResults> loginResultsObservable = mApiInterface.getLoginResults(mContextName,
                mSite, mUsername, mPassword);
        loginResultsObservable.blockingForEach(new Consumer<LoginResults>() {
            @Override
            public void accept(LoginResults loginResults) throws Exception {
            Assert.assertNotNull(loginResults);
            }
        });
        
//        verify(mApiInterface).getLoginResults(mContextName,
//                mSite, mUsername, mPassword);
//        verifyLoginField(loginViewModel, mLoginResults);
        
    }
    
    private void verifyLoginField(LoginViewModel loginViewModel, LoginResults mLoginResults) {
        Assert.assertEquals(mLoginResults.getSuccess(), "true");
    }
    
    @Test
    public void getScanItemTest() {
        setUpItemStatus();

//        mViewModel.getScanItem("1", "0");
        final Observable<ItemDetails> scanItem = mApiInterface.getScanItem(AppUtils.getHeader(mApplication), mContextName, mSite, "1", "0");
        scanItem.subscribe(new Consumer<ItemDetails>() {
            @Override
            public void accept(ItemDetails itemDetails) throws Exception {
            Assert.assertNotNull(itemDetails);
            }
        });
        
    
        verify(mApiInterface).getScanItem(AppUtils.getHeader(mApplication),mContextName,mSite,"1","0");
        assertFalse(mViewModel.getIsLoading().get());

        verifyItemField(mViewModel, mItemDetails);

    }

    private void verifyItemField(ItemStatusViewModel mViewModel, ItemDetails mItemDetails) {
        Assert.assertEquals(
                mItemDetails.getAuthor(), mViewModel.itemDetailsInfo.getValue().getAuthor());
    }
    
    private void setUpItemStatus() {
            // given that the API service returns a movie observable when getMovieDetails is called
            when(mApiInterface.getScanItem(AppUtils.getHeader(mApplication),mContextName,mSite,"1","0"))
                    .thenReturn(Observable.just(mItemDetails));
        
    }
    
    private void setUpLogin() {
        when(mApiInterface.getLoginResults(mContextName,
                mSite, mUsername, mPassword))
                .thenReturn(Observable.just(mLoginResults));
        
    }
}
