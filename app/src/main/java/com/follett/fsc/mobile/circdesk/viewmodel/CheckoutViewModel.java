package com.follett.fsc.mobile.circdesk.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.checkout.CheckoutResult;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CheckoutViewModel extends BaseViewModel {

    //this is the data that we will fetch asynchronously
    public ScanPatron scanPatron;
    public CheckoutResult checkoutResult;
    private Application mApplication;
    private UpdateUIListener updateUIListener;

    private AppRemoteRepository mAppRemoteRepository;

    public CheckoutViewModel(Application application, AppRemoteRepository appRemoteRepository, UpdateUIListener updateUIListener) {
        super(application);
        this.mApplication = application;
        this.mAppRemoteRepository = appRemoteRepository;
        this.updateUIListener = updateUIListener;
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


    public void getScanPatron(String patronBarcodeID) {
        mAppRemoteRepository.getScanPatron(patronBarcodeID).subscribeWith(new Observer<ScanPatron>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ScanPatron value) {
                try {
                    scanPatron = value;

                } catch (Exception e) {
                    FollettLog.d("Exception", e.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                FollettLog.d("Exception", e.getMessage());
            }

            @Override
            public void onComplete() {
                String selectedPatronID = AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
                if (!TextUtils.isEmpty(selectedPatronID)) {
                    AppSharedPreferences.getInstance(mApplication).setString(AppSharedPreferences.KEY_BARCODE, selectedPatronID);
                    AppSharedPreferences.getInstance(mApplication).setString(AppSharedPreferences.KEY_PATRON_ID, scanPatron.getPatronID());
                    AppSharedPreferences.getInstance(mApplication).setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
                }
                updateUIListener.updateUI(scanPatron);
            }
        });
    }

    public void getCheckoutResult(String patronID, String barcode) {
        mAppRemoteRepository.getCheckoutResult(patronID, barcode).subscribeWith(new Observer<CheckoutResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckoutResult value) {
                try {
                    checkoutResult = value;
                } catch (Exception e) {
                    FollettLog.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                FollettLog.d("Exception", e.getMessage());

            }

            @Override
            public void onComplete() {
                updateUIListener.updateUI(checkoutResult);

            }
        });
    }

}
