/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.databinding.ActivityInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

/**
 * Created by muthulakshmi on 11/09/18.
 */

public class InventoryActivity extends BaseActivity<InventoryViewModel> implements NavigationListener, View.OnClickListener {

    private ActivityInventoryBinding mInventoryBinding;
    private InventoryFragment mInventoryFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInventoryBinding = putContentView(R.layout.activity_inventory);
        initViews();
        pushFragment(new InventoryFragment(), R.id.inventoryContainer, "InventoryFragment", true);
    }

    private void initViews() {
        setBackBtnVisible();
        setTitleBar(getString(R.string.inventory));
        baseBinding.backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNavigation(int position) {
        // Do Nothing
    }

    @Override
    public void onNavigation(Object model, int position) {

    }

    @Override
    public void setToolBarTitle(String titleText) {
        setTitleBar(titleText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
