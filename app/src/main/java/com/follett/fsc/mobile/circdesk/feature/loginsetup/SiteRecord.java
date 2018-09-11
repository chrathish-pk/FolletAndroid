/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "siteRecord")
public class SiteRecord implements Parcelable {
    
    @Element(required = false) String asset;
    
    @Element(required = false) String library;
    
    @Element(required = false) String media;
    
    @Element(required = false) String siteID;
    
    @Element(required = false) String siteName;
    
    @Element(required = false) String siteShortName;
    
    @Element(required = false) String textbook;

    protected SiteRecord(Parcel in) {
        asset = in.readString();
        library = in.readString();
        media = in.readString();
        siteID = in.readString();
        siteName = in.readString();
        siteShortName = in.readString();
        textbook = in.readString();
    }

    public static final Creator<SiteRecord> CREATOR = new Creator<SiteRecord>() {
        @Override
        public SiteRecord createFromParcel(Parcel in) {
            return new SiteRecord(in);
        }

        @Override
        public SiteRecord[] newArray(int size) {
            return new SiteRecord[size];
        }
    };

    public String getAsset() {
        return asset;
    }
    
    public void setAsset(String asset) {
        this.asset = asset;
    }
    
    public String getLibrary() {
        return library;
    }
    
    public void setLibrary(String library) {
        this.library = library;
    }
    
    public String getMedia() {
        return media;
    }
    
    public void setMedia(String media) {
        this.media = media;
    }
    
    public String getSiteID() {
        return siteID;
    }
    
    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }
    
    public String getSiteShortName() {
        return siteShortName;
    }
    
    public void setSiteShortName(String siteShortName) {
        this.siteShortName = siteShortName;
    }
    
    public String getTextbook() {
        return textbook;
    }
    
    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }
    
    public String getSiteName() {
        return siteName;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(asset);
        dest.writeString(library);
        dest.writeString(media);
        dest.writeString(siteID);
        dest.writeString(siteName);
        dest.writeString(siteShortName);
        dest.writeString(textbook);
    }
}
