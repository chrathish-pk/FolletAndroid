package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCallNumbersExcludeItemsBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.SeenOnOrAfterViewModel;

public class SeenOnOrAfterFragment extends BaseFragment<FragmentCallNumbersExcludeItemsBinding, SeenOnOrAfterViewModel> implements ItemClickListener {
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

        fragmentCallNumbersExcludeItemsBinding.descriptionText.setText(getString(R.string.excludeItemsDescription));
        fragmentCallNumbersExcludeItemsBinding.enterDate.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.enterDate) {
            DialogFragment dFragment = new DatePickerFragment();
            dFragment.show(getActivity().getSupportFragmentManager(), "Date Picker");
        }
    }
}
