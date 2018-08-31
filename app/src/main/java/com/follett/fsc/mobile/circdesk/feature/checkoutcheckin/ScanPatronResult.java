
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScanPatronResult implements Serializable {

    @SerializedName("assetCheckouts")
    @Expose
    private String assetCheckouts;
    @SerializedName("assetOverdues")
    @Expose
    private String assetOverdues;
    @SerializedName("libraryCheckouts")
    @Expose
    private String libraryCheckouts;
    @SerializedName("libraryOverdues")
    @Expose
    private String libraryOverdues;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("patronList")
    @Expose
    private PatronList patronList;
    @SerializedName("patronNotes")
    @Expose
    private String patronNotes;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("textbookCheckouts")
    @Expose
    private String textbookCheckouts;
    @SerializedName("textbookOverdues")
    @Expose
    private String textbookOverdues;

    public String getAssetCheckouts() {
        return assetCheckouts;
    }

    public void setAssetCheckouts(String assetCheckouts) {
        this.assetCheckouts = assetCheckouts;
    }

    public String getAssetOverdues() {
        return assetOverdues;
    }

    public void setAssetOverdues(String assetOverdues) {
        this.assetOverdues = assetOverdues;
    }

    public String getLibraryCheckouts() {
        return libraryCheckouts;
    }

    public void setLibraryCheckouts(String libraryCheckouts) {
        this.libraryCheckouts = libraryCheckouts;
    }

    public String getLibraryOverdues() {
        return libraryOverdues;
    }

    public void setLibraryOverdues(String libraryOverdues) {
        this.libraryOverdues = libraryOverdues;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public PatronList getPatronList() {
        return patronList;
    }

    public void setPatronList(PatronList patronList) {
        this.patronList = patronList;
    }

    public String getPatronNotes() {
        return patronNotes;
    }

    public void setPatronNotes(String patronNotes) {
        this.patronNotes = patronNotes;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTextbookCheckouts() {
        return textbookCheckouts;
    }

    public void setTextbookCheckouts(String textbookCheckouts) {
        this.textbookCheckouts = textbookCheckouts;
    }

    public String getTextbookOverdues() {
        return textbookOverdues;
    }

    public void setTextbookOverdues(String textbookOverdues) {
        this.textbookOverdues = textbookOverdues;
    }

}
