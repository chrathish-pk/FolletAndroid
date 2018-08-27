/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

public class SchoolItemViewModel {
    
    private final SchoolItemModelListener mListener;
    
    public SchoolItemViewModel(SchoolItemModelListener listener) {
        this.mListener = listener;
    }
    
    public void onRetryClick() {
        mListener.onRetryClick();
    }
    
    public interface SchoolItemModelListener {
        
        void onRetryClick();
    }
}
