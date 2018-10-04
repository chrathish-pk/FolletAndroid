
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SublocationList implements Parcelable
{

    @SerializedName("sublocationName")
    @Expose
    private String sublocationName;
    @SerializedName("sublocationID")
    @Expose
    private Integer sublocationID;
    public final static Creator<SublocationList> CREATOR = new Creator<SublocationList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SublocationList createFromParcel(Parcel in) {
            return new SublocationList(in);
        }

        public SublocationList[] newArray(int size) {
            return (new SublocationList[size]);
        }

    }
    ;

    protected SublocationList(Parcel in) {
        this.sublocationName = ((String) in.readValue((String.class.getClassLoader())));
        this.sublocationID = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public SublocationList() {
    }

    public String getSublocationName() {
        return sublocationName;
    }

    public void setSublocationName(String sublocationName) {
        this.sublocationName = sublocationName;
    }

    public Integer getSublocationID() {
        return sublocationID;
    }

    public void setSublocationID(Integer sublocationID) {
        this.sublocationID = sublocationID;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sublocationName);
        dest.writeValue(sublocationID);
    }

    public int describeContents() {
        return  0;
    }

}
