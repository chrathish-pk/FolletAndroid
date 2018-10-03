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

import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CircTypeRecord;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventory;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResult;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class NewInventoryViewModel extends BaseViewModel implements NetworkInterface {

    private ItemClickListener itemClickListener;
    public MutableLiveData<CreateInventoryResult> createInventoryResultMutableLiveData = new MutableLiveData<>();

    public NewInventoryViewModel(@NonNull Application application, ItemClickListener itemClickListener) {
        super(application);
        this.itemClickListener = itemClickListener;
    }

    public void onItemClicked(View view) {
        itemClickListener.onItemClick(view, 0);
    }

    public void getCreatedInventory() {
        setIsLoding(true);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");

        AppRemoteRepository.getInstance().createInventory(map, this, AppSharedPreferences.getInstance()
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
                circTypeRecords, 0, null, false, null,
                0, null, 0, 0, false,
                false, false, false, false, 0);

        return createInventory;
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        createInventoryResultMutableLiveData.postValue((CreateInventoryResult)model);
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
    }

    @Override
    public void onRefreshToken(int requestCode) {
        // Do Nothing
    }
}
