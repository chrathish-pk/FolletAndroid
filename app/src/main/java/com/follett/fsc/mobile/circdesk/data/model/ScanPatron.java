package com.follett.fsc.mobile.circdesk.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "scanPatronResult")
public class ScanPatron {

    @Element(name = "assetCheckouts")
    private String assetCheckouts;

    @Element(name = "assetOverdues")
    private String assetOverdues;

    @Element(name = "libraryCheckouts")
    private String libraryCheckouts;

    @Element(name = "libraryOverdues")
    private String libraryOverdues;

    @Element(name = "messages")
    private String messages;

    @ElementList(name = "patronList")
    private List<Patron> patronList;

    @Element(name = "patronNotes")
    private String patronNotes;

    @Element(name = "success")
    private String success;

    @Element(name = "textbookCheckouts")
    private String textbookCheckouts;

    @Element(name = "textbookOverdues")
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

    public List<Patron> getPatronList() {
        return patronList;
    }

    public void setPatronList(List<Patron> patronList) {
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
