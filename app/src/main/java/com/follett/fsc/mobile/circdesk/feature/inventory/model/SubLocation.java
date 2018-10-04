
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubLocation implements Parcelable
{

    @SerializedName("sublocationList")
    @Expose
    private List<SublocationList> sublocationList = null;
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
        in.readList(this.sublocationList, (SublocationList.class.getClassLoader()));
    }

    public SubLocation() {
    }

    public List<SublocationList> getSublocationList() {
        return sublocationList;
    }

    public void setSublocationList(List<SublocationList> sublocationList) {
        this.sublocationList = sublocationList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(sublocationList);
    }

    public int describeContents() {
        return  0;
    }

}
