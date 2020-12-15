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
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.ui.Source;

public class PlantInfoFragment extends Fragment {
    long speciesId;
    TextView information;
    Source source;

    ImageView water2;
    ImageView water3;
    ImageView water4;
    ImageView sun2;
    ImageView sun3;
    ImageView sun4;
    ImageView care2;
    ImageView care3;
    ImageView care4;


    SinglePlantViewModel singlePlantViewModel;

    public PlantInfoFragment(long speciesId, Source source) {
        this.speciesId = speciesId;
        this.source = source;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        singlePlantViewModel = new ViewModelProvider(requireActivity()).get(SinglePlantViewModel.class);

        View root = inflater.inflate(R.layout.fragment_plant_info, container, false);
        information = root.findViewById(R.id.moreInformation);

        singlePlantViewModel.getSpeciesById(speciesId).observe(getViewLifecycleOwner(), species -> {
            information.setText(species.getDescription());
            int waterAmount = (int) species.getWater();
            switch (waterAmount) {
                case 1:
                    water4 = root.findViewById(R.id.water4);
                    water4.setVisibility(View.INVISIBLE);
                    care4 = root.findViewById(R.id.heart4);
                    care4.setVisibility(View.INVISIBLE);
                    water3 = root.findViewById(R.id.water3);
                    water3.setVisibility(View.INVISIBLE);
                    care3 = root.findViewById(R.id.heart3);
                    care3.setVisibility(View.INVISIBLE);
                    water2 = root.findViewById(R.id.water2);
                    water2.setVisibility(View.INVISIBLE);
                    care2 = root.findViewById(R.id.heart2);
                    care2.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    water3 = root.findViewById(R.id.water3);
                    water3.setVisibility(View.INVISIBLE);
                    care3 = root.findViewById(R.id.heart3);
                    care3.setVisibility(View.INVISIBLE);
                    water2 = root.findViewById(R.id.water2);
                    water2.setVisibility(View.INVISIBLE);
                    care2 = root.findViewById(R.id.heart2);
                    care2.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    water2 = root.findViewById(R.id.water2);
                    water2.setVisibility(View.INVISIBLE);
                    care2 = root.findViewById(R.id.heart2);
                    care2.setVisibility(View.INVISIBLE);
                default:
                    break;
            }
            int sunAmount = (int) species.getSun();
            switch (sunAmount) {
                case 1:
                    ImageView sun4 = root.findViewById(R.id.sun4);
                    sun4.setVisibility(View.INVISIBLE);
                case 2:
                    ImageView sun3 = root.findViewById(R.id.sun3);
                    sun3.setVisibility(View.INVISIBLE);
                case 3:
                    ImageView sun2 = root.findViewById(R.id.sun2);
                    sun2.setVisibility(View.INVISIBLE);
                default:
                    break;
            }
            System.out.println("Water: " + waterAmount);
        });


        return root;

    }

}