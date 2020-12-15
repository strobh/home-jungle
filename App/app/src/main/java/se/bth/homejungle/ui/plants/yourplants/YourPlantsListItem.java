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
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.plants.HomeFragmentDirections;

public class YourPlantsListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView plant_name;
    TextView plant_desc;
    TextView water_amount;
    TextView water_time;
    ImageView plant_img;
    long species_id;

    public YourPlantsListItem(View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.species_name);
        plant_desc = itemView.findViewById(R.id.user_name);
        water_amount = itemView.findViewById(R.id.distance);
        water_time = itemView.findViewById(R.id.tv_water_time);
        plant_img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
    }

    public void bind(PlantWithSpecies plantWithSpecies) {
        plant_name.setText(plantWithSpecies.getSpecies().getName());
        plant_desc.setText(plantWithSpecies.getPlant().getDescription());
        water_amount.setText(String.valueOf(plantWithSpecies.getSpecies().getWater()));
        water_time.setText(plantWithSpecies.getNextWateringDate().format(DateTimeFormatter.ISO_DATE));
        species_id = plantWithSpecies.getSpecies().getId();
        plant_img.setImageURI(AppDatabase.getUriForFileName(plantWithSpecies.getSpecies().getImage()));
    }

    public static YourPlantsListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_plants_list_item, parent, false);
        return new YourPlantsListItem(view);
    }

    @Override
    public void onClick(View view) {
        NavDirections action = HomeFragmentDirections.homeToPlantpage().setPlantid(species_id);
        Navigation.findNavController(view).navigate(action);
    }

}
