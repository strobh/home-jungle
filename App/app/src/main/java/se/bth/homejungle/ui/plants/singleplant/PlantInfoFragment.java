package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.bth.homejungle.R;

public class PlantInfoFragment extends Fragment
{
    long plantId;

    public PlantInfoFragment(long plantId){
        this.plantId = plantId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plant_start2, container, false);

    }
}