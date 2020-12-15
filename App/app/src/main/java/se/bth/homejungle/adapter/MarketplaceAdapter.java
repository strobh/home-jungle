package se.bth.homejungle.adapter;

import android.location.Location;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.marketplace.marketplace.MarketplaceFragment;
import se.bth.homejungle.ui.marketplace.marketplace.MarketplaceListItem;

public class MarketplaceAdapter extends ListAdapter<MarketplacePlant, MarketplaceListItem> {
    MarketplaceFragment marketplaceFragment;
    Location location;

    public MarketplaceAdapter(@NonNull DiffUtil.ItemCallback<MarketplacePlant> diffCallback, MarketplaceFragment marketplaceFragment, Location location) {
        super(diffCallback);
        this.marketplaceFragment = marketplaceFragment;
        this.location = location;
    }

    @NonNull
    @Override
    public MarketplaceListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MarketplaceListItem.create(parent, location);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketplaceListItem holder, int position) {
        MarketplacePlant currentMarketplacePlant = getItem(position);
        holder.bind(currentMarketplacePlant, marketplaceFragment);
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
