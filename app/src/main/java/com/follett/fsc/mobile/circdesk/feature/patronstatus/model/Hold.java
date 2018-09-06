
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hold {
    
    @SerializedName("siteName") @Expose private String siteName;
    @SerializedName("state") @Expose private Integer state;
    @SerializedName("title") @Expose private String title;
    @SerializedName("quantity") @Expose private Integer quantity;
    @SerializedName("datePlaced") @Expose private String datePlaced;
    @SerializedName("holdDetail") @Expose private String holdDetail;
    @SerializedName("canPlaceHold") @Expose private Object canPlaceHold;
    @SerializedName("bibID") @Expose private Integer bibID;
    @SerializedName("siteShortName") @Expose private String siteShortName;
    @SerializedName("holdID") @Expose private Integer holdID;
    @SerializedName("totalHoldableCopies") @Expose private Object totalHoldableCopies;
    @SerializedName("dateExpires") @Expose private String dateExpires;
    
    public String getSiteName() {
        return siteName;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getDatePlaced() {
        return datePlaced;
    }
    
    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }
    
    public String getHoldDetail() {
        return holdDetail;
    }
    
    public void setHoldDetail(String holdDetail) {
        this.holdDetail = holdDetail;
    }
    
    public Object getCanPlaceHold() {
        return canPlaceHold;
    }
    
    public void setCanPlaceHold(Object canPlaceHold) {
        this.canPlaceHold = canPlaceHold;
    }
    
    public Integer getBibID() {
        return bibID;
    }
    
    public void setBibID(Integer bibID) {
        this.bibID = bibID;
    }
    
    public String getSiteShortName() {
        return siteShortName;
    }
    
    public void setSiteShortName(String siteShortName) {
        this.siteShortName = siteShortName;
    }
    
    public Integer getHoldID() {
        return holdID;
    }
    
    public void setHoldID(Integer holdID) {
        this.holdID = holdID;
    }
    
    public Object getTotalHoldableCopies() {
        return totalHoldableCopies;
    }
    
    public void setTotalHoldableCopies(Object totalHoldableCopies) {
        this.totalHoldableCopies = totalHoldableCopies;
    }
    
    public String getDateExpires() {
        return dateExpires;
    }
    
    public void setDateExpires(String dateExpires) {
        this.dateExpires = dateExpires;
    }
    
}
