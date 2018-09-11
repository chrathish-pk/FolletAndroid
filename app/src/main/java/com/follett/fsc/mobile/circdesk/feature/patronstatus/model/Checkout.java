
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Checkout implements Parcelable {
    
    @SerializedName("id") @Expose private Integer id;
    @SerializedName("isbn") @Expose private String isbn;
    @SerializedName("copyBarcode") @Expose private String copyBarcode;
    @SerializedName("title") @Expose private String title;
    @SerializedName("lexile") @Expose private String lexile;
    @SerializedName("temporary") @Expose private Boolean temporary;
    @SerializedName("reviewCount") @Expose private Integer reviewCount;
    @SerializedName("dueDate") @Expose private String dueDate;
    @SerializedName("status") @Expose private Integer status;
    @SerializedName("siteName") @Expose private String siteName;
    @SerializedName("follettDesignation") @Expose private Object follettDesignation;
    @SerializedName("dcpiRecordGUID") @Expose private Object dcpiRecordGUID;
    @SerializedName("fountasAndPinnell") @Expose private String fountasAndPinnell;
    @SerializedName("electronicResourceDisplayable") @Expose private String electronicResourceDisplayable;
    @SerializedName("providerIconLink") @Expose private String providerIconLink;
    @SerializedName("contentImageLink") @Expose private String contentImageLink;
    @SerializedName("isInUsersBooklist") @Expose private Object isInUsersBooklist;
    @SerializedName("electronicResourceIsRelative") @Expose private Boolean electronicResourceIsRelative;
    @SerializedName("canViewTitleDetails") @Expose private Boolean canViewTitleDetails;
    @SerializedName("dcpiProviderName") @Expose private Object dcpiProviderName;
    @SerializedName("follettShelfEBook") @Expose private Boolean follettShelfEBook;
    @SerializedName("electronicResourceURL") @Expose private String electronicResourceURL;
    @SerializedName("titleDetailsLink") @Expose private String titleDetailsLink;
    @SerializedName("pubYear") @Expose private String pubYear;
    @SerializedName("lostLocal") @Expose private Integer lostLocal;
    @SerializedName("renewable") @Expose private Boolean renewable;
    @SerializedName("totalLocal") @Expose private Integer totalLocal;
    @SerializedName("totalOffsite") @Expose private Integer totalOffsite;
    @SerializedName("myRating") @Expose private Object myRating;
    @SerializedName("reviewAverage") @Expose private String reviewAverage;
    @SerializedName("resoldMaterialType") @Expose private Integer resoldMaterialType;
    @SerializedName("materialType") @Expose private Integer materialType;
    @SerializedName("author") @Expose private String author;
    @SerializedName("extent") @Expose private String extent;
    @SerializedName("siteShortName") @Expose private Object siteShortName;
    @SerializedName("callNumber") @Expose private String callNumber;
    @SerializedName("summary") @Expose private String summary;
    @SerializedName("availableOffsite") @Expose private Integer availableOffsite;
    @SerializedName("availableLocal") @Expose private Integer availableLocal;
    @SerializedName("myRatingApproved") @Expose private Boolean myRatingApproved;
    @SerializedName("resoldShelfID") @Expose private Object resoldShelfID;
    @SerializedName("shelfNumber") @Expose private Object shelfNumber;
    @SerializedName("ktsID") @Expose private Object ktsID;
    @SerializedName("digitalRecord") @Expose private Boolean digitalRecord;
    @SerializedName("reviewPending") @Expose private Boolean reviewPending;
    @SerializedName("overDue") @Expose private Boolean overDue;
    @SerializedName("copyid") @Expose private Integer copyid;
    
    protected Checkout(Parcel in) {
        if (in.readByte() == 0) { id = null; } else { id = in.readInt(); }
        isbn = in.readString();
        copyBarcode = in.readString();
        title = in.readString();
        lexile = in.readString();
        byte tmpTemporary = in.readByte();
        temporary = tmpTemporary == 0 ? null : tmpTemporary == 1;
        if (in.readByte() == 0) { reviewCount = null; } else { reviewCount = in.readInt(); }
        dueDate = in.readString();
        if (in.readByte() == 0) { status = null; } else { status = in.readInt(); }
        siteName = in.readString();
        fountasAndPinnell = in.readString();
        electronicResourceDisplayable = in.readString();
        providerIconLink = in.readString();
        contentImageLink = in.readString();
        byte tmpElectronicResourceIsRelative = in.readByte();
        electronicResourceIsRelative = tmpElectronicResourceIsRelative == 0 ? null : tmpElectronicResourceIsRelative == 1;
        byte tmpCanViewTitleDetails = in.readByte();
        canViewTitleDetails = tmpCanViewTitleDetails == 0 ? null : tmpCanViewTitleDetails == 1;
        byte tmpFollettShelfEBook = in.readByte();
        follettShelfEBook = tmpFollettShelfEBook == 0 ? null : tmpFollettShelfEBook == 1;
        electronicResourceURL = in.readString();
        titleDetailsLink = in.readString();
        pubYear = in.readString();
        if (in.readByte() == 0) { lostLocal = null; } else { lostLocal = in.readInt(); }
        byte tmpRenewable = in.readByte();
        renewable = tmpRenewable == 0 ? null : tmpRenewable == 1;
        if (in.readByte() == 0) { totalLocal = null; } else { totalLocal = in.readInt(); }
        if (in.readByte() == 0) { totalOffsite = null; } else { totalOffsite = in.readInt(); }
        reviewAverage = in.readString();
        if (in.readByte() == 0) { resoldMaterialType = null; } else { resoldMaterialType = in.readInt(); }
        if (in.readByte() == 0) { materialType = null; } else { materialType = in.readInt(); }
        author = in.readString();
        extent = in.readString();
        callNumber = in.readString();
        summary = in.readString();
        if (in.readByte() == 0) { availableOffsite = null; } else { availableOffsite = in.readInt(); }
        if (in.readByte() == 0) { availableLocal = null; } else { availableLocal = in.readInt(); }
        byte tmpMyRatingApproved = in.readByte();
        myRatingApproved = tmpMyRatingApproved == 0 ? null : tmpMyRatingApproved == 1;
        byte tmpDigitalRecord = in.readByte();
        digitalRecord = tmpDigitalRecord == 0 ? null : tmpDigitalRecord == 1;
        byte tmpReviewPending = in.readByte();
        reviewPending = tmpReviewPending == 0 ? null : tmpReviewPending == 1;
        byte tmpOverDue = in.readByte();
        overDue = tmpOverDue == 0 ? null : tmpOverDue == 1;
        if (in.readByte() == 0) { copyid = null; } else { copyid = in.readInt(); }
    }
    
    public static final Creator<Checkout> CREATOR = new Creator<Checkout>() {
        @Override
        public Checkout createFromParcel(Parcel in) {
            return new Checkout(in);
        }
        
        @Override
        public Checkout[] newArray(int size) {
            return new Checkout[size];
        }
    };
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getCopyBarcode() {
        return copyBarcode;
    }
    
    public void setCopyBarcode(String copyBarcode) {
        this.copyBarcode = copyBarcode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getLexile() {
        return lexile;
    }
    
    public void setLexile(String lexile) {
        this.lexile = lexile;
    }
    
    public Boolean getTemporary() {
        return temporary;
    }
    
    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }
    
    public Integer getReviewCount() {
        return reviewCount;
    }
    
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
    
    public String getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getSiteName() {
        return siteName;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public Object getFollettDesignation() {
        return follettDesignation;
    }
    
    public void setFollettDesignation(Object follettDesignation) {
        this.follettDesignation = follettDesignation;
    }
    
    public Object getDcpiRecordGUID() {
        return dcpiRecordGUID;
    }
    
    public void setDcpiRecordGUID(Object dcpiRecordGUID) {
        this.dcpiRecordGUID = dcpiRecordGUID;
    }
    
    public String getFountasAndPinnell() {
        return fountasAndPinnell;
    }
    
    public void setFountasAndPinnell(String fountasAndPinnell) {
        this.fountasAndPinnell = fountasAndPinnell;
    }
    
    public String getElectronicResourceDisplayable() {
        return electronicResourceDisplayable;
    }
    
    public void setElectronicResourceDisplayable(String electronicResourceDisplayable) {
        this.electronicResourceDisplayable = electronicResourceDisplayable;
    }
    
    public String getProviderIconLink() {
        return providerIconLink;
    }
    
    public void setProviderIconLink(String providerIconLink) {
        this.providerIconLink = providerIconLink;
    }
    
    public String getContentImageLink() {
        return contentImageLink;
    }
    
    public void setContentImageLink(String contentImageLink) {
        this.contentImageLink = contentImageLink;
    }
    
    public Object getIsInUsersBooklist() {
        return isInUsersBooklist;
    }
    
    public void setIsInUsersBooklist(Object isInUsersBooklist) {
        this.isInUsersBooklist = isInUsersBooklist;
    }
    
    public Boolean getElectronicResourceIsRelative() {
        return electronicResourceIsRelative;
    }
    
    public void setElectronicResourceIsRelative(Boolean electronicResourceIsRelative) {
        this.electronicResourceIsRelative = electronicResourceIsRelative;
    }
    
    public Boolean getCanViewTitleDetails() {
        return canViewTitleDetails;
    }
    
    public void setCanViewTitleDetails(Boolean canViewTitleDetails) {
        this.canViewTitleDetails = canViewTitleDetails;
    }
    
    public Object getDcpiProviderName() {
        return dcpiProviderName;
    }
    
    public void setDcpiProviderName(Object dcpiProviderName) {
        this.dcpiProviderName = dcpiProviderName;
    }
    
    public Boolean getFollettShelfEBook() {
        return follettShelfEBook;
    }
    
    public void setFollettShelfEBook(Boolean follettShelfEBook) {
        this.follettShelfEBook = follettShelfEBook;
    }
    
    public String getElectronicResourceURL() {
        return electronicResourceURL;
    }
    
    public void setElectronicResourceURL(String electronicResourceURL) {
        this.electronicResourceURL = electronicResourceURL;
    }
    
    public String getTitleDetailsLink() {
        return titleDetailsLink;
    }
    
    public void setTitleDetailsLink(String titleDetailsLink) {
        this.titleDetailsLink = titleDetailsLink;
    }
    
    public String getPubYear() {
        return pubYear;
    }
    
    public void setPubYear(String pubYear) {
        this.pubYear = pubYear;
    }
    
    public Integer getLostLocal() {
        return lostLocal;
    }
    
    public void setLostLocal(Integer lostLocal) {
        this.lostLocal = lostLocal;
    }
    
    public Boolean getRenewable() {
        return renewable;
    }
    
    public void setRenewable(Boolean renewable) {
        this.renewable = renewable;
    }
    
    public Integer getTotalLocal() {
        return totalLocal;
    }
    
    public void setTotalLocal(Integer totalLocal) {
        this.totalLocal = totalLocal;
    }
    
    public Integer getTotalOffsite() {
        return totalOffsite;
    }
    
    public void setTotalOffsite(Integer totalOffsite) {
        this.totalOffsite = totalOffsite;
    }
    
    public Object getMyRating() {
        return myRating;
    }
    
    public void setMyRating(Object myRating) {
        this.myRating = myRating;
    }
    
    public String getReviewAverage() {
        return reviewAverage;
    }
    
    public void setReviewAverage(String reviewAverage) {
        this.reviewAverage = reviewAverage;
    }
    
    public Integer getResoldMaterialType() {
        return resoldMaterialType;
    }
    
    public void setResoldMaterialType(Integer resoldMaterialType) {
        this.resoldMaterialType = resoldMaterialType;
    }
    
    public Integer getMaterialType() {
        return materialType;
    }
    
    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getExtent() {
        return extent;
    }
    
    public void setExtent(String extent) {
        this.extent = extent;
    }
    
    public Object getSiteShortName() {
        return siteShortName;
    }
    
    public void setSiteShortName(Object siteShortName) {
        this.siteShortName = siteShortName;
    }
    
    public String getCallNumber() {
        return callNumber;
    }
    
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public Integer getAvailableOffsite() {
        return availableOffsite;
    }
    
    public void setAvailableOffsite(Integer availableOffsite) {
        this.availableOffsite = availableOffsite;
    }
    
    public Integer getAvailableLocal() {
        return availableLocal;
    }
    
    public void setAvailableLocal(Integer availableLocal) {
        this.availableLocal = availableLocal;
    }
    
    public Boolean getMyRatingApproved() {
        return myRatingApproved;
    }
    
    public void setMyRatingApproved(Boolean myRatingApproved) {
        this.myRatingApproved = myRatingApproved;
    }
    
    public Object getResoldShelfID() {
        return resoldShelfID;
    }
    
    public void setResoldShelfID(Object resoldShelfID) {
        this.resoldShelfID = resoldShelfID;
    }
    
    public Object getShelfNumber() {
        return shelfNumber;
    }
    
    public void setShelfNumber(Object shelfNumber) {
        this.shelfNumber = shelfNumber;
    }
    
    public Object getKtsID() {
        return ktsID;
    }
    
    public void setKtsID(Object ktsID) {
        this.ktsID = ktsID;
    }
    
    public Boolean getDigitalRecord() {
        return digitalRecord;
    }
    
    public void setDigitalRecord(Boolean digitalRecord) {
        this.digitalRecord = digitalRecord;
    }
    
    public Boolean getReviewPending() {
        return reviewPending;
    }
    
    public void setReviewPending(Boolean reviewPending) {
        this.reviewPending = reviewPending;
    }
    
    public Boolean getOverDue() {
        return overDue;
    }
    
    public void setOverDue(Boolean overDue) {
        this.overDue = overDue;
    }
    
    public Integer getCopyid() {
        return copyid;
    }
    
    public void setCopyid(Integer copyid) {
        this.copyid = copyid;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    
        if (id == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(isbn);
        parcel.writeString(copyBarcode);
        parcel.writeString(title);
        parcel.writeString(lexile);
        if (temporary) parcel.writeByte((byte) (temporary == null ? 0 : 1));
        else parcel.writeByte((byte) (temporary == null ? 0 : 2));
        if (reviewCount == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(reviewCount);
        }
        parcel.writeString(dueDate);
        if (status == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        parcel.writeString(siteName);
        parcel.writeString(fountasAndPinnell);
        parcel.writeString(electronicResourceDisplayable);
        parcel.writeString(providerIconLink);
        parcel.writeString(contentImageLink);
        if (electronicResourceIsRelative)
            parcel.writeByte((byte) (electronicResourceIsRelative == null ? 0 : 1));
        else parcel.writeByte((byte) (electronicResourceIsRelative == null ? 0 : 2));
        if (canViewTitleDetails) parcel.writeByte((byte) (canViewTitleDetails == null ? 0 : 1));
        else parcel.writeByte((byte) (canViewTitleDetails == null ? 0 : 2));
        if (follettShelfEBook) parcel.writeByte((byte) (follettShelfEBook == null ? 0 : 1));
        else parcel.writeByte((byte) (follettShelfEBook == null ? 0 : 2));
        parcel.writeString(electronicResourceURL);
        parcel.writeString(titleDetailsLink);
        parcel.writeString(pubYear);
        if (lostLocal == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(lostLocal);
        }
        if (renewable) parcel.writeByte((byte) (renewable == null ? 0 : 1));
        else parcel.writeByte((byte) (renewable == null ? 0 : 2));
        if (totalLocal == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalLocal);
        }
        if (totalOffsite == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalOffsite);
        }
        parcel.writeString(reviewAverage);
        if (resoldMaterialType == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(resoldMaterialType);
        }
        if (materialType == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(materialType);
        }
        parcel.writeString(author);
        parcel.writeString(extent);
        parcel.writeString(callNumber);
        parcel.writeString(summary);
        if (availableOffsite == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(availableOffsite);
        }
        if (availableLocal == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(availableLocal);
        }
        if (myRatingApproved) parcel.writeByte((byte) (myRatingApproved == null ? 0 : 1));
        else parcel.writeByte((byte) (myRatingApproved == null ? 0 : 2));
        if (digitalRecord) parcel.writeByte((byte) (digitalRecord == null ? 0 : 1));
        else parcel.writeByte((byte) (digitalRecord == null ? 0 : 2));
        if (reviewPending) parcel.writeByte((byte) (reviewPending == null ? 0 : 1));
        else parcel.writeByte((byte) (reviewPending == null ? 0 : 2));
        if (overDue) parcel.writeByte((byte) (overDue == null ? 0 : 1));
        else parcel.writeByte((byte) (overDue == null ? 0 : 2));
        if (copyid == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(copyid);
        }
    }
}
