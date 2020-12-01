package se.bth.homejungle.ui.database.databaselist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.Species;

public class DatabaseListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView plant_name;
    ImageView plant_img;
    long plant_id;

    public DatabaseListItem(@NonNull View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.category_name);
        plant_img = itemView.findViewById(R.id.category_img);
        itemView.setOnClickListener(this);
        ImageButton add_button = itemView.findViewById(R.id.btn_add);
       /* add_button.setOnClickListener(new View.OnClickListener(){

        });*/
    }

    public void bind(Species currentPlant) {
        plant_name.setText(currentPlant.getName());
        plant_id = currentPlant.getId();
        //TODO: img
    }

    public static DatabaseListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.database_list_item, parent, false);
        return new DatabaseListItem(view);
    }

    @Override
    public void onClick(View view) {
        Log.v("Click", "");
        NavDirections action = DatabaseListFragmentDirections.databaseToPlantpage(plant_id);
        Navigation.findNavController(view).navigate(action);
    }

}
