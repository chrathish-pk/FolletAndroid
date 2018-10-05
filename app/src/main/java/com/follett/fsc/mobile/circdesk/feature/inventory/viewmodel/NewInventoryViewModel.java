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
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CircTypeRecord;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventory;
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
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
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
        if(view.getId()==R.id.startInventoryBtn) {
            itemClickListener.onItemClick(view, 100);
        }else if(view.getId()==R.id.newInventoryCancelBtn){
            itemClickListener.onItemClick(view, 101);
        }
    }

    public List<NewInventoryData> getNewInventoryDataForLibrary() {
        List<NewInventoryData> newInventoryDataList = new ArrayList<>();
        if (AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_FROM).isEmpty() && AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_TO).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.callNumbersLabel), "All"));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.callNumbersLabel), AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_FROM) + " - " + AppRemoteRepository.getInstance().getString(KEY_CALL_NUMBER_TO)));

        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.circulationTypeLabel), "All Ciruculation Types"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.subLocationLabel), "Sub Location"));

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE).isEmpty())
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), "No exclustions"));
        else
            newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE)));
        return newInventoryDataList;
    }

    public List<NewInventoryData> getNewInventoryDataForResource() {
        List<NewInventoryData> newInventoryDataList = new ArrayList<>();
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.limitedToLabel), "Unlimited"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.resourceTypesLabel), "All Resource Types"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.purchasePriceLabel), "All Resource Types"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.includeItemsLabel), "With all tracking attributes"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.checkoutHandlingLabel), "Checked Out, In Circulation"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.excludeItems), "No exclusions"));
        newInventoryDataList.add(new NewInventoryData(application.getString(R.string.mismatchedItemLocationLabel), "Do Nothing"));

        return newInventoryDataList;
    }

    public void getCreatedInventory() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().createInventory(AppUtils.getInstance().getHeader(application), this, AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), constructJson());
    }

    private CreateInventory constructJson() {

        String circTypes = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST);
        List<CircTypeRecord> circTypeRecords = new Gson().fromJson(circTypes, new TypeToken<List<CircTypeRecord>>() {
        }.getType());

        CreateInventory createInventory = new CreateInventory(AppRemoteRepository.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4,
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_INVENTORY_NAME),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_FROM),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_TO), null,
                circTypeRecords);

        return createInventory;
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
        getCreatedInventory();
    }

}
