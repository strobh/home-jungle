package se.bth.homejungle.ui.database.databaselist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.Species;

public class DatabaseListItem extends RecyclerView.ViewHolder  {

    TextView plant_name;
    ImageView plant_img;

    public DatabaseListItem(@NonNull View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.plant_name);
        plant_img = itemView.findViewById(R.id.plant_img);
    }

    public void bind(Species currentPlant) {
        plant_name.setText(currentPlant.getName());
        //TODO: img
    }

    public static DatabaseListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.database_list_item, parent, false);
        return new DatabaseListItem(view);
    }


}
