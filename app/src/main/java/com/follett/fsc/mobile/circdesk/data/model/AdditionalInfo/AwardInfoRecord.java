
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model.AdditionalInfo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwardInfoRecord implements Serializable {

    @SerializedName("awardList")
    @Expose
    private List<Object> awardList = null;

    public List<Object> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Object> awardList) {
        this.awardList = awardList;
    }

}
