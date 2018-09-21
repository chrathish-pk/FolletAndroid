/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "siteResults")
public class SiteResults implements Parcelable {
    @ElementList(name = "sites") public List<SiteRecord> sites;
    
    public SiteResults(List<SiteRecord> sites) {
        this.sites = sites;
    }
    
    protected SiteResults(Parcel in) {
        sites = in.createTypedArrayList(SiteRecord.CREATOR);
    }

    public static final Creator<SiteResults> CREATOR = new Creator<SiteResults>() {
        @Override
        public SiteResults createFromParcel(Parcel in) {
            return new SiteResults(in);
        }

        @Override
        public SiteResults[] newArray(int size) {
            return new SiteResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(sites);
    }
}
