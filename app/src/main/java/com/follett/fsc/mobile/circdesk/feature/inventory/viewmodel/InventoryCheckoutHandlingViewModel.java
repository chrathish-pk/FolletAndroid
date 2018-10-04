package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CheckoutHandling;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;

import java.util.ArrayList;

public class InventoryCheckoutHandlingViewModel extends BaseViewModel {

    public MutableLiveData<ArrayList<CheckoutHandling>> checkoutHnadlingListMutableLiveData = new MutableLiveData<>();
    private Application mApplication;

    public InventoryCheckoutHandlingViewModel(@NonNull Application application) {
        super(application);

    }

    public void setCheckoutHandlingData() {

        String[] checkoutHandlingArray = {"Make these items unaccounted for", "Check In Items In-Circulation"};
        ArrayList<CheckoutHandling> checkoutHandlingList = new ArrayList();
        for (int i = 0; i < checkoutHandlingArray.length; i++) {
            checkoutHandlingList.add(new CheckoutHandling(checkoutHandlingArray[i]));
        }
        checkoutHnadlingListMutableLiveData.postValue(checkoutHandlingList);
    }
}