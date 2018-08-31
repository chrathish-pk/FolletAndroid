
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HoldInfoRecord implements Serializable {

    @SerializedName("canPlaceHold")
    @Expose
    private String canPlaceHold;
    @SerializedName("totalHoldableCopies")
    @Expose
    private String totalHoldableCopies;

    public String getCanPlaceHold() {
        return canPlaceHold;
    }

    public void setCanPlaceHold(String canPlaceHold) {
        this.canPlaceHold = canPlaceHold;
    }

    public String getTotalHoldableCopies() {
        return totalHoldableCopies;
    }

    public void setTotalHoldableCopies(String totalHoldableCopies) {
        this.totalHoldableCopies = totalHoldableCopies;
    }

}
