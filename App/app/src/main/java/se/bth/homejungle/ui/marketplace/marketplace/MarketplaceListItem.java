package se.bth.homejungle.ui.marketplace.marketplace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplaceListItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView speciesName;
    TextView userName;
    TextView distance;
    String id;
    MarketplaceFragment marketplaceFragment;
    MarketplacePlant currentPlant;


    public MarketplaceListItem(@NonNull View itemView) {
        super(itemView);
        speciesName = itemView.findViewById(R.id.species_name);
        userName = itemView.findViewById(R.id.user_name);
        distance = itemView.findViewById(R.id.distance);
        itemView.setOnClickListener(this);
    }

    public void bind(MarketplacePlant marketplacePlant, MarketplaceFragment marketplaceFragment){
        speciesName.setText(marketplacePlant.getSpeciesName());
        userName.setText(marketplacePlant.getUsername());
        distance.setText(marketplacePlant.getDistance() + "km away");
        id = marketplacePlant.getId();
        this.marketplaceFragment = marketplaceFragment;
        this.currentPlant = marketplacePlant;
    }

    public static MarketplaceListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marketplace_list_item, parent, false);
        return new MarketplaceListItem(view);
    }

    @Override
    public void onClick(View view) {
        marketplaceFragment.setCurrentPlant(currentPlant);
        NavDirections action = MarketplaceFragmentDirections.openGiveaway(id);
        Navigation.findNavController(view).navigate(action);
    }
}
