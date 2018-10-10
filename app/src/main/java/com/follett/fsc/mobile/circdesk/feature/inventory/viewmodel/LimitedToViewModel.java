package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LimitedToParentData;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.view.UpdateItemUIListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class LimitedToViewModel extends BaseViewModel implements NetworkInterface {

    private SubLocation subLocation;
    private Application application;
    private UpdateItemUIListener updateItemUIListener;

    public LimitedToViewModel(@NonNull Application application, UpdateItemUIListener updateItemUIListener) {
        super(application);
        this.application = application;
        this.updateItemUIListener = updateItemUIListener;
    }

    public void fetchSubLocationList() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getSubLocationList(this, AppUtils.getInstance().getHeader(application),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SITE_SHORT_NAME),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        subLocation = ((SubLocation) model);
        updateItemUIListener.updateUI(model);
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
    }

    @Override
    public void onRefreshToken(int requestCode) {

    }

    public List<LimitedToParentData> getLimitedToParentData() {
        List<LimitedToParentData> limitedToParentDataList = new ArrayList<>();

        limitedToParentDataList.add(new LimitedToParentData(application.getString(R.string.unlimited), 0, null, false));
        limitedToParentDataList.add(new LimitedToParentData(application.getString(R.string.homeLocation), 1, subLocation, false));
        limitedToParentDataList.add(new LimitedToParentData(application.getString(R.string.custodian), 2, null, false));
        limitedToParentDataList.add(new LimitedToParentData(application.getString(R.string.department), 3, null, false));

        return limitedToParentDataList;
    }


}
