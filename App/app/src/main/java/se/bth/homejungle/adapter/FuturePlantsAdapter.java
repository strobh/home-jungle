package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;
import java.util.Objects;

import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;
import se.bth.homejungle.ui.plants.futureplants.FuturePlantListItem;

public class FuturePlantsAdapter extends ListAdapter<FuturePlantWithSpecies, FuturePlantListItem> {

    public FuturePlantsAdapter(@NonNull DiffUtil.ItemCallback<FuturePlantWithSpecies> diffCallback) {
        super(diffCallback);
    }

    public FuturePlantWithSpecies getByPosition(int position) {
        return getCurrentList().get(position);
    }

    @Override
    public FuturePlantListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return FuturePlantListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(FuturePlantListItem holder, int position) {
        FuturePlantWithSpecies currentPlant = getItem(position);
        holder.bind(currentPlant);
    }

    public static class PlantDiff extends DiffUtil.ItemCallback<FuturePlantWithSpecies> {
        @Override
        public boolean areItemsTheSame(@NonNull FuturePlantWithSpecies oldItem, @NonNull FuturePlantWithSpecies newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull FuturePlantWithSpecies oldItem, @NonNull FuturePlantWithSpecies newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
