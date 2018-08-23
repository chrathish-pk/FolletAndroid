package com.follett.fsc.mobile.circdesk.viewmodel;

import android.app.Application;

import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CheckoutViewModel extends BaseViewModel {

    //this is the data that we will fetch asynchronously
    private ScanPatron scanPatron;

    private Application mApplication;

    private AppRemoteRepository mAppRemoteRepository;

    public CheckoutViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
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


    public void getScanPatron() {
        mAppRemoteRepository.getScanPatron()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<ScanPatron>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScanPatron value) {
                        scanPatron = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
