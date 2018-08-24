
package com.follett.fsc.mobile.circdesk.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PatronList implements Serializable{

    @SerializedName("patron")
    @Expose
    private List<Patron> patron = null;

    public List<Patron> getPatron() {
        return patron;
    }

    public void setPatron(List<Patron> patron) {
        this.patron = patron;
    }
}
