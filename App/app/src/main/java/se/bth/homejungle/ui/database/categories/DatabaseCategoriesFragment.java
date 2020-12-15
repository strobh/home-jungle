package se.bth.homejungle.ui.database.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.DatabaseGridAdapter;
import se.bth.homejungle.storage.entity.SpeciesCategory;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.database.databaselist.DatabaseListFragmentArgs;

public class DatabaseCategoriesFragment extends Fragment {
    RecyclerView recyclerView;

    private DatabaseViewModel databaseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseViewModel =
                new ViewModelProvider(this).get(DatabaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_database_categories, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        Source source = DatabaseListFragmentArgs.fromBundle(getArguments()).getSource();
        final DatabaseGridAdapter adapter = new DatabaseGridAdapter(new DatabaseGridAdapter.PlantDiff(), source);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        databaseViewModel.getSpeciesCategories().observe(getViewLifecycleOwner(), speciesCategories -> {
            if(!speciesCategories.get(0).getName().equals("All")){
                SpeciesCategory speciesCategoryAll = new SpeciesCategory("all", "All");
                speciesCategories.add(0, speciesCategoryAll);
            }
            Log.v("Database:", "Grid-Species: " + speciesCategories.size());
            adapter.submitList(speciesCategories);
        });

        return root;
    }
}