/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.interfaces.LoginNavigator;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    
    public LoginViewModel(Application application) {
        super(application);
    }
}
