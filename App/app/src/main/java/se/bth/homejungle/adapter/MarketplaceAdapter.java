package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.marketplace.MarketplaceListItem;

public class MarketplaceAdapter extends ListAdapter<MarketplacePlant, MarketplaceListItem> {

    public MarketplaceAdapter(@NonNull DiffUtil.ItemCallback<MarketplacePlant> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MarketplaceListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MarketplaceListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketplaceListItem holder, int position) {
        MarketplacePlant currentMarketplacePlant = getItem(position);
        holder.bind(currentMarketplacePlant);
    }


    public static class MarketplacePlantDiff extends DiffUtil.ItemCallback<MarketplacePlant> {
        @Override
        public boolean areItemsTheSame(@NonNull MarketplacePlant oldItem, @NonNull MarketplacePlant newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MarketplacePlant oldItem, @NonNull MarketplacePlant newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
