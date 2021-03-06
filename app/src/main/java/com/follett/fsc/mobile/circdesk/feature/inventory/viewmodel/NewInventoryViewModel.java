/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryLibRequest;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResRequest;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResult;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.NewInventoryData;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CALL_NUMBER_FROM;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CALL_NUMBER_TO;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_IS_LIBRARY_SELECTED;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_LIMITED_TO_LIST;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_SUB_LOCATION;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class NewInventoryViewModel extends BaseViewModel implements NetworkInterface {

    private ItemClickListener itemClickListener;
    public MutableLiveData<CreateInventoryResult> createInventoryResultMutableLiveData = new MutableLiveData<>();
    private Application application;

    public NewInventoryViewModel(@NonNull Application application, ItemClickListener itemClickListener) {
        super(application);
        this.application = application;
        this.itemClickListener = itemClickListener;
    }

    public void onItemClicked(View view) {
        if (view.getId() == R.id.startInventoryBtn) {
            itemClickListener.onItemClick(view, 100);
        } else if (view.getId() == R.id.newInventoryCancelBtn) {
            itemClickListener.onItemClick(view, 101);
        }
    }

    public List<NewInventoryData> getNewInventoryDataForLibrary() {
        List<NewInventoryData> newInventoryDataList = new ArrayList<>();
        if (AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_FROM).isEmpty() && AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_TO).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.callNumbersLabel), application.getString(R.string.all)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.callNumbersLabel), AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_FROM) + " - " + AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_TO)));

        if (AppRemoteRepository.getInstance().getString(KEY_CIRCULATION_TYPE_LIST).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.circulationTypeLabel), application.getString(R.string.allCirculationTypes)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.circulationTypeLabel), AppRemoteRepository.getInstance().getString(KEY_CIRCULATION_TYPE_LIST)));


        if (AppRemoteRepository.getInstance().getString(KEY_SELECTED_SUB_LOCATION).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.subLocationLabel), application.getString(R.string.subLocationLabel)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.subLocationLabel), AppRemoteRepository.getInstance().getString(KEY_SELECTED_SUB_LOCATION)));


        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_DATE).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), application.getString(R.string.noExclusions)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_DATE)));
        return newInventoryDataList;
    }

    public List<NewInventoryData> getNewInventoryDataForResource() {
        List<NewInventoryData> newInventoryDataList = new ArrayList<>();

        if (AppRemoteRepository.getInstance().getString(KEY_SELECTED_LIMITED_TO_LIST).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.limitedToLabel), application.getString(R.string.unlimited)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.limitedToLabel), AppRemoteRepository.getInstance().getString(KEY_SELECTED_LIMITED_TO_LIST)));


        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.resourceTypesLabel), application.getString(R.string.allResourceTypes)));

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_OPTION_Value).isEmpty() &&
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_VALUE).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.purchasePriceLabel), application.getString(R.string.anyValue)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.purchasePriceLabel),
                    AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_OPTION_Value) + " " +
                            AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_VALUE)));


        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_INCLUDE_ITEMS).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.includeItemsLabel), application.getString(R.string.withAllTrackingAttributes)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.includeItemsLabel), AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_INCLUDE_ITEMS)));

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_CHECKOUT_HANDLING).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.checkoutHandlingLabel), application.getString(R.string.checkedOutInCirculation)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.checkoutHandlingLabel), AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_CHECKOUT_HANDLING)));

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_DATE).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), application.getString(R.string.noExclusions)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_DATE)));

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_MISMATCHED_ITEM).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.mismatchedItemLocationLabel), application.getString(R.string.doNothing)));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.mismatchedItemLocationLabel), AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_MISMATCHED_ITEM)));

        return newInventoryDataList;
    }

    public void getCreatedInventory() {
        setIsLoding(true);
        if (AppSharedPreferences.getInstance().getBoolean(KEY_IS_LIBRARY_SELECTED)) {
            AppRemoteRepository.getInstance().createLibInventory(AppUtils.getInstance().getHeader(application), this, AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), constructLibJson());
        } else {
            AppRemoteRepository.getInstance().createResInventory(AppUtils.getInstance().getHeader(application), this, AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), constructResJSon());
        }
    }

    private CreateInventoryLibRequest constructLibJson() {
        String circTypes = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST_JSON);
        List<Integer> circulationIDList = new Gson().fromJson(circTypes, new TypeToken<List<Integer>>() {
        }.getType());

        String subLocation = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_SUB_LOCATION_JSON);
        List<Integer> subLocationList = new Gson().fromJson(subLocation, new TypeToken<List<Integer>>() {
        }.getType());

        CreateInventoryLibRequest createInventoryLibRequest = new CreateInventoryLibRequest(AppRemoteRepository.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4,
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_INVENTORY_NAME),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_FROM),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_TO), subLocationList,
                circulationIDList);

        return createInventoryLibRequest;
    }

    private CreateInventoryResRequest constructResJSon() {
        String priceLimiterValueListString = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_LIMITED_TO_LIST_JSON);
        List<Integer> selectedPriceLimiterValue = new Gson().fromJson(priceLimiterValueListString, new TypeToken<List<Integer>>() {
        }.getType());

        CreateInventoryResRequest createInventoryResRequest = new CreateInventoryResRequest(null,
                AppRemoteRepository.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4,
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_INVENTORY_NAME),
                AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_INCLUDE_BARCODED_SELECTED),
                AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_INCLUDE_UNBARCODED_SELECTED),
                AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_INCLUDE_CONSUMMABLE_SELECTED),
                true,
                AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_CHECKOUT_HANDLING_UNACCOUNTED_SELECTED),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_VALUE),
                String.valueOf(AppSharedPreferences.getInstance().getInt(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_OPTION)),
                AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_UNLIMITED_SELECTED),
                String.valueOf(AppSharedPreferences.getInstance().getInt(AppSharedPreferences.KEY_SELECTED_LIMITED_TO_ID))
                , selectedPriceLimiterValue);

        return createInventoryResRequest;
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        createInventoryResultMutableLiveData.postValue((CreateInventoryResult) model);
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
    }

    @Override
    public void onRefreshToken(int requestCode) {
        if (AppSharedPreferences.getInstance().getBoolean(KEY_IS_LIBRARY_SELECTED)) {
            AppRemoteRepository.getInstance().createLibInventory(AppUtils.getInstance().getHeader(application), this, AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), constructLibJson());
        } else {
            AppRemoteRepository.getInstance().createResInventory(AppUtils.getInstance().getHeader(application), this, AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), constructResJSon());
        }
    }

}
