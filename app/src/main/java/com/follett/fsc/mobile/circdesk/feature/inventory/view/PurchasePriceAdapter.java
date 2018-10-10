package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.RowPurchasepriceItemBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LocationList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.PurchasePriceItem;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_PRICE_ITEM;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SCANNING_LOCATION_ID;

class PurchasePriceAdapter extends RecyclerView.Adapter<PurchasePriceAdapter.PurchasePriceViewHolder> {

    private Context context;
    private PurchasePriceItem purchasePriceItem;
    private ItemClickListener itemClickListener;
    private List<PurchasePriceItem> priceItemList;
    private int lastSelectedPosition = -1;

    public PurchasePriceAdapter(Context context, List<PurchasePriceItem> priceItemList,ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.priceItemList = priceItemList;
    }

    @NonNull
    @Override
    public PurchasePriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowPurchasepriceItemBinding rowPurchasepriceItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_purchaseprice_item, parent, false);
        return new PurchasePriceViewHolder(rowPurchasepriceItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull PurchasePriceViewHolder holder, int position) {
        holder.rowPurchasepriceItemBinding.setPurchasePriceList(priceItemList.get(position));
        holder.rowPurchasepriceItemBinding.purchasePriceRadioButton.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return priceItemList != null ? priceItemList.size() : 0;
    }

    public class PurchasePriceViewHolder extends RecyclerView.ViewHolder {

        RowPurchasepriceItemBinding rowPurchasepriceItemBinding;

        public PurchasePriceViewHolder(RowPurchasepriceItemBinding rowPurchasepriceItemBinding) {
            super(rowPurchasepriceItemBinding.getRoot());
            this.rowPurchasepriceItemBinding = rowPurchasepriceItemBinding;

            for (int i = 0; i < priceItemList.size(); i++) {
                if (priceItemList.get(i).getPriceValueText() == AppRemoteRepository.getInstance().getString(KEY_SELECTED_PRICE_ITEM)) {
                    lastSelectedPosition = i;
                }
            }

            this.rowPurchasepriceItemBinding.purchasePriceRadioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    AppRemoteRepository.getInstance()
                            .setString(KEY_SELECTED_PRICE_ITEM, priceItemList
                                    .get(lastSelectedPosition)
                                    .getPriceValueText());

                    itemClickListener.onItemClick(v, lastSelectedPosition);

                }
            });
        }
    }
}
