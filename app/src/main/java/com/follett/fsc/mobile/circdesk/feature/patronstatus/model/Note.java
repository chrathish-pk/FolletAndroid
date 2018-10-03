package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    
    @SerializedName("text") @Expose private String text;
    @SerializedName("noteID") @Expose private Integer noteID;
    @SerializedName("urgent") @Expose private Boolean urgent;
    
    protected Note(Parcel in) {
        text = in.readString();
        if (in.readByte() == 0) { noteID = null; } else { noteID = in.readInt(); }
        byte tmpUrgent = in.readByte();
        urgent = tmpUrgent == 0 ? null : tmpUrgent == 1;
    }
    
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }
        
        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Integer getNoteID() {
        return noteID;
    }
    
    public void setNoteID(Integer noteID) {
        this.noteID = noteID;
    }
    
    public Boolean getUrgent() {
        return urgent;
    }
    
    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(text);
        if (noteID == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(noteID);
        }
        parcel.writeByte((byte) (urgent == null ? 0 : urgent ? 1 : 2));
    }
}