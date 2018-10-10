package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.PurchasePriceItem;

import java.util.ArrayList;
import java.util.List;

public class PurchasePriceViewModel extends BaseViewModel {
    public MutableLiveData<List<PurchasePriceItem>> priceItemListMutableLiveData = new MutableLiveData<>();
    private Application mApplication;

    public PurchasePriceViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void setPriceItemData() {

        String[] array = {mApplication.getString(R.string.priceGreater),mApplication.getString(R.string.priceLess)};
        ArrayList<PurchasePriceItem> priceList = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            priceList.add(new PurchasePriceItem(array[i]));
        }
        priceItemListMutableLiveData.postValue(priceList);

    }
}
