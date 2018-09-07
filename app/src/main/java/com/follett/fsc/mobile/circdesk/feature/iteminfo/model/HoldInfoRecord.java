
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoldInfoRecord implements Parcelable
{

    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("quantity")
    @Expose
    private Object quantity;
    @SerializedName("datePlaced")
    @Expose
    private Object datePlaced;
    @SerializedName("holdDetail")
    @Expose
    private Object holdDetail;
    @SerializedName("canPlaceHold")
    @Expose
    private Boolean canPlaceHold;
    @SerializedName("bibID")
    @Expose
    private Object bibID;
    @SerializedName("siteShortName")
    @Expose
    private Object siteShortName;
    @SerializedName("holdID")
    @Expose
    private Object holdID;
    @SerializedName("totalHoldableCopies")
    @Expose
    private Integer totalHoldableCopies;
    @SerializedName("dateExpires")
    @Expose
    private Object dateExpires;
    private final static long serialVersionUID = 7869101393155848547L;

    protected HoldInfoRecord(Parcel in) {
        byte tmpCanPlaceHold = in.readByte();
        canPlaceHold = tmpCanPlaceHold == 0 ? null : tmpCanPlaceHold == 1;
        if (in.readByte() == 0) {
            totalHoldableCopies = null;
        } else {
            totalHoldableCopies = in.readInt();
        }
    }

    public static final Creator<HoldInfoRecord> CREATOR = new Creator<HoldInfoRecord>() {
        @Override
        public HoldInfoRecord createFromParcel(Parcel in) {
            return new HoldInfoRecord(in);
        }

        @Override
        public HoldInfoRecord[] newArray(int size) {
            return new HoldInfoRecord[size];
        }
    };

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public Object getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Object datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Object getHoldDetail() {
        return holdDetail;
    }

    public void setHoldDetail(Object holdDetail) {
        this.holdDetail = holdDetail;
    }

    public Boolean getCanPlaceHold() {
        return canPlaceHold;
    }

    public void setCanPlaceHold(Boolean canPlaceHold) {
        this.canPlaceHold = canPlaceHold;
    }

    public Object getBibID() {
        return bibID;
    }

    public void setBibID(Object bibID) {
        this.bibID = bibID;
    }

    public Object getSiteShortName() {
        return siteShortName;
    }

    public void setSiteShortName(Object siteShortName) {
        this.siteShortName = siteShortName;
    }

    public Object getHoldID() {
        return holdID;
    }

    public void setHoldID(Object holdID) {
        this.holdID = holdID;
    }

    public Integer getTotalHoldableCopies() {
        return totalHoldableCopies;
    }

    public void setTotalHoldableCopies(Integer totalHoldableCopies) {
        this.totalHoldableCopies = totalHoldableCopies;
    }

    public Object getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(Object dateExpires) {
        this.dateExpires = dateExpires;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (canPlaceHold == null ? 0 : canPlaceHold ? 1 : 2));
        if (totalHoldableCopies == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalHoldableCopies);
        }
    }
}
