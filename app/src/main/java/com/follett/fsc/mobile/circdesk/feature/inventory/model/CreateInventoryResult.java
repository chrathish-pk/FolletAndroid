package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateInventoryResult {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("partialID")
    @Expose
    private Integer partialID;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getPartialID() {
        return partialID;
    }

    public void setPartialID(Integer partialID) {
        this.partialID = partialID;
    }
}
