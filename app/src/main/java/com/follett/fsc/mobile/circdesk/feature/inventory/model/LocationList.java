
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
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
    public final static Creator<LocationList> CREATOR = new Creator<LocationList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LocationList createFromParcel(Parcel in) {
            return new LocationList(in);
        }

        public LocationList[] newArray(int size) {
            return (new LocationList[size]);
        }

    }
    ;

    protected LocationList(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((Object) in.readValue((Object.class.getClassLoader())));
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.locationID = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public LocationList() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(message);
        dest.writeValue(success);
        dest.writeValue(locationID);
    }

    public int describeContents() {
        return  0;
    }

}
