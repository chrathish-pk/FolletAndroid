package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;

import java.util.ArrayList;

public class IncludeItemAttributesViewModel extends BaseViewModel {

    public MutableLiveData<ArrayList<IncludeItem>> includeItemListMutableLiveData = new MutableLiveData<>();
    private Application mApplication;

    public IncludeItemAttributesViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void setIncludeItemData() {

        String[] array = {"Barcoded", "Unbarcoded", "Consummable"};
        ArrayList<IncludeItem> includeList = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            includeList.add(new IncludeItem(array[i]));
        }
        includeItemListMutableLiveData.postValue(includeList);


    }
}