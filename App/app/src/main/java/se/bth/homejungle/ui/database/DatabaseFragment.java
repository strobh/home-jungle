package se.bth.homejungle.ui.database;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import se.bth.homejungle.R;

public class DatabaseFragment extends Fragment {
    TextView direction;

    private DatabaseViewModel databaseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseViewModel =
                new ViewModelProvider(this).get(DatabaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_database, container, false);
        direction = root.findViewById(R.id.direction);

        //TODO try catch not the best way to handle this!
        int origin = 0;
        try {
            origin = DatabaseFragmentArgs.fromBundle(getArguments()).getDirection();
        }  catch (Exception e) {
            origin = 0;
        }
        switch (origin){
            case 1:
                direction.setText("From YourPlants");
                break;
            case 2:
                direction.setText("From FuturePlants");
                break;
            default:
                direction.setText("From anywhere else");
        }

        return root;
    }
}