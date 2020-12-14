package se.bth.homejungle.ui.marketplace.marketplace;

import android.graphics.Bitmap;
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



    public MarketplaceListItem(@NonNull View itemView) {
        super(itemView);
        speciesName = itemView.findViewById(R.id.species_name);
        userName = itemView.findViewById(R.id.user_name);
        distance = itemView.findViewById(R.id.distance);
        img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
    }

    public void bind(MarketplacePlant marketplacePlant, MarketplaceFragment marketplaceFragment){
        speciesName.setText(marketplacePlant.getSpeciesname());
        userName.setText(marketplacePlant.getUsername());
        distance.setText(marketplacePlant.getDistance() + "km away");
        id = marketplacePlant.getId();
        this.marketplaceFragment = marketplaceFragment;
        this.currentPlant = marketplacePlant;

        StorageReference storageReference = storage.getReference();
    //    StorageReference pathReference = storageReference.child("images/yBhFsX5KH4dBYpppmdRN");
     //   StorageReference gsReference = storage.getReferenceFromUrl("gs://home-jungle.appspot.com/images/TbLKuO2e2ULvfR4uzvF7.jpg");
        StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/home-jungle.appspot.com/o/images%2FTbLKuO2e2ULvfR4uzvF7.jpg?alt=media&token=c3e09889-1b6f-4995-923d-cc30e411687f");
     //   String url = "https://storage.googleapis.com/home-jungle/images/TbLKuO2e2ULvfR4uzvF7.jpg";
 //       Task pathReferencetask = storageReference.child("images/TbLKuO2e2ULvfR4uzvF7.jpg").getDownloadUrl();
        Glide.with(marketplaceFragment.getContext())
           //     .load()
                .load(httpsReference)
            //    .using(new FirebaseImageLoader())
            //    .load(pathReferencetask)
                .into(img);
    }

    public static MarketplaceListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marketplace_list_item, parent, false);
        return new MarketplaceListItem(view);
    }

    @Override
    public void onClick(View view) {
        marketplaceFragment.setCurrentPlant(currentPlant);
        NavDirections action = MarketplaceFragmentDirections.openGiveaway();
        Navigation.findNavController(view).navigate(action);
    }
}
