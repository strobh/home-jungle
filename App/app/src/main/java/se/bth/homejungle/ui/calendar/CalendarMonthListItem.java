package se.bth.homejungle.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.entity.view.CalendarEventType;

public class CalendarMonthListItem extends RecyclerView.ViewHolder {
    TextView month_name;
    TextView date;
    TextView plant_name;
    TextView plant_desc;
    ImageView icon;
    CheckBox check_box;

    public CalendarMonthListItem(@NonNull View itemView) {
        super(itemView);
        month_name = itemView.findViewById(R.id.month_name);
        date = itemView.findViewById(R.id.date);
        plant_name = itemView.findViewById(R.id.plant_name);
        plant_desc = itemView.findViewById(R.id.plant_desc);
        icon = itemView.findViewById(R.id.icon);
        check_box = itemView.findViewById(R.id.checkBox);
    }

    public void bind(CalendarEvent calendarEvent, CalendarFragment calendarFragment) {
        month_name.setText(calendarEvent.getDate().getMonth().toString());
        if (calendarEvent.getType() == CalendarEventType.PLANT) {
            icon.setImageResource(R.drawable.ic_flower);
        } else {
            icon.setImageResource(R.drawable.ic_baseline_drop);
        }
        LocalDate nextWateringDay = calendarEvent.getDate();
        if (nextWateringDay.isAfter(LocalDate.now())) {
            check_box.setVisibility(View.INVISIBLE);
        } else {
            check_box.setVisibility(View.VISIBLE);
        }
        date.setText("" + nextWateringDay.getDayOfMonth());
        plant_name.setText(calendarEvent.getSpecies().getName());
        plant_desc.setText(calendarEvent.getSourceDescription());
        check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     System.out.println("type: " + calendarEvent.getType());
                calendarFragment.calendarEventChecked(calendarEvent);
            }
        });
    }

    public static CalendarMonthListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_month_list_item, parent, false);
        return new CalendarMonthListItem(view);
    }

}
