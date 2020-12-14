package se.bth.homejungle.ui.marketplace.marketplace;

import android.content.SharedPreferences;
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

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.MarketplaceAdapter;
import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.location.LocationFragment;

import static android.content.Context.MODE_PRIVATE;

public class MarketplaceFragment extends LocationFragment {
    private static final String TAG = "Marketplace";
    RecyclerView recyclerView;
    private MarketplaceViewModel marketplaceViewModel;

    public static MarketplaceFragment newInstance() {
        return new MarketplaceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        marketplaceViewModel = new ViewModelProvider(requireActivity()).get(MarketplaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marketplace, container, false);

        checkLocationPermission();
        checkLocationService();

        final MarketplaceAdapter adapter = new MarketplaceAdapter(new MarketplaceAdapter.MarketplacePlantDiff(), this);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences sp = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        if(sp.contains("userid")){
            Log.v("MarketplaceFragment", "has userid: " + sp.getString("userid", null));
            marketplaceViewModel.getOtherGiveawaysLiveData(sp.getString("userid", null)).observe(getViewLifecycleOwner(), Observable -> {});
            marketplaceViewModel.getPlantList().observe(getViewLifecycleOwner(), marketplacePlants -> {
             //   Log.v(TAG, "MarketplacePlants: " + marketplacePlants.size());
                adapter.submitList(marketplacePlants);
            });
        } else {
            Log.v("MarketplaceFragment", "has no userid");
            marketplaceViewModel.getMarketplacePlantsLiveData().observe(getViewLifecycleOwner(), Observable -> {});
            marketplaceViewModel.getPlantList().observe(getViewLifecycleOwner(), marketplacePlants -> {
             //   Log.v(TAG, "MarketplacePlants: " + marketplacePlants.size());
                adapter.submitList(marketplacePlants);
            });
        }

        return root;
    }



    public void setCurrentPlant(MarketplacePlant currentPlant){
        marketplaceViewModel.setCurrentPlant(currentPlant);
    }
}