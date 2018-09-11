
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckoutResult {

    @SerializedName("info")
    @Expose
    private CheckoutInfo info;
    @SerializedName("textbookCheckouts")
    @Expose
    private Integer textbookCheckouts;
    @SerializedName("libraryCheckouts")
    @Expose
    private Integer libraryCheckouts;
    @SerializedName("assetCheckouts")
    @Expose
    private Integer assetCheckouts;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("notes")
    @Expose
    private List<Object> notes = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public CheckoutInfo getInfo() {
        return info;
    }

    public void setInfo(CheckoutInfo info) {
        this.info = info;
    }

    public Integer getTextbookCheckouts() {
        return textbookCheckouts;
    }

    public void setTextbookCheckouts(Integer textbookCheckouts) {
        this.textbookCheckouts = textbookCheckouts;
    }

    public Integer getLibraryCheckouts() {
        return libraryCheckouts;
    }

    public void setLibraryCheckouts(Integer libraryCheckouts) {
        this.libraryCheckouts = libraryCheckouts;
    }

    public Integer getAssetCheckouts() {
        return assetCheckouts;
    }

    public void setAssetCheckouts(Integer assetCheckouts) {
        this.assetCheckouts = assetCheckouts;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Object> getNotes() {
        return notes;
    }

    public void setNotes(List<Object> notes) {
        this.notes = notes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
