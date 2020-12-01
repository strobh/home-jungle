package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.ui.database.categories.DatabaseGridItem;

public class DatabaseGridAdapter extends ListAdapter<Species, DatabaseGridItem>{
    int source;

    public DatabaseGridAdapter(@NonNull DiffUtil.ItemCallback<Species> diffCallback, int source) {
        super(diffCallback);
        this.source = source;
    }

    @NonNull
    @Override
    public DatabaseGridItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return DatabaseGridItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseGridItem holder, int position) {
        Species currentPlant = getItem(position);
        holder.bind(currentPlant, source);
    }

    public static class PlantDiff extends DiffUtil.ItemCallback<Species>{

        @Override
        public boolean areItemsTheSame(@NonNull Species oldItem, @NonNull Species newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Species oldItem, @NonNull Species newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }

}
