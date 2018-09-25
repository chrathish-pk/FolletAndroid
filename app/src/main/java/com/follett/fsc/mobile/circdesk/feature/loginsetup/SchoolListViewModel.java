/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteRecord;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class SchoolListViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {
    
    public final MutableLiveData<SiteResults> siteResult = new MutableLiveData<>();
    
    public final MutableLiveData<String> noSchoolFoundMsg = new MutableLiveData<>();

    public SchoolListViewModel(Application application) {
        super(application);
        fetchSchoolList();
    }
    
    public void fetchSchoolList() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getSchoolList(this, AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME));
    }
    
    private void dismissProgrssBar() {
        setIsLoding(false);
    }
    
    @Override
    public void onCallCompleted(Object model) {
        dismissProgrssBar();
        try {
            SiteResults siteResults = (SiteResults) model;
            int size = siteResults.sites.size();
            List<SiteRecord> schoolList = siteResults.sites;
            if (size == 0) {
                String districtName = getApplication().getString(R.string.double_quote) + AppSharedPreferences.getInstance()
                        .getString(KEY_DISTRICT_NAME) + getApplication().getString(R.string.double_quote);
                noSchoolFoundMsg.setValue(getApplication().getString(R.string.no_schools, districtName));
            } else if (size == 1) {
                AppSharedPreferences.getInstance()
                        .setString(KEY_SITE_SHORT_NAME, schoolList.get(0)
                                .getSiteShortName());
                AppSharedPreferences.getInstance()
                        .setString(KEY_SITE_ID, schoolList.get(0)
                                .getSiteID());
                AppSharedPreferences.getInstance()
                        .setString(KEY_SITE_NAME, schoolList.get(0)
                                .getSiteName());
                setStatus(Status.SUCCESS);
            } else {
                siteResult.postValue((SiteResults) model);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }
    
    @Override
    public void onCallFailed(Throwable throwable) {
        dismissProgrssBar();
    }
    

}
