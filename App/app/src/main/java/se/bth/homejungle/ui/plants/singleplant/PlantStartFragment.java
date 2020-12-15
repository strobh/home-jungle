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

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;

public class PlantStartFragment extends Fragment
{
    TextView title;
    ImageView plantImage;
    TextView startDate;
    TextView steps;
    SinglePlantViewModel singlePlantViewModel;
    long speciesId;
    ImageButton delete;

    public PlantStartFragment(long speciesId){
        this.speciesId = speciesId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        singlePlantViewModel = new ViewModelProvider(requireActivity()).get(SinglePlantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_start2, container, false);
        title = root.findViewById(R.id.title);
        startDate = root.findViewById(R.id.startMonths);
        steps = root.findViewById(R.id.stepsInfo);
        delete = root.findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlant();
            }
        });

        singlePlantViewModel.getSpeciesById(speciesId).observe(getViewLifecycleOwner(), species -> {
            title.setText(species.getName());
            startDate.setText(species.getPlantDate().toString());
            steps.setText(species.getHowToStart());
        });

        return root;
    }

    public void deletePlant(){

    }
}