package se.bth.homejungle.ui.database.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.DatabaseGridAdapter;
import se.bth.homejungle.ui.database.categories.DatabaseFragmentArgs;

public class DatabaseFragment extends Fragment {
    TextView direction;
    RecyclerView recyclerView;

    private DatabaseViewModel databaseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseViewModel =
                new ViewModelProvider(this).get(DatabaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_database, container, false);
        direction = root.findViewById(R.id.direction);
        recyclerView = root.findViewById(R.id.idRecyclerView);


        //TODO try catch not the best way to handle this!
        int source = 0;
        try {
            source = DatabaseFragmentArgs.fromBundle(getArguments()).getSource();
        }  catch (Exception e) {
            source = 0;
        }
        switch (source){
            case 1:
                direction.setText("From YourPlants");
                break;
            case 2:
                direction.setText("From FuturePlants");
                break;
            default:
                direction.setText("From anywhere else");
        }

        final DatabaseGridAdapter adapter = new DatabaseGridAdapter(new DatabaseGridAdapter.PlantDiff(), source);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        databaseViewModel.getSpecies().observe(getViewLifecycleOwner(), species -> {
            Log.v("Database:", "Grid-Species: " + species.size());
            adapter.submitList(species);
        });




        return root;
    }
}