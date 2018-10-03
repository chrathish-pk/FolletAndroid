package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CallNumbers implements Parcelable {

    private String  callNumberFrom;
    private String callNumberTo;

    public CallNumbers(String callNumberFrom, String callNumberTo) {
        this.callNumberFrom = callNumberFrom;
        this.callNumberTo = callNumberTo;
    }

    public String getCallNumberFrom() {
        return callNumberFrom;
    }

    public void setCallNumberFrom(String callNumberFrom) {
        this.callNumberFrom = callNumberFrom;
    }

    public String getCallNumberTo() {
        return callNumberTo;
    }

    public void setCallNumberTo(String callNumberTo) {
        this.callNumberTo = callNumberTo;
    }

    protected CallNumbers(Parcel in) {
        callNumberFrom = in.readString();
        callNumberTo = in.readString();
    }

    public static final Creator<CallNumbers> CREATOR = new Creator<CallNumbers>() {
        @Override
        public CallNumbers createFromParcel(Parcel in) {
            return new CallNumbers(in);
        }

        @Override
        public CallNumbers[] newArray(int size) {
            return new CallNumbers[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(callNumberFrom);
        dest.writeString(callNumberTo);
    }
}
