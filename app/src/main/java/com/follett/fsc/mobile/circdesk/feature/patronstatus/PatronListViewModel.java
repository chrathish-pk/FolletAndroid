/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.AssetCheckOut;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Checkout;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Hold;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_INFO_KEY;
import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_TITLE_KEY;

public class PatronListViewModel extends BaseViewModel<CTAButtonListener> {
    
    public final MutableLiveData<List<CustomCheckoutItem>> checkoutLiveData = new MutableLiveData<>();
    
    public PatronListViewModel(Application application) {
        super(application);
    }
    
    public void createCheckoutModelData(final Bundle arguments) {
        if (arguments != null) {
            setIsLoding(true);
            PatronInfo patronInfo = arguments.getParcelable(PATRON_INFO_KEY);
            String title = arguments.getString(PATRON_TITLE_KEY);
            
            
            List<CustomCheckoutItem> checkoutItemList = new ArrayList<>();
            
            if (title.equalsIgnoreCase(getApplication().getString(R.string.item_checkout_label))) {
                final List<Checkout> checkouts = patronInfo.getCheckouts();
                final List<AssetCheckOut> assetsCheckouts = patronInfo.getAssetCheckOuts();
                
                
                for (int i = 0; i < checkouts.size(); i++) {
                    if (i == 0) {
                        checkoutItemList.add(new CustomCheckoutItem(getApplication().getString(R.string.library_title_label), checkouts.get(i)
                                .getTitle(), checkouts.get(i)
                                .getCopyBarcode(), checkouts.get(i)
                                .getDueDate(), true, checkouts.get(i)
                                .getOverDue()));
                    } else {
                        checkoutItemList.add(new CustomCheckoutItem(null, checkouts.get(i)
                                .getTitle(), checkouts.get(i)
                                .getCopyBarcode(), checkouts.get(i)
                                .getDueDate(), true, checkouts.get(i)
                                .getOverDue()));
                    }
                }
                
                for (int i = 0; i < assetsCheckouts.size(); i++) {
                    if (i == 0) {
                        checkoutItemList.add(new CustomCheckoutItem(getApplication().getString(R.string.resource_item_label), assetsCheckouts.get(i)
                                .getTitle(), assetsCheckouts.get(i)
                                .getCopyBarcode(), assetsCheckouts.get(i)
                                .getDueDate(), false, assetsCheckouts.get(i)
                                .getOverDue()));
                    } else {
                        checkoutItemList.add(new CustomCheckoutItem(null, assetsCheckouts.get(i)
                                .getTitle(), assetsCheckouts.get(i)
                                .getCopyBarcode(), assetsCheckouts.get(i)
                                .getDueDate(), false, assetsCheckouts.get(i)
                                .getOverDue()));
                    }
                }
            } else if (title.equalsIgnoreCase(getApplication().getString(R.string.on_hold_label))) {
                final List<Hold> holdList = patronInfo.getHolds();
                
                for (Hold hold : holdList) {
                    checkoutItemList.add(new CustomCheckoutItem(null, hold.getTitle(), String.valueOf(hold.getBibID()), hold.getDateExpires(), true, false));
                }
            }
            setIsLoding(false);
            checkoutLiveData.postValue(checkoutItemList);
        }
    }
}
