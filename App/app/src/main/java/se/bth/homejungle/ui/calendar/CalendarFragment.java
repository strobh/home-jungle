package se.bth.homejungle.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.CalendarAdapter;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.entity.view.CalendarEventType;

public class CalendarFragment extends Fragment {

    private CalendarAdapter adapter;
    RecyclerView recyclerView;
    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        adapter = new CalendarAdapter(new CalendarAdapter.CalendarDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        calendarViewModel.getCalendarEvents().observe(getViewLifecycleOwner(), calendarEvent -> {
            //Log.v("Database", "Calendar: " + plantWithSpecies.size());
            adapter.submitList(calendarEvent);
        });

        return root;
    }

    public void waterPlant(long plantId){
        calendarViewModel.waterPlant(plantId);
        adapter.notifyDataSetChanged();
    }

    public void createFromFuturePlant(long futurePlantId, String description, Species species){
        calendarViewModel.createFromFuturePlant(futurePlantId, description, species);
    }

    public void calendarEventChecked(CalendarEvent calendarEvent) {
        if(calendarEvent.getType() == CalendarEventType.PLANT){
            createFromFuturePlant(calendarEvent.getSourceId()
                    , calendarEvent.getSourceDescription(), calendarEvent.getSpecies());
        } else if (calendarEvent.getType() == CalendarEventType.WATER){
            waterPlant(calendarEvent.getSourceId());
        }
    }

}

