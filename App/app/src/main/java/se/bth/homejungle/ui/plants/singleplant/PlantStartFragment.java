package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import se.bth.homejungle.R;

public class PlantStartFragment extends Fragment
{
    ImageView plantImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_plant_info, container, false);
   //     plantImage = root.findViewById(R.id.imageView2);
        return root;
    }
}