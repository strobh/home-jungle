package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesCategory;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.database.categories.DatabaseGridItem;

/**
 * The DatabaseAdapter is an adapter for a recyclerview which binds the data of a category
 * to a recyclerview. This is adapter is used in DatabaseGridFragment.
 */

public class DatabaseGridAdapter extends ListAdapter<SpeciesCategory, DatabaseGridItem>{
    Source source;

    public DatabaseGridAdapter(@NonNull DiffUtil.ItemCallback<SpeciesCategory> diffCallback, Source source) {
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
        SpeciesCategory currentCategory = getItem(position);
        holder.bind(currentCategory, source);
    }

    public static class PlantDiff extends DiffUtil.ItemCallback<SpeciesCategory>{

        @Override
        public boolean areItemsTheSame(@NonNull SpeciesCategory oldItem, @NonNull SpeciesCategory newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SpeciesCategory oldItem, @NonNull SpeciesCategory newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }

}
