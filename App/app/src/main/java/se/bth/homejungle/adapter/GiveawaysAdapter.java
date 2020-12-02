package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.ui.giveaways.GiveawaysListItem;

public class GiveawaysAdapter extends ListAdapter<Plant, GiveawaysListItem> {


    public GiveawaysAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public GiveawaysListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GiveawaysListItem.create(parent);
    }

    @Override
    //TODO: change plant to giveaway
    public void onBindViewHolder(@NonNull GiveawaysListItem holder, int position) {
        Plant currentPlant = (getItem(position));
        holder.bind(currentPlant);
    }

    //TODO: change plant to giveaway
    public static class PlantDiff extends DiffUtil.ItemCallback<Plant>{

        @Override
        public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
