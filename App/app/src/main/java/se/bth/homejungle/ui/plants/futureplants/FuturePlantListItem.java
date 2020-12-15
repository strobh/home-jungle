package se.bth.homejungle.ui.plants.futureplants;

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
import se.bth.homejungle.storage.entity.view.FuturePlantWithSpecies;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.plants.HomeFragmentDirections;

/**
 * FuturePlantsListItem contains the databinding from a futureplant to a listitem in the recyclerview.
 *
 * A click on a listitem will navigate to a single plant page "SinglePlantFragment".
 */

public class FuturePlantListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView plant_name;
    TextView plant_desc;
    TextView plant_date;
    ImageView plant_img;
    long plant_id;
    long species_id;

    public FuturePlantListItem(View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.species_name);
        plant_desc = itemView.findViewById(R.id.tv_plant_desc3);
        plant_date = itemView.findViewById(R.id.plant_date);
        plant_img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
    }

    public void bind(FuturePlantWithSpecies plantWithSpecies) {
        plant_name.setText(plantWithSpecies.getSpecies().getName());
        plant_desc.setText(plantWithSpecies.getFuturePlant().getDescription());
        plant_date.setText(plantWithSpecies.getFuturePlant().getPlantDay().format(DateTimeFormatter.ISO_DATE));
        species_id = plantWithSpecies.getSpecies().getId();
        plant_img.setImageURI(AppDatabase.getUriForFileName(plantWithSpecies.getSpecies().getImage()));
    }

    public static FuturePlantListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.future_plants_list_item, parent, false);
        return new FuturePlantListItem(view);
    }

    @Override
    public void onClick(View view) {
        NavDirections action = HomeFragmentDirections.homeToPlantpage(Source.FUTUREPLANTS,species_id).setPlantid(plant_id);
        Navigation.findNavController(view).navigate(action);
    }
}
