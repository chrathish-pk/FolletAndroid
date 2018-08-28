/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.interfaces;

import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;

/**
 * Created by muthulakshmi on 28/08/18.
 */

public interface ChangeUIListener {

    void updatePatronUI(ScanPatron scanPatron);

    void updateCheckoutUI();

    void updateErrorPatronUI(ScanPatron scanPatron);
}
