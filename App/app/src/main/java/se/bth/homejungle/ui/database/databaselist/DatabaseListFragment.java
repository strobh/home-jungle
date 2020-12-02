package se.bth.homejungle.ui.database.databaselist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.DatabaseAdapter;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.plants.yourplants.YourPlantsViewModel;


public class DatabaseListFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseListViewModel databaseListViewModel;
    Source source;
    long categoryId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseListViewModel = new ViewModelProvider(this).get(DatabaseListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_database_list, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        source = DatabaseListFragmentArgs.fromBundle(getArguments()).getSource();
        categoryId = DatabaseListFragmentArgs.fromBundle(getArguments()).getCategoryId();

        final DatabaseAdapter adapter = new DatabaseAdapter(new DatabaseAdapter.PlantDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseListViewModel.getSpecies().observe(getViewLifecycleOwner(), species -> {
            Log.v("Database:", "Species: " + species.size());
            adapter.submitList(species);
        });
        return root;
    }

    public Source getSource(){
        return this.source;
    }

    public void insertToOwnPlants(long speciesId, String description){
        databaseListViewModel.insertToOwnPlants(speciesId, description);
    }

    public void insertToFuturePlants(long speciesId, String description, LocalDate plantDay){
        databaseListViewModel.insertToFuturePlants(speciesId, description, plantDay);
    }
}