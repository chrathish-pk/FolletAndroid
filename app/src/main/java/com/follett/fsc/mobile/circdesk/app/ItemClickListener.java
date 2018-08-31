/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.app;

import android.view.View;

public interface ItemClickListener {
    void OnItemClicked();
    void OnItemClick(View view, int position);
}
