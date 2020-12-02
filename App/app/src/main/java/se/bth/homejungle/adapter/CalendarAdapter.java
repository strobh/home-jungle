package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.calendar.CalendarFragment;

import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.ui.calendar.CalendarListItem;

public class CalendarAdapter extends ListAdapter<CalendarEvent, CalendarListItem> {
    CalendarFragment calendarFragment;

    public CalendarAdapter(DiffUtil.ItemCallback<CalendarEvent> diffCallback, CalendarFragment calendarFragment){
        super(diffCallback);
        this.calendarFragment = calendarFragment;
    }

    @NonNull
    @Override
    public CalendarListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CalendarListItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarListItem holder, int position) {
        CalendarEvent currentCalendarEvent = getItem(position);
        holder.bind(currentCalendarEvent, calendarFragment);
    }

    public static class PlantDiff extends DiffUtil.ItemCallback<CalendarEvent> {
        @Override
        public boolean areItemsTheSame(@NonNull CalendarEvent oldItem, @NonNull CalendarEvent newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CalendarEvent oldItem, @NonNull CalendarEvent newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
