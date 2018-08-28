/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.interfaces.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;

public class AdvancedViewModel extends BaseViewModel<CTAButtonListener> {
    
    
    public AdvancedViewModel(Application application) {
        super(application);
    }
    
    public void connectToServerOnClick () {
        getNavigator().ctaButtonOnClick();
    }
}
