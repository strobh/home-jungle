package se.bth.homejungle.ui.calendar;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.view.CalendarEvent;
import se.bth.homejungle.storage.repository.CalendarRepository;
import se.bth.homejungle.storage.repository.FuturePlantRepository;
import se.bth.homejungle.storage.repository.PlantRepository;

public class CalendarViewModel extends AndroidViewModel {

    private final CalendarRepository calendarRepository;
    private final PlantRepository plantRepository;
    private final FuturePlantRepository futurePlantRepository;
    private final LiveData<List<CalendarEvent>> calendarEvents;

    public CalendarViewModel(Application application) {
        super(application);
        calendarRepository = new CalendarRepository(application);
        plantRepository = new PlantRepository(application);
        futurePlantRepository = new FuturePlantRepository(application);
        calendarEvents = calendarRepository.getCalendarEvents();
    }

    public LiveData<List<CalendarEvent>> getCalendarEvents() {
        return calendarEvents;
    }


    public void waterPlant(long plantId) {
        plantRepository.updateLastWatered(plantId, LocalDate.now());
    }

    public void createFromFuturePlant(long futurePlantId, String description, Species species) {
        futurePlantRepository.deleteById(futurePlantId);

        Plant newPlant = new Plant(species.getId(),  description);
        plantRepository.insert(newPlant);
    }
}
