
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

public class ScanPatron implements Parcelable{

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
    private List<Message> messages = null;
    @SerializedName("patronList")
    @Expose
    private List<Patron> patronList;
    @SerializedName("patronNotes")
    @Expose
    private List<Note> patronNotes;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("textbookCheckouts")
    @Expose
    private String textbookCheckouts;
    @SerializedName("textbookOverdues")
    @Expose
    private String textbookOverdues;

    @SerializedName("patronPictureFileName")
    @Expose
    private String patronPictureFileName;

    @SerializedName("libraryFines")
    @Expose
    private String libraryFines;

    @SerializedName("textbookFines")
    @Expose
    private String textbookFines;

    @SerializedName("assetFines")
    @Expose
    private String assetFines;

    @SerializedName("patronID")
    @Expose
    private String patronID;

    @SerializedName("lastFirstMiddleName")
    @Expose
    private String lastFirstMiddleName;

    @SerializedName("barcode")
    @Expose
    private String barcode;

    @SerializedName("patronType")
    @Expose
    private String patronType;

    private boolean isLibrarySelected;

    public ScanPatron(String assetCheckouts, String assetOverdues, String libraryCheckouts, String libraryOverdues, List<Message> messages, List<Patron> patronList, List<Note> patronNotes, String success, String textbookCheckouts, String textbookOverdues, String patronPictureFileName, String libraryFines, String textbookFines, String assetFines, String patronID, String lastFirstMiddleName, String barcode, String patronType, boolean isLibrarySelected) {
        this.assetCheckouts = assetCheckouts;
        this.assetOverdues = assetOverdues;
        this.libraryCheckouts = libraryCheckouts;
        this.libraryOverdues = libraryOverdues;
        this.messages = messages;
        this.patronList = patronList;
        this.patronNotes = patronNotes;
        this.success = success;
        this.textbookCheckouts = textbookCheckouts;
        this.textbookOverdues = textbookOverdues;
        this.patronPictureFileName = patronPictureFileName;
        this.libraryFines = libraryFines;
        this.textbookFines = textbookFines;
        this.assetFines = assetFines;
        this.patronID = patronID;
        this.lastFirstMiddleName = lastFirstMiddleName;
        this.barcode = barcode;
        this.patronType = patronType;
        this.isLibrarySelected = isLibrarySelected;
    }

    protected ScanPatron(Parcel in) {
        assetCheckouts = in.readString();
        assetOverdues = in.readString();
        libraryCheckouts = in.readString();
        libraryOverdues = in.readString();
        messages = in.createTypedArrayList(Message.CREATOR);
        patronList = in.createTypedArrayList(Patron.CREATOR);
        patronNotes = in.createTypedArrayList(Note.CREATOR);
        success = in.readString();
        textbookCheckouts = in.readString();
        textbookOverdues = in.readString();
        patronPictureFileName = in.readString();
        libraryFines = in.readString();
        textbookFines = in.readString();
        assetFines = in.readString();
        patronID = in.readString();
        lastFirstMiddleName = in.readString();
        barcode = in.readString();
        patronType = in.readString();
        isLibrarySelected = in.readByte() != 0;
    }


    public static final Creator<ScanPatron> CREATOR = new Creator<ScanPatron>() {
        @Override
        public ScanPatron createFromParcel(Parcel in) {
            return new ScanPatron(in);
        }

        @Override
        public ScanPatron[] newArray(int size) {
            return new ScanPatron[size];
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Patron> getPatronList() {
        return patronList;
    }

    public void setPatronList(List<Patron> patronList) {
        this.patronList = patronList;
    }

    public List<Note> getPatronNotes() {
        return patronNotes;
    }

    public void setPatronNotes(List<Note> patronNotes) {
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

    public String getPatronPictureFileName() {
        return patronPictureFileName;
    }

    public void setPatronPictureFileName(String patronPictureFileName) {
        this.patronPictureFileName = patronPictureFileName;
    }

    public String getLibraryFines() {
        return libraryFines;
    }

    public void setLibraryFines(String libraryFines) {
        this.libraryFines = libraryFines;
    }

    public String getTextbookFines() {
        return textbookFines;
    }

    public void setTextbookFines(String textbookFines) {
        this.textbookFines = textbookFines;
    }

    public String getAssetFines() {
        return assetFines;
    }

    public void setAssetFines(String assetFines) {
        this.assetFines = assetFines;
    }

    public String getPatronID() {
        return patronID;
    }

    public void setPatronID(String patronID) {
        this.patronID = patronID;
    }

    public String getLastFirstMiddleName() {
        return lastFirstMiddleName;
    }

    public void setLastFirstMiddleName(String lastFirstMiddleName) {
        this.lastFirstMiddleName = lastFirstMiddleName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPatronType() {
        return patronType;
    }

    public void setPatronType(String patronType) {
        this.patronType = patronType;
    }

    public boolean isLibrarySelected() {
        return isLibrarySelected;
    }

    public void setLibrarySelected(boolean librarySelected) {
        isLibrarySelected = librarySelected;
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
        dest.writeTypedList(messages);
        dest.writeTypedList(patronList);
        dest.writeString(success);
        dest.writeString(textbookCheckouts);
        dest.writeString(textbookOverdues);
        dest.writeString(patronPictureFileName);
        dest.writeString(libraryFines);
        dest.writeString(textbookFines);
        dest.writeString(assetFines);
        dest.writeString(patronID);
        dest.writeString(lastFirstMiddleName);
        dest.writeString(barcode);
        dest.writeString(patronType);
        dest.writeByte((byte) (isLibrarySelected ? 1 : 0));
    }
}
