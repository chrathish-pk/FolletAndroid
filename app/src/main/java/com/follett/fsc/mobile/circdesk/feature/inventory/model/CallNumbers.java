package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CallNumbers implements Parcelable {

    private int callNumberFrom;
    private int callNumberTo;

    public CallNumbers(int callNumberFrom, int callNumberTo) {
        this.callNumberFrom = callNumberFrom;
        this.callNumberTo = callNumberTo;
    }

    protected CallNumbers(Parcel in) {
        callNumberFrom = in.readInt();
        callNumberTo = in.readInt();
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

    public int getCallNumberFrom() {
        return callNumberFrom;
    }

    public void setCallNumberFrom(int callNumberFrom) {
        this.callNumberFrom = callNumberFrom;
    }

    public int getCallNumberTo() {
        return callNumberTo;
    }

    public void setCallNumberTo(int callNumberTo) {
        this.callNumberTo = callNumberTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(callNumberFrom);
        dest.writeInt(callNumberTo);
    }
}
