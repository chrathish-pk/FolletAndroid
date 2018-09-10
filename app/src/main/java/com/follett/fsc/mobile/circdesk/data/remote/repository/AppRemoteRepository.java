/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

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

import android.support.annotation.Nullable;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AppRemoteRepository {

    private APIInterface apiService;

    public static final String BASE_URL = "https://uat-destiny.follettsoftware.com/";

    public AppRemoteRepository() {
        apiService = FollettAPIManager.getClient(BASE_URL)
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
                    }
                });
    }

    public void getScanPatron(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String patronBarcodeID) {
        apiService.getScanPatron(headers, "dvpdt_devprodtest", "FDPSA", "COGNITE", patronBarcodeID, "DestinyCirc", "Android_24_7.0_lge_lucye_LG-H870DS", "1_Android",
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
                    }
                });
    }

    public void getCheckoutResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String patronID, String barcode, String collectionType) {

        apiService.getCheckoutResult(headers, "dvpdt_devprodtest", "FDPSA", barcode, patronID, collectionType, "false")
                .subscribeWith(new Observer<CheckoutResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
                    }
                });
    }

    public void getCheckinResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String barcode, String collectionType, boolean isLibraryUse) {

        apiService.getCheckinResult(headers, "dvpdt_devprodtest", "FDPSA", barcode, collectionType, String.valueOf(isLibraryUse))
                .subscribeWith(new Observer<CheckinResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
                    }
                });
    }

    public void getTitleDetails(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String bibID) {
        apiService.getTitleDetails(headers, "dvpdt_devprodtest", "FDPSA", bibID)
                .subscribeWith(new Observer<TitleDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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

                    }
                });
    }

    public void getItemStatus(Map<String, String> headers,@Nullable final NetworkInterface networkInterface,String itemBarcodeID) {

        apiService.getScanItem("dvpdt_devprodtest","FDPSA",itemBarcodeID,"0")
               .subscribeWith(new Observer<ItemDetails>() {
                   @Override
                   public void onSubscribe(Disposable d) {

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

                    }
                });
    }

    public void getPatronStatus(@Nullable final NetworkInterface networkInterface, Map<String, String> headerMap, String contextName, String site, String patronBarcode) {

        apiService.getPatronStatus(headerMap, contextName, site, patronBarcode)
                .subscribeWith(new Observer<PatronInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
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
                    }
                });
    }
}
