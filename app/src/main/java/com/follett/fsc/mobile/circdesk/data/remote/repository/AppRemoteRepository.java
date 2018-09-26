/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.data.remote.api.FollettAPIManager;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.DisposableObserverWrapper;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckinResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.model.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Version;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;

import android.support.annotation.Nullable;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;

public class AppRemoteRepository {
    
    private static APIInterface apiService;
    public static AppRemoteRepository mInstance;
    
    public static AppRemoteRepository getInstance() {
        if (mInstance == null) { mInstance = new AppRemoteRepository(); }
        return mInstance;
    }
    
    
    public AppRemoteRepository() {
        apiService = FollettAPIManager.getClient(getString(SERVER_URI_VALUE))
                .create(APIInterface.class);
    }
    
    public void getVersion(@Nullable final NetworkInterface networkInterface) {
        apiService.getVersion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<Version>() {
                    @Override
                    protected void onSuccess(Version version) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(version);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getDistrictList(@Nullable final NetworkInterface networkInterface) {
        apiService.getDistrictList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<DistrictList>() {
                    @Override
                    protected void onSuccess(DistrictList districtList) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(districtList);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    
    public void getCirculationTypeList(@Nullable final NetworkInterface networkInterface, Map<String, String> headers, String site, String contextName) {
        apiService.getCirculationTypeList(headers, site,contextName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<CirculationTypeList>() {
                    @Override
                    protected void onSuccess(CirculationTypeList circulationTypeList) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(circulationTypeList);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getSchoolList(@Nullable final NetworkInterface networkInterface, String contextName) {
        apiService.getSchoolList(contextName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<SiteResults>() {
                    @Override
                    protected void onSuccess(SiteResults siteResults) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(siteResults);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getLoginResults(@Nullable final NetworkInterface networkInterface, String contextName, String site, String userName, String password) {
        
        apiService.getLoginResults(contextName, site, userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<LoginResults>() {
                    @Override
                    protected void onSuccess(LoginResults loginResults) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(loginResults);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getInProgressInventoryResults(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String site, String
            contextName, int collectionType) {
        
        apiService.getInProgressInventoryResults(headers, site, contextName, collectionType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<InProgressInventoryResults>() {
                    @Override
                    protected void onSuccess(InProgressInventoryResults inProgressInventoryResults) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(inProgressInventoryResults);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getInventoryDetails(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String site, String contextName, int partialID) {
        
        apiService.getInventoryDetails(headers, site, contextName, partialID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<InventoryDetails>() {
                    @Override
                    protected void onSuccess(InventoryDetails inventoryDetails) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(inventoryDetails);
                        }
                    }
    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getScanPatron(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            patronBarcodeID) {
        apiService.getScanPatron(headers, contextName, site, patronBarcodeID)
                .subscribeWith(new DisposableObserverWrapper<ScanPatron>() {
                    @Override
                    protected void onSuccess(ScanPatron scanPatron) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(scanPatron);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getCheckoutResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            patronID, String barcode, String collectionType, boolean overrideBlocks) {
        
        apiService.getCheckoutResult(headers, contextName, site, barcode, patronID, collectionType, String.valueOf(overrideBlocks))
                .subscribeWith(new DisposableObserverWrapper<CheckoutResult>() {
                    @Override
                    protected void onSuccess(CheckoutResult checkoutResult) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(checkoutResult);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getCheckinResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            barcode, String collectionType, boolean isLibraryUse) {
        
        apiService.getCheckinResult(headers, contextName, site, barcode, collectionType, String.valueOf(isLibraryUse))
                .subscribeWith(new DisposableObserverWrapper<CheckinResult>() {
                    @Override
                    protected void onSuccess(CheckinResult checkinResult) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(checkinResult);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getTitleDetails(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String bibID) {
        apiService.getTitleDetails(headers, contextName, site, bibID)
                .subscribeWith(new DisposableObserverWrapper<TitleDetails>() {
                    @Override
                    protected void onSuccess(TitleDetails titleDetails) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(titleDetails);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getItemStatus(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            itemBarcodeID, String collectionType) {
        
        apiService.getScanItem(headers, contextName, site, itemBarcodeID, collectionType)
                .subscribeWith(new DisposableObserverWrapper<ItemDetails>() {
                    @Override
                    protected void onSuccess(ItemDetails itemDetails) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(itemDetails);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    public void getPatronStatus(@Nullable final NetworkInterface networkInterface, Map<String, String> headerMap, String contextName, String site, String
            patronBarcode) {
        
        apiService.getPatronStatus(headerMap, contextName, site, patronBarcode)
                .subscribeWith(new DisposableObserverWrapper<PatronInfo>() {
                    @Override
                    protected void onSuccess(PatronInfo patronInfo) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(patronInfo);
                        }
                    }
                    
                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }
                });
    }
    
    
    public void setString(String key, String value) {
        AppSharedPreferences.getInstance()
                .setString(key, value);
    }
    
    public String getString(String key) {
        return AppSharedPreferences.getInstance()
                .getString(key);
    }
    
    public void setInt(String key, int value) {
        AppSharedPreferences.getInstance()
                .setInt(key, value);
    }
    
    public int getInt(String key) {
        return AppSharedPreferences.getInstance()
                .getInt(key);
    }
    
    public void setBoolean(String key, Boolean value) {
        AppSharedPreferences.getInstance()
                .setBoolean(key, value);
    }
    
    public Boolean getBoolean(String key) {
        return AppSharedPreferences.getInstance()
                .getBoolean(key);
    }
    
    public void removeValues(String key) {
        AppSharedPreferences.getInstance()
                .removeValues(key);
    }
    
    public void removeAllSession() {
        AppSharedPreferences.getInstance()
                .removeAllSession();
    }
}
