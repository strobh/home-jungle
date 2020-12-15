package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import se.bth.homejungle.R;

public class PlantInfoFragment extends Fragment
{
    long speciesId;
    TextView title;
    ImageView plantImg;
    TextView information;
    ImageButton delete;

    SinglePlantViewModel singlePlantViewModel;

    public PlantInfoFragment(long speciesId){
        this.speciesId = speciesId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        singlePlantViewModel = new ViewModelProvider(requireActivity()).get(SinglePlantViewModel.class);

        View root = inflater.inflate(R.layout.fragment_plant_info, container, false);
        title = root.findViewById(R.id.plantName);
        plantImg = root.findViewById(R.id.plantPicture);
        delete = root.findViewById(R.id.deleteButton);
        singlePlantViewModel.getSpeciesById(speciesId).observe(getViewLifecycleOwner(), species -> {
            title.setText(species.getName());
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlant();
            }
        });

        return root;

    }

    private void deletePlant() {
    }
}