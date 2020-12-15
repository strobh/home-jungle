package se.bth.homejungle.ui.marketplace.single_giveaway;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.marketplace.marketplace.MarketplaceFragment;
import se.bth.homejungle.ui.marketplace.marketplace.MarketplaceViewModel;

public class SingleGiveaway extends Fragment implements OnMapReadyCallback
{
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    MarketplaceViewModel marketplaceViewModel;
    MarketplacePlant currentPlant;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView speciesName;
    TextView userName;
    TextView distance;
    TextView contact;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        marketplaceViewModel = new ViewModelProvider(requireActivity()).get(MarketplaceViewModel.class);
        currentPlant = marketplaceViewModel.getCurrentPlant();
        mView = inflater.inflate(R.layout.fragment_single_giveaway, container, false);


        speciesName = mView.findViewById(R.id.species_name);
        userName = mView.findViewById(R.id.tv_username);
        distance = mView.findViewById(R.id.tv_distance);
        contact = mView.findViewById(R.id.tv_contact);

        speciesName.setText(currentPlant.getSpeciesname());
        userName.setText(currentPlant.getUsername());
        //     distance.setText(currentPlant.getDistance());
        contact.setText(currentPlant.getContact());

        return mView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.mapView);
        if (mMapView != null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng plantPosition = new LatLng(currentPlant.getLatitude(), currentPlant.getLongitude());

        googleMap.addMarker(new MarkerOptions().position(plantPosition).title("Location of give-away"));
        CameraPosition cameraPosition = CameraPosition.builder().target(plantPosition).zoom(14).bearing(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}


        /*

        db.collection("user").document(docName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot doc = task.getResult();
                        currentPlant = doc.toObject(MarketplacePlant.class);
                        speciesName.setText(currentPlant.getSpeciesName());
                        userName.setText(currentPlant.getUsername());
                   //     distance.setText(currentPlant.getDistance());
                        contact.setText(currentPlant.getContact());
                    }
                });
*/