package se.bth.homejungle.ui.marketplace.marketplace;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.MarketplaceAdapter;
import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.location.LocationFragment;

import static android.content.Context.MODE_PRIVATE;

public class MarketplaceFragment extends LocationFragment implements LocationFragment.LocationCallback {
    private static final String TAG = "Marketplace";
    SearchView searchView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView errorMessage;
    TextView noPlants;
    private MarketplaceViewModel marketplaceViewModel;

    final MarketplaceAdapter adapter = new MarketplaceAdapter(new MarketplaceAdapter.MarketplacePlantDiff(), this);

    public static MarketplaceFragment newInstance() {
        return new MarketplaceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        marketplaceViewModel = new ViewModelProvider(requireActivity()).get(MarketplaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marketplace, container, false);

        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        searchView = root.findViewById(R.id.idSearchView);
        searchView.setVisibility(View.INVISIBLE);

        errorMessage = root.findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.idRecyclerView);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        noPlants = root.findViewById(R.id.tv_no_nearby_plant);

        checkLocationPermission(this);

        return root;
    }

    public void setCurrentPlant(MarketplacePlant currentPlant){
        marketplaceViewModel.setCurrentPlant(currentPlant);
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            requestLocation(true, this);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            errorMessage.setText("Home Jungle needs your location in order to find give-aways in your neighbourhood. Make sure that Home Jungle can access your location.");
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLocationResult(LocationResult locationResult, Location location) {
        if (locationResult == LocationResult.SUCCESS) {
            Log.v("MarketplaceFragment", "Got location");
            progressBar.setVisibility(View.INVISIBLE);
            searchView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            displayList(location);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    public void displayList(Location location) {
        adapter.setLocation(location);

        SharedPreferences sp = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        if(sp.contains("userid")){
            Log.v("MarketplaceFragment", "has userid: " + sp.getString("userid", null));
            marketplaceViewModel.getOtherGiveawaysLiveData(sp.getString("userid", null)).observe(getViewLifecycleOwner(), Observable -> {});
            marketplaceViewModel.getPlantList().observe(getViewLifecycleOwner(), marketplacePlants -> {

                // filter giveaways to distances of less than 5 km
             //   marketplacePlants = marketplacePlants.stream().filter((MarketplacePlant plant) -> plant.getDistance(location) < 5).collect(Collectors.toList());
                if(marketplacePlants.size() > 0){
                    noPlants.setVisibility(View.INVISIBLE);
                }

                //marketplacePlants = marketplacePlants.stream().filter((MarketplacePlant plant) -> plant.getDistance(location) < 5).collect(Collectors.toList());
                //Log.v(TAG, "MarketplacePlants: " + marketplacePlants.size());
                adapter.submitList(marketplacePlants);
            });
        } else {
            Log.v("MarketplaceFragment", "has no userid");
            marketplaceViewModel.getMarketplacePlantsLiveData().observe(getViewLifecycleOwner(), Observable -> {});
            marketplaceViewModel.getPlantList().observe(getViewLifecycleOwner(), marketplacePlants -> {

                // filter giveaways to distances of less than 5 km
                marketplacePlants = marketplacePlants.stream().filter((MarketplacePlant plant) -> plant.getDistance(location) < 5).collect(Collectors.toList());
                if(marketplacePlants.size() > 0){
                    noPlants.setVisibility(View.INVISIBLE);
                }
                //marketplacePlants = marketplacePlants.stream().filter((MarketplacePlant plant) -> plant.getDistance(location) < 5).collect(Collectors.toList());
                //Log.v(TAG, "MarketplacePlants: " + marketplacePlants.size());
                adapter.submitList(marketplacePlants);
            });
        }
    }
}
