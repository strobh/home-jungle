package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.CalendarManager;
import se.bth.homejungle.storage.entity.view.CalendarEvent;

/**
 * CalendarRepository is an intermediate helper in between the Data Access Object (DAO) and the view
 * model that is used to retrieve calendar events from the local database.
 *
 * The repository is used to decouple the view model from the real source of the data (database).
 */
public class CalendarRepository {

    /**
     * The calendar manager that is used to access the database.
     */
    private final CalendarManager calendarManager;

    /**
     * Creates a new calendar repository.
     *
     * @param application Reference to the application which is needed to create the database.
     */
    public CalendarRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.calendarManager = db.getCalendarManager();
    }

    /**
     * Returns a list of all calendar events including watering of a plant and planting of a new
     * plant.
     *
     * @return List of all calendar events including watering of a plant and planting of a new
     * plant.
     */
    public LiveData<List<CalendarEvent>> getCalendarEvents() {
        return calendarManager.getCalendarEvents();
    }
}
