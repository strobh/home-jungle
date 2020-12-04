package se.bth.homejungle.ui.database.databaselist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

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

    TextView title;
    RecyclerView recyclerView;
    SearchView searchView;
    DatabaseListViewModel databaseListViewModel;
    Source source;
    String categoryName;
    long categoryId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseListViewModel = new ViewModelProvider(this).get(DatabaseListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_database_list, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        searchView = root.findViewById(R.id.idSearchView);
        source = DatabaseListFragmentArgs.fromBundle(getArguments()).getSource();
        categoryName = DatabaseListFragmentArgs.fromBundle(getArguments()).getCategoryName();
        title = root.findViewById(R.id.tv_title);
        title.setText(categoryName);
        final DatabaseAdapter adapter = new DatabaseAdapter(new DatabaseAdapter.PlantDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(categoryName.equals("All")){
            databaseListViewModel.getSpecies().observe(getViewLifecycleOwner(), species -> {
                Log.v("Database:", "Species: " + species.size());
                adapter.submitList(species);
            });

        } else{
            databaseListViewModel.getSpeciesByName(categoryName).observe(getViewLifecycleOwner(), species -> {
                Log.v("Database:", "Species: " + species.size());
                adapter.submitList(species);
            });
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                databaseListViewModel.getSpeciesByNameAndCategory(s, categoryId).observe(getViewLifecycleOwner(), species -> {
                    Log.v("Database:", "Species: " + species.size());
                    adapter.submitList(species);
                });
                System.out.println(s);
                return false;
            }
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