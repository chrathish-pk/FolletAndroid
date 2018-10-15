package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCallNumbersExcludeItemsBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.NewInventoryData;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.CallNumbersViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class CallNumbersFragment extends BaseFragment<FragmentCallNumbersExcludeItemsBinding, CallNumbersViewModel> implements View.OnClickListener {

    private CallNumbersViewModel callNumbersViewModel;
    private FragmentCallNumbersExcludeItemsBinding fragmentCallNumbersExcludeItemsBinding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_call_numbers_exclude_items;
    }

    @Override
    public CallNumbersViewModel getViewModel() {
        callNumbersViewModel = new CallNumbersViewModel(getBaseActivity().getApplication());
        return callNumbersViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.callViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCallNumbersExcludeItemsBinding = getViewDataBinding();

        mActivity.baseBinding.backBtn.setOnClickListener(this);

        fragmentCallNumbersExcludeItemsBinding.callNumbersLayout.setVisibility(View.VISIBLE);
        fragmentCallNumbersExcludeItemsBinding.descriptionText.setText(getString(R.string.callNumbersDescription));
        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_FROM).isEmpty())
            fragmentCallNumbersExcludeItemsBinding.callFromEdittext.setText("");
        else
            fragmentCallNumbersExcludeItemsBinding.callFromEdittext.setText(AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_FROM));

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_TO).isEmpty())
            fragmentCallNumbersExcludeItemsBinding.callToEdittext.setText("");
        else
            fragmentCallNumbersExcludeItemsBinding.callToEdittext.setText(AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_CALL_NUMBER_TO));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_CALL_NUMBER_FROM, fragmentCallNumbersExcludeItemsBinding.callFromEdittext.getText().toString());
            AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_CALL_NUMBER_TO, fragmentCallNumbersExcludeItemsBinding.callToEdittext.getText().toString());
            if (getActivity() != null)
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            mActivity.onBackPressed();
        }
    }

}
