package se.bth.homejungle.ui.plants;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.bth.homejungle.R;

public class TestPlantFragment extends Fragment {
    TextView planttype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_test_plant, container, false);
        planttype = root.findViewById(R.id.tv_plant_type);
        return root;
    }
/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String string_planttype = TestPlantFragmentArgs.fromBundle(getArguments()).getTypeOfPlant();
        planttype.setText(string_planttype);
    }*/
}