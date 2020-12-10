package se.bth.homejungle.ui.marketplace;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Observable;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.MarketplaceAdapter;
import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplaceFragment extends Fragment {
    private static final String TAG = "Marketplace";
    RecyclerView recyclerView;
    private MarketplaceViewModel marketplaceViewModel;

    public static MarketplaceFragment newInstance() {
        return new MarketplaceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        marketplaceViewModel = new ViewModelProvider(this).get(MarketplaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marketplace, container, false);

        final MarketplaceAdapter adapter = new MarketplaceAdapter(new MarketplaceAdapter.MarketplacePlantDiff());
        recyclerView = root.findViewById(R.id.idRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        marketplaceViewModel.getMarketplacePlantsLiveData().observe(getViewLifecycleOwner(), Observable -> {});
        marketplaceViewModel.getPlantList().observe(getViewLifecycleOwner(), marketplacePlants -> {
            Log.v(TAG, "MarketplacePlants: " + marketplacePlants.size());
            adapter.submitList(marketplacePlants);
        });

        return root;
    }
}