package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectionCriteriaItemList implements Parcelable {

    @SerializedName("values")
    @Expose
    private List<String> values = null;
    @SerializedName("prompt")
    @Expose
    private String prompt;

    protected SelectionCriteriaItemList(Parcel in) {
        values = in.createStringArrayList();
        prompt = in.readString();
    }

    public static final Creator<SelectionCriteriaItemList> CREATOR = new Creator<SelectionCriteriaItemList>() {
        @Override
        public SelectionCriteriaItemList createFromParcel(Parcel in) {
            return new SelectionCriteriaItemList(in);
        }

        @Override
        public SelectionCriteriaItemList[] newArray(int size) {
            return new SelectionCriteriaItemList[size];
        }
    };


    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(values);
        parcel.writeString(prompt);
    }

}
