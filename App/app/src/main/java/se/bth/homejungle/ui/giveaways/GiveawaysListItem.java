package se.bth.homejungle.ui.giveaways;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.CustomAdapter;
import se.bth.homejungle.ui.MarketplacePlant;

public class GiveawaysListItem extends CustomAdapter.ViewHolder {

    ImageView giveaways_img;
    TextView giveaways_name;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public GiveawaysListItem(View view) {
        super(view);
        giveaways_img = view.findViewById(R.id.giveaway_img);
        giveaways_name = view.findViewById(R.id.species_name);
    }

    public void bind(MarketplacePlant giveaway, GiveawaysFragment giveawaysFragment){
        giveaways_name.setText(giveaway.getSpeciesname());
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("images/" + giveaway.getId() + ".jpg");
        pathReference.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    Glide.with(giveawaysFragment.getContext())
                            .load(uri)
                            .into(giveaways_img);
                });
    }

    public static GiveawaysListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.giveaways_list_item, parent, false);
        return new GiveawaysListItem(view);
    }
}
