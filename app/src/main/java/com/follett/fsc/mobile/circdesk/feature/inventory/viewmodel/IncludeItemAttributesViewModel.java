package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;

import java.util.ArrayList;
import java.util.List;

public class IncludeItemAttributesViewModel extends BaseViewModel {

    public MutableLiveData<List<IncludeItem>> includeItemListMutableLiveData = new MutableLiveData<>();
    private Application mApplication;

    public IncludeItemAttributesViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void setIncludeItemData() {

        String[] array = {mApplication.getString(R.string.barcoded), mApplication.getString(R.string.unBarcoded), mApplication.getString(R.string.consummable)};
        ArrayList<IncludeItem> includeList = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            includeList.add(new IncludeItem(array[i], i));
        }
        includeItemListMutableLiveData.postValue(includeList);


    }



}
