package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CheckoutHandling;

import java.util.ArrayList;
import java.util.List;

public class InventoryCheckoutHandlingViewModel extends BaseViewModel {

    public MutableLiveData<List<CheckoutHandling>> checkoutHnadlingListMutableLiveData = new MutableLiveData<>();
    private Application mApplication;

    public InventoryCheckoutHandlingViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;

    }

    public void setCheckoutHandlingData() {

        String[] checkoutHandlingArray = {mApplication.getString(R.string.makeTheseItemsUnaccountedFor), mApplication.getString(R.string.checkInItemsInCirculation)};
        ArrayList<CheckoutHandling> checkoutHandlingList = new ArrayList();
        for (int i = 0; i < checkoutHandlingArray.length; i++) {
            checkoutHandlingList.add(new CheckoutHandling(checkoutHandlingArray[i], i));
        }
        checkoutHnadlingListMutableLiveData.postValue(checkoutHandlingList);
    }
}
