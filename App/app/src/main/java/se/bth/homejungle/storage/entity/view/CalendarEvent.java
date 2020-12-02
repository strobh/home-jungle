package se.bth.homejungle.storage.entity.view;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Objects;

import se.bth.homejungle.storage.converter.CalendarEventTypeConverter;
import se.bth.homejungle.storage.converter.DateConverter;
import se.bth.homejungle.storage.entity.Species;

/**
 * The Plant entity class models a database view that contains all information necessary for the
 * calendar/planner of the app.
 *
 * It contains the event type (watering a plant or planting a new plant/seeds), its date and data
 * about the plant and its species.
 */
@TypeConverters({DateConverter.class, CalendarEventTypeConverter.class})
public class CalendarEvent {

    /**
     * The event type can either be to water a plant or to plant a new plant.
     */
    @ColumnInfo(name = "event_type")
    public CalendarEventType type;

    /**
     * The date of the event.
     */
    @ColumnInfo(name = "event_date")
    public LocalDate date;

    /**
     * The plant id or future plant id (depending on the event type).
     */
    @ColumnInfo(name = "plant_id")
    public long sourceId;

    /**
     * The description of the plant or future plant (depending on the event type).
     */
    @ColumnInfo(name = "plant_description")
    public String sourceDescription;

    /**
     * The species of the plant or future plant (depending on the event type).
     */
    @Embedded
    public Species species;

    public CalendarEventType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getSourceId() {
        return sourceId;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public Species getSpecies() {
        return species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEvent that = (CalendarEvent) o;
        return sourceId == that.sourceId &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(sourceDescription, that.sourceDescription) &&
                Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, sourceId, sourceDescription, species);
    }
}
