
package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import java.io.Serializable;
import java.util.List;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.Note;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDetails implements Serializable
{

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currentCheckout")
    @Expose
    private CurrentCheckout currentCheckout;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("bibID")
    @Expose
    private Integer bibID;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("callNumber")
    @Expose
    private Object callNumber;
    @SerializedName("custodian")
    @Expose
    private Object custodian;
    @SerializedName("follettEBookURL")
    @Expose
    private Object follettEBookURL;
    @SerializedName("circType")
    @Expose
    private Object circType;



    @SerializedName("notes")
    @Expose
    private List<Note> itemNotes;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("coverImage")
    @Expose
    private Object coverImage;
    private final static long serialVersionUID = -2194385777627677709L;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CurrentCheckout getCurrentCheckout() {
        return currentCheckout;
    }

    public void setCurrentCheckout(CurrentCheckout currentCheckout) {
        this.currentCheckout = currentCheckout;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getBibID() {
        return bibID;
    }

    public void setBibID(Integer bibID) {
        this.bibID = bibID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(Object callNumber) {
        this.callNumber = callNumber;
    }

    public Object getCustodian() {
        return custodian;
    }

    public void setCustodian(Object custodian) {
        this.custodian = custodian;
    }

    public Object getFollettEBookURL() {
        return follettEBookURL;
    }

    public void setFollettEBookURL(Object follettEBookURL) {
        this.follettEBookURL = follettEBookURL;
    }

    public Object getCircType() {
        return circType;
    }

    public void setCircType(Object circType) {
        this.circType = circType;
    }

    public List<Note> getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(List<Note> itemNotes) {
        this.itemNotes = itemNotes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Object getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Object coverImage) {
        this.coverImage = coverImage;
    }

}
