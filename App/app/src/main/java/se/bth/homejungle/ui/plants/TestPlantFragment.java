package se.bth.homejungle.ui.plants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import se.bth.homejungle.R;

public class TestPlantFragment extends Fragment {
    TextView planttype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_test_plant, container, false);
        planttype = root.findViewById(R.id.tv_plant_type);
        long plantid = TestPlantFragmentArgs.fromBundle(getArguments()).getPlantid();
        planttype.setText("plantid: " + plantid);
        return root;
    }

}