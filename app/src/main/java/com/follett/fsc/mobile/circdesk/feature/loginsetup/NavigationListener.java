/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

public interface NavigationListener<T> {
    public void onNavigation(int position);
    
    public void setToolBarTitle(String titleText);
}
