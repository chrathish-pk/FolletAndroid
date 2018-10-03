
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubLocation implements Parcelable
{

    @SerializedName("subLocationList")
    @Expose
    private List<SubLocationList> subLocationList = null;
    public final static Creator<SubLocation> CREATOR = new Creator<SubLocation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SubLocation createFromParcel(Parcel in) {
            return new SubLocation(in);
        }

        public SubLocation[] newArray(int size) {
            return (new SubLocation[size]);
        }

    }
    ;

    protected SubLocation(Parcel in) {
        in.readList(this.subLocationList, (SubLocationList.class.getClassLoader()));
    }

    public SubLocation() {
    }

    public List<SubLocationList> getSubLocationList() {
        return subLocationList;
    }

    public void setSubLocationList(List<SubLocationList> subLocationList) {
        this.subLocationList = subLocationList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(subLocationList);
    }

    public int describeContents() {
        return  0;
    }

}
