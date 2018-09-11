
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicationList implements Parcelable
{

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("prompt")
    @Expose
    private String prompt;

    protected PublicationList(Parcel in) {
        value = in.readString();
        prompt = in.readString();
    }

    public static final Creator<PublicationList> CREATOR = new Creator<PublicationList>() {
        @Override
        public PublicationList createFromParcel(Parcel in) {
            return new PublicationList(in);
        }

        @Override
        public PublicationList[] newArray(int size) {
            return new PublicationList[size];
        }
    };

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeString(prompt);
    }
}
