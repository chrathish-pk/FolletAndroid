
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

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

    private boolean isSelected;

    public SublocationList(String sublocationName, Integer sublocationID, boolean isSelected) {
        this.sublocationName = sublocationName;
        this.sublocationID = sublocationID;
        this.isSelected = isSelected;
    }

    protected SublocationList(Parcel in) {
        sublocationName = in.readString();
        if (in.readByte() == 0) {
            sublocationID = null;
        } else {
            sublocationID = in.readInt();
        }
        isSelected = in.readByte() != 0;
    }

    public static final Creator<SublocationList> CREATOR = new Creator<SublocationList>() {
        @Override
        public SublocationList createFromParcel(Parcel in) {
            return new SublocationList(in);
        }

        @Override
        public SublocationList[] newArray(int size) {
            return new SublocationList[size];
        }
    };

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
        dest.writeString(sublocationName);
        if (sublocationID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sublocationID);
        }
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
