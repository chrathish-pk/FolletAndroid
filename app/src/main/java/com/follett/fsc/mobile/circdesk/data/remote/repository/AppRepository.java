/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.model.Version;
import com.follett.fsc.mobile.circdesk.data.model.checkout.CheckoutResult;

import io.reactivex.Observable;

public interface AppRepository {


    /*
     * Get the latest version from Destiny
     */
    Observable<Version> getVersion();

    Observable<SiteResults> getSchoolList();

    Observable<LoginResults> getLoginResults(String userName, String password);

    /**
     * Get scan patron result
     */
    Observable<ScanPatron> getScanPatron(String patronBarcodeID);

    /**
     * Get Checkout result
     *
     * @param patronID selected patron id
     * @param barcode  entered barcode
     * @return Checkout result model data
     */
    Observable<CheckoutResult> getCheckoutResult(String patronID, String barcode, String collectionType);
}
