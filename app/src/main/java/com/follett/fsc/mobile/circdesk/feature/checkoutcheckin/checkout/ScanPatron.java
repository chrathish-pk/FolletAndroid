
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ScanPatron implements Serializable {

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
}
