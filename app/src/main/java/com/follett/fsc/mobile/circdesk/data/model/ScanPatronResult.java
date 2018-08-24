
package com.follett.fsc.mobile.circdesk.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScanPatronResult implements Parcelable {

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

    protected ScanPatronResult(Parcel in) {
        assetCheckouts = in.readString();
        assetOverdues = in.readString();
        libraryCheckouts = in.readString();
        libraryOverdues = in.readString();
        messages = in.readString();
        patronNotes = in.readString();
        success = in.readString();
        textbookCheckouts = in.readString();
        textbookOverdues = in.readString();
    }

    public static final Creator<ScanPatronResult> CREATOR = new Creator<ScanPatronResult>() {
        @Override
        public ScanPatronResult createFromParcel(Parcel in) {
            return new ScanPatronResult(in);
        }

        @Override
        public ScanPatronResult[] newArray(int size) {
            return new ScanPatronResult[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(assetCheckouts);
        dest.writeString(assetOverdues);
        dest.writeString(libraryCheckouts);
        dest.writeString(libraryOverdues);
        dest.writeString(messages);
        dest.writeString(patronNotes);
        dest.writeString(success);
        dest.writeString(textbookCheckouts);
        dest.writeString(textbookOverdues);
    }
}
