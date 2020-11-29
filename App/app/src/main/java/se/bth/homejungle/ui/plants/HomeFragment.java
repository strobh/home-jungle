package se.bth.homejungle.ui.plants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.SwipeAdapter;
import se.bth.homejungle.ui.plants.futureplants.FuturePlantsFragment;
import se.bth.homejungle.ui.plants.yourplants.YourPlantsFragment;


public class HomeFragment extends Fragment {

    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);

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
        SwipeAdapter adapter = new SwipeAdapter(getChildFragmentManager());

        adapter.addFragement(new YourPlantsFragment());
        adapter.addFragement(new FuturePlantsFragment());

        viewPager.setAdapter(adapter);
    }

}