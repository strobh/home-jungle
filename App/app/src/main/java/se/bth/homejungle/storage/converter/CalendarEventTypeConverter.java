package se.bth.homejungle.storage.converter;

import androidx.room.TypeConverter;

import se.bth.homejungle.storage.entity.view.CalendarEventType;

/**
 * This CalendarEventTypeConverter is used by the Android Room Persistence Library in order
 * to convert objects of type CalendarEventType to a String that can be stored in the SQLite
 * database.
 */
public class CalendarEventTypeConverter {

    /**
     * Converts the String representation of an event type in the database back to a
     * CalendarEventType object.
     *
     * @param calendarEventType String representation of an event type in the database.
     * @return Corresponding CalendarEventType object.
     */
    @TypeConverter
    public static CalendarEventType toCalendarEventType(String calendarEventType) {
        for (CalendarEventType type : CalendarEventType.values()) {
            if (type.getType().equalsIgnoreCase(calendarEventType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + calendarEventType + " found");
    }

    /**
     * Converts a CalendarEventType object to its String representation in the database.
     *
     * @param calendarEventType CalendarEventType object to convert.
     * @return String representation of the event type for the database.
     */
    @TypeConverter
    public static String fromCalendarEventType(CalendarEventType calendarEventType) {
        return calendarEventType.getType();
    }
}
