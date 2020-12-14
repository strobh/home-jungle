package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public PlantStartFragment(long speciesId){
        this.speciesId = speciesId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        singlePlantViewModel = new ViewModelProvider(requireActivity()).get(SinglePlantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_info, container, false);
        title = root.findViewById(R.id.title);
/*
        singlePlantViewModel.getSpeciesById(speciesId).observe(getViewLifecycleOwner(), species -> {
            title.setText(species.getName());
        });

*/
   //     plantImage = root.findViewById(R.id.imageView2);
        return root;
    }
}