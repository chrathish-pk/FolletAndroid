/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;

public interface NetworkInterface<T> {
    
    void onCallCompleted(T model);
    
    void onCallFailed(Throwable throwable);
}
