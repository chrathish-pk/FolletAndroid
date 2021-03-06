package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryViewSelectionModel extends BaseViewModel implements NetworkInterface {

    public final MutableLiveData<InventorySelectionCriteria> inventorySelectionCriteriaMutableLiveData = new MutableLiveData<>();

    public final MutableLiveData<String> noSelectedInventoriesFoundMsg = new MutableLiveData<>();
    private UpdateUIListener updateUIListener;
    private Application mApplication;

    public InventoryViewSelectionModel(Application application, UpdateUIListener updateUIListener) {
        super(application);
        mApplication = application;
        this.updateUIListener = updateUIListener;
        fetchSelectedInventoryList();
    }

    public void fetchSelectedInventoryList() {
        setIsLoding(true);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");

        AppRemoteRepository.getInstance().getSelectedInventoriesList(map,this, AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_SITE_SHORT_NAME), AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID));
    }

    private void dismissProgrssBar() {
        setIsLoding(false);
    }

    @Override
    public void onCallCompleted(Object model) {
        dismissProgrssBar();
        try {
            InventorySelectionCriteria inventorySelectionCriteria = (InventorySelectionCriteria) model;
            int size = inventorySelectionCriteria.getItemList().size();
            List<SelectionCriteriaItemList> selectionCriteriaItemLists = inventorySelectionCriteria.getItemList();
            if (size == 0) {
                noSelectedInventoriesFoundMsg.setValue(getApplication().getString(R.string.no_selected_inventories));
            } else {
                if (model instanceof InventorySelectionCriteria) {
                    inventorySelectionCriteriaMutableLiveData.postValue((InventorySelectionCriteria) model);
                    InventorySelectionCriteria inventorySelectionCriteria1 = (InventorySelectionCriteria) model;
                    updateUIListener.updateUI(inventorySelectionCriteria1);
                }
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }

    @Override
    public void onCallFailed(Throwable throwable, String error) {
        dismissProgrssBar();
    }

    @Override
    public void onRefreshToken(int requestCode) {
        fetchSelectedInventoryList();
    }


}
