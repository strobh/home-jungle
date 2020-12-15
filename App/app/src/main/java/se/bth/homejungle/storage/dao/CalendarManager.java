package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import se.bth.homejungle.storage.entity.view.CalendarEvent;

/**
 * CalendarManager is the Data Access Object (DAO) used to manage planned events like watering a
 * plant or planting seeds of a new plant.
 *
 * The Android Room Persistence Library automatically creates the implementation for this interface.
 */
@Dao
public interface CalendarManager {

    /**
     * Returns a list of all calendar events including watering of a plant and planting of a new
     * plant.
     *
     * @return List of all calendar events including watering of a plant and planting of a new
     * plant.
     */
    @Transaction
    @Query("SELECT 'water' as event_type, date(plant.last_watered, '+'||species.water_period||' days') as event_date, plant.plant_id, plant.description as plant_description, species.* FROM plant INNER JOIN species ON plant.species_id = species.species_id " +
            "UNION " +
            "SELECT 'plant' as event_type, future_plant.plant_day as event_date, future_plant.future_plant_id as plant_id, future_plant.description as plant_description, species.* FROM future_plant INNER JOIN species ON future_plant.species_id = species.species_id " +
            "ORDER BY event_date")
    LiveData<List<CalendarEvent>> getCalendarEvents();

    /**
     * Returns a list of all calendar events including watering of a plant and planting of a new
     * plant.
     *
     * @return List of all calendar events including watering of a plant and planting of a new
     * plant.
     */
    @Transaction
    @Query("SELECT 'water' as event_type, date(plant.last_watered, '+'||species.water_period||' days') as event_date, plant.plant_id, plant.description as plant_description, species.* FROM plant INNER JOIN species ON plant.species_id = species.species_id " +
            "UNION " +
            "SELECT 'plant' as event_type, future_plant.plant_day as event_date, future_plant.future_plant_id as plant_id, future_plant.description as plant_description, species.* FROM future_plant INNER JOIN species ON future_plant.species_id = species.species_id " +
            "ORDER BY event_date")
    List<CalendarEvent> getCalendarEventsSynchronously();
}
