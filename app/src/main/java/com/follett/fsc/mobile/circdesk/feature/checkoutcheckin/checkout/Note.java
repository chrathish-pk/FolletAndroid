/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("noteID")
    @Expose
    private String noteID;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("urgent")
    @Expose
    private String urgent;

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }
}
