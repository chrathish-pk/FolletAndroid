/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.model.Version;

import io.reactivex.Observable;

public interface AppRepository {
    
    /*
     * Get the latest version from Destiny
     */
    Observable<Version> getVersion();
}
