/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppPrefHelper;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppPreferencesHelper;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.data.remote.api.FollettAPIManager;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.CheckinResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.Version;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;

public class AppRemoteRepository {

    private APIInterface apiService;

    private AppPrefHelper appPreferencesHelper;

    public AppRemoteRepository(AppSharedPreferences appPref) {
        appPreferencesHelper = new AppPreferencesHelper(appPref);
        apiService = FollettAPIManager.getClient(getString(SERVER_URI_VALUE))
                .create(APIInterface.class);
    }

    public void getVersion(@Nullable final NetworkInterface networkInterface) {
        apiService.getVersion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Version>() {
                    @Override
                    public void onNext(Version version) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(version);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        // Do Nothing
                    }
                });
    }

    public void getDistrictList(@Nullable final NetworkInterface networkInterface) {
        apiService.getDistrictList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<DistrictList>() {
                    @Override
                    public void onNext(DistrictList districtList) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(districtList);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        // Do Nothing
                    }
                });
    }

    public void getSchoolList(@Nullable final NetworkInterface networkInterface, String contextName) {
        apiService.getSchoolList(contextName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<SiteResults>() {
                    @Override
                    public void onNext(SiteResults siteResults) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(siteResults);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        // Do Nothing
                    }
                });
    }

    public void getLoginResults(@Nullable final NetworkInterface networkInterface, String contextName, String site, String userName, String password) {

        apiService.getLoginResults(contextName, site, userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<LoginResults>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Do Nothing
                    }

                    @Override
                    public void onNext(LoginResults loginResults) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(loginResults);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                });
    }

    public void getScanPatron(Map<String, String> headers, @Nullable final NetworkInterface networkInterface,String contextName, String site, String patronBarcodeID) {
        apiService.getScanPatron(headers, contextName, site, "COGNITE", patronBarcodeID, "DestinyCirc", "Android_24_7.0_lge_lucye_LG-H870DS", "1_Android",
                "English")
                .subscribeWith(new Observer<ScanPatron>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Do Nothing
                    }

                    @Override
                    public void onNext(ScanPatron scanPatron) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(scanPatron);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                });
    }

    public void getCheckoutResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface,String contextName, String site, String patronID, String barcode, String collectionType) {

        apiService.getCheckoutResult(headers, contextName, site, barcode, patronID, collectionType, "false")
                .subscribeWith(new Observer<CheckoutResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Do Nothing
                    }

                    @Override
                    public void onNext(CheckoutResult checkoutResult) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(checkoutResult);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        //onComplete
                    }
                });
    }

    public void getCheckinResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface,String contextName, String site, String barcode, String collectionType, boolean isLibraryUse) {

        apiService.getCheckinResult(headers, contextName, site, barcode, collectionType, String.valueOf(isLibraryUse))
                .subscribeWith(new Observer<CheckinResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //onSubscribe

                    }

                    @Override
                    public void onNext(CheckinResult checkinResult) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(checkinResult);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        //onComplete

                    }
                });
    }

    public void getTitleDetails(Map<String, String> headers, @Nullable final NetworkInterface networkInterface,String contextName, String site, String bibID) {
        apiService.getTitleDetails(headers, contextName, site, bibID)
                .subscribeWith(new Observer<TitleDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Do Nothing
                    }

                    @Override
                    public void onNext(TitleDetails titleDetails) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(titleDetails);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }
                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                });
    }

    public void getItemStatus(Map<String, String> headers,@Nullable final NetworkInterface networkInterface,String contextName, String site,String itemBarcodeID,String collectionType) {

        apiService.getScanItem(headers, contextName,site,itemBarcodeID,collectionType)
               .subscribeWith(new Observer<ItemDetails>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                       //Do Nothing

                   }

                   @Override
                   public void onNext(ItemDetails itemDetails) {
                       if(networkInterface!=null)
                       {
                           networkInterface.onCallCompleted(itemDetails);
                       }
                   }

                   @Override
                   public void onError(Throwable throwable) {
                       if (networkInterface != null) {
                           networkInterface.onCallFailed(throwable);
                       }
                   }

                   @Override
                   public void onComplete() {
                       //Do Nothing
                    }
                });
    }

    public void getPatronStatus(@Nullable final NetworkInterface networkInterface, Map<String, String> headerMap, String contextName, String site, String patronBarcode) {

        apiService.getPatronStatus(headerMap, contextName, site, patronBarcode)
                .subscribeWith(new Observer<PatronInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Do Nothing
                    }

                    @Override
                    public void onNext(PatronInfo patronInfoResult) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(patronInfoResult);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable);
                        }
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                });
    }


    public void setString(String key, String value) {
        appPreferencesHelper.setString(key, value);
    }

    public String getString(String key) {
        return appPreferencesHelper.getString(key);
    }

    public void setInt(String key, int value) {
        appPreferencesHelper.setInt(key, value);
    }

    public int getInt(String key) {
        return appPreferencesHelper.getInt(key);
    }

    public void setBoolean(String key, Boolean value) {
        appPreferencesHelper.setBoolean(key, value);
    }

    public Boolean getBoolean(String key) {
        return appPreferencesHelper.getBoolean(key);
    }

    public void removeValues(String key) {
        appPreferencesHelper.removeValues(key);
    }

    public void removeAllSession() {
        appPreferencesHelper.removeAllSession();
    }
}
