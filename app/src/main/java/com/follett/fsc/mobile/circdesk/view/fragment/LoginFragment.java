/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentLoginLayoutBinding;
import com.follett.fsc.mobile.circdesk.interfaces.LoginNavigator;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.LoginViewModel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class LoginFragment extends BaseFragment<FragmentLoginLayoutBinding, LoginViewModel> implements LoginNavigator {
    
    private static final String TAG = LoginFragment.class.getSimpleName();
    
    private FragmentLoginLayoutBinding mLayoutBinding;
    
    private LoginViewModel mLoginViewModel;
    
    private AppRemoteRepository appRemoteRepository;
    
    private NavigationListener navigationListener;
    
    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_login_layout;
    }
    
    @Override
    public LoginViewModel getViewModel() {
        appRemoteRepository = new AppRemoteRepository();
        mLoginViewModel = new LoginViewModel(getBaseApplication(), appRemoteRepository);
        mLoginViewModel.setNavigator(this);
        return mLoginViewModel;
    }
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            navigationListener = (NavigationListener) context;
        } catch (ClassCastException ex) {
            FollettLog.e(TAG, "ClassCastException");
        }
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mLoginViewModel.setNavigator(this);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutBinding = getViewDataBinding();
        inItView(mLayoutBinding);
    }
    
    @Override
    public void loginOnClick() {
        
        if (AppUtils.getInstance()
                .isEditTextNotEmpty(mLayoutBinding.useridEditText) && AppUtils.getInstance()
                .isEditTextNotEmpty(mLayoutBinding.passwordEditText)) {
            
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(getBaseActivity());
                return;
            }
            
            mLoginViewModel.getLoginResults(AppUtils.getInstance()
                    .getEditTextValue(mLayoutBinding.useridEditText), AppUtils.getInstance()
                    .getEditTextValue(mLayoutBinding.passwordEditText));
        }
    }
    
    @Override
    public void navigationToNextFragment() {
        navigationListener.onNavigation(2);
    }

//    @Override
//    public void asyncTaskResult(boolean result) {
//        if (result) {
//            mLoginViewModel.getVersion();
//        }
//    }
//
//    @Override
//    public void displayErrorDialog(final String message) {
//        getBaseActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                AppUtils.getInstance()
//                        .showShortToastMessages(getBaseActivity(), message);
//            }
//        });
//    }

//    @Override
//    public void navigationToNextFragment(int fragmentNumber) {
//        navigationListener.onNavigation(0);
//    }
    
    
    private void inItView(final FragmentLoginLayoutBinding basicLayoutBinding) {
        
        basicLayoutBinding.passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                
                if (i == EditorInfo.IME_ACTION_DONE) {
                }
                return true;
            }
        });
        basicLayoutBinding.useridEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            }
        });
        basicLayoutBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            }
        });
    }
    
    public void onTextChangedInEditText() {
        if (AppUtils.getInstance()
                .isEditTextNotEmpty(mLayoutBinding.useridEditText) && AppUtils.getInstance()
                .isEditTextNotEmpty(mLayoutBinding.passwordEditText)) {
            mLayoutBinding.loginTextview.setSelected(true);
        } else {
            mLayoutBinding.loginTextview.setSelected(false);
        }
    }
    
    @Override
    public void onDetach() {
        navigationListener.setToolBarTitle(getBaseActivity().getString(R.string.select_school_label));
        super.onDetach();
    }
}
