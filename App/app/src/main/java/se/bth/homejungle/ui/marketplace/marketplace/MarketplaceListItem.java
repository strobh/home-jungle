package se.bth.homejungle.ui.marketplace.marketplace;

import android.graphics.Bitmap;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import se.bth.homejungle.FirebaseImageLoader;
import se.bth.homejungle.R;
import se.bth.homejungle.ui.GlideApp;
import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplaceListItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView speciesName;
    TextView userName;
    TextView distance;
    ImageView img;
    String id;
    MarketplaceFragment marketplaceFragment;
    MarketplacePlant currentPlant;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    Location location;



    public MarketplaceListItem(@NonNull View itemView, Location location) {
        super(itemView);
        this.location = location;
        speciesName = itemView.findViewById(R.id.species_name);
        userName = itemView.findViewById(R.id.user_name);
        distance = itemView.findViewById(R.id.distance);
        img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
    }

    public void bind(MarketplacePlant marketplacePlant, MarketplaceFragment marketplaceFragment){
        speciesName.setText(marketplacePlant.getSpeciesname());
        userName.setText(marketplacePlant.getUsername());
        distance.setText(marketplacePlant.getDistance(location) + " km away");
        id = marketplacePlant.getId();
        this.marketplaceFragment = marketplaceFragment;
        this.currentPlant = marketplacePlant;

        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("images/" + marketplacePlant.getId() + ".jpg");
        pathReference.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    Glide.with(marketplaceFragment.getContext())
                            .load(uri)
                            .into(img);
                });
    }

    public static MarketplaceListItem create(ViewGroup parent, Location location) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marketplace_list_item, parent, false);
        return new MarketplaceListItem(view, location);
    }

    @Override
    public void onClick(View view) {
        marketplaceFragment.setCurrentPlant(currentPlant);
        NavDirections action = MarketplaceFragmentDirections.openGiveaway();
        Navigation.findNavController(view).navigate(action);
    }
}
