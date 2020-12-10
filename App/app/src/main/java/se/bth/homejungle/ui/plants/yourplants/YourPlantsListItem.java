package se.bth.homejungle.ui.plants.yourplants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.plants.HomeFragmentDirections;

public class YourPlantsListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView plant_name;
    TextView plant_desc;
    TextView water_amount;
    TextView water_time;
    ImageView plant_img;
    long plant_id;

    public YourPlantsListItem(View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.giveaway_name);
        plant_desc = itemView.findViewById(R.id.user_name);
        water_amount = itemView.findViewById(R.id.distance);
        water_time = itemView.findViewById(R.id.tv_water_time);
        plant_img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
    }

    public void bind(PlantWithSpecies plantWithSpecies) {
        plant_name.setText(plantWithSpecies.getSpecies().getName());
        plant_desc.setText(plantWithSpecies.getPlant().getDescription());
        water_amount.setText(plantWithSpecies.getSpecies().getWater() + " l");
        water_time.setText(plantWithSpecies.getNextWateringDate().format(DateTimeFormatter.ISO_DATE));
        plant_id = plantWithSpecies.getPlant().getId();
        // TODO: finish
    }

    public static YourPlantsListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_plants_list_item, parent, false);
        return new YourPlantsListItem(view);
    }

    @Override
    public void onClick(View view) {
        NavDirections action = HomeFragmentDirections.homeToPlantpage(plant_id);
        Navigation.findNavController(view).navigate(action);
    }
}
