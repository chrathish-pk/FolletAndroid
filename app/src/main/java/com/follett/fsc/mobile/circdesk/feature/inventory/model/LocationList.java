
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationList implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("locationID")
    @Expose
    private Integer locationID;

    private boolean isSelected;

    public LocationList(String name, Object message, Boolean success, Integer locationID, boolean isSelected) {
        this.name = name;
        this.message = message;
        this.success = success;
        this.locationID = locationID;
        this.isSelected = isSelected;
    }

    protected LocationList(Parcel in) {
        name = in.readString();
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        if (in.readByte() == 0) {
            locationID = null;
        } else {
            locationID = in.readInt();
        }
        isSelected = in.readByte() != 0;
    }

    public static final Creator<LocationList> CREATOR = new Creator<LocationList>() {
        @Override
        public LocationList createFromParcel(Parcel in) {
            return new LocationList(in);
        }

        @Override
        public LocationList[] newArray(int size) {
            return new LocationList[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        if (locationID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(locationID);
        }
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
