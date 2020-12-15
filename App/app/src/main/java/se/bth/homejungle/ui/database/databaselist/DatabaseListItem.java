package se.bth.homejungle.ui.database.databaselist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.ui.Source;

public class DatabaseListItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    Species species;

    TextView plant_name;
    ImageView plant_img;
    long species_id;
    ImageButton add_button;
    Source source;

    public DatabaseListItem(@NonNull View itemView) {
        super(itemView);
        plant_name = itemView.findViewById(R.id.species_name);
        plant_img = itemView.findViewById(R.id.giveaway_img);
        itemView.setOnClickListener(this);
        add_button = itemView.findViewById(R.id.btn_add);
    }

    public void bind(Species currentSpecies, DatabaseListFragment databaseListFragment) {
        species = currentSpecies;
        plant_name.setText(currentSpecies.getName());
        species_id = currentSpecies.getId();
        source = databaseListFragment.getSource();
        plant_img.setImageURI(AppDatabase.getUriForFileName(currentSpecies.getImage()));
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(source){
                    case BOTTOMBAR:
                        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                                .setTitle(R.string.add_destination_pop_up)
                                .setItems(R.array.destinationChoices, new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        switch(i){
                                            case 0:
                                                showYourPlantsAlertDialog(databaseListFragment);
                                                break;
                                            case 1:
                                                showFuturePlantsAlertDialog(databaseListFragment);
                                                break;
                                            case 2:
                                                NavDirections action = DatabaseListFragmentDirections.addPlantToGiveaway(currentSpecies.getName(), currentSpecies.getImage());
                                                Navigation.findNavController(view).navigate(action);
                                                break;
                                        }
                                    }
                                })
                                .show();
                        break;
                    case YOURPLANTS:
                        showYourPlantsAlertDialog(databaseListFragment);
                        break;
                    case FUTUREPLANTS:
                        showFuturePlantsAlertDialog(databaseListFragment);
                        break;
                    case GIVEAWAYS:
                        NavDirections action = DatabaseListFragmentDirections.addPlantToGiveaway(currentSpecies.getName(), currentSpecies.getImage());
                        Navigation.findNavController(view).navigate(action);
                        break;
                }
                System.out.println("Click on button");
            }
        });
        //TODO: img
    }

    public void showYourPlantsAlertDialog(DatabaseListFragment databaseListFragment){
        final EditText input = new EditText(databaseListFragment.getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(lp);
        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                .setTitle(R.string.add_pop_up)
                .setMessage(R.string.add_description_pop_up)
                .setView(input)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String description = input.getText().toString();
                        databaseListFragment.insertToOwnPlants(species_id, description);
                        Toast toast = Toast.makeText(databaseListFragment.getActivity(), R.string.toast_your_plants, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                })
                .show();
    }

    public void showFuturePlantsAlertDialog(DatabaseListFragment databaseListFragment){
        final EditText input = new EditText(databaseListFragment.getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(lp);
        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                .setTitle(R.string.add_futureplant_pop_up)
                .setMessage(R.string.add_description_pop_up)
                .setView(input)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        String description = input.getText().toString();
                        databaseListFragment.insertToFuturePlants(species_id, description, FuturePlant.calculatePlantDateFromSpecies(species));
                        Toast toast = Toast.makeText(databaseListFragment.getActivity(), R.string.toast_future_plants, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                })
                .show();
    }

    public static DatabaseListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.database_list_item, parent, false);
        return new DatabaseListItem(view);
    }

    @Override
    public void onClick(View view) {
        Log.v("Click", "");
        NavDirections action = DatabaseListFragmentDirections.actionDatabaseListFragmentToPlantInfoFragment().setPlantid(species_id);
//        NavDirections action = DatabaseListFragmentDirections.databaseToPlantpage(species_id);
        Navigation.findNavController(view).navigate(action);
    }

}
