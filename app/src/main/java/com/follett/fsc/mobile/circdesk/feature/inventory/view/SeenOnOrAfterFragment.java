package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCallNumbersExcludeItemsBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.SeenOnOrAfterViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SeenOnOrAfterFragment extends BaseFragment<FragmentCallNumbersExcludeItemsBinding, SeenOnOrAfterViewModel> implements ItemClickListener,View.OnClickListener{
    private FragmentCallNumbersExcludeItemsBinding fragmentCallNumbersExcludeItemsBinding;
    private SeenOnOrAfterViewModel seenOnOrAfterViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_call_numbers_exclude_items;
    }

    @Override
    public SeenOnOrAfterViewModel getViewModel() {
        seenOnOrAfterViewModel = new SeenOnOrAfterViewModel(getBaseActivity().getApplication(), this);
        return seenOnOrAfterViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.seenViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCallNumbersExcludeItemsBinding = getViewDataBinding();

        fragmentCallNumbersExcludeItemsBinding.enterDate.setVisibility(View.VISIBLE);
        mActivity.baseBinding.backBtn.setOnClickListener(this);

        if (AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
            fragmentCallNumbersExcludeItemsBinding.descriptionText.setText(getString(R.string.excludeItemsLibDescription));

        } else {
            fragmentCallNumbersExcludeItemsBinding.descriptionText.setText(getString(R.string.excludeItemsResDescription));

        }

        if (getActivity() != null) {
            Date c = Calendar.getInstance().getTime();
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);

            ((SetupActivity) getActivity()).selectedDateLiveData.postValue(df.format(c));

            ((SetupActivity) getActivity()).selectedDateLiveData.observeForever(new Observer<String>() {
                @Override
                public void onChanged(@Nullable String date) {
                    fragmentCallNumbersExcludeItemsBinding.enterDate.setText(date);


                }
            });
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.enterDate) {
            DialogFragment dFragment = new DatePickerFragment();
            dFragment.show(getActivity().getSupportFragmentManager(), "Date Picker");
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            if (getActivity() != null) {
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SEEN_DATE, fragmentCallNumbersExcludeItemsBinding.enterDate.getText().toString());
                //AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE, AppUtils.getInstance().getFormatDate(formattedDate));
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }
}
