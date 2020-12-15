package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Date;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.Source;

public class PlantStartFragment extends Fragment
{
    TextView startDate;
    TextView steps;
    SinglePlantViewModel singlePlantViewModel;
    long speciesId;
    Source source;

    public PlantStartFragment(long speciesId, Source source){
        this.speciesId = speciesId;
        this.source = source;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        singlePlantViewModel = new ViewModelProvider(requireActivity()).get(SinglePlantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_start2, container, false);
        startDate = root.findViewById(R.id.startMonths);
        steps = root.findViewById(R.id.stepsInfo);

        singlePlantViewModel.getSpeciesById(speciesId).observe(getViewLifecycleOwner(), species -> {
            LocalDate plantDate = species.getPlantDate();
            startDate.setText(plantDate.getDayOfMonth() + "." + plantDate.getMonth().toString().toLowerCase());
            steps.setText(species.getHowToStart());
        });

        return root;
    }
}