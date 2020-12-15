package se.bth.homejungle.ui.plants.yourplants;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.plants.HomeFragmentDirections;

/**
 * YourPlantsListItem contains the databinding from a plant to a listitem in the recyclerview.
 *
 *  A click on a listitem will navigate to a single plant page "SinglePlantFragment".
 */

public class YourPlantsListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView plant_name;
    TextView plant_desc;
    TextView water_amount;
    TextView water_time;
    ImageView plant_img;
    long plantId;
    long species_id;

    public YourPlantsListItem(View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.species_name);
        plant_desc = itemView.findViewById(R.id.user_name);
        water_amount = itemView.findViewById(R.id.water_amount);
        water_time = itemView.findViewById(R.id.water_time);
        plant_img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void bind(PlantWithSpecies plantWithSpecies) {
        plant_name.setText(plantWithSpecies.getSpecies().getName());
        plant_desc.setText(plantWithSpecies.getPlant().getDescription());
        water_amount.setText((plantWithSpecies.getSpecies().getWater() * 0.2) + " l");

        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), plantWithSpecies.getNextWateringDate());
        if (daysBetween < -1) {
            water_time.setText(Math.abs(daysBetween) + " days ago");
        } else if (daysBetween == -1) {
            water_time.setText("yesterday");
        } else if (daysBetween == 0) {
            water_time.setText("today");
        } else if (daysBetween == 1) {
            water_time.setText("tomorrow");
        } else {
            water_time.setText("in " + daysBetween + " days");
        }

        species_id = plantWithSpecies.getSpecies().getId();
        plantId = plantWithSpecies.getPlant().getId();
        plant_img.setImageURI(AppDatabase.getUriForFileName(plantWithSpecies.getSpecies().getImage()));
    }

    public static YourPlantsListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_plants_list_item, parent, false);
        return new YourPlantsListItem(view);
    }

    @Override
    public void onClick(View view) {
        NavDirections action = HomeFragmentDirections.homeToPlantpage(Source.YOURPLANTS, species_id).setPlantid(plantId);
        Navigation.findNavController(view).navigate(action);
    }

}
