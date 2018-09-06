
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwardInfoRecord implements Serializable
{

    @SerializedName("awardList")
    @Expose
    private List<Object> awardList = null;
    private final static long serialVersionUID = -8656971996702398491L;

    public List<Object> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Object> awardList) {
        this.awardList = awardList;
    }

}
