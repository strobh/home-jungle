package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.giveaways.GiveawaysFragment;
import se.bth.homejungle.ui.giveaways.GiveawaysListItem;

/**
 * The GiveawaysAdapter is an adapter for a recyclerview which binds the data of all own giveaways
 * to a recyclerview.
 */

public class GiveawaysAdapter extends ListAdapter<MarketplacePlant, GiveawaysListItem> {

    GiveawaysFragment giveawaysFragment;


    public GiveawaysAdapter(@NonNull DiffUtil.ItemCallback diffCallback, GiveawaysFragment giveawaysFragment) {
        super(diffCallback);
        this.giveawaysFragment = giveawaysFragment;
    }

    @NonNull
    @Override
    public GiveawaysListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GiveawaysListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GiveawaysListItem holder, int position) {
        MarketplacePlant currentGiveaway = (getItem(position));
        holder.bind(currentGiveaway, giveawaysFragment);
    }

    public MarketplacePlant getByPosition(int position){
        return getCurrentList().get(position);
    }

    public static class GiveawayDiff extends DiffUtil.ItemCallback<MarketplacePlant>{

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
