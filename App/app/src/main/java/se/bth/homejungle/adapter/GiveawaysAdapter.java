package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.giveaways.GiveawaysListItem;

public class GiveawaysAdapter extends ListAdapter<MarketplacePlant, GiveawaysListItem> {


    public GiveawaysAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public GiveawaysListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GiveawaysListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GiveawaysListItem holder, int position) {
        MarketplacePlant currentGiveaway = (getItem(position));
        holder.bind(currentGiveaway);
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
