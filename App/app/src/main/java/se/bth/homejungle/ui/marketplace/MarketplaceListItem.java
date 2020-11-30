package se.bth.homejungle.ui.marketplace;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;

public class MarketplaceListItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView plant_name;
    TextView user_name;
    TextView distance;
    long id;


    public MarketplaceListItem(@NonNull View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.plant_name);
        user_name = itemView.findViewById(R.id.user_name);
        distance = itemView.findViewById(R.id.distance);
        itemView.setOnClickListener(this);
    }
    /*
    public void bind(MarketplacePlant marketplacePlant){
        plant_name.setText(marketplacePlant.getPlantName());
        user_name.setText(marketplacePlant.getUserName());
        distance.setText((marketplacePlant.getDistance());
        id = marketplacePlant.getId());
    }*/

    @Override
    public void onClick(View view) {

    }
}
