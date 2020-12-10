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

public class CalendarFragment extends Fragment {

    RecyclerView recyclerView;
    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        final CalendarAdapter adapter = new CalendarAdapter(new CalendarAdapter.PlantDiff(), this);
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
    }

    public void createFromFuturePlant(long plantId, String description, Species species){
        calendarViewModel.createFromFuturePlant(plantId, description, species);
    }
}

