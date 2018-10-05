package com.follett.fsc.mobile.circdesk.feature.inventory.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {
    
    @SerializedName("coverURL") @Expose private Object coverURL;
    @SerializedName("barcode") @Expose private String barcode;
    @SerializedName("materialType") @Expose private Integer materialType;
    @SerializedName("dueDate") @Expose private Object dueDate;
    @SerializedName("title") @Expose private String title;
    @SerializedName("copyID") @Expose private Integer copyID;
    @SerializedName("bibID") @Expose private Integer bibID;
    
    public Object getCoverURL() {
        return coverURL;
    }
    
    public void setCoverURL(Object coverURL) {
        this.coverURL = coverURL;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public Integer getMaterialType() {
        return materialType;
    }
    
    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }
    
    public Object getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getCopyID() {
        return copyID;
    }
    
    public void setCopyID(Integer copyID) {
        this.copyID = copyID;
    }
    
    public Integer getBibID() {
        return bibID;
    }
    
    public void setBibID(Integer bibID) {
        this.bibID = bibID;
    }
    
}
