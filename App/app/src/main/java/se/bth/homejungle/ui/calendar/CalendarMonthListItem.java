package se.bth.homejungle.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.view.CalendarEvent;

public class CalendarMonthListItem extends RecyclerView.ViewHolder {
    TextView month_name;

    public CalendarMonthListItem(@NonNull View itemView) {
        super(itemView);
        month_name = itemView.findViewById(R.id.month_name);
    }

    public void bind(CalendarEvent currentCalendarEvent) {
        month_name.setText(currentCalendarEvent.getDate().getMonth().toString());
    }

    public static CalendarMonthListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_month_list_item, parent, false);
        return new CalendarMonthListItem(view);
    }


}
