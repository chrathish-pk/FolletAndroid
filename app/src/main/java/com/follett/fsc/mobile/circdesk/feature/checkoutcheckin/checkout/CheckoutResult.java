
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.os.Parcel;
import android.os.Parcelable;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckoutResult implements Parcelable {

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

    protected CheckoutResult(Parcel in) {
        info = in.readParcelable(CheckoutInfo.class.getClassLoader());
        if (in.readByte() == 0) {
            textbookCheckouts = null;
        } else {
            textbookCheckouts = in.readInt();
        }
        if (in.readByte() == 0) {
            libraryCheckouts = null;
        } else {
            libraryCheckouts = in.readInt();
        }
        if (in.readByte() == 0) {
            assetCheckouts = null;
        } else {
            assetCheckouts = in.readInt();
        }
        messages = in.createTypedArrayList(Message.CREATOR);
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
    }

    public static final Creator<CheckoutResult> CREATOR = new Creator<CheckoutResult>() {
        @Override
        public CheckoutResult createFromParcel(Parcel in) {
            return new CheckoutResult(in);
        }

        @Override
        public CheckoutResult[] newArray(int size) {
            return new CheckoutResult[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(info, flags);
        if (textbookCheckouts == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(textbookCheckouts);
        }
        if (libraryCheckouts == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(libraryCheckouts);
        }
        if (assetCheckouts == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(assetCheckouts);
        }
        dest.writeTypedList(messages);
        if (success) dest.writeByte((byte) (success == null ? 0 : 1));
        else dest.writeByte((byte) (success == null ? 0 : 2));
    }
}
