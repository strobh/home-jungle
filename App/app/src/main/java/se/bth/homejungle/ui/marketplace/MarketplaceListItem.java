package se.bth.homejungle.ui.marketplace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.Giveaway;
import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplaceListItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView plant_name;
    TextView user_name;
    TextView distance;
    long id;


    public MarketplaceListItem(@NonNull View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.giveaway_name);
        user_name = itemView.findViewById(R.id.user_name);
        distance = itemView.findViewById(R.id.distance);
        itemView.setOnClickListener(this);
    }

    public void bind(MarketplacePlant marketplacePlant){
     //  plant_name.setText(marketplacePlant.getSpeciesName());
        user_name.setText(marketplacePlant.getUsername());
      //  distance.setText(marketplacePlant.getDistance());
      //  id = marketplacePlant.getId();
    }

    public static MarketplaceListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marketplace_list_item, parent, false);
        return new MarketplaceListItem(view);
    }

    @Override
    public void onClick(View view) {

    }
}
