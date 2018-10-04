
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable
{

    @SerializedName("locationList")
    @Expose
    private List<LocationList> locationList = null;
    public final static Creator<Location> CREATOR = new Creator<Location>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    }
    ;

    protected Location(Parcel in) {
        in.readList(this.locationList, (LocationList.class.getClassLoader()));
    }

    public Location() {
    }

    public List<LocationList> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationList> locationList) {
        this.locationList = locationList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(locationList);
    }

    public int describeContents() {
        return  0;
    }

}
