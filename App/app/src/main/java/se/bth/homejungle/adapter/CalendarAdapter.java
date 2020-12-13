package se.bth.homejungle.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Month;
import java.util.Objects;

import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.ui.calendar.CalendarFragment;

import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.ui.calendar.CalendarListItem;
import se.bth.homejungle.ui.calendar.CalendarMonthListItem;

public class CalendarAdapter extends ListAdapter<CalendarEvent, RecyclerView.ViewHolder> {
    CalendarFragment calendarFragment;
    private static int TYPE_MONTH = 0;
    private static int TYPE_DAY = 1;

    public CalendarAdapter(DiffUtil.ItemCallback<CalendarEvent> diffCallback, CalendarFragment calendarFragment) {
        super(diffCallback);
        this.calendarFragment = calendarFragment;
    }

    public CalendarEvent getByPosition(int position) {
        return getCurrentList().get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DAY){
            return CalendarListItem.create(parent);
        } else {
            return CalendarMonthListItem.create(parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CalendarEvent currentCalendarEvent = getItem(position);
        if(getItemViewType(position) == TYPE_DAY){
            ((CalendarListItem)holder).bind(currentCalendarEvent, calendarFragment);
        } else if (getItemViewType(position) == TYPE_MONTH){
            ((CalendarMonthListItem)holder).bind(currentCalendarEvent, calendarFragment);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || getItem(position).getDate().getMonth() != getItem(position-1).getDate().getMonth()){
            return TYPE_MONTH;
        } else {
            return TYPE_DAY;
        }
    }

    public static class CalendarDiff extends DiffUtil.ItemCallback<CalendarEvent> {
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
