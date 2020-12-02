package se.bth.homejungle.ui.calendar;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.Locale;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.PlantWithSpecies;

public class CalendarListItem extends RecyclerView.ViewHolder {
    TextView date;
    TextView plant_name;
    TextView plant_desc;
    ImageView icon;
    RadioButton check_button;


    public CalendarListItem(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        plant_name = itemView.findViewById(R.id.plant_name);
        plant_desc = itemView.findViewById(R.id.plant_desc);
        check_button = itemView.findViewById(R.id.check_btn);
    }

    public void bind(PlantWithSpecies plantWithSpecies, CalendarFragment calendarFragment){
        LocalDate today = LocalDate.now();
      //  LocalDate nextWateringDay = LocalDate.of(2020, 11, 02);
        LocalDate nextWateringDay = plantWithSpecies.getNextWateringDate();
        if(nextWateringDay.isAfter(today)){
            check_button.setVisibility(View.INVISIBLE);
        }
        date.setText("" + nextWateringDay.getDayOfMonth());
        plant_name.setText(plantWithSpecies.getSpecies().getName());
        plant_desc.setText(plantWithSpecies.getPlant().getDescription());
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarFragment.waterPlant(plantWithSpecies.getPlant().getId());
            }
        });
    }

    public static CalendarListItem create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_list_item, parent, false);
        return new CalendarListItem(view);
    }
}
