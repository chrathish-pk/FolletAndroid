/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.app.Application;

import static com.follett.fsc.mobile.circdesk.feature.utils.JunitHelper.inItSetUp;

public class LoginViewModelTest {
    
    private LoginViewModel mViewModel;
    
   @Mock Application mApplication;

    
    @Before
    public void setUp() {
        inItSetUp();
        MockitoAnnotations.initMocks(this);
        mViewModel = new LoginViewModel(mApplication);
    }
    
    @Test
    public void onCallCompletedWithSuccess() {
        mViewModel.onCallCompleted(generateLoginResult());
    }
    
    
    
    private LoginResults generateLoginResult () {
        return new LoginResults("", "", "", "",
                "", "", "", "",
                "", "", "", "",
                null, "", "", "true");
    }

}
