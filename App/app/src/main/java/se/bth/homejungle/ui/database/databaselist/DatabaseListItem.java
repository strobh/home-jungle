package se.bth.homejungle.ui.database.databaselist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.ui.database.categories.DatabaseFragment;

public class DatabaseListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView plant_name;
    ImageView plant_img;
    long plant_id;
    ImageButton add_button;

    public DatabaseListItem(@NonNull View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.category_name);
        plant_img = itemView.findViewById(R.id.category_img);
        itemView.setOnClickListener(this);
        add_button = itemView.findViewById(R.id.btn_add);

    }

    public void bind(Species currentPlant, DatabaseListFragment databaseListFragment) {
        plant_name.setText(currentPlant.getName());
        plant_id = currentPlant.getId();
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(databaseListFragment.getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);
                switch(databaseListFragment.getSource()){
                    case BOTTOMBAR:
                        String[] destinations = {"Giveaways", "Your plants", "Future plants"};
                        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                                .setTitle(R.string.add_pop_up)
                                .setMessage(R.string.add_description_pop_up)
                                .setView(input)
                                .setItems(destinations, new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                        //show popupwindow
                        break;
                    case YOURPLANTS:
                        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                                .setTitle(R.string.add_pop_up)
                                .setMessage(R.string.add_description_pop_up)
                                .setView(input)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        String description = input.getText().toString();
                                        databaseListFragment.insertToOwnPlants(plant_id, description);
                                    }
                                })
                                .show();
                        break;
                    case FUTUREPLANTS:
                        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                                .setTitle(R.string.add_futureplant_pop_up)
                                .setMessage(R.string.add_description_pop_up)
                                .setView(input)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        String description = input.getText().toString();
                                        databaseListFragment.insertToFuturePlants(plant_id, description, LocalDate.now().plusMonths(5));
                                    }
                                })
                                .show();
                        break;
                    default:
                }
                System.out.println("Click on button");
            }
        });
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
      //  view.getContext();
        NavDirections action = DatabaseListFragmentDirections.databaseToPlantpage(plant_id);
        Navigation.findNavController(view).navigate(action);
    }

}
