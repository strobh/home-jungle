package se.bth.homejungle.ui.plants.yourplants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.Plant;

public class PlantListItem extends RecyclerView.ViewHolder {

    TextView plant_name;
    TextView plant_desc;
    TextView water_amount;
    TextView water_time;
    ImageView plant_img;

    public PlantListItem(View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.tv_plant_name);
        plant_desc = itemView.findViewById(R.id.tv_plant_desc);
        water_amount = itemView.findViewById(R.id.tv_water_amount);
        water_time = itemView.findViewById(R.id.tv_water_time);
        plant_img = itemView.findViewById(R.id.plant_img);
    }

    public void bind(Plant plant) {
        plant_name.setText(plant.name);
        plant_desc.setText(plant.description);
        // TODO: finish
    }

    public static PlantListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_plants_list_item, parent, false);
        return new PlantListItem(view);
    }
}
