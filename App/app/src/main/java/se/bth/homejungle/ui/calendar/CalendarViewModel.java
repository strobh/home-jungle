package se.bth.homejungle.ui.calendar;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.repository.CalendarRepository;
import se.bth.homejungle.storage.repository.PlantRepository;

public class CalendarViewModel extends AndroidViewModel {

    private final CalendarRepository calendarRepository;
    private final PlantRepository plantRepository;
    private final LiveData<List<CalendarEvent>> calendarEvents;

    public CalendarViewModel(Application application) {
        super(application);
        calendarRepository = new CalendarRepository(application);
        plantRepository = new PlantRepository(application);
        calendarEvents = calendarRepository.getCalendarEvents();
    }

    public LiveData<List<CalendarEvent>> getCalendarEvents() {
        return calendarEvents;
    }


    public void waterPlant(long plantId) {
        plantRepository.setLastWateredOfPlantToToday(plantId);
    }
}

