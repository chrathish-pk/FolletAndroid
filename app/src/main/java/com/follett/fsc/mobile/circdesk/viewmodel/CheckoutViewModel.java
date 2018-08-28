package com.follett.fsc.mobile.circdesk.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.ChangeUIListener;
import com.follett.fsc.mobile.circdesk.view.activity.PatronListActivity;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CheckoutViewModel extends BaseViewModel {

    //this is the data that we will fetch asynchronously
    public ScanPatron scanPatron;

    private Application mApplication;
    private ChangeUIListener changeUIListener;

    private AppRemoteRepository mAppRemoteRepository;

    public CheckoutViewModel(Application application, AppRemoteRepository appRemoteRepository, ChangeUIListener changeUIListener) {
        super(application);
        this.mApplication = application;
        this.mAppRemoteRepository = appRemoteRepository;
        this.changeUIListener = changeUIListener;
    }

    //we will call this method to get the data
   /* public LiveData<ScanPatron> getScanPatronLiveData() {
        //if the list is null
        if (scanPatronMutableLiveData == null) {
            scanPatronMutableLiveData = new MutableLiveData<ScanPatron>();
            //we will load it asynchronously from server in this method
            getScanPatron();
        }

        //finally we will return the list
        return scanPatronMutableLiveData;
    }*/


    public ScanPatron getScanPatron(String patronBarcodeID) {
        mAppRemoteRepository.getScanPatron(patronBarcodeID).subscribeWith(new Observer<ScanPatron>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ScanPatron value) {
                try {
                    scanPatron = value;

                    if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SELECTED_PATRON_ID))) {
                        AppSharedPreferences.getInstance(mApplication).setString(AppSharedPreferences.KEY_SELECTED_PATRON_ID, null);
                    }
                    if (scanPatron != null) {
                        if (scanPatron.getSuccess().equalsIgnoreCase("true")) {
                            if (scanPatron.getPatronList() != null) {
                                Intent patronListIntent = new Intent(mApplication, PatronListActivity.class);
                                patronListIntent.putExtra("scanPatron", scanPatron);
                                mApplication.startActivity(patronListIntent);
                            } else {
                                changeUIListener.updatePatronUI(scanPatron);
                            }
                        } else {
                            changeUIListener.updateErrorPatronUI(scanPatron);
                        }
                    } else {
                        changeUIListener.updateErrorPatronUI(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });

        return null;
    }

}
