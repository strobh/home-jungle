package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.ui.plants.yourplants.PlantListItem;


public class YourPlantsListAdapter extends ListAdapter<Plant, PlantListItem> {

    public YourPlantsListAdapter(@NonNull DiffUtil.ItemCallback<Plant> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PlantListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlantListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(PlantListItem holder, int position) {
        Plant currentPlant = getItem(position);
        holder.bind(currentPlant);
    }

    public static class PlantDiff extends DiffUtil.ItemCallback<Plant> {
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
