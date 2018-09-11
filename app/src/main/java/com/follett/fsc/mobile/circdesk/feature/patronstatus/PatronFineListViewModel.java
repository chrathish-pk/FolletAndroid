/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Fine;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

public class PatronFineListViewModel extends BaseViewModel<CTAButtonListener> {
    
    public final MutableLiveData<List<Fine>> fineLiveData = new MutableLiveData<>();
    
    public PatronFineListViewModel(Application application) {
        super(application);
    }


}
