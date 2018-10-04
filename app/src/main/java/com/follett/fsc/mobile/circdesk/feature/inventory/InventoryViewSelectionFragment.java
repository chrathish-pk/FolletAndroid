package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CustomAlert;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.InventoryViewSelectionBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class InventoryViewSelectionFragment extends BaseFragment<InventoryViewSelectionBinding, InventoryViewSelectionModel> implements
        View.OnClickListener, ItemClickListener, UpdateUIListener {

    private InventoryViewSelectionModel inventoryViewSelectionModel;
    private InventoryViewSelectionBinding inventoryViewSelectionBinding;

    public static InventoryViewSelectionFragment newInstance() {
        Bundle args = new Bundle();
        InventoryViewSelectionFragment fragment = new InventoryViewSelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.inventory_view_selection;
    }

    @Override
    public InventoryViewSelectionModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        inventoryViewSelectionModel = new InventoryViewSelectionModel(getBaseApplication(), this);
        return inventoryViewSelectionModel;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inventoryViewSelectionBinding = getViewDataBinding();

        mActivity.baseBinding.backBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mActivity.onBackPressed();
    }

    @Override
    public void updateUI(final Object value) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (value instanceof InventorySelectionCriteria) {
                        InventorySelectionCriteria inventorySelectionCriteria = (InventorySelectionCriteria) value;

                        if (getBaseActivity() == null) {
                            return;
                        }
                        inventoryViewSelectionBinding.inventorySelectionRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

                        inventoryViewSelectionModel.inventorySelectionCriteriaMutableLiveData.observe(InventoryViewSelectionFragment.this, new Observer<InventorySelectionCriteria>() {
                            @Override
                            public void onChanged(@Nullable InventorySelectionCriteria inventorySelectionCriteria) {
                                InventoryViewSelectionAdapter adapter = new InventoryViewSelectionAdapter(getBaseActivity(), inventorySelectionCriteria.getItemList());
                                inventoryViewSelectionBinding.inventorySelectionRecyclerview.setAdapter(adapter);
                            }
                        });
                        inventoryViewSelectionModel.noSelectedInventoriesFoundMsg.observe(InventoryViewSelectionFragment.this, new Observer<String>() {
                            @Override
                            public void onChanged(@Nullable String msg) {
                                showAlert(msg);
                            }
                        });

                    }
                } catch (Exception e) {
                    FollettLog.e(getString(R.string.error), e.getMessage());
                }
            }
        });
    }

    private void showAlert(String msg) {
        if (getBaseActivity() == null) {
            return;
        }

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }

            }
        };
        CustomAlert.showDialog(getBaseActivity(), null, msg, getString(R.string.ok), onClickListener, null, onClickListener);

    }


    @Override
    public void onItemClick(View view, int position) {

    }
}
