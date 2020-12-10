package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.plants.yourplants.YourPlantsListItem;

public class YourPlantsAdapter extends ListAdapter<PlantWithSpecies, YourPlantsListItem> {

    public YourPlantsAdapter(@NonNull DiffUtil.ItemCallback<PlantWithSpecies> diffCallback) {
        super(diffCallback);
    }

    public PlantWithSpecies getByPosition(int position) {
        return getCurrentList().get(position);
    }

    @Override
    public YourPlantsListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return YourPlantsListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(YourPlantsListItem holder, int position) {
        PlantWithSpecies currentPlant = getItem(position);
        holder.bind(currentPlant);
    }

    public static class PlantDiff extends DiffUtil.ItemCallback<PlantWithSpecies> {
        @Override
        public boolean areItemsTheSame(@NonNull PlantWithSpecies oldItem, @NonNull PlantWithSpecies newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlantWithSpecies oldItem, @NonNull PlantWithSpecies newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
