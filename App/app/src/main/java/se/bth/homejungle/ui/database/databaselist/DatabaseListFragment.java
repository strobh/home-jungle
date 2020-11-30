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
import androidx.room.DatabaseView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.DatabaseAdapter;
import se.bth.homejungle.ui.database.DatabaseViewModel;


public class DatabaseListFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseListViewModel databaseListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseListViewModel = new ViewModelProvider(this).get(DatabaseListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_database_list, container, false);

        recyclerView = root.findViewById(R.id.idRecyclerView);
        final DatabaseAdapter adapter = new DatabaseAdapter(new DatabaseAdapter.PlantDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseListViewModel.getSpecies().observe(getViewLifecycleOwner(), species -> {
            Log.v("Database:", "Species: " + species.size());
            adapter.submitList(species);
        });

        return root;
    }
}