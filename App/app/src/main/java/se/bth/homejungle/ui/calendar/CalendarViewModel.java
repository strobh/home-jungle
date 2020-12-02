package se.bth.homejungle.ui.calendar;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.repository.CalendarRepository;

public class CalendarViewModel extends AndroidViewModel {

    private final CalendarRepository calendarRepository;
    private final LiveData<List<CalendarEvent>> calendarEvents;

    public CalendarViewModel(Application application) {
        super(application);
        calendarRepository = new CalendarRepository(application);
        calendarEvents = calendarRepository.getCalendarEvents();
    }

    public LiveData<List<CalendarEvent>> getCalendarEvents() {
        return calendarEvents;
    }


    public void waterPlant(long plantId){
        //TODO: water plant in database and update list
    }
}

