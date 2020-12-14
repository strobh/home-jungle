package se.bth.homejungle.ui.plants.singleplant;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.SwipeAdapter;

public class SinglePlantFragment extends Fragment {

    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment =  inflater.inflate(R.layout.fragment_single_plant, container, false);
        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tablayout);

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
        swipeAdapter.addFragement(new PlantStartFragment());
        swipeAdapter.addFragement(new PlantInfoFragment());

        viewPager.setAdapter(swipeAdapter);
    }
}