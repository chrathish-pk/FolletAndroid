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
import com.follett.fsc.mobile.circdesk.feature.inventory.InventorySelectionCriteria;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventory;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResult;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.model.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Version;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.google.gson.Gson;
import com.google.gson.Gson;

import android.support.annotation.Nullable;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_PERMISSIONS;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SECRET_PASS;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SECRET_USERNAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_USERNAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CHECKIN_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CHECK_OUT_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CIRCULATION_TYPE_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CREATE_INVENTORY_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.GET_SELECTED_INVENTORY_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INPROGRESS_INVENTORY_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INVENTORY_DETAILS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.ITEM_STATUS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.PATRON_STATUS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.SCAN_PATRON_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.SERVICE_ISSUE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.TITLE_DETAILS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CHECKIN_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CHECK_OUT_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CIRCULATION_TYPE_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INPROGRESS_INVENTORY_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INVENTORY_DETAILS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.ITEM_STATUS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.PATRON_STATUS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.SCAN_PATRON_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.SERVICE_ISSUE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.TITLE_DETAILS_REQUEST_CODE;

public class AppRemoteRepository<T> {
    
    private static APIInterface apiService;

    public static AppRemoteRepository mInstance;

    private int mCount = 0;

    public static AppRemoteRepository getInstance() {
        if (mInstance == null) {
            mInstance = new AppRemoteRepository();
        }
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
                        onSuccessResult(networkInterface, version);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        // Do Nothing
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
                        onSuccessResult(networkInterface, districtList);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        // Do Nothing
                    }
                });
    }


    public void getCirculationTypeList(@Nullable final NetworkInterface networkInterface, Map<String, String> headers, String site, String contextName) {
        apiService.getCirculationTypeList(headers, site, contextName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<CirculationTypeList>() {
                    @Override
                    protected void onSuccess(CirculationTypeList circulationTypeList) {
                        onSuccessResult(networkInterface, circulationTypeList);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, CIRCULATION_TYPE_REQUEST_CODE);
                    }
                });
    }

    public void getSubLocationList(@Nullable final NetworkInterface networkInterface, Map<String, String> headers, String site, String contextName){
        apiService.getSubLocationList(headers,site,contextName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<SubLocation>() {
                    @Override
                    protected void onSuccess(SubLocation subLocation) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(subLocation);
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
                        onSuccessResult(networkInterface, siteResults);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        // Do Nothing
                    }
                });
    }

    public void getLoginResults(final boolean isSessionExpireReq, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            userName, String password, final int reqCode) {

        apiService.getLoginResults(contextName, site, userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<LoginResults>() {
                    @Override
                    protected void onSuccess(LoginResults loginResults) {
                        if (networkInterface == null) {
                            return;
                        }
                        checkLoginResult(networkInterface, loginResults, isSessionExpireReq, reqCode);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        // DO Nothing
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
                        onSuccessResult(networkInterface, inProgressInventoryResults);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, INPROGRESS_INVENTORY_REQUEST_CODE);
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
                        onSuccessResult(networkInterface, inventoryDetails);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, INVENTORY_DETAILS_REQUEST_CODE);
                    }
                });
    }

    public void getScanPatron(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            patronBarcodeID) {
        apiService.getScanPatron(headers, contextName, site, patronBarcodeID)
                .subscribeWith(new DisposableObserverWrapper<ScanPatron>() {
                    @Override
                    protected void onSuccess(ScanPatron scanPatron) {
                        onSuccessResult(networkInterface, scanPatron);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, SCAN_PATRON_REQUEST_CODE);
                    }
                });
    }

    public void getCheckoutResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            patronID, String barcode, String collectionType, boolean overrideBlocks) {

        apiService.getCheckoutResult(headers, contextName, site, barcode, patronID, collectionType, String.valueOf(overrideBlocks))
                .subscribeWith(new DisposableObserverWrapper<CheckoutResult>() {
                    @Override
                    protected void onSuccess(CheckoutResult checkoutResult) {
                        onSuccessResult(networkInterface, checkoutResult);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, CHECK_OUT_REQUEST_CODE);
                    }
                });
    }

    public void getCheckinResult(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            barcode, String collectionType, boolean isLibraryUse) {

        apiService.getCheckinResult(headers, contextName, site, barcode, collectionType, String.valueOf(isLibraryUse))
                .subscribeWith(new DisposableObserverWrapper<CheckinResult>() {
                    @Override
                    protected void onSuccess(CheckinResult checkinResult) {
                        onSuccessResult(networkInterface, checkinResult);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, CHECKIN_REQUEST_CODE);
                    }
                });
    }

    public void getTitleDetails(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String bibID) {
        apiService.getTitleDetails(headers, contextName, site, bibID)
                .subscribeWith(new DisposableObserverWrapper<TitleDetails>() {
                    @Override
                    protected void onSuccess(TitleDetails titleDetails) {
                        onSuccessResult(networkInterface, titleDetails);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, TITLE_DETAILS_REQUEST_CODE);
                    }
                });
    }

    public void getItemStatus(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, String
            itemBarcodeID, String collectionType) {

        apiService.getScanItem(headers, contextName, site, itemBarcodeID, collectionType)
                .subscribeWith(new DisposableObserverWrapper<ItemDetails>() {
                    @Override
                    protected void onSuccess(ItemDetails itemDetails) {
                        onSuccessResult(networkInterface, itemDetails);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, ITEM_STATUS_REQUEST_CODE);
                    }
                });
    }

    public void getPatronStatus(@Nullable final NetworkInterface networkInterface, Map<String, String> headerMap, String contextName, String site, String
            patronBarcode) {
        apiService.getPatronStatus(headerMap, contextName, site, patronBarcode)
                .subscribeWith(new DisposableObserverWrapper<PatronInfo>() {
                    @Override
                    protected void onSuccess(PatronInfo patronInfo) {
                        onSuccessResult(networkInterface, patronInfo);
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        onFailedResult(networkInterface, throwable, errorMessage);
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, PATRON_STATUS_REQUEST_CODE);
                    }
                });
    }


    public void getSelectedInventoriesList(Map<String, String> headers, @Nullable final NetworkInterface networkInterface,  String site, String contextName, int partialID) {
        apiService.getSelectedInventoriesList(headers, site, contextName, partialID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserverWrapper<InventorySelectionCriteria>() {
                    @Override
                    protected void onSuccess(InventorySelectionCriteria inventorySelectionCriteria) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(inventorySelectionCriteria);
                        }
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, GET_SELECTED_INVENTORY_REQUEST_CODE);
                    }
                });
    }


    public void createInventory(Map<String, String> headers, @Nullable final NetworkInterface networkInterface, String contextName, String site, CreateInventory createInventory) {

        apiService.createInventory(headers, contextName, site, createInventory)
                .subscribeWith(new DisposableObserverWrapper<CreateInventoryResult>() {
                    @Override
                    protected void onSuccess(CreateInventoryResult createInventoryResult) {
                        if (networkInterface != null) {
                            networkInterface.onCallCompleted(createInventoryResult);
                        }
                    }

                    @Override
                    protected void onFailed(Throwable throwable, String errorMessage) {
                        if (networkInterface != null) {
                            networkInterface.onCallFailed(throwable, errorMessage);
                        }
                    }

                    @Override
                    protected void onRefreshToken() {
                        onRefreshSession(networkInterface, CREATE_INVENTORY_REQUEST_CODE);
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

    private void onSuccessResult(NetworkInterface networkInterface, Object model) {
        mCount = 0;
        if (networkInterface != null) {
            networkInterface.onCallCompleted(model);
        }
    }

    private void onFailedResult(NetworkInterface networkInterface, Throwable throwable, String errorMessage) {
        if (networkInterface != null) {
            networkInterface.onCallFailed(throwable, errorMessage);
        }
    }

    private void onRefreshSession(@Nullable final NetworkInterface networkInterface, int reqCode) {
        if (mCount == 0) {
            mCount++;
            getLoginResults(true, networkInterface, getString(KEY_CONTEXT_NAME), getString(KEY_SITE_SHORT_NAME), getString(KEY_SECRET_USERNAME), getString
                    (KEY_SECRET_PASS), reqCode);
        } else {
            mCount = 0;
            onFailedResult(networkInterface, new IllegalArgumentException(), SERVICE_ISSUE);
        }
    }

    private void checkLoginResult(NetworkInterface networkInterface, LoginResults loginResults, boolean isSessionExpireReq, int reqCode) {

        if (loginResults.getSuccess() != null && loginResults.getSuccess()
                .equalsIgnoreCase("true")) {
            setString(KEY_SESSION_ID, loginResults.getSessionID());
            setString(KEY_PERMISSIONS, new Gson().toJson((loginResults.getPermissions())));
            setString(KEY_USERNAME, loginResults.getLastName());
            if (isSessionExpireReq) {
                networkInterface.onRefreshToken(reqCode);
            } else {
                onSuccessResult(networkInterface, loginResults);
            }
        } else if (isSessionExpireReq) {
            onFailedResult(networkInterface, new IllegalStateException(), SERVICE_ISSUE);
        }
    }
}
