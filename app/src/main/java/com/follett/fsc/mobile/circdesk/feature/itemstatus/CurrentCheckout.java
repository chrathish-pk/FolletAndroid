
package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentCheckout implements Serializable
{
    @SerializedName("dateDue")
    @Expose
    private String dateDue;
    @SerializedName("dateOut")
    @Expose
    private String dateOut;
    @SerializedName("dateReturned")
    @Expose
    private String dateReturned;
    @SerializedName("checkedOutToName")
    @Expose
    private String checkedOutToName;
    @SerializedName("checkedOutToBarcode")
    @Expose
    private String checkedOutToBarcode;
    private final static long serialVersionUID = -1287248026689003198L;

    public boolean isEmpty() {
        return checkedOutToName == null && checkedOutToBarcode == null;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getCheckedOutToName() {
        return checkedOutToName;
    }

    public void setCheckedOutToName(String checkedOutToName) {
        this.checkedOutToName = checkedOutToName;
    }

    public String getCheckedOutToBarcode() {
        return checkedOutToBarcode;
    }

    public void setCheckedOutToBarcode(String checkedOutToBarcode) {
        this.checkedOutToBarcode = checkedOutToBarcode;
    }

}