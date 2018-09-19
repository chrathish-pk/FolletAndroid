
package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.os.Parcel;
import android.os.Parcelable;

import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.Note;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemDetails implements Parcelable
{

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currentCheckout")
    @Expose
    private CurrentCheckout currentCheckout;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("bibID")
    @Expose
    private Integer bibID;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("callNumber")
    @Expose
    private Object callNumber;
    @SerializedName("custodian")
    @Expose
    private Object custodian;
    @SerializedName("follettEBookURL")
    @Expose
    private Object follettEBookURL;
    @SerializedName("circType")
    @Expose
    private Object circType;



    @SerializedName("notes")
    @Expose
    private List<Note> itemNotes;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("coverImage")
    @Expose
    private Object coverImage;

    protected ItemDetails(Parcel in) {
        location = in.readString();
        isbn = in.readString();
        title = in.readString();
        status = in.readString();
        currentCheckout = in.readParcelable(CurrentCheckout.class.getClassLoader());
        department = in.readString();
        if (in.readByte() == 0) {
            bibID = null;
        } else {
            bibID = in.readInt();
        }
        author = in.readString();
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        barcode = in.readString();
    }

    public static final Creator<ItemDetails> CREATOR = new Creator<ItemDetails>() {
        @Override
        public ItemDetails createFromParcel(Parcel in) {
            return new ItemDetails(in);
        }

        @Override
        public ItemDetails[] newArray(int size) {
            return new ItemDetails[size];
        }
    };

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CurrentCheckout getCurrentCheckout() {
        return currentCheckout;
    }

    public void setCurrentCheckout(CurrentCheckout currentCheckout) {
        this.currentCheckout = currentCheckout;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getBibID() {
        return bibID;
    }

    public void setBibID(Integer bibID) {
        this.bibID = bibID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(Object callNumber) {
        this.callNumber = callNumber;
    }

    public Object getCustodian() {
        return custodian;
    }

    public void setCustodian(Object custodian) {
        this.custodian = custodian;
    }

    public Object getFollettEBookURL() {
        return follettEBookURL;
    }

    public void setFollettEBookURL(Object follettEBookURL) {
        this.follettEBookURL = follettEBookURL;
    }

    public Object getCircType() {
        return circType;
    }

    public void setCircType(Object circType) {
        this.circType = circType;
    }

    public List<Note> getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(List<Note> itemNotes) {
        this.itemNotes = itemNotes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Object getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Object coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeParcelable(currentCheckout, flags);
        dest.writeString(department);
        if (bibID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bibID);
        }
        dest.writeString(author);
        if (success) dest.writeByte((byte) (success == null ? 0 : 1));
        else dest.writeByte((byte) (success == null ? 0 : 2));
        dest.writeString(barcode);
    }
    
    public ItemDetails(String location, String isbn, String title, String status, CurrentCheckout currentCheckout, String department, Integer bibID, String
            author, Object callNumber, Object custodian, Object follettEBookURL, Object circType, List<Note> itemNotes, Boolean success, String barcode,
            Object coverImage) {
        this.location = location;
        this.isbn = isbn;
        this.title = title;
        this.status = status;
        this.currentCheckout = currentCheckout;
        this.department = department;
        this.bibID = bibID;
        this.author = author;
        this.callNumber = callNumber;
        this.custodian = custodian;
        this.follettEBookURL = follettEBookURL;
        this.circType = circType;
        this.itemNotes = itemNotes;
        this.success = success;
        this.barcode = barcode;
        this.coverImage = coverImage;
    }
  
}
