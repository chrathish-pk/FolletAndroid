/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.app.base;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {
    
    private static final String TAG = BaseFragment.class.getSimpleName();
    
    public BaseActivity mActivity;

    private View mRootView;

    private T mViewDataBinding;
    
    private V mViewModel;
    
    public abstract int getLayoutId();
    
    public abstract V getViewModel();
    
    public abstract int getBindingVariable();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mActivity = (BaseActivity) context;
        } catch (ClassCastException e) {
            FollettLog.e(TAG, "ClassCastException");
        }
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();


    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        mRootView.setBackgroundColor(getResources().getColor(R.color.white));
        return mRootView;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
        inIt();
    }
    
    private void inIt() {
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        mViewModel.getErrorMessage()
                .observe(this, new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AppUtils.getInstance()
                                .showAlertDialog(activity, String.valueOf(o));
                    }
                });
    }
    
    protected Activity getBaseActivity() {
        return mActivity;
    }
    
    protected Application getBaseApplication() {
        if (mActivity == null) {
            return null;
        }
        
        return mActivity.getApplication();
    }
    
    public T getViewDataBinding() {
        return mViewDataBinding;
    }
    
    protected boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getBaseActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
