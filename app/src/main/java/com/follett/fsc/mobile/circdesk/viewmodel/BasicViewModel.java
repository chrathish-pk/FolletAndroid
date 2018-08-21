/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.interfaces.BasicNavigator;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

public class BasicViewModel extends BaseViewModel<BasicNavigator> {
    
    public BasicViewModel() {
        super();
    }
    
    public void connectToServerOnClick () {
        getNavigator().connectOnClick();
    }
}
