package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.SwipeAdapter;
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.ui.Source;

/**
 * The SinglePlantFragment is used to display the "profile"page of a plant. It contains
 * a viewpager, so that the user can swipe between general information about the plant
 * (like how much to water, how easy to care for it, how much sun it needs) and a page
 * that gives more information about how to start.
 *
 * These two pages can be found in the PlantStartFragment and PlantInfoFragment.
 */
public class SinglePlantFragment extends Fragment {

    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView plantImage;
    ImageButton delete;
    TextView title;
    long speciesId;
    long plantId;
    Source source;

    SinglePlantViewModel singlePlantViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragment =  inflater.inflate(R.layout.fragment_single_plant, container, false);
        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tablayout);
        plantImage = myFragment.findViewById(R.id.plant_img);
        title = myFragment.findViewById(R.id.title);
        delete = myFragment.findViewById(R.id.deleteButton);

        singlePlantViewModel = new ViewModelProvider(requireActivity()).get(SinglePlantViewModel.class);

        speciesId = SinglePlantFragmentArgs.fromBundle(getArguments()).getSpeciesid();
        plantId = SinglePlantFragmentArgs.fromBundle(getArguments()).getPlantid();
        source = SinglePlantFragmentArgs.fromBundle(getArguments()).getSource();

        singlePlantViewModel.getSpeciesById(speciesId).observe(getViewLifecycleOwner(), species -> {
            title.setText(species.getName());
            plantImage.setImageURI(AppDatabase.getUriForFileName(species.getImage()));
        });

        if(source == Source.BOTTOMBAR){
            delete.setVisibility(View.INVISIBLE);
        } else {
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletePlant();
                }
            });
        }

        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        SwipeAdapter swipeAdapter = new SwipeAdapter(getChildFragmentManager());
        swipeAdapter.addFragement(new PlantInfoFragment(speciesId, source));
        swipeAdapter.addFragement(new PlantStartFragment(speciesId, source));
        viewPager.setAdapter(swipeAdapter);
    }

    private void deletePlant() {
        if(source == Source.FUTUREPLANTS){
            singlePlantViewModel.deleteFuturePlant(plantId);

        } else if(source == Source.YOURPLANTS){
            singlePlantViewModel.deletePlant(plantId);
        }
        NavDirections action = SinglePlantFragmentDirections.actionSinglePlantFragmentToHomeFragment();
        Navigation.findNavController(myFragment).navigate(action);
    }
}