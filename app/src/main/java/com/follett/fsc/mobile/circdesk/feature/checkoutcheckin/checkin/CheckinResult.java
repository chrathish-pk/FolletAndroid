
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckinResult {

    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("notes")
    @Expose
    private List<Object> notes = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
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
