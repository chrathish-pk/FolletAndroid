
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwardInfoRecord implements Parcelable {

    @SerializedName("awardList")
    @Expose
    private List<Object> awardList = null;

    protected AwardInfoRecord(Parcel in) {
    }

    public static final Creator<AwardInfoRecord> CREATOR = new Creator<AwardInfoRecord>() {
        @Override
        public AwardInfoRecord createFromParcel(Parcel in) {
            return new AwardInfoRecord(in);
        }

        @Override
        public AwardInfoRecord[] newArray(int size) {
            return new AwardInfoRecord[size];
        }
    };

    public List<Object> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Object> awardList) {
        this.awardList = awardList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //do nothing
    }
}
