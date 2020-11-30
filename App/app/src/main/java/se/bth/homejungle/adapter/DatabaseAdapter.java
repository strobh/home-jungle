package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.ui.database.databaselist.DatabaseListItem;

public class DatabaseAdapter extends ListAdapter<Species, DatabaseListItem> {

    public DatabaseAdapter(DiffUtil.ItemCallback<Species> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public DatabaseListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return DatabaseListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseListItem holder, int position) {
        Species currentPlant = getItem(position);
        holder.bind(currentPlant);
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