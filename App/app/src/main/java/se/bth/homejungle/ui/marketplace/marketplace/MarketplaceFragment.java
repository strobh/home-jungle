package se.bth.homejungle.ui.marketplace.marketplace;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class MarketplaceFragment extends LocationFragment implements LocationFragment.LocationCallback {
    private static final String TAG = "Marketplace";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView errorMessage;
    private MarketplaceViewModel marketplaceViewModel;

    public static MarketplaceFragment newInstance() {
        return new MarketplaceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        marketplaceViewModel = new ViewModelProvider(requireActivity()).get(MarketplaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marketplace, container, false);

        checkLocationPermission(this);

        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        errorMessage = root.findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.idRecyclerView);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
            recyclerView.setVisibility(View.VISIBLE);
            displayList(location);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    public void displayList(Location location) {
        final MarketplaceAdapter adapter = new MarketplaceAdapter(new MarketplaceAdapter.MarketplacePlantDiff(), this);
        recyclerView.setAdapter(adapter);

        /*SharedPreferences sp = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        distance(location.getLatitude(), location.getLongitude(), LAT_OF_GIVEAWAY, LON_OF_GIVEAWAY);
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
        }*/
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
