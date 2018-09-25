
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CheckinResult implements Parcelable {

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

    public CheckinResult(Info info, List<Message> messages, List<Object> notes, Boolean success) {
        this.info = info;
        this.messages = messages;
        this.notes = notes;
        this.success = success;
    }

    protected CheckinResult(Parcel in) {
        messages = in.createTypedArrayList(Message.CREATOR);
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
    }

    public static final Creator<CheckinResult> CREATOR = new Creator<CheckinResult>() {
        @Override
        public CheckinResult createFromParcel(Parcel in) {
            return new CheckinResult(in);
        }

        @Override
        public CheckinResult[] newArray(int size) {
            return new CheckinResult[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(messages);
        if (success) dest.writeByte((byte) (success == null ? 0 : 1));
        else dest.writeByte((byte) (success == null ? 0 : 2));
    }
}
