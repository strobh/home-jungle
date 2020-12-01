package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.calendar.CalendarListItem;

public class CalendarAdapter extends ListAdapter<PlantWithSpecies, CalendarListItem> {

    public CalendarAdapter(DiffUtil.ItemCallback<PlantWithSpecies> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public CalendarListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CalendarListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarListItem holder, int position) {
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
