package se.bth.homejungle.ui.plants.futureplants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;

public class FuturePlantListItem extends RecyclerView.ViewHolder {

    TextView plant_name;
    TextView plant_desc;
    TextView plant_date;
    ImageView plant_img;

    public FuturePlantListItem(View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.plant_name);
        plant_desc = itemView.findViewById(R.id.tv_plant_desc3);
        plant_date = itemView.findViewById(R.id.plant_date);
        plant_img = itemView.findViewById(R.id.plant_img);
    }

    public void bind(FuturePlantWithSpecies plantWithSpecies) {
        plant_name.setText(plantWithSpecies.getSpecies().getName());
        plant_desc.setText(plantWithSpecies.getFuturePlant().getDescription());
        plant_date.setText(plantWithSpecies.getFuturePlant().getPlantDay().format(DateTimeFormatter.ISO_DATE));
        // TODO: finish
    }

    public static FuturePlantListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.future_plants_list_item, parent, false);
        return new FuturePlantListItem(view);
    }
}
