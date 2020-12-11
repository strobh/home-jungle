package se.bth.homejungle.ui.marketplace.single_giveaway;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.MarketplacePlant;
import se.bth.homejungle.ui.marketplace.marketplace.MarketplaceFragment;
import se.bth.homejungle.ui.marketplace.marketplace.MarketplaceViewModel;

public class SingleGiveaway extends Fragment {
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
        View root = inflater.inflate(R.layout.fragment_single_giveaway, container, false);

        speciesName = root.findViewById(R.id.species_name);
        userName = root.findViewById(R.id.tv_username);
        distance = root.findViewById(R.id.tv_distance);
        contact = root.findViewById(R.id.tv_contact);

        speciesName.setText(currentPlant.getSpeciesname());
        userName.setText(currentPlant.getUsername());
        //     distance.setText(currentPlant.getDistance());
        contact.setText(currentPlant.getContact());


        return root;
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