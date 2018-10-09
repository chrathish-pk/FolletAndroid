package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.MismatchedItemLocation;

import java.util.ArrayList;


public class MismatchedItemLocationsViewModel extends BaseViewModel {

    public MutableLiveData<ArrayList<MismatchedItemLocation>> mismatchedItemLocListMutableLiveData = new MutableLiveData<>();
    private Application mApplication;

    public MismatchedItemLocationsViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;
    }

    public void setMismatchedItemLocationsData() {

        String[] array = {mApplication.getString(R.string.doNothing),mApplication.getString(R.string.alertButDoNothing), mApplication.getString(R.string.promptForChange),mApplication.getString(R.string.automaticallyChangeHomeLocation)};
        Integer[] statusValue = {0,1,2,3};
        ArrayList<MismatchedItemLocation> mismatchedItemLocations = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            mismatchedItemLocations.add(new MismatchedItemLocation(array[i],statusValue[i]));
        }
        mismatchedItemLocListMutableLiveData.postValue(mismatchedItemLocations);

    }
}
