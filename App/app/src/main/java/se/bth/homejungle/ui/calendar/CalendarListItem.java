package se.bth.homejungle.ui.calendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.entity.view.CalendarEventType;

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


    public void bind(CalendarEvent calendarEvent, CalendarFragment calendarFragment) {
        if (calendarEvent.getType() == CalendarEventType.WATER) {
            // water plant
            date.setBackgroundColor(Color.parseColor("#87CEFA"));
        } else if (calendarEvent.getType() == CalendarEventType.PLANT) {
            // plant future plant
            date.setBackgroundColor(Color.parseColor("#90EE90"));
        }

        LocalDate nextWateringDay = calendarEvent.getDate();
        if (nextWateringDay.isAfter(LocalDate.now())) {
            check_button.setVisibility(View.INVISIBLE);
        }
        date.setText("" + nextWateringDay.toString());
        plant_name.setText(calendarEvent.getSpecies().getName());
        plant_desc.setText(calendarEvent.getSourceDescription());
        /*
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarFragment.waterPlant(calendarEvent.getPlant().getId());
            }
        });*/
    }

    public static CalendarListItem create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_list_item, parent, false);
        return new CalendarListItem(view);
    }
}
