/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.interfaces;

import com.follett.fsc.mobile.circdesk.data.model.AdditionalInfo.TitleDetails;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;

public interface AdditionalInfoListener {

    void updateTitleDetails(TitleDetails titleDetails);
}
