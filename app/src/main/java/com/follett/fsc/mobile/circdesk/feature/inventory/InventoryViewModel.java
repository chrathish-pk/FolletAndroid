/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.AssetCheckOut;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Checkout;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Hold;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;

import java.util.ArrayList;
import java.util.List;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_INFO_KEY;
import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_TITLE_KEY;

public class InventoryViewModel extends BaseViewModel<CTAButtonListener> {

    public InventoryViewModel(Application application) {
        super(application);
    }
    
}
