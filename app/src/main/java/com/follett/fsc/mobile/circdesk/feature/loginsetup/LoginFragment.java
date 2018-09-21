/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.arch.lifecycle.Observer;
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

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.databinding.FragmentLoginLayoutBinding;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class LoginFragment extends BaseFragment<FragmentLoginLayoutBinding, LoginViewModel> implements CTAButtonListener
                , TextView.OnEditorActionListener {
    
    private static final String TAG = LoginFragment.class.getSimpleName();
    
    private FragmentLoginLayoutBinding mLayoutBinding;
    
    private LoginViewModel mLoginViewModel;
    
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
        mLoginViewModel = new LoginViewModel(getBaseApplication());
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
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutBinding = getViewDataBinding();
        inItView(mLayoutBinding);
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        
        AppUtils.getInstance().hideKeyBoard(getBaseActivity(), mLayoutBinding.getRoot());
        if (!AppUtils.getInstance()
                .isEditTextNotEmpty(mLayoutBinding.useridEditText)) {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.username_empty_lebel));
        } else if (!AppUtils.getInstance()
                .isEditTextNotEmpty(mLayoutBinding.passwordEditText)) {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.password_empty_lebel));
        } else {
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(getBaseActivity());
                return;
            }
            
            mLoginViewModel.getLoginResults(AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), AppUtils.getInstance()
                    .getEditTextValue(mLayoutBinding.useridEditText), AppUtils.getInstance()
                    .getEditTextValue(mLayoutBinding.passwordEditText));
        }
    }



    
    
    private void inItView(final FragmentLoginLayoutBinding basicLayoutBinding) {
        
        basicLayoutBinding.setLoginListener(this);
        basicLayoutBinding.useridEditText.setOnEditorActionListener(this);
        basicLayoutBinding.passwordEditText.setOnEditorActionListener(this);
        basicLayoutBinding.useridEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing

            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing

            }
        });
        basicLayoutBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing

            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing

            }
        });
        mLoginViewModel.getStatus()
                .observe(this, new Observer<Status>() {
                    @Override
                    public void onChanged(@Nullable Status status) {
                        if (Status.SUCCESS.equals(status)) {
                            navigationListener.onNavigation(null, 3);
                        } else if (Status.ERROR.equals(status)) {
                            AppUtils.getInstance()
                                    .showShortToastMessages(getBaseActivity(), getString(R.string.invalid_user_id));
                        }
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
    
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_NEXT) {
            mLayoutBinding.passwordEditText.requestFocus();
        } else if (i == EditorInfo.IME_ACTION_DONE) {
            ctaButtonOnClick(mLayoutBinding.passwordEditText);
        }
        return true;
    }
}
