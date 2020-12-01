package se.bth.homejungle.ui.database.databaselist;

import android.app.AlertDialog;
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
                switch(databaseListFragment.getSource()){
                    case 0:
                        //show popupwindow
                        break;
                    case 1:
                        final EditText input = new EditText(databaseListFragment.getContext());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                     //   lp.leftMargin = 20;
                        input.setLayoutParams(lp);
                        new AlertDialog.Builder(databaseListFragment.getContext(), R.style.AlertDialogStyle)
                                .setTitle(R.string.add_pop_up)
                                .setMessage(R.string.add_description_pop_up)
                                .setView(input)
                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })
                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .show();
                     //   databaseListFragment.insertToOwnPlants(plant_id);
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
