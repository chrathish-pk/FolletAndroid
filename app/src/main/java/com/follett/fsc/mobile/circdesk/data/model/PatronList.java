
package com.follett.fsc.mobile.circdesk.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatronList implements Parcelable{

    @SerializedName("patron")
    @Expose
    private List<Patron> patron = null;

    protected PatronList(Parcel in) {
        patron = in.createTypedArrayList(Patron.CREATOR);
    }

    public static final Creator<PatronList> CREATOR = new Creator<PatronList>() {
        @Override
        public PatronList createFromParcel(Parcel in) {
            return new PatronList(in);
        }

        @Override
        public PatronList[] newArray(int size) {
            return new PatronList[size];
        }
    };

    public List<Patron> getPatron() {
        return patron;
    }

    public void setPatron(List<Patron> patron) {
        this.patron = patron;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(patron);
    }
}
